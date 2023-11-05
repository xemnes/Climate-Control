//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.api.BiomeRandomizer;
import climateControl.api.ClimateControlSettings;
import climateControl.api.DistributionPartitioner;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.IntRandomizer;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeByTaggedClimate extends GenLayerPack {
    private BiomeRandomizer biomeRandomizer;
    private IntRandomizer randomCallback;
    private BiomeRandomizer.PickByClimate pickByClimate;
    private ArrayList<DistributionPartitioner> partitioners;

    public GenLayerBiomeByTaggedClimate(long par1, GenLayer par3GenLayer, ClimateControlSettings settings) {
        super(par1);
        this.parent = par3GenLayer;
        this.biomeRandomizer = new BiomeRandomizer(settings.biomeSettings());
        this.pickByClimate = this.biomeRandomizer.pickByClimate();
        this.randomCallback = new IntRandomizer() {
            public int nextInt(int maximum) {
                return GenLayerBiomeByTaggedClimate.this.nextInt(maximum);
            }
        };
        this.partitioners = settings.partitioners();
    }

    public void initWorldGenSeed(long par1) {
        super.initWorldGenSeed(par1);
        Iterator var3 = this.partitioners.iterator();

        while(var3.hasNext()) {
            DistributionPartitioner partitioner = (DistributionPartitioner)var3.next();
            partitioner.initWorldGenSeed(par1);
        }

    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = this.parent.getInts(par1, par2, par3, par4);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        int i1;
        for(i1 = 0; i1 < par4; ++i1) {
            for(int j1 = 0; j1 < par3; ++j1) {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + i1 * par3];
                int climate = k1 % 4;
                if (climate == 0) {
                    climate = 4;
                }

                if (k1 == Biome.getIdForBiome(Biomes.DEEP_OCEAN)) {
                    climate = k1;
                }

                if (k1 == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND)) {
                    climate = k1;
                }

                if (k1 == Biome.getIdForBiome(Biomes.FROZEN_OCEAN)) {
                    climate = k1;
                }

                if (k1 == 0) {
                    climate = 0;
                }

                if (k1 == Biome.getIdForBiome(Biomes.FROZEN_OCEAN)) {
                    aint1[j1 + i1 * par3] = k1;
                } else if (k1 == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND)) {
                    aint1[j1 + i1 * par3] = k1;
                } else {
                    BiomeRandomizer.PickByClimate picker = this.pickByClimate;

                    DistributionPartitioner partitioner;
                    for(Iterator var12 = this.partitioners.iterator(); var12.hasNext(); picker = partitioner.partitioned(picker, j1 + par1, i1 + par2)) {
                        partitioner = (DistributionPartitioner)var12.next();
                    }

                    aint1[j1 + i1 * par3] = picker.biome(climate, this.randomCallback);
                }
            }
        }

        for(i1 = 0; i1 < par3 * par4; ++i1) {
            if (aint1[i1] > 255) {
                throw new RuntimeException();
            }
        }

        return aint1;
    }
}
