//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.generator;

import com.Zeno410Utils.Acceptor;

public class IndirectDecoder extends Acceptor<Decoder> implements Decoder {
    private Decoder manager;

    public IndirectDecoder() {
    }

    public int decode(Integer code) {
        return this.manager.decode(code);
    }

    public void accept(Decoder accepted) {
        this.manager = accepted;
    }
}
