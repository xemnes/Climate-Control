//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;

public abstract class GenLayerNeighborTesting extends GenLayerPack {
    public GenLayerNeighborTesting(long seed) {
        super(seed);
    }

    public final boolean acceptableNeighbors(int newValue, int[] array, int i2, int j2, int k1) {
        if (isOceanic(newValue)) {
            return true;
        } else {
            int upper = newValue + 1;
            int lower = newValue - 1;
            int neighbor = array[j2 + 0 + (i2 + 0) * k1];
            if (!isOceanic(neighbor)) {
                if (neighbor < lower) {
                    return false;
                }

                if (neighbor > upper) {
                    return false;
                }
            }

            neighbor = array[j2 + 1 + (i2 + 0) * k1];
            if (!isOceanic(neighbor)) {
                if (neighbor < lower) {
                    return false;
                }

                if (neighbor > upper) {
                    return false;
                }
            }

            neighbor = array[j2 + 2 + (i2 + 0) * k1];
            if (!isOceanic(neighbor)) {
                if (neighbor < lower) {
                    return false;
                }

                if (neighbor > upper) {
                    return false;
                }
            }

            neighbor = array[j2 + 0 + (i2 + 1) * k1];
            if (!isOceanic(neighbor)) {
                if (neighbor < lower) {
                    return false;
                }

                if (neighbor > upper) {
                    return false;
                }
            }

            neighbor = array[j2 + 2 + (i2 + 1) * k1];
            if (!isOceanic(neighbor)) {
                if (neighbor < lower) {
                    return false;
                }

                if (neighbor > upper) {
                    return false;
                }
            }

            neighbor = array[j2 + 0 + (i2 + 2) * k1];
            if (!isOceanic(neighbor)) {
                if (neighbor < lower) {
                    return false;
                }

                if (neighbor > upper) {
                    return false;
                }
            }

            neighbor = array[j2 + 1 + (i2 + 2) * k1];
            if (!isOceanic(neighbor)) {
                if (neighbor < lower) {
                    return false;
                }

                if (neighbor > upper) {
                    return false;
                }
            }

            neighbor = array[j2 + 2 + (i2 + 2) * k1];
            if (!isOceanic(neighbor)) {
                if (neighbor < lower) {
                    return false;
                }

                if (neighbor > upper) {
                    return false;
                }
            }

            return true;
        }
    }
}
