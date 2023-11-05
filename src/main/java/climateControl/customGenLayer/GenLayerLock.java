//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.PlaneLocated;
import com.Zeno410Utils.PlaneLocation;
import com.Zeno410Utils.Zeno410Logger;
import java.util.HashMap;
import java.util.logging.Logger;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLock extends GenLayerPack {
    public static Logger logger = (new Zeno410Logger("GenLayerLock")).logger();
    private boolean watching = false;
    private PlaneLocated<Integer> storedVals;
    private int excludeEdge = 3;

    public GenLayerLock(GenLayer parent, PlaneLocated<Integer> storedVals, int excludeEdge) {
        super(0L);
        this.parent = parent;
        this.storedVals = storedVals;
        this.excludeEdge = excludeEdge;
    }

    public void setWatch(boolean newWatching) {
        this.watching = newWatching;
    }

    public int[] getInts(int x0, int z0, int xSize, int zSize) {
        if (this.watching) {
            logger.info("location " + x0 + " " + z0 + " " + xSize + " " + zSize);
        }

        HashMap<PlaneLocation, Integer> addedVals = null;
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
                        addedVals = new HashMap();
                    }

                    locked = parentInts[z * xSize + x];
                    result[z * xSize + x] = locked;
                    PlaneLocation location = new PlaneLocation(probe.x(), probe.z());
                    if (this.shouldStore(location, x0, z0, xSize, zSize)) {
                        if (this.watching) {
                            logger.info("locking " + location.toString() + " to " + locked + " " + this.parent.toString());
                        }

                        addedVals.put(location, locked);
                    }
                } else {
                    result[z * xSize + x] = locked;
                }
            }
        }

        if (addedVals != null && addedVals.size() > 0) {
            this.storedVals.putAll(addedVals);
            if (this.watching) {
                logger.info("lock size " + this.storedVals.size());
            }
        }

        return result;
    }

    private final boolean shouldStore(PlaneLocation location, int x0, int z0, int xSize, int zSize) {
        int thisExclusion = Math.min(this.excludeEdge, xSize / 2 - 1);
        if (location.x() - x0 < thisExclusion) {
            return false;
        } else if (location.z() - z0 < thisExclusion) {
            return false;
        } else if (x0 + xSize - location.x() < thisExclusion) {
            return false;
        } else {
            return z0 + zSize - location.z() >= thisExclusion;
        }
    }
}
