//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerTemperClimate extends GenLayer {
    public GenLayerTemperClimate(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    public int[] getInts(int p_151626_1_, int p_151626_2_, int p_151626_3_, int p_151626_4_) {
        int i1 = p_151626_1_ - 1;
        int j1 = p_151626_2_ - 1;
        int k1 = 1 + p_151626_3_ + 1;
        int l1 = 1 + p_151626_4_ + 1;
        int[] aint = this.parent.getInts(i1, j1, k1, l1);
        int[] aint1 = IntCache.getIntCache(p_151626_3_ * p_151626_4_);

        for(int i2 = 0; i2 < p_151626_4_; ++i2) {
            for(int j2 = 0; j2 < p_151626_3_; ++j2) {
                this.initChunkSeed((long)(j2 + p_151626_1_), (long)(i2 + p_151626_2_));
                int k2 = aint[j2 + 1 + (i2 + 1) * k1];
                int l2;
                int i3;
                int j3;
                int k3;
                boolean nearHot;
                boolean nearCold;
                if (k2 == 2) {
                    l2 = aint[j2 + 1 + (i2 + 1 - 1) * k1];
                    i3 = aint[j2 + 1 + 1 + (i2 + 1) * k1];
                    j3 = aint[j2 + 1 - 1 + (i2 + 1) * k1];
                    k3 = aint[j2 + 1 + (i2 + 1 + 1) * k1];
                    nearHot = l2 == 1 || i3 == 1 || j3 == 1 || k3 == 1;
                    nearCold = l2 == 4 || i3 == 4 || j3 == 4 || k3 == 4;
                    if (nearCold && !nearHot) {
                        k2 = 3;
                    }
                }

                if (k2 == 2) {
                    l2 = aint[j2 + 1 + (i2 + 1 - 1) * k1];
                    i3 = aint[j2 + 1 + 1 + (i2 + 1) * k1];
                    j3 = aint[j2 + 1 - 1 + (i2 + 1) * k1];
                    k3 = aint[j2 + 1 + (i2 + 1 + 1) * k1];
                    nearHot = l2 == 1 || i3 == 1 || j3 == 1 || k3 == 1;
                    nearCold = l2 == 4 || i3 == 4 || j3 == 4 || k3 == 4;
                    if (!nearCold && nearHot) {
                        k2 = 2;
                    }
                }

                aint1[j2 + i2 * p_151626_3_] = k2;
            }
        }

        return aint1;
    }
}
