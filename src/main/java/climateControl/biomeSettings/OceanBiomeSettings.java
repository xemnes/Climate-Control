//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.biomeSettings;

import climateControl.api.BiomeSettings;
import climateControl.api.ClimateControlRules;
import climateControl.api.ClimateDistribution;
import java.io.File;

public class OceanBiomeSettings extends BiomeSettings {
    public static final String biomeCategory = "OceanBiome";
    public final BiomeSettings.Element coastalOcean = new OceanElement("Ocean", 0, 100);
    public final BiomeSettings.Element deepOcean = new DeepOceanElement("DeepOcean", 24, 100);

    public OceanBiomeSettings() {
        super("OceanBiome");
    }

    public void setNativeBiomeIDs(File configDirectory) {
    }

    public void setRules(ClimateControlRules rules) {
    }

    public void onNewWorld() {
    }

    public boolean biomesAreActive() {
        return true;
    }

    protected class DeepOceanElement extends BiomeSettings.Element {
        DeepOceanElement(String name, int ID, int incidence) {
            super(name, ID, incidence);
            this.setDistribution(ClimateDistribution.DEEP_OCEAN);
        }
    }

    protected class OceanElement extends BiomeSettings.Element {
        OceanElement(String name, int ID, int incidence) {
            super(name, ID, incidence);
            this.setDistribution(ClimateDistribution.OCEAN);
        }
    }
}
