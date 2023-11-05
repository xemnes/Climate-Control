//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.genLayerPack;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerHills extends GenLayerPack {
    private GenLayer riverLayer;
    private static final String __OBFID = "CL_00000563";

    public GenLayerHills(long p_i45479_1_, GenLayer p_i45479_3_, GenLayer p_i45479_4_) {
        super(p_i45479_1_);
        this.parent = p_i45479_3_;
        this.riverLayer = p_i45479_4_;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] aint1 = this.riverLayer.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] aint2 = IntCache.getIntCache(par3 * par4);

        for(int i1 = 0; i1 < par4; ++i1) {
            for(int j1 = 0; j1 < par3; ++j1) {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + 1 + (i1 + 1) * (par3 + 2)];
                int l1 = aint1[j1 + 1 + (i1 + 1) * (par3 + 2)];
                boolean flag = (l1 - 2) % 29 == 0;
                if (k1 > 255) {
                }

                if (k1 != 0 && l1 >= 2 && (l1 - 2) % 29 == 1 && k1 < 128) {
                    if (Biome.getBiome(k1 + 128) != null) {
                        aint2[j1 + i1 * par3] = k1 + 128;
                    } else {
                        aint2[j1 + i1 * par3] = k1;
                    }
                } else if (this.nextInt(3) != 0 && !flag) {
                    aint2[j1 + i1 * par3] = k1;
                } else {
                    int i2 = k1;
                    int j2;
                    if (k1 == Biome.getIdForBiome(Biomes.DESERT)) {
                        i2 = Biome.getIdForBiome(Biomes.DESERT_HILLS);
                    } else if (k1 == Biome.getIdForBiome(Biomes.FOREST)) {
                        i2 = Biome.getIdForBiome(Biomes.FOREST_HILLS);
                    } else if (k1 == Biome.getIdForBiome(Biomes.BIRCH_FOREST)) {
                        i2 = Biome.getIdForBiome(Biomes.BIRCH_FOREST_HILLS);
                    } else if (k1 == Biome.getIdForBiome(Biomes.ROOFED_FOREST)) {
                        i2 = Biome.getIdForBiome(Biomes.PLAINS);
                    } else if (k1 == Biome.getIdForBiome(Biomes.TAIGA)) {
                        i2 = Biome.getIdForBiome(Biomes.TAIGA_HILLS);
                    } else if (k1 == Biome.getIdForBiome(Biomes.REDWOOD_TAIGA)) {
                        i2 = Biome.getIdForBiome(Biomes.REDWOOD_TAIGA_HILLS);
                    } else if (k1 == Biome.getIdForBiome(Biomes.COLD_TAIGA)) {
                        i2 = Biome.getIdForBiome(Biomes.COLD_TAIGA_HILLS);
                    } else if (k1 == Biome.getIdForBiome(Biomes.PLAINS)) {
                        if (this.nextInt(3) == 0) {
                            i2 = Biome.getIdForBiome(Biomes.FOREST_HILLS);
                        } else {
                            i2 = Biome.getIdForBiome(Biomes.FOREST);
                        }
                    } else if (k1 == Biome.getIdForBiome(Biomes.ICE_PLAINS)) {
                        i2 = Biome.getIdForBiome(Biomes.ICE_MOUNTAINS);
                    } else if (k1 == Biome.getIdForBiome(Biomes.JUNGLE)) {
                        i2 = Biome.getIdForBiome(Biomes.JUNGLE_HILLS);
                    } else if (k1 == Biome.getIdForBiome(Biomes.OCEAN)) {
                        i2 = Biome.getIdForBiome(Biomes.DEEP_OCEAN);
                    } else if (k1 == Biome.getIdForBiome(Biomes.EXTREME_HILLS)) {
                        i2 = Biome.getIdForBiome(Biomes.EXTREME_HILLS_EDGE);
                    } else if (k1 == Biome.getIdForBiome(Biomes.SAVANNA)) {
                        i2 = Biome.getIdForBiome(Biomes.SAVANNA_PLATEAU);
                    } else if (compareBiomesById(k1, Biome.getIdForBiome(Biomes.MESA_ROCK))) {
                        i2 = Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK);
                    } else if (k1 == Biome.getIdForBiome(Biomes.DEEP_OCEAN) && this.nextInt(3) == 0) {
                        j2 = this.nextInt(2);
                        if (j2 == 0) {
                            i2 = Biome.getIdForBiome(Biomes.PLAINS);
                        } else {
                            i2 = Biome.getIdForBiome(Biomes.FOREST);
                        }
                    }

                    if (flag && i2 != k1) {
                        if (Biome.getBiome(i2 + 128) != null) {
                            i2 += 128;
                        } else {
                            i2 = k1;
                        }
                    }

                    if (i2 == k1) {
                        aint2[j1 + i1 * par3] = k1;
                    } else {
                        j2 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                        int k2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                        int l2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                        int i3 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                        int j3 = 0;
                        if (compareBiomesById(j2, k1)) {
                            ++j3;
                        }

                        if (compareBiomesById(k2, k1)) {
                            ++j3;
                        }

                        if (compareBiomesById(l2, k1)) {
                            ++j3;
                        }

                        if (compareBiomesById(i3, k1)) {
                            ++j3;
                        }

                        if (j3 >= 3) {
                            aint2[j1 + i1 * par3] = i2;
                        } else {
                            aint2[j1 + i1 * par3] = k1;
                        }
                    }
                }
            }
        }

        return aint2;
    }
}
