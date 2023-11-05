//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.WeakHashMap;

public abstract class DistributionPartitioner {
    private static HashMap<String, DistributionPartitioner> registered = new HashMap();
    protected final ArrayList<IncidenceModifier> modifiers;
    private WeakHashMap<BiomeRandomizer.PickByClimate, HashMap<IncidenceModifier, BiomeRandomizer.PickByClimate>> calculatedClimates = new WeakHashMap();

    public static void register(String name, DistributionPartitioner partitioner) {
        if (registered.containsKey(name)) {
            throw new RuntimeException("Partitioner " + name + " already registered");
        } else {
            registered.put(name, partitioner);
        }
    }

    public static Collection<DistributionPartitioner> registeredPartitioners() {
        return registered.values();
    }

    public static void unregister(String name) {
        registered.remove(name);
    }

    protected abstract IncidenceModifier modifier(int var1, int var2);

    public DistributionPartitioner(ArrayList<IncidenceModifier> modifiers) {
        this.modifiers = modifiers;
    }

    public abstract void initWorldGenSeed(long var1);

    public BiomeRandomizer.PickByClimate partitioned(BiomeRandomizer.PickByClimate global, int x, int z) {
        HashMap<IncidenceModifier, BiomeRandomizer.PickByClimate> grouping = (HashMap)this.calculatedClimates.get(global);
        if (grouping == null) {
            grouping = global.modifiedDistributions(this.modifiers);
            this.calculatedClimates.put(global, grouping);
        }

        return (BiomeRandomizer.PickByClimate)grouping.get(this.modifier(x, z));
    }
}
