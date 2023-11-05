//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSmoothZoom extends GenLayerPack {
    private static final String __OBFID = "CL_00000572";

    public GenLayerSmoothZoom(long par1, GenLayer par3GenLayer) {
        super(par1);
        super.parent = par3GenLayer;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int i1 = par1 >> 1;
        int j1 = par2 >> 1;
        int k1 = (par3 >> 1) + 2;
        int l1 = (par4 >> 1) + 2;
        int[] aint = this.parent.getInts(i1, j1, k1, l1);
        int i2 = k1 - 1 << 1;
        int j2 = l1 - 1 << 1;
        int[] aint1 = IntCache.getIntCache(i2 * j2);

        int l2;
        int k2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        for(k2 = 0; k2 < l1 - 1; ++k2) {
            l2 = (k2 << 1) * i2;
            i3 = 0;
            j3 = aint[i3 + 0 + (k2 + 0) * k1];

            for(k3 = aint[i3 + 0 + (k2 + 1) * k1]; i3 < k1 - 1; ++i3) {
                this.initChunkSeed((long)(i3 + i1 << 1), (long)(k2 + j1 << 1));
                l3 = aint[i3 + 1 + (k2 + 0) * k1];
                i4 = aint[i3 + 1 + (k2 + 1) * k1];
                aint1[l2] = j3;
                aint1[l2++ + i2] = this.selectRandom(new int[]{j3, k3});
                aint1[l2] = this.selectRandom(new int[]{j3, l3});
                j3 = l3;
                k3 = i4;
            }
        }

        for(k2 = 0; k2 < l1 - 1; ++k2) {
            l2 = (k2 << 1) * i2;
            i3 = 0;
            j3 = aint[i3 + 0 + (k2 + 0) * k1];

            for(k3 = aint[i3 + 0 + (k2 + 1) * k1]; i3 < k1 - 1; ++i3) {
                this.initChunkSeed((long)(i3 + i1), (long)(k2 + j1));
                l3 = aint[i3 + 1 + (k2 + 0) * k1];
                i4 = aint[i3 + 1 + (k2 + 1) * k1];
                aint1[l2++ + i2] = this.selectModeOrRandom(j3, l3, k3, i4);
                j3 = l3;
                k3 = i4;
            }
        }

        int[] aint2 = IntCache.getIntCache(par3 * par4);

        for(l2 = 0; l2 < par4; ++l2) {
            System.arraycopy(aint1, (l2 + (par2 & 1)) * i2 + (par1 & 1), aint2, l2 * par3, par3);
        }

        return aint2;
    }
}
