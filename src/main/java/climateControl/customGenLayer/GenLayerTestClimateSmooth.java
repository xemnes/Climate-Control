//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.Zeno410Logger;
import java.util.logging.Logger;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerTestClimateSmooth extends GenLayerPack {
    public static Logger logger = (new Zeno410Logger("GenLayerClimateSmooth")).logger();

    public GenLayerTestClimateSmooth(GenLayer parent) {
        super(0L);
        this.parent = parent;
    }

    public int[] getInts(int x0, int z0, int xSize, int zSize) {
        int[] parentInts = this.parent.getInts(x0, z0, xSize, zSize);

        int x;
        int z;
        int first;
        int second;
        for(x = 0; x < xSize; ++x) {
            for(z = 0; z < zSize - 1; ++z) {
                first = parentInts[x + xSize * z];
                second = parentInts[x + xSize * (z + 1)];
                if (!isOceanic(first) && !isOceanic(second) && Math.abs(first - second) > 2) {
                    logger.info("first " + first + " to " + second);

                    for(int i = 0; i < xSize; ++i) {
                        String line = "";

                        for(int j = 0; j < zSize - 1; ++j) {
                            line = line + " " + parentInts[i + xSize * j];
                        }

                        logger.info(line);
                    }

                    throw new RuntimeException();
                }
            }
        }

        for(x = 0; x < xSize - 1; ++x) {
            for(z = 0; z < zSize; ++z) {
                first = parentInts[x + xSize * z];
                second = parentInts[x + 1 + xSize * z];
                if (!isOceanic(first) && !isOceanic(second) && Math.abs(first - second) > 2) {
                    logger.info("first " + first + " to " + second);
                    throw new RuntimeException();
                }
            }
        }

        return parentInts;
    }
}
