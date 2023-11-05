//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.biomeSettings;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.init.ModBiomes;
import climateControl.api.BiomePackage;
import climateControl.api.BiomeSettings;

public class BopPackage extends BiomePackage {
    public BopPackage() {
        super("biomesoplentyInCC.cfg");
        Class biomesClass = ModBiomes.class;

        try {
            int var2 = BOPBiomes.alps.hashCode();
        } catch (NullPointerException var3) {
        }

    }

    public BiomeSettings freshBiomeSetting() {
        return new BoPSettings();
    }
}
