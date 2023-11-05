//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.genLayerPack;

import com.Zeno410Utils.Zeno410Logger;
import java.util.logging.Logger;
import net.minecraft.init.Biomes;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiome extends GenLayerPack {
    private static Logger logger = (new Zeno410Logger("GenLayerBiome")).logger();
    private Biome[] warmBiomes;
    private Biome[] mediumBiomes;
    private Biome[] coldBiomes;
    private Biome[] iceBiomes;
    private static final String __OBFID = "CL_00000555";

    public GenLayerBiome(long par1, GenLayer par3GenLayer, WorldType worldType) {
        super(par1);
        this.warmBiomes = new Biome[]{Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, Biomes.SAVANNA, Biomes.SAVANNA, Biomes.PLAINS};
        this.mediumBiomes = new Biome[]{Biomes.FOREST, Biomes.ROOFED_FOREST, Biomes.EXTREME_HILLS, Biomes.PLAINS, Biomes.BIRCH_FOREST, Biomes.SWAMPLAND};
        this.coldBiomes = new Biome[]{Biomes.FOREST, Biomes.EXTREME_HILLS, Biomes.TAIGA, Biomes.PLAINS};
        this.iceBiomes = new Biome[]{Biomes.ICE_PLAINS, Biomes.ICE_PLAINS, Biomes.ICE_PLAINS, Biomes.COLD_TAIGA};
        this.parent = par3GenLayer;
        if (worldType == WorldType.DEFAULT_1_1) {
            this.warmBiomes = new Biome[]{Biomes.DESERT, Biomes.FOREST, Biomes.EXTREME_HILLS, Biomes.SWAMPLAND, Biomes.PLAINS, Biomes.TAIGA};
        }

    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = this.parent.getInts(par1, par2, par3, par4);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for(int i1 = 0; i1 < par4; ++i1) {
            for(int j1 = 0; j1 < par3; ++j1) {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + i1 * par3];
                int l1 = (k1 & 3840) >> 8;
                k1 &= -3841;
                if (isOceanic(k1)) {
                    aint1[j1 + i1 * par3] = k1;
                } else if (k1 == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND)) {
                    aint1[j1 + i1 * par3] = k1;
                } else if (k1 == 1) {
                    if (l1 > 0) {
                        if (this.nextInt(3) == 0) {
                            aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK);
                        } else {
                            aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.MESA_ROCK);
                        }
                    } else {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(this.warmBiomes[this.nextInt(this.warmBiomes.length)]);
                    }
                } else if (k1 == 2) {
                    if (l1 > 0) {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.JUNGLE);
                    } else {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(this.mediumBiomes[this.nextInt(this.mediumBiomes.length)]);
                    }
                } else if (k1 == 3) {
                    if (l1 > 0) {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.REDWOOD_TAIGA);
                    } else {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(this.coldBiomes[this.nextInt(this.coldBiomes.length)]);
                    }
                } else if (k1 == 4) {
                    aint1[j1 + i1 * par3] = Biome.getIdForBiome(this.iceBiomes[this.nextInt(this.iceBiomes.length)]);
                } else {
                    aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND);
                }

                logger.info("(" + (i1 + par2) + "," + (j1 + par1) + ") Climate " + k1 + " " + aint[j1 + i1 * par3] + " Biome " + aint1[j1 + i1 * par3]);
            }
        }

        return aint1;
    }
}
