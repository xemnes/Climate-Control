
package climateControl.customGenLayer;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.overworld.*;
import climateControl.api.ClimateControlRules;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.AccessFloat;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import com.google.common.base.Optional;

/*
 * This routine puts borders between biomes. Primarily that's appropriate beaches at the water's edge
 * but it also includes desert borders on mesa and Jungle Edge for jungles
 * */
public class GenLayerPrettyShore extends GenLayerPack
{
    private static final String __OBFID = "CL_00000568";
    private float maxChasm;
    private ClimateControlRules rules;
    private boolean mesaMesaBorders;
    private AccessFloat<Biome> baseHeight = new AccessFloat<Biome>("field_76748_D");
    private AccessFloat<Biome> heightVariation = new AccessFloat<Biome>("field_76749_E");

    public GenLayerPrettyShore(long par1, GenLayer par3GenLayer, float maxChasm, ClimateControlRules rules,boolean mesaMesaBorders)
    {
        super(par1);
        this.parent = par3GenLayer;
        this.maxChasm = maxChasm;
        this.rules = rules;
        this.mesaMesaBorders = mesaMesaBorders;
    }

    private boolean waterBiome(int biomeID) {
        if (isOceanic(biomeID)) return true;
        return rules.noBeachesAllowed(biomeID);
    }

    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] biomeIds = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int z = 0; z < areaHeight; ++z)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                this.initChunkSeed((long)(x + areaX), (long)(z + areaY));
                int biomeId = biomeIds[x + 1 + (z + 1) * (areaWidth + 2)];
                Biome biome = Biome.getBiome(biomeId);
                int biomeNorth;
                int biomeEast;
                int biomeWest;
                int biomeSouth;

                if (biomeId == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND))
                {
                    biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];

                    if (biomeNorth != Biome.getIdForBiome(Biomes.OCEAN) && biomeEast != Biome.getIdForBiome(Biomes.OCEAN) && biomeWest != Biome.getIdForBiome(Biomes.OCEAN) && biomeSouth != Biome.getIdForBiome(Biomes.OCEAN))
                    {
                        out[x + z * areaWidth] = biomeId;
                    }
                    else
                    {
                        out[x + z * areaWidth] = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE);
                    }
                }
                else if (biome != null && biome.getBiomeClass() == BiomeJungle.class)
                {
                    biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];

                    if (this.isJungleCompatible(biomeNorth) && this.isJungleCompatible(biomeEast) && this.isJungleCompatible(biomeWest) && this.isJungleCompatible(biomeSouth))
                    {
                        if (!waterBiome(biomeNorth) && !waterBiome(biomeEast) && !waterBiome(biomeWest) && !waterBiome(biomeSouth))
                        {
                            out[x + z * areaWidth] = biomeId;
                        }
                        else
                        {
                            out[x + z * areaWidth] = Biome.getIdForBiome(Biomes.BEACH);
                        }
                    }
                    else
                    {
                        out[x + z * areaWidth] = Biome.getIdForBiome(Biomes.JUNGLE_EDGE);
                    }
                }
                else if (biome != null && biome.getBiomeClass() == BiomeGenPasture.class) {
                    biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                    if (biomeNorth == bopID(BOPBiomes.pasture) && biomeEast == bopID(BOPBiomes.pasture) && biomeWest == bopID(BOPBiomes.pasture) && biomeSouth == bopID(BOPBiomes.pasture)) {
                        out[x + z * areaWidth] = biomeId;
                    } else {
                        out[x + z * areaWidth] = bopID(BOPBiomes.prairie);
                    }
                }
                else if (biome != null && biome.getBiomeClass() == BiomeGenRedwoodForest.class) {
                    biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                    if (biomeNorth == bopID(BOPBiomes.redwood_forest) && biomeEast == bopID(BOPBiomes.redwood_forest) && biomeWest == bopID(BOPBiomes.redwood_forest) && biomeSouth == bopID(BOPBiomes.redwood_forest)) {
                        out[x + z * areaWidth] = biomeId;
                    } else {
                        out[x + z * areaWidth] = bopID(BOPBiomes.redwood_forest_edge);
                    }
                }
                else if (biome != null && biome.getBiomeClass() == BiomeGenMountain.class) {
                    biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                    if (biomeNorth == bopID(BOPBiomes.mountain) && biomeEast == bopID(BOPBiomes.mountain) && biomeWest == bopID(BOPBiomes.mountain) && biomeSouth == bopID(BOPBiomes.mountain)) {
                        out[x + z * areaWidth] = biomeId;
                    } else {
                        out[x + z * areaWidth] = bopID(BOPBiomes.mountain_foothills);
                    }
                }
                else if (biome != null && biome.getBiomeClass() == BiomeGenAlps.class) {
                    biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                    if (biomeNorth == bopID(BOPBiomes.alps) && biomeEast == bopID(BOPBiomes.alps) && biomeWest == bopID(BOPBiomes.alps) && biomeSouth == bopID(BOPBiomes.alps)) {
                        out[x + z * areaWidth] = biomeId;
                    } else {
                        out[x + z * areaWidth] = bopID(BOPBiomes.alps_foothills);
                    }
                }
                //wrap beaches round island biomes
                else if (biome != null && biome.getBiomeClass() == BiomeGenTropicalIsland.class) {
                    biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                    if (biomeNorth == bopID(BOPBiomes.tropical_island) && biomeEast == bopID(BOPBiomes.tropical_island) && biomeWest == bopID(BOPBiomes.tropical_island) && biomeSouth == bopID(BOPBiomes.tropical_island)) {
                        out[x + z * areaWidth] = biomeId;
                    } else {
                        out[x + z * areaWidth] = bopID(BOPBiomes.white_beach);
                    }
                }
                else if (biome != null && biome.getBiomeClass() == BiomeGenFlowerIsland.class) {
                    biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                    if (biomeNorth == bopID(BOPBiomes.flower_island) && biomeEast == bopID(BOPBiomes.flower_island) && biomeWest == bopID(BOPBiomes.flower_island) && biomeSouth == bopID(BOPBiomes.flower_island)) {
                        out[x + z * areaWidth] = biomeId;
                    } else {
                        out[x + z * areaWidth] = bopID(BOPBiomes.white_beach);
                    }
                }
                else if (biome != null && biome.getBiomeClass() == BiomeGenOriginIsland.class) {
                    biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                    if (biomeNorth == bopID(BOPBiomes.origin_island) && biomeEast == bopID(BOPBiomes.origin_island) && biomeWest == bopID(BOPBiomes.origin_island) && biomeSouth == bopID(BOPBiomes.origin_island)) {
                        out[x + z * areaWidth] = biomeId;
                    } else {
                        out[x + z * areaWidth] = bopID(BOPBiomes.origin_beach);
                    }
                }
                else if (biome != null && biome.getBiomeClass() == BiomeGenSacredSprings.class) {
                    biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                    biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                    biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                    biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                    if (biomeNorth == bopID(BOPBiomes.sacred_springs) && biomeEast == bopID(BOPBiomes.sacred_springs) && biomeWest == bopID(BOPBiomes.sacred_springs) && biomeSouth == bopID(BOPBiomes.sacred_springs)) {
                        out[x + z * areaWidth] = biomeId;
                    } else {
                        out[x + z * areaWidth] = bopID(BOPBiomes.white_beach);
                    }
                }
                else if (biomeId != Biome.getIdForBiome(Biomes.EXTREME_HILLS) && biomeId != Biome.getIdForBiome(Biomes.EXTREME_HILLS_WITH_TREES) && biomeId != Biome.getIdForBiome(Biomes.EXTREME_HILLS_EDGE))
                {
                    if (biome != null && biome.getEnableSnow())//&&!biome.getBiomeName().equalsIgnoreCase("Dead Forest"))
                    {
                        this.func_151632_a(biomeIds, out, x, z, areaWidth, biomeId, biome.getIdForBiome(Biomes.COLD_BEACH));
                    }
                    else if (biomeId != Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK) && biomeId != Biome.getIdForBiome(Biomes.MESA_ROCK))
                    {
                        if (!waterBiome(biomeId))
                        {
                            biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                            biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                            biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                            biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];

                            if (!waterBiome(biomeNorth) && !waterBiome(biomeEast) && !waterBiome(biomeWest) && !waterBiome(biomeSouth))
                            {
                                out[x + z * areaWidth] = biomeId;
                            }
                            else
                            {//
                                if (biome == null) throw new RuntimeException("no biome found for biome #"+biomeId);
                                float height = this.baseHeight.get(biome) + this.heightVariation.get(biome);
                                if ((height>maxChasm+0.5)&&(rules.stoneBeachAllowed(biomeId))) {
                                        out[x + z * areaWidth] = Biome.getIdForBiome(Biomes.STONE_BEACH);

                                } else {
                                    if (biome.getTempCategory() == Biome.TempCategory.COLD){
                                        out[x + z * areaWidth] = Biome.getIdForBiome(Biomes.COLD_BEACH);
                                    } else if (biomeIds[x + z * areaWidth] != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND)
                                            && biomeIds[x + z * areaWidth] != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE)){
                                                    out[x + z * areaWidth] = Biome.getIdForBiome(Biomes.BEACH);
                                    } else {
                                        out[x + z * areaWidth] = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND_SHORE);
                                    }
                                }
                            }
                        }
                        else
                        {
                            out[x + z * areaWidth] = biomeId;
                        }
                    }
                    else // Mesa biomes
                    {
                        biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                        biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                        biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                        biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];

                        if (!waterBiome(biomeNorth) && !waterBiome(biomeEast) && !waterBiome(biomeWest) && !waterBiome(biomeSouth))
                        {
                            if (this.isMesa(biomeNorth) && this.isMesa(biomeEast) && this.isMesa(biomeWest) && this.isMesa(biomeSouth))
                            {
                                out[x + z * areaWidth] = biomeId;
                            }
                            else
                            {
                                if (this.mesaMesaBorders) {
                                    out[x + z * areaWidth] = Biome.getIdForBiome(Biomes.MESA);
                                } else {
                                    out[x + z * areaWidth] = Biome.getIdForBiome(Biomes.DESERT);
                                }
                            }
                        }
                        else
                        {
                            out[x + z * areaWidth] = biomeId;
                        }
                    }
                }
                else
                {
                    this.func_151632_a(biomeIds, out, x, z, areaWidth, biomeId, Biome.getIdForBiome(Biomes.STONE_BEACH));
                }
            }
        }

        return out;
    }

    private void func_151632_a(int[] p_151632_1_, int[] p_151632_2_, int p_151632_3_, int p_151632_4_, int p_151632_5_, int p_151632_6_, int p_151632_7_)
    {
        if (waterBiome(p_151632_6_))
        {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
        }
        else
        {
            int j1 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
            int k1 = p_151632_1_[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int l1 = p_151632_1_[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int i2 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];

            if (!waterBiome(j1) && !waterBiome(k1) && !waterBiome(l1) && !waterBiome(i2))
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
            }
            else
            {
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

    private boolean isJungleCompatible(int p_151631_1_)
    {
        return Biome.getBiome(p_151631_1_) != null && Biome.getBiome(p_151631_1_).getBiomeClass() == BiomeJungle.class ? true : p_151631_1_ == Biome.getIdForBiome(Biomes.JUNGLE_EDGE)
                || p_151631_1_ == Biome.getIdForBiome(Biomes.JUNGLE) || p_151631_1_ == Biome.getIdForBiome(Biomes.JUNGLE_HILLS)
                || p_151631_1_ == Biome.getIdForBiome(Biomes.FOREST) || p_151631_1_ == Biome.getIdForBiome(Biomes.TAIGA) || isOceanic(p_151631_1_);
    }

    private boolean isMesa(int p_151633_1_)
    {
        return Biome.getBiome(p_151633_1_) != null && Biome.getBiome(p_151633_1_) instanceof BiomeMesa;
    }
}