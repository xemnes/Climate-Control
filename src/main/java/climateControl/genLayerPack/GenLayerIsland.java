//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.genLayerPack;

import net.minecraft.world.gen.layer.IntCache;

public class GenLayerIsland extends GenLayerPack {
    private static final String __OBFID = "CL_00000558";
    private final int squaresPerLand;

    public GenLayerIsland(long par1, int squaresPerLand) {
        super(par1);
        this.squaresPerLand = squaresPerLand;
    }

    public GenLayerIsland(long par1) {
        this(par1, 10);
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = IntCache.getIntCache(par3 * par4);

        for(int i1 = 0; i1 < par4; ++i1) {
            for(int j1 = 0; j1 < par3; ++j1) {
                this.initChunkSeed((long)(par1 + j1), (long)(par2 + i1));
                aint[j1 + i1 * par3] = this.nextInt(this.squaresPerLand) == 0 ? 1 : 0;
            }
        }

        if (par1 > -par3 && par1 <= 0 && par2 > -par4 && par2 <= 0) {
        }

        return aint;
    }
}
