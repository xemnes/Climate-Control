//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.generator;

import java.util.HashMap;

public class Encoder implements Decoder {
    private HashMap<Integer, Integer> encodings;

    public Encoder(int[] matrix) {
        this.encodings = new HashMap(matrix.length);
    }

    public Encoder(int size) {
        this.encodings = new HashMap(size);
    }

    public int decode(Integer code) {
        return (Integer)this.encodings.get(code);
    }

    public void encode(Integer code, Integer biome) {
        this.encodings.put(code, biome);
    }
}
