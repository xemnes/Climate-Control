//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

public abstract class DimensionalSettingsModifier {
    public DimensionalSettingsModifier() {
    }

    public abstract boolean signalCCActive(int var1);

    public abstract void modify(int var1, ClimateControlSettings var2);

    public abstract void onWorldLoad(WorldEvent.Load var1);

    public abstract void unloadWorld(WorldEvent.Unload var1);

    public abstract void serverStarted(FMLServerStartedEvent var1);

    public abstract void serverStopped(FMLServerStoppedEvent var1);
}
