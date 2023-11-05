//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.generator;

import com.Zeno410Utils.ListWrapper;
import java.util.Iterator;
import java.util.List;
import net.minecraft.world.biome.Biome;

public class VillageBiomes extends ListWrapper<Biome> {
    public VillageBiomes(List<Biome> biomes) {
        super(biomes);
    }

    public boolean contains(Object arg0) {
        if (!(arg0 instanceof Biome)) {
            return false;
        } else {
            Biome biome = (Biome)arg0;
            boolean result = super.contains(arg0);
            return result;
        }
    }

    public void reportMembers() {
        Biome var2;
        for(Iterator var1 = this.iterator(); var1.hasNext(); var2 = (Biome)var1.next()) {
        }

    }
}
