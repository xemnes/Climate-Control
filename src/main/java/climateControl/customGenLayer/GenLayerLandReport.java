//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.Receiver;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerLandReport extends GenLayerPack {
    private boolean reported = false;
    private final Receiver<String> reportee;
    private final int distanceFromOrigin;

    public GenLayerLandReport(GenLayer parent, int distance, Receiver<String> reportee) {
        super(0L);
        this.parent = parent;
        this.distanceFromOrigin = distance;
        this.reportee = reportee;
    }

    public int[] getInts(int var1, int var2, int var3, int var4) {
        if (!this.reported) {
            this.report();
            this.reported = true;
        }

        return this.parent.getInts(var1, var2, var3, var4);
    }

    private void report() {
        int width = 2 * this.distanceFromOrigin + 1;
        int[] toReport = this.parent.getInts(-this.distanceFromOrigin, -this.distanceFromOrigin, 2 * this.distanceFromOrigin + 1, 2 * this.distanceFromOrigin + 1);
        int total = 0;
        int[] climates = new int[]{0, 0, 0, 0, 0};

        int i;
        String report;
        int j;
        for(i = 0; i < 2 * this.distanceFromOrigin + 1; ++i) {
            report = "";

            for(j = 0; j < 2 * this.distanceFromOrigin + 1; ++j) {
                int value = toReport[i * (2 * this.distanceFromOrigin + 1) + j];
                if (value == 24) {
                    value = 0;
                }

                if (value == 0) {
                    report = report + ". ";
                } else {
                    if (value > 0 && value < 100000) {
                        int climate = value % 4;
                        if (climate == 0) {
                            climate = 4;
                        }

                        int var10002 = climates[climate]++;
                        String result = "" + climate + " ";
                        report = report + result;
                    } else {
                        report = report + "1 ";
                    }

                    ++total;
                }
            }

            this.reportee.accept(report);
        }

        this.reportee.accept("");
        this.reportee.accept("Land " + total + " of " + width * width + " " + (int)((double)((float)(total * 100) / (float)(width * width)) + 0.5) + "%");
        if (climates[2] > 0 || climates[3] > 0 || climates[4] > 0) {
            this.reportee.accept("Hot   " + climates[1] + " " + (int)((float)(climates[1] * 100) / (float)total) + "%");
            this.reportee.accept("Warm  " + climates[2] + " " + (int)((float)(climates[2] * 100) / (float)total) + "%");
            this.reportee.accept("Cool  " + climates[3] + " " + (int)((float)(climates[3] * 100) / (float)total) + "%");
            this.reportee.accept("Snowy " + climates[4] + " " + (int)((float)(climates[4] * 100) / (float)total) + "%");
        }

        this.reportee.accept("");

        for(i = 0; i < 2 * this.distanceFromOrigin + 1; ++i) {
            report = "";

            for(j = 0; j < 2 * this.distanceFromOrigin + 1; ++j) {
                report = report + i + " ";
            }
        }

        this.reportee.accept("");

        for(i = 0; i < 2 * this.distanceFromOrigin + 1; ++i) {
            report = "";

            for(j = 0; j < 2 * this.distanceFromOrigin + 1; ++j) {
                report = report + j + " ";
            }
        }

        this.reportee.done();
    }
}
