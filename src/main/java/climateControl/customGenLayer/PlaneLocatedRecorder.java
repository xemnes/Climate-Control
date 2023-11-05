//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import com.Zeno410Utils.Acceptor;
import com.Zeno410Utils.PlaneLocated;
import com.Zeno410Utils.PlaneLocation;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

class PlaneLocatedRecorder extends Acceptor<PlaneLocated<Integer>> {
    final DataOutputStream recording;
    final HashSet<PlaneLocation> alreadyRecorded = new HashSet();

    public PlaneLocatedRecorder(DataOutputStream target) {
        this.recording = target;
    }

    public void writeSeed(long seed) {
        try {
            this.recording.writeUTF("seed " + seed + '\r');
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    public void accept(PlaneLocated<Integer> accepted) {
        Iterator var2 = accepted.locations().iterator();

        while(var2.hasNext()) {
            PlaneLocation location = (PlaneLocation)var2.next();
            if (!this.alreadyRecorded.contains(location)) {
                this.alreadyRecorded.add(location);
                String toWrite = "" + location.x() + '\t' + location.z() + '\t' + accepted.get(location) + '\r';

                try {
                    this.recording.writeUTF(toWrite);
                } catch (IOException var6) {
                    throw new RuntimeException(var6);
                }
            }
        }

    }
}
