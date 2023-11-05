//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.PlaneLocated;
import com.Zeno410Utils.PlaneLocation;
import com.Zeno410Utils.Zeno410Logger;
import java.util.logging.Logger;
import net.minecraft.world.gen.layer.GenLayer;

public class ConfirmBiome extends GenLayerPack {
    private int exclusion;
    public static Logger logger = (new Zeno410Logger("ConfirmBiome")).logger();
    private PlaneLocated<Integer> storedVals;

    public ConfirmBiome(GenLayer parent, int exclusion) {
        super(0L);
        this.storedVals = new PlaneLocated();
        this.parent = parent;
        this.exclusion = exclusion;
    }

    public ConfirmBiome(GenLayer parent) {
        this(parent, 0);
    }

    public int[] getInts(int x0, int z0, int xSize, int zSize) {
        PlaneLocation.Probe probe = new PlaneLocation.Probe(x0, z0);
        String problems = probe.toString() + ":";
        int originalLength = problems.length();
        int[] result = this.parent.getInts(x0, z0, xSize, zSize);

        for(int x = this.exclusion; x < xSize - this.exclusion; ++x) {
            probe.setX(x + x0);

            for(int z = this.exclusion; z < zSize - this.exclusion; ++z) {
                probe.setZ(z + z0);
                Integer locked = result[z * xSize + x];
                if (locked > 255 || locked < 0) {
                    problems = problems + (new PlaneLocation(x, z)).toString() + locked + " ";
                }
            }
        }

        if (problems.length() > originalLength) {
            throw new RuntimeException(problems);
        } else {
            return result;
        }
    }
}
