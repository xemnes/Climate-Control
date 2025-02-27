
package climateControl.customGenLayer;

import climateControl.api.ClimateControlRules;
import climateControl.genLayerPack.GenLayerPack;
import climateControl.utils.IntPad;
import com.Zeno410Utils.AccessFloat;
import java.io.File;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverMix;

public class GenLayerLowlandRiverMix extends GenLayerRiverMix
{
    private GenLayer biomePatternGeneratorChain;
    private GenLayer riverPatternGeneratorChain;
    private ClimateControlRules rules;
    private float maxChasm;
    private IntPad output = new IntPad();
    private AccessFloat<Biome> baseHeight = new AccessFloat<Biome>("field_76748_D");
    private AccessFloat<Biome> heightVariation = new AccessFloat<Biome>("field_76749_E");

    public GenLayerLowlandRiverMix(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer, float maxChasm,
            ClimateControlRules rules)
    {
        super(par1,par3GenLayer,par4GenLayer);
        if (!(par3GenLayer instanceof GenLayerPack)) throw new RuntimeException();
        if (!(par4GenLayer instanceof GenLayerPack)) throw new RuntimeException();
        this.biomePatternGeneratorChain = par3GenLayer;
        this.riverPatternGeneratorChain = par4GenLayer;
        this.maxChasm= maxChasm;
        this.rules = rules;
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the world's global seed (passed in as an
     * argument).
     */
    public void initWorldGenSeed(long par1)
    {
        this.biomePatternGeneratorChain.initWorldGenSeed(par1);
        this.riverPatternGeneratorChain.initWorldGenSeed(par1);
        super.initWorldGenSeed(par1);
    }

    public GenLayer forLocking() {
        return biomePatternGeneratorChain;
    }

    public void setMaxChasm(float newValue) {
        this.maxChasm = newValue;
    }
    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public synchronized int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] aint = null;
        int[] aint1 = null;
        try {
            aint = this.biomePatternGeneratorChain.getInts(par1, par2, par3, par4);
            aint1 = this.riverPatternGeneratorChain.getInts(par1, par2, par3, par4);
        } catch (Exception e) {
            throw e;
        }
        int[] aint2 = output.pad(par3 * par4);

        for (int i = 0; i < par3 * par4;i++) {
            if (aint[i]>255) throw new RuntimeException(biomePatternGeneratorChain.toString());
            if (aint[i]<0) throw new RuntimeException(biomePatternGeneratorChain.toString());
        }
        for (int i1 = 0; i1 < par3 * par4; ++i1)
        {
            if (aint[i1] != Biome.getIdForBiome(Biomes.OCEAN) && aint[i1] != Biome.getIdForBiome(Biomes.DEEP_OCEAN)&& aint[i1] != Biome.getIdForBiome(Biomes.FROZEN_OCEAN)&&(rules.beachesAllowed(aint[i1])))
            {
                if (aint1[i1] == Biome.getIdForBiome(Biomes.RIVER))
                {
                    int biomeID = aint[i1];
                    if (biomeID>255) throw new RuntimeException();
                    Biome biome = Biome.getBiome(biomeID);
                    float height = this.baseHeight.get(biome) + this.heightVariation.get(biome);
                    if ((height>maxChasm)||rules.riversDisallowed(biomeID)) {
                            aint2[i1] = aint[i1];

                    } else {
                        if (biome.getTempCategory() == Biome.TempCategory.COLD){
                            aint2[i1] = Biome.getIdForBiome(Biomes.FROZEN_RIVER);
                        } else if (aint[i1] != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND)
                                && aint[i1] != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE)){
                                aint2[i1] = Biome.getIdForBiome(Biomes.RIVER);
                        } else {
                            aint2[i1] = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE);
                        }
                    }
                }
                else
                {
                    aint2[i1] = aint[i1];
                }
            }
            else
            {
                aint2[i1] = aint[i1];
            }
            //if (aint2[i1] >256) throw new RuntimeException();
        }

        for (int i = 0; i < par3 * par4;i++) {
            if (aint2[i]>255) throw new RuntimeException();
            if (aint2[i]<0) throw new RuntimeException();
        }
        return aint2;
    }

}