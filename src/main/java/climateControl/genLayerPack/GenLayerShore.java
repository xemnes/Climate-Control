//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.genLayerPack;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerShore extends GenLayerPack {
    private static final String __OBFID = "CL_00000568";

    public GenLayerShore(long par1, GenLayer par3GenLayer) {
        super(par1);
        this.parent = par3GenLayer;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for(int i1 = 0; i1 < par4; ++i1) {
            for(int j1 = 0; j1 < par3; ++j1) {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + 1 + (i1 + 1) * (par3 + 2)];
                Biome biome = Biome.getBiome(k1);
                int l1;
                int i2;
                int j2;
                int k2;
                if (k1 == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND)) {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                    if (l1 != 0 && i2 != 0 && j2 != 0 && k2 != 0) {
                        aint1[j1 + i1 * par3] = k1;
                    } else {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE);
                    }
                } else if (biome != null && Biome.getIdForBiome(biome) > 20 && Biome.getIdForBiome(biome) < 24) {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                    if (this.isJungleCompatible(l1) && this.isJungleCompatible(i2) && this.isJungleCompatible(j2) && this.isJungleCompatible(k2)) {
                        if (!isOceanic(l1) && !isOceanic(i2) && !isOceanic(j2) && !isOceanic(k2)) {
                            aint1[j1 + i1 * par3] = k1;
                        } else {
                            aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.BEACH);
                        }
                    } else {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.JUNGLE_EDGE);
                    }
                } else if (k1 != Biome.getIdForBiome(Biomes.EXTREME_HILLS) && k1 != Biome.getIdForBiome(Biomes.EXTREME_HILLS_WITH_TREES) && k1 != Biome.getIdForBiome(Biomes.EXTREME_HILLS_EDGE)) {
                    if (biome != null && biome.isSnowyBiome()) {
                        this.replaceIfNeighborOcean(aint, aint1, j1, i1, par3, k1, Biome.getIdForBiome(Biomes.COLD_BEACH));
                    } else if (k1 != Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK) && k1 != Biome.getIdForBiome(Biomes.MESA_ROCK)) {
                        if (k1 != Biome.getIdForBiome(Biomes.OCEAN) && k1 != Biome.getIdForBiome(Biomes.DEEP_OCEAN) && k1 != Biome.getIdForBiome(Biomes.RIVER) && k1 != Biome.getIdForBiome(Biomes.SWAMPLAND)) {
                            l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                            i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                            j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                            k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                            if (!isOceanic(l1) && !isOceanic(i2) && !isOceanic(j2) && !isOceanic(k2)) {
                                aint1[j1 + i1 * par3] = k1;
                            } else {
                                aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.BEACH);
                            }
                        } else {
                            aint1[j1 + i1 * par3] = k1;
                        }
                    } else {
                        l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                        i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                        j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                        k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                        if (!isOceanic(l1) && !isOceanic(i2) && !isOceanic(j2) && !isOceanic(k2)) {
                            if (this.isMesa(l1) && this.isMesa(i2) && this.isMesa(j2) && this.isMesa(k2)) {
                                aint1[j1 + i1 * par3] = k1;
                            } else {
                                aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.DESERT);
                            }
                        } else {
                            aint1[j1 + i1 * par3] = k1;
                        }
                    }
                } else {
                    this.replaceIfNeighborOcean(aint, aint1, j1, i1, par3, k1, Biome.getIdForBiome(Biomes.STONE_BEACH));
                }
            }
        }

        return aint1;
    }

    private void replaceIfNeighborOcean(int[] parentVals, int[] resultVals, int p_151632_3_, int p_151632_4_, int p_151632_5_, int originalBiome, int replacementBeach) {
        if (isOceanic(originalBiome)) {
            resultVals[p_151632_3_ + p_151632_4_ * p_151632_5_] = originalBiome;
        } else {
            int j1 = parentVals[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
            int k1 = parentVals[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int l1 = parentVals[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int i2 = parentVals[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];
            if (!isOceanic(j1) && !isOceanic(k1) && !isOceanic(l1) && !isOceanic(i2)) {
                resultVals[p_151632_3_ + p_151632_4_ * p_151632_5_] = originalBiome;
            } else {
                resultVals[p_151632_3_ + p_151632_4_ * p_151632_5_] = replacementBeach;
            }
        }

    }

    private boolean isJungleCompatible(int p_151631_1_) {
        return Biome.getBiome(p_151631_1_) != null && Biome.getBiome(p_151631_1_).getBiomeClass() == BiomeJungle.class ? true : p_151631_1_ == Biome.getIdForBiome(Biomes.JUNGLE_EDGE) || p_151631_1_ == Biome.getIdForBiome(Biomes.JUNGLE) || p_151631_1_ == Biome.getIdForBiome(Biomes.JUNGLE_HILLS) || p_151631_1_ == Biome.getIdForBiome(Biomes.FOREST) || p_151631_1_ == Biome.getIdForBiome(Biomes.TAIGA) || isBiomeOceanic(p_151631_1_);
    }

    private boolean isMesa(int p_151633_1_) {
        return Biome.getBiome(p_151633_1_) != null && p_151633_1_ > 36;
    }
}
