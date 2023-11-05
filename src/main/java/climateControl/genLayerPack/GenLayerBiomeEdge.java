//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.genLayerPack;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeEdge extends GenLayerPack {
    private static final String __OBFID = "CL_00000554";

    public GenLayerBiomeEdge(long p_i45475_1_, GenLayerPack p_i45475_3_) {
        super(p_i45475_1_);
        this.parent = p_i45475_3_;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for(int i1 = 0; i1 < par4; ++i1) {
            for(int j1 = 0; j1 < par3; ++j1) {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + 1 + (i1 + 1) * (par3 + 2)];
                if (!this.replaceBiomeEdgeIfNecessary(aint, aint1, j1, i1, par3, k1, Biome.getIdForBiome(Biomes.EXTREME_HILLS), Biome.getIdForBiome(Biomes.EXTREME_HILLS_EDGE)) && !this.replaceBiomeEdge(aint, aint1, j1, i1, par3, k1, Biome.getIdForBiome(Biomes.MESA_ROCK), Biome.getIdForBiome(Biomes.MESA)) && !this.replaceBiomeEdge(aint, aint1, j1, i1, par3, k1, Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK), Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK)) && !this.replaceBiomeEdge(aint, aint1, j1, i1, par3, k1, Biome.getIdForBiome(Biomes.REDWOOD_TAIGA), Biome.getIdForBiome(Biomes.TAIGA))) {
                    int l1;
                    int i2;
                    int j2;
                    int k2;
                    if (k1 == Biome.getIdForBiome(Biomes.DESERT)) {
                        l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                        i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                        j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                        k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                        if (l1 != Biome.getIdForBiome(Biomes.ICE_PLAINS) && i2 != Biome.getIdForBiome(Biomes.ICE_PLAINS) && j2 != Biome.getIdForBiome(Biomes.ICE_PLAINS) && k2 != Biome.getIdForBiome(Biomes.ICE_PLAINS)) {
                            aint1[j1 + i1 * par3] = k1;
                        } else {
                            aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.EXTREME_HILLS_EDGE);
                        }
                    } else if (k1 == Biome.getIdForBiome(Biomes.SWAMPLAND)) {
                        l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                        i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                        j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                        k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                        if (l1 != Biome.getIdForBiome(Biomes.DESERT) && i2 != Biome.getIdForBiome(Biomes.DESERT) && j2 != Biome.getIdForBiome(Biomes.DESERT) && k2 != Biome.getIdForBiome(Biomes.DESERT) && l1 != Biome.getIdForBiome(Biomes.COLD_TAIGA) && i2 != Biome.getIdForBiome(Biomes.COLD_TAIGA) && j2 != Biome.getIdForBiome(Biomes.COLD_TAIGA) && k2 != Biome.getIdForBiome(Biomes.COLD_TAIGA) && l1 != Biome.getIdForBiome(Biomes.ICE_PLAINS) && i2 != Biome.getIdForBiome(Biomes.ICE_PLAINS) && j2 != Biome.getIdForBiome(Biomes.ICE_PLAINS) && k2 != Biome.getIdForBiome(Biomes.ICE_PLAINS)) {
                            if (l1 != Biome.getIdForBiome(Biomes.JUNGLE) && k2 != Biome.getIdForBiome(Biomes.JUNGLE) && i2 != Biome.getIdForBiome(Biomes.JUNGLE) && j2 != Biome.getIdForBiome(Biomes.JUNGLE)) {
                                aint1[j1 + i1 * par3] = k1;
                            } else {
                                aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.JUNGLE_EDGE);
                            }
                        } else {
                            aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.PLAINS);
                        }
                    } else {
                        aint1[j1 + i1 * par3] = k1;
                    }
                }
            }
        }

        return aint1;
    }

    private boolean replaceBiomeEdgeIfNecessary(int[] p_151636_1_, int[] p_151636_2_, int p_151636_3_, int p_151636_4_, int p_151636_5_, int p_151636_6_, int p_151636_7_, int p_151636_8_) {
        if (!compareBiomesById(p_151636_6_, p_151636_7_)) {
            return false;
        } else {
            int k1 = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 - 1) * (p_151636_5_ + 2)];
            int l1 = p_151636_1_[p_151636_3_ + 1 + 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
            int i2 = p_151636_1_[p_151636_3_ + 1 - 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
            int j2 = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 + 1) * (p_151636_5_ + 2)];
            if (this.canBiomesBeNeighbors(k1, p_151636_7_) && this.canBiomesBeNeighbors(l1, p_151636_7_) && this.canBiomesBeNeighbors(i2, p_151636_7_) && this.canBiomesBeNeighbors(j2, p_151636_7_)) {
                p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_6_;
            } else {
                p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_8_;
            }

            return true;
        }
    }

    private boolean replaceBiomeEdge(int[] p_151635_1_, int[] p_151635_2_, int p_151635_3_, int p_151635_4_, int p_151635_5_, int p_151635_6_, int p_151635_7_, int p_151635_8_) {
        if (p_151635_6_ != p_151635_7_) {
            return false;
        } else {
            int k1 = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 - 1) * (p_151635_5_ + 2)];
            int l1 = p_151635_1_[p_151635_3_ + 1 + 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
            int i2 = p_151635_1_[p_151635_3_ + 1 - 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
            int j2 = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 + 1) * (p_151635_5_ + 2)];
            if (compareBiomesById(k1, p_151635_7_) && compareBiomesById(l1, p_151635_7_) && compareBiomesById(i2, p_151635_7_) && compareBiomesById(j2, p_151635_7_)) {
                p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_6_;
            } else {
                p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_8_;
            }

            return true;
        }
    }

    private boolean canBiomesBeNeighbors(int p_151634_1_, int p_151634_2_) {
        if (compareBiomesById(p_151634_1_, p_151634_2_)) {
            return true;
        } else if (Biome.getBiome(p_151634_1_) != null && Biome.getBiome(p_151634_2_) != null) {
            Biome.TempCategory tempcategory = Biome.getBiome(p_151634_1_).getTempCategory();
            Biome.TempCategory tempcategory1 = Biome.getBiome(p_151634_2_).getTempCategory();
            return tempcategory == tempcategory1 || tempcategory == TempCategory.MEDIUM || tempcategory1 == TempCategory.MEDIUM;
        } else {
            return false;
        }
    }
}
