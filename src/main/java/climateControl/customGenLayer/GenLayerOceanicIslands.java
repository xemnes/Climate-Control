//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.api.IslandClimateMaker;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.IntRandomizer;
import com.Zeno410Utils.Numbered;
import com.Zeno410Utils.PlaneLocation;
import com.Zeno410Utils.RandomIntUser;
import com.Zeno410Utils.Zeno410Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Logger;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerOceanicIslands extends GenLayerPack {
    public static final Logger logger = (new Zeno410Logger("OceanicIslands")).logger();
    private final IntRandomizer passer;
    private final boolean suppressDiagonals;
    private final int milleFill;
    private final IslandClimateMaker island;
    private final String layerName;

    private GenLayerOceanicIslands(long p_i45480_1_, GenLayer p_i45480_3_, int milleFill, final int islandValue, boolean suppressDiagonals, String layerName) {
        super(p_i45480_1_);
        this.passer = new NamelessClass_1();
        this.parent = p_i45480_3_;
        this.milleFill = milleFill;
        this.island = new IslandClimateMaker() {
            public int climate(int x, int z, IntRandomizer randomizer) {
                return islandValue;
            }
        };
        this.suppressDiagonals = suppressDiagonals;
        this.layerName = layerName;
    }

    public GenLayerOceanicIslands(long p_i45480_1_, GenLayer p_i45480_3_, int milleFill, final RandomIntUser island, boolean suppressDiagonals, String layerName) {
        super(p_i45480_1_);
        this.passer = new NamelessClass_1();
        this.parent = p_i45480_3_;
        this.milleFill = milleFill;
        this.island = new IslandClimateMaker() {
            public int climate(int x, int z, IntRandomizer randomizer) {
                return island.value(randomizer);
            }
        };
        this.suppressDiagonals = suppressDiagonals;
        this.layerName = layerName;
    }

    public GenLayerOceanicIslands(long p_i45480_1_, GenLayer p_i45480_3_, int milleFill, IslandClimateMaker island, boolean suppressDiagonals, String layerName) {
        super(p_i45480_1_);

        this.passer = new NamelessClass_1();
        this.parent = p_i45480_3_;
        this.milleFill = milleFill;
        this.island = island;
        this.suppressDiagonals = suppressDiagonals;
        this.layerName = layerName;
    }

    class NamelessClass_1 extends IntRandomizer {
        NamelessClass_1() {
        }

        public int nextInt(int range) {
            return GenLayerOceanicIslands.this.nextInt(range);
        }
    }
    public int[] getInts(int par1, int par2, int par3, int par4) {
        if (this.suppressDiagonals) {
            return this.getSeparatedIslands(par1, par2, par3, par4);
        } else {
            int i1 = par1 - 1;
            int j1 = par2 - 1;
            int k1 = par3 + 2;
            int l1 = par4 + 2;
            int[] aint = this.parent.getInts(i1, j1, k1, l1);
            int[] aint1 = IntCache.getIntCache(par3 * par4);

            for(int i2 = 0; i2 < par4; ++i2) {
                for(int j2 = 0; j2 < par3; ++j2) {
                    int k2 = aint[j2 + 1 + (i2 + 1 - 1) * (par3 + 2)];
                    int l2 = aint[j2 + 1 + 1 + (i2 + 1) * (par3 + 2)];
                    int i3 = aint[j2 + 1 - 1 + (i2 + 1) * (par3 + 2)];
                    int j3 = aint[j2 + 1 + (i2 + 1 + 1) * (par3 + 2)];
                    int k3 = aint[j2 + 1 + (i2 + 1) * k1];
                    aint1[j2 + i2 * par3] = k3;
                    if (isOceanic(k3) && isOceanic(k2) && isOceanic(l2) && isOceanic(i3) && isOceanic(j3)) {
                        if (this.suppressDiagonals) {
                            int upperLeft = aint[j2 + i2 * (par3 + 2)];
                            int upperRight = aint[j2 + 2 + i2 * (par3 + 2)];
                            int lowerLeft = aint[j2 + (i2 + 2) * (par3 + 2)];
                            int lowerRight = aint[j2 + 2 + (i2 + 2) * (par3 + 2)];
                            if (!isOceanic(upperLeft) || !isOceanic(upperRight) || !isOceanic(lowerLeft) || !isOceanic(lowerRight)) {
                                continue;
                            }
                        }

                        this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                        if (this.nextInt(1000) < this.milleFill) {
                            aint1[j2 + i2 * par3] = this.island.climate(j2 + par1, i2 + par2, this.passer);
                        }
                    }
                }
            }

            return aint1;
        }
    }

    public int[] getSeparatedIslands(int par1, int par2, int par3, int par4) {
        ArrayList<Numbered<LocatedInt>> newIslands = new ArrayList();
        int i1 = par1 - 2;
        int j1 = par2 - 2;
        int parentXSize = par3 + 4;
        int l1 = par4 + 4;
        int[] parentVals = this.parent.getInts(i1, j1, parentXSize, l1);
        int[] changed = new int[parentVals.length];

        int i2;
        for(i2 = 0; i2 < parentVals.length; ++i2) {
            changed[i2] = parentVals[i2];
        }

        int j2;
        int k3;
        for(i2 = 1; i2 < par4 + 3; ++i2) {
            for(j2 = 1; j2 < par3 + 3; ++j2) {
                j2 = parentVals[j2 + (i2 - 1) * parentXSize];
                k3 = parentVals[j2 + 1 + i2 * parentXSize];
                int i3 = parentVals[j2 - 1 + i2 * parentXSize];
                int j3 = parentVals[j2 + (i2 + 1) * parentXSize];
                k3 = parentVals[j2 + i2 * parentXSize];
                if (isOceanic(k3) && isOceanic(j2) && isOceanic(k3) && isOceanic(i3) && isOceanic(j3)) {
                    if (this.suppressDiagonals) {
                        int upperLeft = parentVals[j2 - 1 + (i2 - 1) * parentXSize];
                        int upperRight = parentVals[j2 + 1 + (i2 - 1) * parentXSize];
                        int lowerLeft = parentVals[j2 - 1 + (i2 + 1) * parentXSize];
                        int lowerRight = parentVals[j2 + 1 + (i2 + 1) * parentXSize];
                        if (!isOceanic(upperLeft) || !isOceanic(upperRight) || !isOceanic(lowerLeft) || !isOceanic(lowerRight)) {
                            continue;
                        }
                    }

                    this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                    if (this.nextInt(1000) < this.milleFill) {
                        LocatedInt toAdd = new LocatedInt(new PlaneLocation(j2, i2), this.island.climate(j2 + par1, i2 + par2, this.passer));
                        newIslands.add(new Numbered(this.passer.nextInt(Integer.MAX_VALUE), toAdd));
                    }
                }
            }
        }

        Collections.sort(newIslands, Numbered.comparator(locatedIntComparator()));
        Iterator var23 = newIslands.iterator();

        while(var23.hasNext()) {
            Numbered<LocatedInt> attempt = (Numbered)var23.next();
            j2 = ((LocatedInt)attempt.item()).location.x();
            k3 = ((LocatedInt)attempt.item()).location.z();
            changed[j2 + k3 * parentXSize] = ((LocatedInt)attempt.item()).climate;
            this.revertNonOcean(j2 + (k3 - 1) * parentXSize, parentVals, changed);
            this.revertNonOcean(j2 + 1 + k3 * parentXSize, parentVals, changed);
            this.revertNonOcean(j2 - 1 + k3 * parentXSize, parentVals, changed);
            this.revertNonOcean(j2 + (k3 + 1) * parentXSize, parentVals, changed);
            if (this.suppressDiagonals) {
                this.revertNonOcean(j2 - 1 + (k3 - 1) * parentXSize, parentVals, changed);
                this.revertNonOcean(j2 + 1 + (k3 - 1) * parentXSize, parentVals, changed);
                this.revertNonOcean(j2 - 1 + (k3 + 1) * parentXSize, parentVals, changed);
                this.revertNonOcean(j2 + 1 + (k3 + 1) * parentXSize, parentVals, changed);
            }
        }

        int[] aint1 = new int[par3 * par4];

        for(j2 = 2; j2 < par4 + 2; ++j2) {
            for(j2 = 2; j2 < par3 + 2; ++j2) {
                k3 = changed[j2 + j2 * parentXSize];
                aint1[j2 - 2 + (j2 - 2) * par3] = k3;
                if (k3 > 0) {
                }
            }
        }

        return aint1;
    }

    private final void revertNonOcean(int location, int[] original, int[] changed) {
        if (!isOceanic(changed[location])) {
            changed[location] = original[location];
        }

    }

    private static Comparator<LocatedInt> locatedIntComparator() {
        return new Comparator<LocatedInt>() {
            public int compare(LocatedInt arg0, LocatedInt arg1) {
                return arg0.location.x() != arg1.location.x() ? arg0.location.x() - arg1.location.x() : arg0.location.z() - arg1.location.z();
            }
        };
    }

    private class LocatedInt {
        final PlaneLocation location;
        final int climate;

        LocatedInt(PlaneLocation location, int climate) {
            this.location = location;
            this.climate = climate;
        }
    }
}
