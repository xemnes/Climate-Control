//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.api.ClimateControlRules;
import climateControl.genLayerPack.GenLayerPack;
import climateControl.utils.IntPad;
import com.Zeno410Utils.AccessFloat;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverMix;

public class GenLayerLowlandRiverMix extends GenLayerRiverMix {
    private GenLayer biomePatternGeneratorChain;
    private GenLayer riverPatternGeneratorChain;
    private ClimateControlRules rules;
    private float maxChasm;
    private IntPad output = new IntPad();
    private AccessFloat<Biome> baseHeight = new AccessFloat("field_76748_D");
    private AccessFloat<Biome> heightVariation = new AccessFloat("field_76749_E");

    public GenLayerLowlandRiverMix(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer, float maxChasm, ClimateControlRules rules) {
        super(par1, par3GenLayer, par4GenLayer);
        if (!(par3GenLayer instanceof GenLayerPack)) {
            throw new RuntimeException();
        } else if (!(par4GenLayer instanceof GenLayerPack)) {
            throw new RuntimeException();
        } else {
            this.biomePatternGeneratorChain = par3GenLayer;
            this.riverPatternGeneratorChain = par4GenLayer;
            this.maxChasm = maxChasm;
            this.rules = rules;
        }
    }

    public void initWorldGenSeed(long par1) {
        this.biomePatternGeneratorChain.initWorldGenSeed(par1);
        this.riverPatternGeneratorChain.initWorldGenSeed(par1);
        super.initWorldGenSeed(par1);
    }

    public GenLayer forLocking() {
        return this.biomePatternGeneratorChain;
    }

    public void setMaxChasm(float newValue) {
        this.maxChasm = newValue;
    }

    public synchronized int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = null;
        int[] aint1 = null;

        try {
            aint = this.biomePatternGeneratorChain.getInts(par1, par2, par3, par4);
            aint1 = this.riverPatternGeneratorChain.getInts(par1, par2, par3, par4);
        } catch (Exception var12) {
            throw new RuntimeException(var12);
        }

        int[] aint2 = this.output.pad(par3 * par4);

        int i1;
        for(i1 = 0; i1 < par3 * par4; ++i1) {
            if (aint[i1] > 255) {
                throw new RuntimeException(this.biomePatternGeneratorChain.toString());
            }

            if (aint[i1] < 0) {
                throw new RuntimeException(this.biomePatternGeneratorChain.toString());
            }
        }

        for(i1 = 0; i1 < par3 * par4; ++i1) {
            if (aint[i1] != Biome.getIdForBiome(Biomes.OCEAN) && aint[i1] != Biome.getIdForBiome(Biomes.DEEP_OCEAN) && aint[i1] != Biome.getIdForBiome(Biomes.FROZEN_OCEAN) && this.rules.beachesAllowed(aint[i1])) {
                if (aint1[i1] == Biome.getIdForBiome(Biomes.RIVER)) {
                    int biomeID = aint[i1];
                    if (biomeID > 255) {
                        throw new RuntimeException();
                    }

                    Biome biome = Biome.getBiome(biomeID);
                    float height = this.baseHeight.get(biome) + this.heightVariation.get(biome);
                    if (!(height > this.maxChasm) && !this.rules.riversDisallowed(biomeID)) {
                        if (biome.getTempCategory() == TempCategory.COLD) {
                            aint2[i1] = Biome.getIdForBiome(Biomes.FROZEN_RIVER);
                        } else if (aint[i1] != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND) && aint[i1] != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE)) {
                            aint2[i1] = Biome.getIdForBiome(Biomes.RIVER);
                        } else {
                            aint2[i1] = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE);
                        }
                    } else {
                        aint2[i1] = aint[i1];
                    }
                } else {
                    aint2[i1] = aint[i1];
                }
            } else {
                aint2[i1] = aint[i1];
            }
        }

        for(i1 = 0; i1 < par3 * par4; ++i1) {
            if (aint2[i1] > 255) {
                throw new RuntimeException();
            }

            if (aint2[i1] < 0) {
                throw new RuntimeException();
            }
        }

        return aint2;
    }
}
