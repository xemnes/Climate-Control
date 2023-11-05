//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

import climateControl.customGenLayer.GenLayerRiverMixWrapper;
import net.minecraft.world.biome.BiomeProvider;

public class WorldChunkManagerWrapper extends BiomeProvider {
    public WorldChunkManagerWrapper(GenLayerRiverMixWrapper riverMix) {
        GenLayerUpdater.accessGenLayer.setField(this, riverMix);
        GenLayerUpdater.accessBiomeIndex.setField(this, riverMix.voronoi());
    }
}
