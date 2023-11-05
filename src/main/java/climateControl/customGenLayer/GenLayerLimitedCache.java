//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.PlaneLocated;
import com.Zeno410Utils.PlaneLocation;
import java.io.DataOutputStream;
import java.util.ArrayList;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLimitedCache extends GenLayerPack {
    private PlaneLocated<Integer> storedVals;
    private PlaneLocatedRecorder target;
    private final int limit;
    private final ArrayList<PlaneLocation> currentStored;
    int nextSlot;
    boolean full;

    public GenLayerLimitedCache(GenLayer parent, int limit) {
        super(0L);
        this.storedVals = new PlaneLocated();
        this.nextSlot = 0;
        this.full = false;
        this.parent = parent;
        this.limit = limit;
        this.currentStored = new ArrayList(limit);
        this.currentStored.ensureCapacity(limit);
    }

    public GenLayerLimitedCache(GenLayer parent, DataOutputStream target, int limit) {
        this(parent, limit);
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
                    if (this.full) {
                        PlaneLocation oldLocation = (PlaneLocation)this.currentStored.get(this.nextSlot);
                        this.storedVals.remove(oldLocation);
                    }

                    this.storedVals.put(location, locked);
                    if (this.full) {
                        this.currentStored.set(this.nextSlot, location);
                    } else {
                        this.currentStored.add(location);
                    }

                    if (this.nextSlot++ >= this.limit) {
                        this.nextSlot = 0;
                        this.full = true;
                    }
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
