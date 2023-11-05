//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.genLayerPack;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSwampRivers extends GenLayer {
    public GenLayerSwampRivers(long par1, GenLayer par3GenLayer) {
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
                if (k1 == Biome.getIdForBiome(Biomes.SWAMPLAND) && this.nextInt(6) == 0 || (k1 == Biome.getIdForBiome(Biomes.JUNGLE) || k1 == Biome.getIdForBiome(Biomes.JUNGLE_HILLS)) && this.nextInt(8) == 0) {
                    aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.RIVER);
                } else {
                    aint1[j1 + i1 * par3] = k1;
                }
            }
        }

        return aint1;
    }
}
