//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.genLayerPack;

import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerZoom extends GenLayerPack {
    private static final String __OBFID = "CL_00000572";
    private boolean biomesOnly = false;

    public GenLayerZoom(long par1, GenLayer par3GenLayer) {
        super(par1);
        super.parent = par3GenLayer;
    }

    public GenLayerZoom(long par1, GenLayer par3GenLayer, boolean biomesOnly) {
        super(par1);
        super.parent = par3GenLayer;
        this.biomesOnly = biomesOnly;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int i1 = par1 >> 1;
        int j1 = par2 >> 1;
        int k1 = (par3 >> 1) + 2;
        int l1 = (par4 >> 1) + 2;
        int[] aint = this.parent.getInts(i1, j1, k1, l1);
        int i2 = k1 - 1 << 1;
        int j2 = l1 - 1 << 1;
        int[] aint1 = new int[i2 * j2];
        this.poison(aint1, i2 * j2);
        int i;
        if (this.biomesOnly) {
            for(i = 0; i < k1; ++i) {
                for(i = 0; i < l1; ++i) {
                    if (aint[i * l1 + i] > 256) {
                        throw new RuntimeException("biome ID" + aint[i * par3 + i] + " " + this.parent.toString());
                    }
                }
            }
        }

        int l2;
        for(i = 0; i < l1 - 1; ++i) {
            l2 = (i << 1) * i2;
            i = 0;
            int j3 = aint[i + 0 + (i + 0) * k1];

            for(int k3 = aint[i + 0 + (i + 1) * k1]; i < k1 - 1; ++i) {
                this.initChunkSeed((long)(i + i1 << 1), (long)(i + j1 << 1));
                int l3 = aint[i + 1 + (i + 0) * k1];
                int i4 = aint[i + 1 + (i + 1) * k1];
                if (this.biomesOnly) {
                    if (j3 > 256) {
                        throw new RuntimeException("j3 " + j3 + " l2 " + l2);
                    }

                    if (k3 > 256) {
                        throw new RuntimeException("k3 " + k3 + " l2 " + l2);
                    }

                    if (l3 > 256) {
                        throw new RuntimeException("l3 " + l3 + " l2 " + l2);
                    }

                    if (l3 > 256) {
                        throw new RuntimeException("i4 " + i4 + " l2 " + l2);
                    }

                    if (j3 < -1) {
                        throw new RuntimeException("j3 " + j3 + " l2 " + l2);
                    }

                    if (k3 < -1) {
                        throw new RuntimeException("k3 " + k3 + " l2 " + l2);
                    }

                    if (l3 < -1) {
                        throw new RuntimeException("l3 " + l3 + " l2 " + l2);
                    }

                    if (l3 < -1) {
                        throw new RuntimeException("i4 " + i4 + " l2 " + l2);
                    }
                }

                aint1[l2] = j3;
                aint1[l2++ + i2] = this.selectRandom(new int[]{j3, k3});
                aint1[l2] = this.selectRandom(new int[]{j3, l3});
                aint1[l2++ + i2] = this.selectModeOrRandom(j3, l3, k3, i4);
                j3 = l3;
                k3 = i4;
            }
        }

        int[] aint2 = new int[par3 * par4];
        this.poison(aint2, par3 * par4);

        for(l2 = 0; l2 < par4; ++l2) {
            System.arraycopy(aint1, (l2 + (par2 & 1)) * i2 + (par1 & 1), aint2, l2 * par3, par3);
        }

        if (this.biomesOnly) {
            for(i = 0; i < par3 * par4; ++i) {
                if (aint2[i] > 256) {
                    throw new RuntimeException();
                }
            }
        }

        this.taste(aint2, par3 * par4);
        return aint2;
    }

    public static GenLayer magnify(long par0, GenLayer par2GenLayer, int par3) {
        Object object = par2GenLayer;

        for(int k = 0; k < par3; ++k) {
            object = new GenLayerZoom(par0 + (long)k, (GenLayer)object);
        }

        return (GenLayer)object;
    }
}
