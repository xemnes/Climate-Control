//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import climateControl.utils.BiomeConfigManager;
import com.Zeno410Utils.Named;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class BiomePackageRegistry {
    public static BiomePackageRegistry instance;
    private HashMap<String, BiomePackage> namedPackages = new HashMap();
    private ArrayList<BiomePackage> orderedPackages = new ArrayList();
    private ArrayList<Named<BiomeSettings>> settings = new ArrayList();
    private final File configDirectory;
    private final BiomeConfigManager taggedConfigManager;

    public BiomePackageRegistry(File configDirectory, BiomeConfigManager taggedConfigManager) {
        this.configDirectory = configDirectory;
        this.taggedConfigManager = taggedConfigManager;
    }

    public final void register(BiomePackage biomePackage) {
        if (!this.namedPackages.containsKey(new String(biomePackage.configFileName()))) {
            this.namedPackages.put(biomePackage.configFileName(), biomePackage);
            this.orderedPackages.add(biomePackage);
            Named<BiomeSettings> namedSettings = biomePackage.namedBiomeSetting();
            this.taggedConfigManager.initializeConfig(namedSettings, this.configDirectory);
            this.settings.add(namedSettings);
        }
    }

    public final Collection<Named<BiomeSettings>> biomeSettings() {
        return this.settings;
    }

    public final Collection<Named<BiomeSettings>> freshBiomeSettings() {
        Collection<Named<BiomeSettings>> result = new ArrayList();
        Iterator var2 = this.orderedPackages.iterator();

        while(var2.hasNext()) {
            BiomePackage biomePackage = (BiomePackage)var2.next();
            result.add(biomePackage.namedBiomeSetting());
        }

        return result;
    }

    public static class BiomePackageAlreadyRegistered extends RuntimeException {
        private final String name;

        public BiomePackageAlreadyRegistered(String name) {
            super("config file name " + name + " is already registered with Climate Control");
            this.name = name;
        }
    }
}
