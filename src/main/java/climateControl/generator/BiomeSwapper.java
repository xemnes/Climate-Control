//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.generator;

import climateControl.api.BiomeSettings;
import java.util.ArrayList;
import java.util.Iterator;

public class BiomeSwapper {
    private int[] swaps = new int[256];

    public BiomeSwapper() {
        for(int i = 0; i < 256; this.swaps[i] = i++) {
        }

    }

    public void clear() {
        for(int i = 0; i < this.swaps.length; this.swaps[i] = i++) {
        }

    }

    public void set(int biome, int replacement) {
        this.swaps[biome] = replacement;
    }

    public void set(ArrayList<BiomeSettings> settings) {
        Iterator var2 = settings.iterator();

        while(var2.hasNext()) {
            BiomeSettings setting = (BiomeSettings)var2.next();
            if (setting.biomesAreActive()) {
                setting.updateMBiomes(this);
            }
        }

    }

    public int replacement(int original) {
        return this.swaps[original];
    }
}
