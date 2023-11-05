//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSmoothWithBiomes extends GenLayerPack {
    private static int deepOcean;
    private static int mushroomIsland;

    public GenLayerSmoothWithBiomes(long par1, GenLayer par3GenLayer) {
        super(par1);
        super.parent = par3GenLayer;
        if (super.parent == null) {
            throw new RuntimeException();
        }
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        deepOcean = Biome.getIdForBiome(Biomes.DEEP_OCEAN);
        mushroomIsland = Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND);
        int i1 = par1 - 1;
        int j1 = par2 - 1;
        int parentSpan = par3 + 2;
        int l1 = par4 + 2;
        int[] aint = this.parent.getInts(i1, j1, parentSpan, l1);
        int[] aint1 = IntCache.getIntCache(par3 * par4);
        this.poison(aint1, par3 * par4);

        for(int z = 0; z < par4; ++z) {
            for(int x = 0; x < par3; ++x) {
                int left = aint[x + 0 + (z + 1) * parentSpan];
                int right = aint[x + 2 + (z + 1) * parentSpan];
                int up = aint[x + 1 + (z + 0) * parentSpan];
                int down = aint[x + 1 + (z + 2) * parentSpan];
                int center = aint[x + 1 + (z + 1) * parentSpan];
                if (center == mushroomIsland) {
                    aint1[x + z * par3] = center;
                } else {
                    if (left == right && up == down) {
                        this.initChunkSeed((long)(x + par1), (long)(z + par2));
                        boolean island = !isOceanic(center) && isOceanic(left) && isOceanic(up);
                        if (!island) {
                            if (this.nextInt(2) == 0) {
                                center = left;
                            } else {
                                center = up;
                            }
                        }
                    } else if (left == right) {
                        center = left;
                    } else if (up == down) {
                        center = up;
                    } else if (center == 0) {
                        center = this.climateSmoothed(i1, j1, parentSpan, l1, left, right, up, down, center, par1, par2, z, x);
                    }

                    aint1[x + z * par3] = center;
                }
            }
        }

        this.taste(aint1, par3 * par4);
        return aint1;
    }

    private final int climateSmoothed(int i1, int j1, int k1, int l1, int k2, int l2, int i3, int j3, int k3, int par1, int par2, int z, int x) {
        if (!isOceanic(k2) && !isOceanic(l2)) {
            this.initChunkSeed((long)(x + par1), (long)(z + par2));
            if (!isOceanic(i3) && !isOceanic(j3)) {
                int pick = this.nextInt(4);
                if (pick == 0) {
                    k3 = k2;
                } else if (pick == 1) {
                    k3 = l2;
                } else if (pick == 2) {
                    k3 = i3;
                } else if (pick == 3) {
                    k3 = j3;
                }
            } else if (this.nextInt(2) == 0) {
                k3 = k2;
            } else {
                k3 = l2;
            }
        } else if (!isOceanic(i3) && !isOceanic(j3)) {
            this.initChunkSeed((long)(x + par1), (long)(z + par2));
            if (this.nextInt(2) == 0) {
                k3 = i3;
            } else {
                k3 = j3;
            }
        }

        return k3;
    }
}
