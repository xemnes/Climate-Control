//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.biomeSettings;

import climateControl.api.BiomeSettings;
import climateControl.api.ClimateControlRules;
import com.Zeno410Utils.Mutable;
import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class ThaumcraftBiomeSettings extends BiomeSettings {
    public static final String biomeCategory = "ThaumcraftBiome";
    public final BiomeSettings.Element tainted = new BiomeSettings.Element("Tainted", 192, 1);
    public final BiomeSettings.Element magicalForest = new BiomeSettings.Element("Magical Forest", 193, 7);
    public final BiomeSettings.Element eerie = new BiomeSettings.Element("Eerie", 194, 3);
    static final String biomesOnName = "ThaumcraftBiomesOn";
    public final Mutable<Boolean> biomesFromConfig;
    static final String configName = "Thaumcraft";
    public final Mutable<Boolean> biomesInNewWorlds;

    public ThaumcraftBiomeSettings() {
        super("ThaumcraftBiome");
        this.biomesFromConfig = this.climateControlCategory.booleanSetting("ThaumcraftBiomesOn", "", false);
        this.biomesInNewWorlds = this.climateControlCategory.booleanSetting(this.startBiomesName("Thaumcraft"), "Use biome in new worlds and dimensions", true);
    }

    public void setNativeBiomeIDs(File configDirectory) {
        Configuration config = new Configuration(new File(configDirectory, "Thaumcraft.cfg"));
        this.tainted.biomeID().set(config.get("biomes", "biome_taint", 192).getInt());
        this.magicalForest.biomeID().set(config.get("biomes", "biome_magical_forest", 193).getInt());
        this.eerie.biomeID().set(config.get("biomes", "biome_eerie", 194).getInt());
    }

    public void setRules(ClimateControlRules rules) {
    }

    public void onNewWorld() {
        this.biomesFromConfig.set(this.biomesInNewWorlds);
    }

    public boolean biomesAreActive() {
        return (Boolean)this.biomesFromConfig.value();
    }
}
