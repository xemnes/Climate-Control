//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import java.util.HashMap;
import java.util.Iterator;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

public class DimensionalSettingsRegistry {
    private HashMap<String, DimensionalSettingsModifier> modifiers = new HashMap();
    private HashMap<Integer, Long> dimensionalSeeds = new HashMap();
    public static DimensionalSettingsRegistry instance;

    public DimensionalSettingsRegistry() {
    }

    public void add(String tag, DimensionalSettingsModifier modifier) {
        this.modifiers.put(tag, modifier);
    }

    public void remove(String tag) {
        this.modifiers.remove(tag);
    }

    public boolean useCCIn(int dimension) {
        Iterator var2 = this.modifiers.values().iterator();

        DimensionalSettingsModifier modifier;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            modifier = (DimensionalSettingsModifier)var2.next();
        } while(!modifier.signalCCActive(dimension));

        return true;
    }

    public void modify(int dimension, ClimateControlSettings settings) {
        Iterator var3 = this.modifiers.values().iterator();

        while(var3.hasNext()) {
            DimensionalSettingsModifier modifier = (DimensionalSettingsModifier)var3.next();
            modifier.modify(dimension, settings);
        }

    }

    public void onWorldLoad(WorldEvent.Load event) {
        Iterator var2 = this.modifiers.values().iterator();

        while(var2.hasNext()) {
            DimensionalSettingsModifier modifier = (DimensionalSettingsModifier)var2.next();
            modifier.onWorldLoad(event);
        }

    }

    public void unloadWorld(WorldEvent.Unload event) {
        Iterator var2 = this.modifiers.values().iterator();

        while(var2.hasNext()) {
            DimensionalSettingsModifier modifier = (DimensionalSettingsModifier)var2.next();
            modifier.unloadWorld(event);
        }

    }

    public void serverStarted(FMLServerStartedEvent event) {
        Iterator var2 = this.modifiers.values().iterator();

        while(var2.hasNext()) {
            DimensionalSettingsModifier modifier = (DimensionalSettingsModifier)var2.next();
            modifier.serverStarted(event);
        }

    }

    public void serverStopped(FMLServerStoppedEvent event) {
        Iterator var2 = this.modifiers.values().iterator();

        while(var2.hasNext()) {
            DimensionalSettingsModifier modifier = (DimensionalSettingsModifier)var2.next();
            modifier.serverStopped(event);
        }

    }
}
