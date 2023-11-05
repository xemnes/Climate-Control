//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import com.Zeno410Utils.IntRandomizer;

public class LandWaterChoices {
    int original;
    boolean isWater;
    UpToFour land = new UpToFour();
    UpToFour water = new UpToFour();

    LandWaterChoices() {
    }

    void setOriginal(int original, boolean isWater) {
        this.original = original;
        this.isWater = isWater;
        this.land.clear();
        this.water.clear();
    }

    int totalItems() {
        int result = this.water.size();
        result += this.land.size();
        return result;
    }

    void add(int value, boolean isAddedWater) {
        this.item(isAddedWater).add(value);
    }

    boolean equal() {
        return this.water.size() == 2;
    }

    boolean isChoiceWater() {
        return this.land.size() < 2;
    }

    UpToFour item(boolean waterItem) {
        return waterItem ? this.water : this.land;
    }

    int mostCommon(IntRandomizer randomizer) {
        int oldCount = this.water.count;
        if (oldCount < 2) {
            return !this.isWater ? this.original : this.land.items[randomizer.nextInt(this.land.count)];
        } else if (oldCount == 2) {
            return this.original;
        } else {
            return !this.isWater ? this.water.items[randomizer.nextInt(this.water.count)] : this.original;
        }
    }

    private class UpToFour {
        int count;
        int value;
        int[] items;

        private UpToFour() {
            this.count = 0;
            this.items = new int[4];
        }

        void setValue(int newValue) {
            this.value = newValue;
            this.count = 0;
        }

        void clear() {
            this.count = 0;
        }

        void add(int added) {
            this.items[this.count] = added;
            ++this.count;
        }

        int size() {
            return this.count;
        }
    }
}
