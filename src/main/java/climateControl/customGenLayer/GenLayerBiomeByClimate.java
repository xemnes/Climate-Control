package climateControl.customGenLayer;

import climateControl.api.BiomeRandomizer;
import com.Zeno410Utils.IntRandomizer;
import climateControl.ClimateControl;

import climateControl.api.ClimateControlSettings;
import climateControl.api.DistributionPartitioner;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.Zeno410Logger;
import java.util.ArrayList;
import java.util.logging.Logger;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
/**
 *
 * @author MasterCaver modified by Zeno410
 */
public class GenLayerBiomeByClimate extends GenLayerPack {
    //public static Logger logger = new Zeno410Logger("GenLayerBiomeByClimate").logger();

    private BiomeRandomizer biomeRandomizer;

    private IntRandomizer randomCallback;

    private BiomeRandomizer.PickByClimate pickByClimate;

    private ArrayList<DistributionPartitioner> partitioners;

    public GenLayerBiomeByClimate(long par1, GenLayer par3GenLayer, ClimateControlSettings settings){
        super(par1);
        this.parent = par3GenLayer;

        biomeRandomizer = new BiomeRandomizer(settings.biomeSettings());
        pickByClimate = biomeRandomizer.pickByClimate();
        randomCallback = new IntRandomizer() {
            public int nextInt(int maximum) {
                return GenLayerBiomeByClimate.this.nextInt(maximum);
            }
        };
        partitioners = settings.partitioners();
    }

    // world gen inits have to be passed to the partitioners
    @Override
    public void initWorldGenSeed(long par1) {
        super.initWorldGenSeed(par1);
        for (DistributionPartitioner partitioner: partitioners) {
            partitioner.initWorldGenSeed(par1);
        }
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] aint = this.parent.getInts(par1, par2, par3, par4);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for (int i1 = 0; i1 < par4; i1++)
        {
            for (int j1 = 0; j1 < par3; j1++)
            {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + i1 * par3];
                k1 &= -3841;
                    if (k1 > 256) {
                        if (ClimateControl.testing) {
                        ClimateControl.logger.info(parent.toString());
                        ClimateControl.logger.info("number "+k1+ " from "+aint[j1 + i1 * par3]);
                    throw new RuntimeException();
                    }
                }
                if (k1 == Biome.getIdForBiome(Biomes.FROZEN_OCEAN)){
                    aint1[j1 + i1 * par3] = k1;
                }else if (k1 == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND)){
                    aint1[j1 + i1 * par3] = k1;
                }
                else {
                    BiomeRandomizer.PickByClimate picker= pickByClimate;
                    for (DistributionPartitioner partitioner: partitioners) {
                        picker = partitioner.partitioned(picker, j1 + par1, i1 + par2);
                    }
                    aint1[j1 + i1 * par3] = picker.biome(k1, randomCallback);
                    //logger.info("("+(i1+par2)+","+(j1+par1)+") Climate "+k1 + " " + aint[j1 + i1 * par3]+" Biome " + aint1[j1 + i1 * par3]);

                }
            }
        }
        for (int i = 0; i < par3 * par4;i++) {
            if (aint1[i]>255) throw new RuntimeException();
        }
        return aint1;
    }
}