//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.overworld.BOPOverworldBiome;
import biomesoplenty.common.biome.overworld.BiomeGenPasture;
import biomesoplenty.common.biome.overworld.BiomeGenPrairie;
import climateControl.api.ClimateControlRules;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.AccessFloat;
import com.google.common.base.Optional;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerPrettyShore extends GenLayerPack {
    private static final String __OBFID = "CL_00000568";
    private float maxChasm;
    private ClimateControlRules rules;
    private boolean mesaMesaBorders;
    private AccessFloat<Biome> baseHeight = new AccessFloat("field_76748_D");
    private AccessFloat<Biome> heightVariation = new AccessFloat("field_76749_E");

    public GenLayerPrettyShore(long par1, GenLayer par3GenLayer, float maxChasm, ClimateControlRules rules, boolean mesaMesaBorders) {
        super(par1);
        this.parent = par3GenLayer;
        this.maxChasm = maxChasm;
        this.rules = rules;
        this.mesaMesaBorders = mesaMesaBorders;
    }

    private boolean waterBiome(int biomeID) {
        return isOceanic(biomeID) ? true : this.rules.noBeachesAllowed(biomeID);
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
                    if (l1 != Biome.getIdForBiome(Biomes.OCEAN) && i2 != Biome.getIdForBiome(Biomes.OCEAN) && j2 != Biome.getIdForBiome(Biomes.OCEAN) && k2 != Biome.getIdForBiome(Biomes.OCEAN)) {
                        aint1[j1 + i1 * par3] = k1;
                    } else {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE);
                    }
                } else if (biome != null && biome.getBiomeClass() == BiomeJungle.class) {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                    if (this.isJungleCompatible(l1) && this.isJungleCompatible(i2) && this.isJungleCompatible(j2) && this.isJungleCompatible(k2)) {
                        if (!this.waterBiome(l1) && !this.waterBiome(i2) && !this.waterBiome(j2) && !this.waterBiome(k2)) {
                            aint1[j1 + i1 * par3] = k1;
                        } else {
                            aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.BEACH);
                        }
                    } else {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.JUNGLE_EDGE);
                    }
                } else if (k1 != Biome.getIdForBiome(Biomes.EXTREME_HILLS) && k1 != Biome.getIdForBiome(Biomes.EXTREME_HILLS_WITH_TREES) && k1 != Biome.getIdForBiome(Biomes.EXTREME_HILLS_EDGE)) {
                    if (biome != null && biome.getTempCategory().equals(TempCategory.COLD)) {
                        this.replaceIfNeighborOcean(aint, aint1, j1, i1, par3, k1, Biome.getIdForBiome(Biomes.COLD_BEACH));
                    } else if (k1 != Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK) && k1 != Biome.getIdForBiome(Biomes.MESA_ROCK)) {
                        if (!this.waterBiome(k1)) {
                            l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                            i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                            j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                            k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                            if (!this.waterBiome(l1) && !this.waterBiome(i2) && !this.waterBiome(j2) && !this.waterBiome(k2)) {
                                aint1[j1 + i1 * par3] = k1;
                            } else {
                                if (biome == null) {
                                    throw new RuntimeException("no biome found for biome #" + k1);
                                }

                                float height = this.baseHeight.get(biome) + this.heightVariation.get(biome);
                                if ((double)height > (double)this.maxChasm + 0.5 && this.rules.stoneBeachAllowed(k1)) {
                                    aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.STONE_BEACH);
                                } else if (biome.getTempCategory() == TempCategory.COLD) {
                                    aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.COLD_BEACH);
                                } else if (aint[j1 + i1 * par3] != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND) && aint[j1 + i1 * par3] != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE)) {
                                    aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.BEACH);
                                } else {
                                    aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE);
                                }
                            }
                        } else {
                            aint1[j1 + i1 * par3] = k1;
                        }
                    }
                    else {
                        l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                        i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                        j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                        k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                        if (!this.waterBiome(l1) && !this.waterBiome(i2) && !this.waterBiome(j2) && !this.waterBiome(k2)) {
                            if (this.isMesa(l1) && this.isMesa(i2) && this.isMesa(j2) && this.isMesa(k2)) {
                                aint1[j1 + i1 * par3] = k1;
                            } else if (this.mesaMesaBorders) {
                                aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.MESA);
                            } else {
                                aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.DESERT);
                            }
                        }
                        else {
                            aint1[j1 + i1 * par3] = k1;
                        }
                    }
                }
                // attempt at implementing bop biomes
                else if (biome != null && biome.getBiomeClass() == BiomeGenPasture.class) {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

                    if (!this.waterBiome(l1) && !this.waterBiome(i2) && !this.waterBiome(j2) && !this.waterBiome(k2)) {
//                        if (this.isPasture(l1) && this.isPasture(i2) && this.isPasture(j2) && this.isPasture(k2)) {
                        if (l1 == bopID(BOPBiomes.pasture) && i2 == bopID(BOPBiomes.pasture) && j2 == bopID(BOPBiomes.pasture) && k2 == bopID(BOPBiomes.pasture)) {

                            aint1[j1 + i1 * par3] = bopID(BOPBiomes.prairie);
                        }
                    }
                }
                else {
                    this.replaceIfNeighborOcean(aint, aint1, j1, i1, par3, k1, Biome.getIdForBiome(Biomes.STONE_BEACH));
                }
            }
        }

        return aint1;
    }

    private void replaceIfNeighborOcean(int[] p_151632_1_, int[] p_151632_2_, int p_151632_3_, int p_151632_4_, int p_151632_5_, int p_151632_6_, int p_151632_7_) {
        if (this.waterBiome(p_151632_6_)) {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
        } else {
            int j1 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
            int k1 = p_151632_1_[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int l1 = p_151632_1_[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int i2 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];
            if (!this.waterBiome(j1) && !this.waterBiome(k1) && !this.waterBiome(l1) && !this.waterBiome(i2)) {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
            } else {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_7_;
            }
        }

    }

    private int bopID(Optional<Biome> bopBiome) {
        try {
            return Biome.getIdForBiome((Biome)bopBiome.get());
        } catch (IllegalStateException noBopBiome) {
            return -1;
        }
    }

    private boolean isJungleCompatible(int p_151631_1_) {
        return Biome.getBiome(p_151631_1_) != null && Biome.getBiome(p_151631_1_).getBiomeClass() == BiomeJungle.class ? true : p_151631_1_ == Biome.getIdForBiome(Biomes.JUNGLE_EDGE) || p_151631_1_ == Biome.getIdForBiome(Biomes.JUNGLE) || p_151631_1_ == Biome.getIdForBiome(Biomes.JUNGLE_HILLS) || p_151631_1_ == Biome.getIdForBiome(Biomes.FOREST) || p_151631_1_ == Biome.getIdForBiome(Biomes.TAIGA) || isOceanic(p_151631_1_);
    }

    private boolean isPasture(int p_151633_1_) {
        return Biome.getBiome(p_151633_1_) != null && Biome.getBiome(p_151633_1_) instanceof BiomeGenPasture;
    }

    private boolean isMesa(int p_151633_1_) {
        return Biome.getBiome(p_151633_1_) != null && Biome.getBiome(p_151633_1_) instanceof BiomeMesa;
    }
}
