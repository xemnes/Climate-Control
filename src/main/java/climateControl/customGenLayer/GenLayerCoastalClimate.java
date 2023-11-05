//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerCoastalClimate extends GenLayerPack {
    public GenLayerCoastalClimate(long par1, GenLayer par3GenLayer) {
        super(par1);
        super.parent = par3GenLayer;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int i1 = par1 - 1;
        int j1 = par2 - 1;
        int k1 = par3 + 2;
        int l1 = par4 + 2;
        int[] aint = this.parent.getInts(i1, j1, k1, l1);
        int[] aint1 = IntCache.getIntCache(par3 * par4);
        int[] adjacencies = new int[4];
        int numberAdjacent = 0;

        for(int i2 = 0; i2 < par4; ++i2) {
            for(int j2 = 0; j2 < par3; ++j2) {
                int k3 = aint[j2 + 1 + (i2 + 1) * k1];
                if (k3 != 0) {
                    aint1[j2 + i2 * par3] = k3;
                } else {
                    int k2 = aint[j2 + 0 + (i2 + 1) * k1];
                    if (!isOceanic(k2)) {
                        adjacencies[numberAdjacent++] = k2;
                    }

                    k2 = aint[j2 + 2 + (i2 + 1) * k1];
                    if (!isOceanic(k2)) {
                        adjacencies[numberAdjacent++] = k2;
                    }

                    k2 = aint[j2 + 1 + (i2 + 0) * k1];
                    if (!isOceanic(k2)) {
                        adjacencies[numberAdjacent++] = k2;
                    }

                    k2 = aint[j2 + 1 + (i2 + 2) * k1];
                    if (!isOceanic(k2)) {
                        adjacencies[numberAdjacent++] = k2;
                    }

                    if (numberAdjacent > 0) {
                        this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                        k3 = adjacencies[this.nextInt(numberAdjacent)];
                    }

                    aint1[j2 + i2 * par3] = k3;
                }
            }
        }

        return aint1;
    }
}
