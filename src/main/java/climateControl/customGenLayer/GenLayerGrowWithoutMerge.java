//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerGrowWithoutMerge extends GenLayerPack {
    public final int changeChances;
    public final int maxLandAdvance;
    public final int minSeaAdvance;

    public GenLayerGrowWithoutMerge(long par1, GenLayer par3GenLayer, int maxLandAdvance, int minSeaAdvance, int changeChances) {
        super(par1);
        this.parent = par3GenLayer;
        this.changeChances = changeChances;
        this.maxLandAdvance = maxLandAdvance;
        this.minSeaAdvance = minSeaAdvance;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int i1 = par1 - 2;
        int j1 = par2 - 2;
        int k1 = par3 + 4;
        int l1 = par4 + 4;
        int[] parent = this.parent.getInts(i1, j1, k1, l1);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for(int i2 = 0; i2 < par4; ++i2) {
            for(int j2 = 0; j2 < par3; ++j2) {
                int up = parent[j2 + 1 + (i2 + 2) * k1];
                int left = parent[j2 + 2 + (i2 + 1) * k1];
                int right = parent[j2 + 2 + (i2 + 3) * k1];
                int down = parent[j2 + 3 + (i2 + 2) * k1];
                int k3 = parent[j2 + 2 + (i2 + 2) * k1];
                this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                if (isOceanic(k3) && (!isOceanic(up) || !isOceanic(left) || !isOceanic(right) || !isOceanic(down))) {
                    boolean acceptable = true;
                    int i4 = k3;
                    int hits;
                    int upleft;
                    int upright;
                    int downleft;
                    if ((!isOceanic(up) || !isOceanic(down)) && (!isOceanic(left) || !isOceanic(right))) {
                        boolean mixedScene = false;
                        if (isOceanic(up) || isOceanic(left) || isOceanic(right) || isOceanic(down)) {
                            mixedScene = true;
                        }

                        if (mixedScene) {
                            upleft = parent[j2 + 1 + (i2 + 1) * k1];
                            upright = parent[j2 + 1 + (i2 + 3) * k1];
                            downleft = parent[j2 + 3 + (i2 + 1) * k1];
                            int downright = parent[j2 + 3 + (i2 + 3) * k1];
                            if (isOceanic(upleft) && isOceanic(downright) && (!isOceanic(up) || !isOceanic(right) || !isOceanic(upright)) && (!isOceanic(down) || !isOceanic(left) || !isOceanic(downleft))) {
                                acceptable = false;
                            }

                            if (isOceanic(upright) && isOceanic(downleft) && (!isOceanic(up) || !isOceanic(left) || !isOceanic(upleft)) && (!isOceanic(down) || !isOceanic(right) || !isOceanic(downright))) {
                                acceptable = false;
                            }
                        }
                    } else {
                        hits = parent[j2 + 1 + (i2 + 1) * k1];
                        upleft = parent[j2 + 1 + (i2 + 3) * k1];
                        upright = parent[j2 + 3 + (i2 + 1) * k1];
                        downleft = parent[j2 + 3 + (i2 + 3) * k1];
                        if (isOceanic(up) && isOceanic(down)) {
                            if ((!isOceanic(left) || !isOceanic(hits) || !isOceanic(upright)) && (!isOceanic(right) || !isOceanic(upleft) || !isOceanic(downleft))) {
                                acceptable = false;
                            }
                        } else if ((!isOceanic(up) || !isOceanic(hits) || !isOceanic(upleft)) && (!isOceanic(down) || !isOceanic(upright) || !isOceanic(downleft))) {
                            acceptable = false;
                        }
                    }

                    if (acceptable) {
                        hits = 0;
                        long savedSeed = this.savedChunkSeed();
                        if (!isOceanic(up)) {
                            this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                            if (this.nextInt(this.changeChances) < this.maxLandAdvance) {
                                i4 = up;
                                ++hits;
                            }
                        }

                        if (!isOceanic(left)) {
                            this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                            if (this.nextInt(this.changeChances) < this.maxLandAdvance) {
                                if (hits == 0) {
                                    i4 = left;
                                } else if (i4 != left) {
                                    this.restoreChunkSeed(savedSeed);
                                    if (this.nextInt(hits) == 0) {
                                        i4 = left;
                                    }

                                    savedSeed = this.savedChunkSeed();
                                }

                                ++hits;
                            }
                        }

                        if (!isOceanic(right)) {
                            savedSeed = this.savedChunkSeed();
                            this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                            if (this.nextInt(this.changeChances) < this.maxLandAdvance) {
                                if (hits == 0) {
                                    i4 = right;
                                } else if (i4 != right) {
                                    this.restoreChunkSeed(savedSeed);
                                    if (this.nextInt(hits) == 0) {
                                        i4 = right;
                                    }

                                    savedSeed = this.savedChunkSeed();
                                }

                                ++hits;
                            }
                        }

                        if (!isOceanic(down)) {
                            savedSeed = this.savedChunkSeed();
                            this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                            if (this.nextInt(this.changeChances) < this.maxLandAdvance) {
                                if (hits == 0) {
                                    i4 = down;
                                } else if (i4 != down) {
                                    this.restoreChunkSeed(savedSeed);
                                    if (this.nextInt(hits) == 0) {
                                        i4 = down;
                                    }

                                    savedSeed = this.savedChunkSeed();
                                }

                                ++hits;
                            }
                        }
                    }

                    aint1[j2 + i2 * par3] = i4;
                    if (aint1[j2 + i2 * par3] == -2) {
                        throw new RuntimeException();
                    }
                } else if (isOceanic(k3) || !isOceanic(up) && !isOceanic(left) && !isOceanic(right) && !isOceanic(down)) {
                    aint1[j2 + i2 * par3] = k3;
                    if (aint1[j2 + i2 * par3] == -2) {
                        throw new RuntimeException();
                    }
                } else {
                    int i4 = k3;
                    long savedSeed = this.savedChunkSeed();
                    if (isOceanic(up)) {
                        this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                        if (this.nextInt(this.changeChances) >= this.minSeaAdvance) {
                            i4 = up;
                        }
                    }

                    if (isOceanic(left)) {
                        this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                        if (this.nextInt(this.changeChances) >= this.minSeaAdvance) {
                            i4 = left;
                        }
                    }

                    if (isOceanic(right)) {
                        this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                        if (this.nextInt(this.changeChances) >= this.minSeaAdvance) {
                            i4 = right;
                        }
                    }

                    if (isOceanic(down)) {
                        this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                        if (this.nextInt(this.changeChances) >= this.minSeaAdvance) {
                            i4 = down;
                        }
                    }

                    aint1[j2 + i2 * par3] = i4;
                    if (aint1[j2 + i2 * par3] == -2) {
                        throw new RuntimeException();
                    }
                }

                if (aint1[j2 + i2 * par3] == -2) {
                    throw new RuntimeException();
                }
            }
        }

        return aint1;
    }
}
