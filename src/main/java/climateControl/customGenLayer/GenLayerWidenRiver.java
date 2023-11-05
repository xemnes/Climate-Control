//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerWidenRiver extends GenLayerPack {
    private static final String __OBFID = "CL_00000566";

    public GenLayerWidenRiver(long par1, GenLayer par3GenLayer) {
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

        int i2;
        int j2;
        for(i2 = 0; i2 < par4; ++i2) {
            for(j2 = 0; j2 < par3; ++j2) {
                aint1[j2 + i2 * par3] = 0;
            }
        }

        for(i2 = 0; i2 < par4; ++i2) {
            for(j2 = 0; j2 < par3; ++j2) {
                aint1[j2 + i2 * par3] = aint[j2 + 1 + (i2 + 1) * k1];
                int riverID = Biome.getIdForBiome(Biomes.RIVER);
                if (aint[j2 + 0 + (i2 + 1) * k1] == riverID) {
                    aint1[j2 + i2 * par3] = riverID;
                }

                if (aint[j2 + 2 + (i2 + 1) * k1] == riverID) {
                    aint1[j2 + i2 * par3] = riverID;
                }

                if (aint[j2 + 1 + (i2 + 0) * k1] == riverID) {
                    aint1[j2 + i2 * par3] = riverID;
                }

                if (aint[j2 + 1 + (i2 + 2) * k1] == riverID) {
                    aint1[j2 + i2 * par3] = riverID;
                }

                riverID = Biome.getIdForBiome(Biomes.FROZEN_RIVER);
                if (aint[j2 + 0 + (i2 + 1) * k1] == riverID) {
                    aint1[j2 + i2 * par3] = riverID;
                }

                if (aint[j2 + 2 + (i2 + 1) * k1] == riverID) {
                    aint1[j2 + i2 * par3] = riverID;
                }

                if (aint[j2 + 1 + (i2 + 0) * k1] == riverID) {
                    aint1[j2 + i2 * par3] = riverID;
                }

                if (aint[j2 + 1 + (i2 + 2) * k1] == riverID) {
                    aint1[j2 + i2 * par3] = riverID;
                }
            }
        }

        return aint1;
    }

    private int riverFilter(int p_151630_1_) {
        return p_151630_1_ >= 2 ? 2 + (p_151630_1_ & 1) : p_151630_1_;
    }
}
