//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.ClimateControl;
import climateControl.api.ClimateControlSettings;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.Numbered;
import com.Zeno410Utils.PlaneLocation;
import com.Zeno410Utils.Zeno410Logger;
import java.util.HashSet;
import java.util.logging.Logger;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerIdentifiedClimate extends GenLayerPack {
    private final int hotLevel;
    private final int warmLevel;
    private final int coldLevel;
    private final int totalLevel;
    public static Logger logger = (new Zeno410Logger("IdentifiedClimate")).logger();

    private GenLayerIdentifiedClimate(long par1, GenLayer par3GenLayer, int hot, int warm, int cold, int snow) {
        super(par1);
        this.parent = par3GenLayer;
        this.hotLevel = hot;
        this.warmLevel = hot + warm;
        this.coldLevel = hot + warm + cold;
        this.totalLevel = hot + warm + cold + snow;
    }

    public GenLayerIdentifiedClimate(long seed, GenLayer genLayer, ClimateControlSettings settings) {
        this(seed, genLayer, (Integer)settings.hotIncidence.value(), (Integer)settings.warmIncidence.value(), (Integer)settings.coolIncidence.value(), (Integer)settings.snowyIncidence.value());
    }

    public int[] getInts(int x0, int z0, int xSize, int zSize) {
        new Prioritizer();
        int parentX0 = x0 - 2;
        int parentZ0 = z0 - 2;
        int parentXSize = xSize + 4;
        int parentZSize = zSize + 4;
        int[] parentIds = this.parent.getInts(parentX0, parentZ0, parentXSize, parentZSize);
        int[] parentVals = this.getRawClimates(parentIds, parentX0, parentZ0, parentXSize, parentZSize);
        int[] vals = new int[xSize * zSize];

        int parentZ;
        int parentX;
        int k2;
        for(parentZ = 0; parentZ < parentZSize; ++parentZ) {
            for(parentX = 0; parentX < parentXSize; ++parentX) {
                k2 = parentVals[parentX + parentZ * parentXSize];
                this.setFromParentCoords(k2, parentX, parentZ, xSize, zSize, vals);
                if (k2 > 4 && k2 != 24 && k2 != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND) && ClimateControl.testing) {
                    ClimateControl.logger.info("GenLayerSmoothClimate " + k2);
                    throw new RuntimeException("GenLayerSmoothClimate " + k2);
                }

                this.initChunkSeed((long)(parentX + parentX0), (long)(parentZ + parentZ0));
                if (k2 != 1 && k2 == 4) {
                }
            }
        }

        parentVals = null;

        for(parentZ = 2; parentZ < parentZSize - 2; ++parentZ) {
            for(parentX = 2; parentX < parentXSize - 2; ++parentX) {
                k2 = parentIds[parentX + parentZ * parentXSize];
                if (k2 >= 256) {
                    vals[parentX - 2 + (parentZ - 2) * xSize] += k2 * 4;
                }
            }
        }

        return vals;
    }

    private final void setFromParentCoords(int value, int px, int pz, int xSize, int zSize, int[] vals) {
        if (px >= 2 && pz >= 2 && px - 1 <= xSize && pz - 1 <= zSize) {
            vals[px - 2 + (pz - 2) * xSize] = value;
        }
    }

    private final void coolerThanHot(int px, int pz, int xSize, int zSize, int[] vals) {
        if (px >= 2 && pz >= 2 && px - 1 <= xSize && pz - 1 <= zSize) {
            int value = vals[px - 2 + (pz - 2) * xSize];
            if (value == 1) {
                vals[px - 2 + (pz - 2) * xSize] = 2;
            }

        }
    }

    private final void warmerThanSnowy(int px, int pz, int xSize, int zSize, int[] vals) {
        if (px >= 2 && pz >= 2 && px - 1 <= xSize && pz - 1 <= zSize) {
            int value = vals[px - 2 + (pz - 2) * xSize];
            if (value == 4) {
                vals[px - 2 + (pz - 2) * xSize] = 3;
            }

        }
    }

    private final void coolerThanWarm(int px, int pz, int xSize, int zSize, int[] vals) {
        if (px >= 2 && pz >= 2 && px - 1 <= xSize && pz - 1 <= zSize) {
            int value = vals[px - 2 + (pz - 2) * xSize];
            if (value == 1) {
                vals[px - 2 + (pz - 2) * xSize] = 3;
            }

            if (value == 2) {
                vals[px - 2 + (pz - 2) * xSize] = 3;
            }

        }
    }

    private final void warmerThanCool(int px, int pz, int xSize, int zSize, int[] vals) {
        if (px >= 2 && pz >= 2 && px - 1 <= xSize && pz - 1 <= zSize) {
            int value = vals[px - 2 + (pz - 2) * xSize];
            if (value == 4) {
                vals[px - 2 + (pz - 2) * xSize] = 2;
            }

            if (value == 3) {
                vals[px - 2 + (pz - 2) * xSize] = 2;
            }

        }
    }

    private int[] getRawClimates(int[] parent, int par1, int par2, int par3, int par4) {
        int[] aint1 = new int[par3 * par4];

        int i2;
        for(i2 = 0; i2 < par4; ++i2) {
            for(int j2 = 0; j2 < par3; ++j2) {
                int k2 = parent[j2 + i2 * par3];
                this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                if (k2 == 0) {
                    aint1[j2 + i2 * par3] = 0;
                } else {
                    int l2 = this.nextInt(this.totalLevel);
                    byte b0;
                    if (l2 < this.hotLevel) {
                        b0 = 1;
                    } else if (l2 < this.warmLevel) {
                        b0 = 2;
                    } else if (l2 < this.coldLevel) {
                        b0 = 3;
                    } else {
                        b0 = 4;
                    }

                    aint1[j2 + i2 * par3] = b0;
                }
            }
        }

        for(i2 = 0; i2 < par3 * par4; ++i2) {
            if (aint1[i2] > 255) {
                throw new RuntimeException();
            }
        }

        return aint1;
    }

    private class Prioritizer {
        HashSet<Integer> numbers;
        boolean duplicate;

        private Prioritizer() {
            this.numbers = new HashSet();
            this.duplicate = false;
        }

        int next() {
            int number = GenLayerIdentifiedClimate.this.nextInt(1000000000);
            number = GenLayerIdentifiedClimate.this.nextInt(1000000000);
            if (!this.numbers.contains(number)) {
                this.numbers.add(number);
                return number;
            } else {
                this.duplicate = true;
                throw new RuntimeException();
            }
        }
    }

    private class Cold extends ExtremeTemp {
        Cold(Prioritizer prioritizer, int x, int z) {
            super(prioritizer, x, z);
        }

        public String toString() {
            return "Cold " + this.x() + "," + this.z();
        }

        void adjust(int[] parentVals, int xSize, int zSize, int[] vals) {
            GenLayerIdentifiedClimate.this.setFromParentCoords(4, this.x(), this.z(), xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanWarm(this.x() - 1, this.z(), xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanWarm(this.x(), this.z() - 1, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanWarm(this.x() + 1, this.z(), xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanWarm(this.x(), this.z() + 1, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanHot(this.x() - 2, this.z(), xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanHot(this.x() - 1, this.z() - 1, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanHot(this.x(), this.z() - 2, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanHot(this.x() + 1, this.z() - 1, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanHot(this.x() + 2, this.z(), xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanHot(this.x() + 1, this.z() + 1, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanHot(this.x(), this.z() + 2, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.coolerThanHot(this.x() - 1, this.z() + 1, xSize, zSize, vals);
        }
    }

    private class Hot extends ExtremeTemp {
        Hot(Prioritizer prioritizer, int x, int z) {
            super(prioritizer, x, z);
        }

        public String toString() {
            return "Hot  " + this.x() + "," + this.z();
        }

        void adjust(int[] parentVals, int xSize, int zSize, int[] vals) {
            GenLayerIdentifiedClimate.this.setFromParentCoords(1, this.x(), this.z(), xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanCool(this.x() - 1, this.z(), xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanCool(this.x(), this.z() - 1, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanCool(this.x() + 1, this.z(), xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanCool(this.x(), this.z() + 1, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanSnowy(this.x() - 2, this.z(), xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanSnowy(this.x() - 1, this.z() - 1, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanSnowy(this.x(), this.z() - 2, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanSnowy(this.x() + 1, this.z() - 1, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanSnowy(this.x() + 2, this.z(), xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanSnowy(this.x() + 1, this.z() + 1, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanSnowy(this.x(), this.z() + 2, xSize, zSize, vals);
            GenLayerIdentifiedClimate.this.warmerThanSnowy(this.x() - 1, this.z() + 1, xSize, zSize, vals);
        }
    }

    private abstract class ExtremeTemp extends Numbered<PlaneLocation> {
        ExtremeTemp(Prioritizer prioritizer, int x, int z) {
            super(prioritizer.next(), new PlaneLocation(x, z));
        }

        final int x() {
            return ((PlaneLocation)this.item()).x();
        }

        final int z() {
            return ((PlaneLocation)this.item()).z();
        }

        abstract void adjust(int[] var1, int var2, int var3, int[] var4);
    }
}
