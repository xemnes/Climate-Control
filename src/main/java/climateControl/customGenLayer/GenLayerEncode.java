//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import java.util.HashMap;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerEncode extends GenLayerPack {
    private HashMap<Integer, Integer> encoding;

    public GenLayerEncode(long p_i45480_1_, GenLayer p_i45480_3_) {
        super(p_i45480_1_);
        this.parent = p_i45480_3_;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = this.parent.getInts(par1, par2, par3, par4);
        int[] aint1 = IntCache.getIntCache(par3 * par4);
        this.encoding = new HashMap(par3 * par4);

        for(int i2 = 0; i2 < par4; ++i2) {
            for(int j2 = 0; j2 < par3; ++j2) {
                this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                int code = this.nextInt(Integer.MAX_VALUE);
                aint1[j2 + i2 * par3] = code;
                this.encoding.put(code, aint[j2 + i2 * par3]);
            }
        }

        return aint1;
    }

    public void decode(int[] coded) {
        for(int i = 0; i < coded.length; ++i) {
            coded[i] = (Integer)this.encoding.get(coded[i]);
        }

    }
}
