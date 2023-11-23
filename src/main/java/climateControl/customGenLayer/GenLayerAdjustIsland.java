package climateControl.customGenLayer;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
/**
 *
 * @author Zeno410
 */
public class GenLayerAdjustIsland extends GenLayerNeighborTesting {

    public GenLayerAdjustIsland(long par1, GenLayer par3GenLayer,
            int maxLandAdvance, int minSeaAdvance, int changeChances, boolean separate){
        super(par1);
        this.parent = par3GenLayer;
        this.changeChances = changeChances;
        this.maxLandAdvance = maxLandAdvance;
        this.minSeaAdvance = minSeaAdvance;
        this.separate = separate;
    }

    public final int changeChances;
    public final int maxLandAdvance;
    public final int minSeaAdvance;
    public final boolean separate;
    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int i1 = par1 - 1;
        int j1 = par2 - 1;
        int k1 = par3 + 2;
        int l1 = par4 + 2;
        int[] aint = this.parent.getInts(i1, j1, k1, l1);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for (int i2 = 0; i2 < par4; i2++)
        {
            for (int j2 = 0; j2 < par3; j2++)
            {
                int up = aint[j2 + 0 + (i2 + 1) * k1];
                int left = aint[j2 + 1 + (i2 + 0) * k1];
                int right = aint[j2 + 1 + (i2 + 2) * k1];
                int down = aint[j2 + 2 + (i2 + 1) * k1];
                int k3 = aint[j2 + 1 + (i2 + 1) * k1];
                this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));

                if (isOceanic(k3) && ((!isOceanic(up)) || (!isOceanic(left)) || (!isOceanic(right)) || (!isOceanic(down)))) {
                    int i4 = k3;
                    int hits = 0;
                    long savedSeed = savedChunkSeed();

                    if (!isOceanic(up) ) {
                        this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2+1));
                        if (this.nextInt(changeChances)< maxLandAdvance) {
                            if ((!separate)||this.acceptableNeighbors(up, aint, i2, j2, k1)){
                                i4 = up;
                                hits++;
                            }
                        }
                    }

                    if (!isOceanic(left)) {
                        this.initChunkSeed((long)(j2 + par1+1), (long)(i2 + par2));
                        if (this.nextInt(changeChances)< maxLandAdvance&&
                                ((!separate)||this.acceptableNeighbors(left, aint, i2, j2, k1))) {
                            //if ( (j2==8&&i2==9)) throw new RuntimeException(""+acceptableNeighbors(left, aint, i2, j2, k1)+
                                    //" " + separate +" "+message);
                           if (hits ==0){
                               i4 = left;
                           } else {
                               if (i4!=left) {
                                   restoreChunkSeed(savedSeed);
                                   if (nextInt(hits)==0) {
                                       i4= left;
                                   }
                               }
                           }
                           hits++;
                        }
                    }

                    if (!isOceanic(right)) {
                        savedSeed = savedChunkSeed();
                        this.initChunkSeed((long)(j2 + par1+1), (long)(i2 + par2+1));
                        if (this.nextInt(changeChances)< maxLandAdvance&&
                                ((!separate)||this.acceptableNeighbors(right, aint, i2, j2, k1))) {
                           if (hits ==0){
                               i4 = right;
                           } else {
                               if (i4!=right) {
                                   restoreChunkSeed(savedSeed);
                                   if (nextInt(hits)==0) {
                                       i4= right;
                                   }
                               }
                           }
                           hits++;
                        }
                    }

                    if (!isOceanic(down)) {
                        savedSeed = savedChunkSeed();
                        this.initChunkSeed((long)(j2 + par1+1), (long)(i2 + par2+1));
                        if (this.nextInt(changeChances)< maxLandAdvance&&
                                ((!separate)||this.acceptableNeighbors(down, aint, i2, j2, k1))) {
                           if (hits ==0){
                               i4 = down;
                           } else {
                               if (i4!=down) {
                                   restoreChunkSeed(savedSeed);
                                   if (nextInt(hits)==0) {
                                       i4= down;
                                   }
                               }
                           }
                           hits++;
                           if (i4 == 0) throw new RuntimeException();
                        } 
                    }
                    aint1[j2 + i2 * par3] = i4;
                }
                else if (!isOceanic(k3) && (isOceanic(up) || isOceanic(left) || isOceanic(right) || isOceanic(down))){
                    int i4 = k3;

                    if (isOceanic(up) ) {
                        this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2+1));
                        if (this.nextInt(changeChances)>= minSeaAdvance) {
                           i4 = up;
                        }
                    }

                    if (isOceanic(left)) {
                        this.initChunkSeed((long)(j2 + par1+1), (long)(i2 + par2));
                        if (this.nextInt(changeChances)>= minSeaAdvance) {
                           i4 = left;
                        }
                    }
                    if (isOceanic(right)) {
                        this.initChunkSeed((long)(j2 + par1+1), (long)(i2 + par2+1));
                        if (this.nextInt(changeChances)>= minSeaAdvance) {
                           i4=right;
                        }
                    }

                    if (isOceanic(down)) {
                        this.initChunkSeed((long)(j2 + par1+1), (long)(i2 + par2+1));
                        if (this.nextInt(changeChances)>= minSeaAdvance) {
                           i4=down;
                        }
                    }
                    aint1[j2 + i2 * par3] = i4;
                }
                else {
                    aint1[j2 + i2 * par3] = k3;
                }
            }
        }

        return aint1;
    }

}