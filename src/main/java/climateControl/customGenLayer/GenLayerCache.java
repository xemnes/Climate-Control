//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.PlaneLocated;
import com.Zeno410Utils.PlaneLocation;
import java.io.DataOutputStream;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerCache extends GenLayerPack {
    private PlaneLocated<Integer> storedVals = new PlaneLocated();
    private PlaneLocatedRecorder target;

    public GenLayerCache(GenLayer parent) {
        super(0L);
        this.parent = parent;
    }

    public GenLayerCache(GenLayer parent, DataOutputStream target) {
        super(0L);
        this.parent = parent;
        this.target = new PlaneLocatedRecorder(target);
    }

    public void initWorldGenSeed(long par1) {
        if (this.target != null) {
            this.target.writeSeed(par1);
        }

        super.initWorldGenSeed(par1);
    }

    public int[] getInts(int x0, int z0, int xSize, int zSize) {
        PlaneLocation.Probe probe = new PlaneLocation.Probe(x0, z0);
        int[] parentInts = null;
        int[] result = IntCache.getIntCache(xSize * zSize);

        for(int x = 0; x < xSize; ++x) {
            probe.setX(x + x0);

            for(int z = 0; z < zSize; ++z) {
                probe.setZ(z + z0);
                Integer locked = (Integer)this.storedVals.get(probe);
                if (locked == null) {
                    if (parentInts == null) {
                        parentInts = this.parent.getInts(x0, z0, xSize, zSize);
                    }

                    locked = parentInts[z * xSize + x];
                    result[z * xSize + x] = locked;
                    PlaneLocation location = new PlaneLocation(probe.x(), probe.z());
                    this.storedVals.put(location, locked);
                } else {
                    result[z * xSize + x] = locked;
                }
            }
        }

        if (this.target != null) {
            this.target.accept(this.storedVals);
        }

        return result;
    }
}
