//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.generator;

import climateControl.ClimateControl;
import climateControl.api.BiomeSettings;
import climateControl.biomeSettings.BiomeReplacer;
import com.Zeno410Utils.IntRandomizer;
import com.Zeno410Utils.Zeno410Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

public class SubBiomeChooser {
    public static Logger logger = (new Zeno410Logger("subBiomeChooser")).logger();
    private BiomeReplacer[] replacers = new BiomeReplacer[256];

    public SubBiomeChooser() {
    }

    public void clear() {
        for(int i = 0; i < this.replacers.length; ++i) {
            this.replacers[i] = BiomeReplacer.noChange;
        }

    }

    public void set(int biomeIndex, BiomeReplacer replacer) {
        if (biomeIndex >= 0 && biomeIndex <= 255) {
            this.replacers[biomeIndex] = new BiomeReplacer.Multiple(replacer, this.replacers[biomeIndex]);
        }
    }

    public void set(ArrayList<BiomeSettings> settings) {
        Iterator var2 = settings.iterator();

        while(var2.hasNext()) {
            BiomeSettings setting = (BiomeSettings)var2.next();
            if (setting.biomesAreActive()) {
                setting.update(this);
            }
        }

    }

    public int subBiome(int biome, IntRandomizer randomizer, int x, int z) {
        try {
            return this.replacers[biome].replacement(biome, randomizer, x, z);
        } catch (NullPointerException var6) {
            if (ClimateControl.testing) {
            }

            return biome;
        }
    }
}
