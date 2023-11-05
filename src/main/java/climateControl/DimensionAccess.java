//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

import java.io.File;
import net.minecraft.world.WorldServer;

public class DimensionAccess extends MinecraftFilesAccess {
    public final int dimension;
    private final File dimensionDirectory;

    public DimensionAccess(int dimension, WorldServer world) {
        this.dimension = dimension;
        this.dimensionDirectory = world.getChunkSaveLocation();
    }

    public DimensionAccess(int dimension, File file) {
        this.dimension = dimension;
        this.dimensionDirectory = file;
    }

    public File configDirectory() {
        return new File(this.dimensionDirectory, "worldSpecificConfig");
    }

    public File baseDirectory() {
        return this.dimensionDirectory;
    }
}
