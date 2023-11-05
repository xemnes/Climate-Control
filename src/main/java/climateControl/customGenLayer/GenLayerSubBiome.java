//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import climateControl.generator.BiomeSwapper;
import climateControl.generator.SubBiomeChooser;
import com.Zeno410Utils.IntRandomizer;
import com.Zeno410Utils.Zeno410Logger;
import java.util.logging.Logger;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSubBiome extends GenLayerPack {
    public static Logger logger = (new Zeno410Logger("GenLayerSubBiome")).logger();
    private GenLayer rivers;
    private final SubBiomeChooser subBiomeChooser;
    private final BiomeSwapper mBiomes;
    private IntRandomizer randomCallback = new IntRandomizer() {
        public int nextInt(int maximum) {
            return GenLayerSubBiome.this.nextInt(maximum);
        }
    };
    private static final String __OBFID = "CL_00000563";

    public GenLayerSubBiome(long p_i45479_1_, GenLayer biomes, GenLayer rivers, SubBiomeChooser subBiomeChooser, BiomeSwapper mBiomes, boolean doBoP) {
        super(p_i45479_1_);
        this.parent = biomes;
        this.rivers = rivers;
        this.subBiomeChooser = subBiomeChooser;
        this.mBiomes = mBiomes;
        this.initChunkSeed(0L, 0L);
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] biomeVals = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] riverVals = this.rivers.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] aint2 = IntCache.getIntCache(par3 * par4);
        this.poison(aint2, par3 * par4);

        int i1;
        for(i1 = 0; i1 < (par3 + 2) * (par4 + 2); ++i1) {
            if (biomeVals[i1] > 256) {
                throw new RuntimeException("" + biomeVals[i1]);
            }
        }

        for(i1 = 0; i1 < par4; ++i1) {
            for(int j1 = 0; j1 < par3; ++j1) {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int biomeVal = biomeVals[j1 + 1 + (i1 + 1) * (par3 + 2)];
                int riverVal = riverVals[j1 + 1 + (i1 + 1) * (par3 + 2)];
                boolean flag = (riverVal - 2) % 29 == 0;
                if (biomeVal != 0 && riverVal >= 2 && (riverVal - 2) % 29 == 1 && biomeVal < 128) {
                    aint2[j1 + i1 * par3] = this.mBiomes.replacement(biomeVal);
                } else if (this.nextInt(3) != 0 && !flag) {
                    aint2[j1 + i1 * par3] = biomeVal;
                } else {
                    int i2 = this.subBiomeChooser.subBiome(biomeVal, this.randomCallback, j1 + par1, i1 + par2);
                    int k2;
                    if (flag && i2 != biomeVal) {
                        k2 = this.mBiomes.replacement(i2);
                        if (k2 != i2) {
                            i2 = k2;
                        } else {
                            i2 = biomeVal;
                        }
                    }

                    if (i2 == biomeVal) {
                        aint2[j1 + i1 * par3] = biomeVal;
                    } else {
                        int j2 = biomeVals[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                        k2 = biomeVals[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                        int l2 = biomeVals[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                        int i3 = biomeVals[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
                        int j3 = 0;
                        if (compareBiomesById(j2, biomeVal)) {
                            ++j3;
                        }

                        if (compareBiomesById(k2, biomeVal)) {
                            ++j3;
                        }

                        if (compareBiomesById(l2, biomeVal)) {
                            ++j3;
                        }

                        if (compareBiomesById(i3, biomeVal)) {
                            ++j3;
                        }

                        if (j3 >= 3) {
                            aint2[j1 + i1 * par3] = i2;
                        } else {
                            aint2[j1 + i1 * par3] = biomeVal;
                        }
                    }
                }
            }
        }

        this.taste(aint2, par3 * par4);
        return aint2;
    }
}
