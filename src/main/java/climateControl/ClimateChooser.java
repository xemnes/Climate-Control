//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

import com.Zeno410Utils.IntRandomizer;
import com.Zeno410Utils.RandomIntUser;

public class ClimateChooser extends RandomIntUser {
    private final int hotLevel;
    private final int warmLevel;
    private final int coldLevel;
    private final int totalLevel;

    public ClimateChooser(int hot, int warm, int cold, int snow) {
        this.hotLevel = hot;
        this.warmLevel = hot + warm;
        this.coldLevel = hot + warm + cold;
        this.totalLevel = hot + warm + cold + snow;
    }

    public int value(IntRandomizer randomizer) {
        int l2 = randomizer.nextInt(this.totalLevel);
        byte b0;
        if (l2 < this.hotLevel) {
            b0 = 1;
        } else if (l2 < this.warmLevel) {
            b0 = 2;
        } else if (l2 < this.coldLevel) {
            b0 = 3;
        } else {
            b0 = 4;
        }

        return b0;
    }
}
