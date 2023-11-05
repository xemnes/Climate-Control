//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.biomeSettings;

import climateControl.api.BiomePackage;
import climateControl.api.BiomeSettings;
import climateControl.api.ClimateControlRules;
import climateControl.api.ClimateDistribution;
import com.Zeno410Utils.Settings;
import java.io.File;
import java.util.ArrayList;
import net.minecraftforge.common.config.Configuration;

public class ExternalBiomePackage extends BiomePackage {
    private String externalBiomeNames;

    public ExternalBiomePackage() {
        super("ExternalBiomesinCC");
    }

    public BiomeSettings freshBiomeSetting() {
        return new ExternalBiomeSettings(this.externalBiomeNames);
    }

    public static class ExternalBiomeSettings extends BiomeSettings {
        public static final String biomeCategory = "ExternalBiome";
        public static final String externalCategory = "ExternalSettings";
        public final Settings.Category externalSettings = new Settings.Category("ExternalSettings");
        public final ArrayList<BiomeSettings.Element> biomes = new ArrayList();

        public ExternalBiomeSettings(String nameList) {
            super("ExternalBiome");
            String[] names = nameList.split(",");

            for(int i = 0; i < names.length; ++i) {
                this.biomes.add(new BiomeSettings.Element(names[i], -1, 10, false, ClimateDistribution.MEDIUM.name()));
            }

        }

        public void stripIDsFrom(Configuration config) {
        }

        public void setRules(ClimateControlRules rules) {
        }

        public void setNativeBiomeIDs(File configDirectory) {
        }

        public void onNewWorld() {
        }

        public boolean biomesAreActive() {
            return true;
        }
    }
}
