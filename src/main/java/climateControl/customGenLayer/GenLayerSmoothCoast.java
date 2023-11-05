//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.IntRandomizer;
import com.Zeno410Utils.Zeno410Logger;
import java.util.logging.Logger;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerSmoothCoast extends GenLayerPack {
    public static Logger logger = (new Zeno410Logger("SmoothCoast")).logger();
    private static final String __OBFID = "CL_00000569";
    private static int sinkLand = 7;
    private static int raiseWater = 10;
    private LandWaterChoices choices = new LandWaterChoices();
    private IntRandomizer passable = new IntRandomizer() {
        public int nextInt(int range) {
            return GenLayerSmoothCoast.this.nextInt(range);
        }
    };

    public GenLayerSmoothCoast(long par1, GenLayer par3GenLayer) {
        super(par1);
        super.parent = par3GenLayer;
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int i1 = par1 - 1;
        int j1 = par2 - 1;
        int k1 = par3 + 2;
        int l1 = par4 + 2;
        int[] aint = this.parent.getInts(i1, j1, k1, l1);
        this.taste(aint, k1 * l1);
        int[] aint1 = new int[par3 * par4];
        this.poison(aint1, par3 * par4);

        int i2;
        for(i2 = 0; i2 < par4; ++i2) {
            for(int j2 = 0; j2 < par3; ++j2) {
                int original = aint[j2 + 1 + (i2 + 1) * k1];
                boolean isOceanic = isOceanic(original);
                this.choices.setOriginal(original, isOceanic);
                int up = aint[j2 + 0 + (i2 + 1) * k1];
                int down = aint[j2 + 2 + (i2 + 1) * k1];
                int left = aint[j2 + 1 + (i2 + 0) * k1];
                int right = aint[j2 + 1 + (i2 + 2) * k1];
                this.choices.add(up, isOceanic(up));
                this.choices.add(down, isOceanic(down));
                this.choices.add(right, isOceanic(right));
                this.choices.add(left, isOceanic(left));
                if (!this.choices.equal() && this.choices.isChoiceWater() != isOceanic) {
                    this.initChunkSeed((long)(j2 + par1), (long)(i2 + par2));
                    int trip = isOceanic ? raiseWater : sinkLand;
                    if (this.nextInt(10) < trip) {
                        aint1[j2 + i2 * par3] = this.choices.mostCommon(this.passable);
                        if (aint1[j2 + i2 * par3] == -2) {
                            throw new RuntimeException("" + i2 + " " + j2);
                        }

                        if (aint1[j2 + i2 * par3] == -2) {
                            throw new RuntimeException();
                        }
                    } else {
                        aint1[j2 + i2 * par3] = original;
                        if (aint1[j2 + i2 * par3] == -2) {
                            throw new RuntimeException("" + i2 + " " + j2);
                        }
                    }
                } else {
                    aint1[j2 + i2 * par3] = original;
                    if (aint1[j2 + i2 * par3] == -2) {
                        throw new RuntimeException("" + i2 + " " + j2);
                    }
                }
            }
        }

        for(i2 = 0; i2 < par3 * par4; ++i2) {
            if (aint1[i2] > 256) {
                throw new RuntimeException();
            }
        }

        this.taste(aint1, par3 * par4);
        return aint1;
    }
}
