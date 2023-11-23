
package climateControl.generator;

import climateControl.ClimateControl;
import com.Zeno410Utils.IntRandomizer;
import climateControl.biomeSettings.BiomeReplacer;
import climateControl.api.BiomeSettings;
import com.Zeno410Utils.Zeno410Logger;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Zeno410
 */
public class SubBiomeChooser {

    public static Logger logger = new Zeno410Logger("subBiomeChooser").logger();
    private BiomeReplacer [] replacers = new BiomeReplacer[256];

    public void clear() {
        for (int i = 0 ; i < replacers.length; i++) {
            replacers[i]= BiomeReplacer.noChange;
        }
    }

    public void set(int biomeIndex, BiomeReplacer replacer) {
        // make a new replace that tries the new and the does the old.
        if (biomeIndex<0||biomeIndex>255) return;
        replacers[biomeIndex] = new BiomeReplacer.Multiple(replacer, replacers[biomeIndex]);
    }

    public void set(ArrayList<BiomeSettings> settings) {
        for (BiomeSettings setting: settings) {
            if (setting.biomesAreActive()) setting.update(this);
        }
    }

    public int subBiome (int biome, IntRandomizer randomizer,int x, int z) {
        try {
            return replacers[biome].replacement(biome, randomizer, x, z);
        } catch (NullPointerException e) {
            if (ClimateControl.testing) {
                //throw new RuntimeException("missing replacement for biome "+biome);
            }
            return biome;
        }
    }
}
