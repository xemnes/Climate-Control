//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

import com.Zeno410Utils.Zeno410Logger;
import java.util.logging.Logger;

public class ExploreSpecial {
    public static Logger logger = (new Zeno410Logger("ExploreSpecial")).logger();

    public ExploreSpecial() {
    }

    public void look() {
        for(int climate = 1; climate < 5; ++climate) {
            for(int special = 0; special < 16; ++special) {
                int k1 = climate | 1 + special << 8 & 3840;
                int l1 = (k1 & 3840) >> 8;
                int k2 = k1 & -3841;
                logger.info("climate " + climate + " flag " + special + " stored " + k1 + " l1 " + l1 + " k2 " + k2);
            }
        }

        throw new RuntimeException();
    }
}
