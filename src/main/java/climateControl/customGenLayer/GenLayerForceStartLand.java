//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.api.IslandClimateMaker;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.IntRandomizer;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerForceStartLand extends GenLayerPack {
    private final IslandClimateMaker climateMaker;
    private final IntRandomizer randomizer = new IntRandomizer() {
        public int nextInt(int range) {
            return GenLayerForceStartLand.this.nextInt(range);
        }
    };

    public GenLayerForceStartLand(GenLayer parent, IslandClimateMaker climateMaker) {
        super(0L);
        this.parent = parent;
        this.climateMaker = climateMaker;
    }

    public int[] getInts(int x0, int z0, int xSize, int zSize) {
        if (x0 <= 0 && z0 <= 0 && x0 + xSize >= 0 && z0 + zSize >= 0) {
            int[] changed = this.parent.getInts(x0 - 1, z0 - 1, xSize + 2, zSize + 2);

            int x;
            for(x = 1; x < xSize + 1; ++x) {
                for(x = 1; x < zSize + 1; ++x) {
                    if (x + x0 - 1 == 0 && x + z0 - 1 == 0 && changed[(x - 1) * (xSize + 2) + x - 1] <= 0 && changed[(x - 1) * (xSize + 2) + x] <= 0 && changed[(x - 1) * (xSize + 2) + x + 1] <= 0 && changed[x * (xSize + 2) + x - 1] <= 0 && changed[x * (xSize + 2) + x] <= 0 && changed[x * (xSize + 2) + x + 1] <= 0 && changed[(x + 1) * (xSize + 2) + x - 1] <= 0 && changed[(x + 1) * (xSize + 2) + x] <= 0 && changed[(x + 1) * (xSize + 2) + x + 1] <= 0) {
                        super.initChunkSeed(0L, 0L);
                        changed[x * (xSize + 2) + x] = this.climateMaker.climate(x + x0 - 1, x + z0 - 1, this.randomizer);
                    }
                }
            }

            int[] result = new int[xSize * zSize];

            for(x = 0; x < xSize; ++x) {
                for(int z = 0; z < zSize; ++z) {
                    result[z * xSize + x] = changed[(z + 1) * (xSize + 2) + x + 1];
                }
            }

            return result;
        } else {
            return this.parent.getInts(x0, z0, xSize, zSize);
        }
    }
}
