//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.generator;

import climateControl.api.BiomeSettings;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.AccessLong;
import com.Zeno410Utils.Accessor;
import net.minecraft.world.gen.layer.GenLayer;

public class TestGeneratorPair {
    private BiomeSettings oldGen;
    private GenLayer newGen;
    private Accessor<GenLayerPack, GenLayerPack> genLayerPackParent = new Accessor("field_75909_a");
    private Accessor<GenLayer, GenLayer> genLayerParent = new Accessor("field_75909_a");
    private AccessLong<GenLayer> genLayerSeed = new AccessLong("field_75907_b");

    public TestGeneratorPair(BiomeSettings oldGen, GenLayer newGen) {
        this.oldGen = oldGen;
        this.newGen = newGen;
    }

    public TestGeneratorPair next() {
        return new TestGeneratorPair(this.oldGen, this.newGen);
    }

    public boolean hasNext() {
        return this.oldGen != null || this.newGen != null;
    }

    public GenLayer parent(BiomeSettings child) {

            return null;
    }

    private String description(GenLayer described) {
        return described == null ? "missing" : described.getClass().getSimpleName() + " " + this.genLayerSeed.get(described);
    }
}
