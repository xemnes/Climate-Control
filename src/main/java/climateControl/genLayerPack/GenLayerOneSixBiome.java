//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.genLayerPack;

import com.google.common.collect.ObjectArrays;
import net.minecraft.init.Biomes;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerOneSixBiome extends GenLayer {
    private Biome[] allowedBiomes;
    public static final Biome[] base11Biomes;
    public static final Biome[] base12Biomes;

    public GenLayerOneSixBiome(long par1, GenLayer par3GenLayer, WorldType par4WorldType) {
        super(par1);
        this.allowedBiomes = base12Biomes;
        this.parent = par3GenLayer;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = this.parent.getInts(par1, par2, par3, par4);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for(int i1 = 0; i1 < par4; ++i1) {
            for(int j1 = 0; j1 < par3; ++j1) {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + i1 * par3];
                if (k1 == 0) {
                    aint1[j1 + i1 * par3] = 0;
                } else if (k1 == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND)) {
                    aint1[j1 + i1 * par3] = k1;
                } else if (k1 == 1) {
                    aint1[j1 + i1 * par3] = Biome.getIdForBiome(this.allowedBiomes[this.nextInt(this.allowedBiomes.length)]);
                } else {
                    int l1 = Biome.getIdForBiome(this.allowedBiomes[this.nextInt(this.allowedBiomes.length)]);
                    if (l1 == Biome.getIdForBiome(Biomes.COLD_TAIGA)) {
                        aint1[j1 + i1 * par3] = l1;
                    } else {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.ICE_PLAINS);
                    }
                }
            }
        }

        return aint1;
    }

    static {
        base11Biomes = new Biome[]{Biomes.DESERT, Biomes.FOREST, Biomes.EXTREME_HILLS, Biomes.SWAMPLAND, Biomes.PLAINS, Biomes.COLD_TAIGA};
        base12Biomes = (Biome[])ObjectArrays.concat(base11Biomes, Biomes.JUNGLE);
    }
}
