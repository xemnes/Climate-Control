//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

import java.io.File;

public class GeneralConfigAccess extends MinecraftFilesAccess {
    private final File configDirectory;

    public GeneralConfigAccess(File configDirectory) {
        this.configDirectory = configDirectory;
    }

    public File baseDirectory() {
        return this.configDirectory.getParentFile();
    }

    public File configDirectory() {
        return this.configDirectory;
    }
}
