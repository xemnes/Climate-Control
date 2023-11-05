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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSmoothClimate extends GenLayerPack {
    private final boolean extremeSeparation;

    public GenLayerSmoothClimate(long par1, GenLayer par3GenLayer, ClimateControlSettings settings) {
        super(par1);
        this.parent = par3GenLayer;
        this.extremeSeparation = (Boolean)settings.extremeClimateSeparation.value();
    }

    public int[] getInts(int x0, int z0, int xSize, int zSize) {
        ArrayList<ExtremeTemp> changes = new ArrayList();
        Prioritizer prioritizer = new Prioritizer();
        int parentX0 = x0 - 2;
        int parentZ0 = z0 - 2;
        int parentXSize = xSize + 4;
        int parentZSize = zSize + 4;
        int[] parentVals = this.parent.getInts(parentX0, parentZ0, parentXSize, parentZSize);
        int[] parentClimates = IntCache.getIntCache(parentXSize * parentZSize);

        int parentZ;
        int parentX;
        int k2;
        for(parentZ = 0; parentZ < parentZSize; ++parentZ) {
            for(parentZ = 0; parentZ < parentXSize; ++parentZ) {
                parentX = parentVals[parentZ + parentZ * parentXSize];
                int climate = 0;
                if (isOceanic(parentX)) {
                    k2 = parentX;
                } else {
                    k2 = parentX % 4;
                    if (k2 == 0) {
                        k2 = 4;
                    }
                }

                parentClimates[parentZ + parentZ * parentXSize] = k2;
            }
        }

        int[] vals = IntCache.getIntCache(xSize * zSize);

        for(parentZ = 0; parentZ < parentZSize; ++parentZ) {
            for(parentX = 0; parentX < parentXSize; ++parentX) {
                k2 = parentClimates[parentX + parentZ * parentXSize];
                this.setFromParentCoords(k2, parentX, parentZ, xSize, zSize, vals);
                if (k2 > 4 && k2 != 24 && k2 != Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND) && k2 != Biome.getIdForBiome(Biomes.FROZEN_OCEAN) && ClimateControl.testing) {
                    ClimateControl.logger.info("GenLayerSmoothClimate " + k2);
                    throw new RuntimeException("GenLayerSmoothClimate " + k2);
                }

                this.initChunkSeed((long)(parentX + parentX0), (long)(parentZ + parentZ0));
                if (k2 == 1) {
                    changes.add(new Hot(prioritizer, parentX, parentZ));
                } else if (k2 == 4) {
                    changes.add(new Cold(prioritizer, parentX, parentZ));
                }
            }
        }

        Collections.sort(changes, Numbered.comparator(PlaneLocation.topLefttoBottomRight()));
        parentClimates = null;
        Iterator var19 = changes.iterator();

        while(var19.hasNext()) {
            ExtremeTemp temp = (ExtremeTemp)var19.next();
            if (this.extremeSeparation) {
                temp.adjustExtreme((int[])parentClimates, xSize, zSize, vals);
            } else {
                temp.adjust((int[])parentClimates, xSize, zSize, vals);
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

    private class Prioritizer {
        HashSet<Integer> numbers;
        boolean duplicate;

        private Prioritizer() {
            this.numbers = new HashSet();
            this.duplicate = false;
        }

        int next() {
            int number = GenLayerSmoothClimate.this.nextInt(1000000000);
            number = GenLayerSmoothClimate.this.nextInt(1000000000);
            if (!this.numbers.contains(number)) {
                this.numbers.add(number);
            } else {
                this.duplicate = true;
            }

            return number;
        }
    }

    private class Cold extends ExtremeTemp {
        Cold(Prioritizer prioritizer, int x, int z) {
            super(prioritizer, x, z);
        }

        void adjust(int[] parentVals, int xSize, int zSize, int[] vals) {
            GenLayerSmoothClimate.this.setFromParentCoords(4, this.x(), this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x() - 1, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x(), this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x() + 1, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x(), this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 2, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 1, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x(), this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 1, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 2, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 1, this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x(), this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 1, this.z() + 1, xSize, zSize, vals);
        }

        void adjustExtreme(int[] parentVals, int xSize, int zSize, int[] vals) {
            GenLayerSmoothClimate.this.setFromParentCoords(4, this.x(), this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x() - 1, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x(), this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x() + 1, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x(), this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x() - 1, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x() + 1, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x() + 1, this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanWarm(this.x() - 1, this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 2, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 2, this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 2, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 2, this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 2, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x(), this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 1, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 1, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 2, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 2, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 2, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 2, this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 2, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 2, this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 2, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 2, this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() - 1, this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x(), this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 1, this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.coolerThanHot(this.x() + 2, this.z() + 2, xSize, zSize, vals);
        }
    }

    private class Hot extends ExtremeTemp {
        Hot(Prioritizer prioritizer, int x, int z) {
            super(prioritizer, x, z);
        }

        void adjust(int[] parentVals, int xSize, int zSize, int[] vals) {
            GenLayerSmoothClimate.this.setFromParentCoords(1, this.x(), this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x() - 1, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x(), this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x() + 1, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x(), this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 2, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 1, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x(), this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 1, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 2, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 1, this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x(), this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 1, this.z() + 1, xSize, zSize, vals);
        }

        void adjustExtreme(int[] parentVals, int xSize, int zSize, int[] vals) {
            GenLayerSmoothClimate.this.setFromParentCoords(1, this.x(), this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x() - 1, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x(), this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x() + 1, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x(), this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x() - 1, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x() + 1, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x() + 1, this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanCool(this.x() - 1, this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 2, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 2, this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 2, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 2, this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 2, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x(), this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 1, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 1, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 2, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 2, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 2, this.z(), xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 2, this.z() + 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 2, this.z() - 1, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 2, this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 2, this.z() - 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 2, this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() - 1, this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x(), this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 1, this.z() + 2, xSize, zSize, vals);
            GenLayerSmoothClimate.this.warmerThanSnowy(this.x() + 2, this.z() + 2, xSize, zSize, vals);
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

        abstract void adjustExtreme(int[] var1, int var2, int var3, int[] var4);
    }
}
