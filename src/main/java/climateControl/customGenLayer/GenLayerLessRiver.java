//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLessRiver extends GenLayerPack {
    private static final String __OBFID = "CL_00000565";
    private final int percentageReduction;

    public GenLayerLessRiver(long par1, GenLayer par3GenLayer, int percentageReduction) {
        super(par1);
        this.parent = par3GenLayer;
        this.percentageReduction = percentageReduction;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = this.parent.getInts(par1, par2, par3, par4);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for(int i1 = 0; i1 < par4; ++i1) {
            for(int j1 = 0; j1 < par3; ++j1) {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                aint1[j1 + i1 * par3] = aint[j1 + i1 * par3] > 0 ? this.nextInt(299999) + 2 : 0;
                if (aint1[j1 + i1 * par3] > 0 && this.nextInt(100) < this.percentageReduction) {
                    aint1[j1 + i1 * par3] = 0;
                }
            }
        }

        return aint1;
    }
}
