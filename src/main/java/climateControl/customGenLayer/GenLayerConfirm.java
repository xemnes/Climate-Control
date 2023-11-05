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

public class GenLayerConfirm extends GenLayerPack {
    private int exclusion;
    public static Logger logger = (new Zeno410Logger("Cache")).logger();
    public String name;
    private PlaneLocated<Integer> storedVals;
    public boolean testing;

    public GenLayerConfirm(GenLayer parent, int exclusion) {
        super(0L);
        this.name = "";
        this.storedVals = new PlaneLocated();
        this.parent = parent;
        this.exclusion = exclusion;
    }

    public GenLayerConfirm(GenLayer parent) {
        this(parent, 0);
    }

    public GenLayerConfirm(GenLayer parent, String name) {
        this(parent, 0);
        this.name = name;
    }

    public void initWorldGenSeed(long par1) {
        super.initWorldGenSeed(par1);
        if (this.testing) {
            throw new RuntimeException("" + par1);
        }
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
                Integer locked = (Integer)this.storedVals.get(probe);
                if (locked == null) {
                    locked = result[z * xSize + x];
                    PlaneLocation location = new PlaneLocation(probe.x(), probe.z());
                    this.storedVals.put(location, locked);
                } else if (result[z * xSize + x] != locked) {
                    problems = problems + (new PlaneLocation(x, z)).toString() + "[" + result[z * xSize + x] + "->" + locked + "]";
                }
            }
        }

        if (problems.length() > originalLength) {
            String grandparent = "";

            try {
                grandparent = ((GenLayerPack)((GenLayerPack)this.parent)).getParent().toString();
            } catch (Exception var13) {
            }

            throw new RuntimeException(problems + " " + this.parent.toString() + " " + grandparent);
        } else {
            return result;
        }
    }
}
