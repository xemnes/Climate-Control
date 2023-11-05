//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAddLand extends GenLayerNeighborTesting {
    private static final String __OBFID = "CL_00000551";
    private final boolean separate;

    public GenLayerAddLand(long par1, GenLayer par3GenLayer, boolean separate) {
        super(par1);
        this.parent = par3GenLayer;
        this.separate = separate;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int i1 = par1 - 1;
        int j1 = par2 - 1;
        int k1 = par3 + 2;
        int l1 = par4 + 2;
        int[] aint = this.parent.getInts(i1, j1, k1, l1);
        this.taste(aint, k1 * l1);

        int[] aint1;
        for(aint1 = IntCache.getIntCache(par3 * par4); aint1 == aint; aint1 = IntCache.getIntCache(par3 * par4)) {
        }

        this.poison(aint1, par3 * par4);
        this.taste(aint, k1 * l1);

        for(int i2 = 0; i2 < par4; ++i2) {
            for(int j2 = 0; j2 < par3; ++j2) {
                int k2 = aint[j2 + 0 + (i2 + 0) * k1];
                int l2 = aint[j2 + 2 + (i2 + 0) * k1];
                int i3 = aint[j2 + 0 + (i2 + 2) * k1];
                int j3 = aint[j2 + 2 + (i2 + 2) * k1];
                int k3 = aint[j2 + 1 + (i2 + 1) * k1];
                this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                if (isOceanic(k3) && (!isOceanic(k2) || !isOceanic(l2) || !isOceanic(i3) || !isOceanic(j3))) {
                    int l3 = 1;
                    int i4 = 0;
                    if (!isOceanic(k2) && this.nextInt(l3++) == 0 && (!this.separate || this.acceptableNeighbors(k2, aint, i2, j2, k1))) {
                        i4 = k2;
                    }

                    if (!isOceanic(l2) && this.nextInt(l3++) == 0 && (!this.separate || this.acceptableNeighbors(l2, aint, i2, j2, k1))) {
                        i4 = l2;
                    }

                    if (!isOceanic(i3) && this.nextInt(l3++) == 0 && (!this.separate || this.acceptableNeighbors(i3, aint, i2, j2, k1))) {
                        i4 = i3;
                    }

                    if (!isOceanic(j3) && this.nextInt(l3++) == 0 && (!this.separate || this.acceptableNeighbors(j3, aint, i2, j2, k1))) {
                        i4 = j3;
                    }

                    if (this.nextInt(3) == 0) {
                        aint1[j2 + i2 * par3] = i4;
                    } else {
                        aint1[j2 + i2 * par3] = k3;
                    }
                } else if (!isOceanic(k3) && (isOceanic(k2) || isOceanic(l2) || isOceanic(i3) || isOceanic(j3))) {
                    if (this.nextInt(5) == 0) {
                        if (isOceanic(k2)) {
                            aint1[j2 + i2 * par3] = k2;
                        }

                        if (isOceanic(l2)) {
                            aint1[j2 + i2 * par3] = l2;
                        }

                        if (isOceanic(i3)) {
                            aint1[j2 + i2 * par3] = i3;
                        }

                        if (isOceanic(j3)) {
                            aint1[j2 + i2 * par3] = j3;
                        }
                    } else {
                        aint1[j2 + i2 * par3] = k3;
                    }
                } else {
                    aint1[j2 + i2 * par3] = k3;
                }

                if (aint1[j2 + i2 * par3] < 0) {
                    throw new RuntimeException("i2 " + i2 + " j2 " + j2 + " k2 " + k2 + " l2 " + l2 + " i3 " + i3 + " j3 " + j3 + " k3 " + k3 + " orig " + aint[j2 + 1 + (i2 + 1) * k1]);
                }
            }
        }

        this.taste(aint1, par3 * par4);
        return aint1;
    }
}
