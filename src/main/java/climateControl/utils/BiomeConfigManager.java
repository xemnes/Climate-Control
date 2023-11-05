//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.utils;

import climateControl.api.BiomeSettings;
import com.Zeno410Utils.Named;
import com.Zeno410Utils.Settings;
import com.Zeno410Utils.Zeno410Logger;
import java.io.File;
import java.util.logging.Logger;
import net.minecraftforge.common.config.Configuration;

public class BiomeConfigManager<Type extends Settings> {
    private final String groupDirectoryName;
    public static final String worldSpecificConfigFileName = "worldSpecificConfig";
    public static Logger logger = (new Zeno410Logger("TaggedConfigManager")).logger();

    public BiomeConfigManager(String groupDirectoryName) {
        this.groupDirectoryName = groupDirectoryName;
    }

    public void updateConfig(Named<Type> namedSettings, File generalDirectory, File specificDirectory) {
        Settings settings = (Settings)namedSettings.object;
        File specificAddOnDirectory = new File(generalDirectory, this.groupDirectoryName);
        if (!specificAddOnDirectory.exists()) {
            specificAddOnDirectory.mkdir();
        }

        File specificAddonFile = new File(specificAddOnDirectory, namedSettings.name);
        this.readConfigs(specificAddonFile, settings, generalDirectory, true);

        try {
            BiomeSettings biomeSettings = (BiomeSettings)settings;
            biomeSettings.setNativeBiomeIDs(generalDirectory);
        } catch (ClassCastException var8) {
        }

        if (!specificDirectory.exists()) {
            specificDirectory.mkdir();
        }

        if (!specificDirectory.exists()) {
            throw new RuntimeException("cannot make directory " + specificDirectory.getAbsolutePath());
        } else {
            specificAddOnDirectory = new File(specificDirectory, this.groupDirectoryName);
            if (!specificAddOnDirectory.exists()) {
                specificAddOnDirectory.mkdir();
            }

            if (!specificAddOnDirectory.exists()) {
                throw new RuntimeException(specificAddOnDirectory.getAbsolutePath());
            } else {
                specificAddonFile = new File(specificAddOnDirectory, namedSettings.name);
                this.readConfigs(specificAddonFile, settings, generalDirectory, false);
            }
        }
    }

    public void initializeConfig(Named<Type> namedSettings, File generalDirectory) {
        Settings settings = (Settings)namedSettings.object;
        File generalAddOnDirectory = new File(generalDirectory, this.groupDirectoryName);
        File generalAddonFile = new File(generalAddOnDirectory, namedSettings.name);
        this.readConfigs(generalAddonFile, settings, generalDirectory, true);

        try {
            BiomeSettings biomeSettings = (BiomeSettings)settings;
            Configuration sample = new Configuration(generalAddonFile);
            biomeSettings.stripIDsFrom(sample);
            sample.save();
            biomeSettings.setNativeBiomeIDs(generalDirectory);
        } catch (ClassCastException var8) {
        }

    }

    private void readConfigs(File specificFile, Settings settings, File generalDirectory, boolean isGeneral) {
        Configuration specific = null;

        try {
            settings.readForeignConfigs(generalDirectory);
        } catch (ClassCastException var8) {
        }

        if (specificFile.exists()) {
            specific = new Configuration(specificFile);
            settings.readFrom(specific);
        } else {
            specific = new Configuration(specificFile);
            settings.copyTo(specific);
        }

        settings.copyTo(specific);

        try {
            BiomeSettings biomeSettings = (BiomeSettings)settings;
            if (isGeneral) {
                biomeSettings.stripIDsFrom(specific);
            }
        } catch (ClassCastException var7) {
        }

        specific.save();
    }

    public void saveConfigs(File generalDirectory, File specificDirectory, Named<Settings> namedSettings) {
        if (!specificDirectory.exists()) {
            specificDirectory.mkdir();
        }

        if (!specificDirectory.exists()) {
            throw new RuntimeException("cannot make directory " + specificDirectory.getAbsolutePath());
        } else {
            File specificAddOnDirectory = new File(specificDirectory, this.groupDirectoryName);
            if (!specificAddOnDirectory.exists()) {
                specificAddOnDirectory.mkdir();
            }

            if (!specificAddOnDirectory.exists()) {
                throw new RuntimeException("cannot make directory " + specificAddOnDirectory.getAbsolutePath());
            } else {
                File specificAddonFile = new File(specificAddOnDirectory, namedSettings.name);
                Configuration specific = new Configuration(specificAddonFile);
                ((Settings)namedSettings.object).copyTo(specific);

                try {
                    BiomeSettings biomeSettings = (BiomeSettings)namedSettings.object;
                    biomeSettings.setNativeBiomeIDs(generalDirectory);
                } catch (ClassCastException var8) {
                }

                specific.save();
            }
        }
    }

    private boolean usable(File tested) {
        return tested != null;
    }
}
