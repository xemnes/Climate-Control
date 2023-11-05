//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import com.Zeno410Utils.Named;

public abstract class BiomePackage {
    private final String configFileName;

    public BiomePackage(String configFileName) {
        this.configFileName = configFileName;
    }

    public String configFileName() {
        return this.configFileName;
    }

    public abstract BiomeSettings freshBiomeSetting();

    public Named<BiomeSettings> namedBiomeSetting() {
        return Named.from(this.configFileName, this.freshBiomeSetting());
    }
}
