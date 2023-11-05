//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.utils;

import java.util.WeakHashMap;

public class IntPad {
    private WeakHashMap<Thread, int[]> pads = new WeakHashMap();

    public IntPad() {
    }

    public synchronized int[] pad(int size) {
        int[] result = (int[])this.pads.get(Thread.currentThread());
        if (result != null && size == result.length) {
            return result;
        } else {
            result = new int[size];
            this.pads.put(Thread.currentThread(), result);
            return result;
        }
    }
}
