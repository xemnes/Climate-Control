//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.DimensionManager;
import climateControl.LockGenLayers;
import com.Zeno410Utils.Accessor;
import com.Zeno410Utils.Maybe;
import com.Zeno410Utils.Zeno410Logger;
import java.util.logging.Logger;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;

public class GenLayerRiverMixWrapper extends GenLayerRiverMix {
    public static Logger logger = (new Zeno410Logger("GenLayerRiverMixWrapper")).logger();
    private GenLayer redirect;
    private GenLayer voronoi;
    private GenLayer original;
    private Accessor<GenLayerRiverMix, GenLayer> riverMixBiome = new Accessor("field_75910_b");
    private LockGenLayers biomeLocker = new LockGenLayers();
    private DimensionManager dimensionManager;
    private boolean found = false;

    private static GenLayer dummyGenLayer() {
        return new GenLayer(0L) {
            public int[] getInts(int var1, int var2, int var3, int var4) {
                return new int[var3 * var4];
            }
        };
    }

    public GenLayerRiverMixWrapper(long baseSeed, GenLayer nonGCLayers, DimensionManager dimensionManager) {
        super(baseSeed, dummyGenLayer(), dummyGenLayer());
        this.voronoi = new GenLayerVoronoiZoom(baseSeed, this);
        this.original = nonGCLayers;
        this.dimensionManager = dimensionManager;
    }

    private void findSelf() {
        if (!this.found) {
            Integer[] dimensions = net.minecraftforge.common.DimensionManager.getIDs();
            Integer[] var2 = dimensions;
            int var3 = dimensions.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Integer dimension = var2[var4];
                BiomeProvider provider = net.minecraftforge.common.DimensionManager.getProvider(dimension).getBiomeProvider();
                GenLayer topLayer = this.workingGenLayer(provider);
                if (topLayer == this) {
                    logger.info(topLayer.toString() + " dimension " + dimension);
                    this.found = true;
                    WorldServer world = net.minecraftforge.common.DimensionManager.getWorld(dimension);
                    Maybe<GenLayerRiverMix> gcLayers = this.dimensionManager.getGeographicraftGenlayers(world, dimension, this.original);
                    if (gcLayers.isKnown()) {
                        this.redirect = (GenLayer)gcLayers.iterator().next();
                    } else {
                        this.redirect = this.original;
                    }

                    return;
                }

                logger.info(dimension + " not a match for " + this.original.toString());
            }

            this.found = true;
            this.redirect = this.original;
        }
    }

    private GenLayer workingGenLayer(BiomeProvider provider) {
        Accessor<BiomeProvider, GenLayer> worldGenLayer = new Accessor("field_76944_d");
        return (GenLayer)worldGenLayer.get(provider);
    }

    public int[] getInts(int arg0, int arg1, int arg2, int arg3) {
        this.findSelf();
        return this.redirect.getInts(arg0, arg1, arg2, arg3);
    }

    public void initChunkSeed(long par1, long par3) {
        super.initChunkSeed(par1, par3);
        this.findSelf();
        this.redirect.initChunkSeed(par1, par3);
    }

    public void initWorldGenSeed(long arg0) {
        super.initWorldGenSeed(arg0);
        this.findSelf();
        this.redirect.initWorldGenSeed(arg0);
    }

    public GenLayer voronoi() {
        return this.voronoi;
    }

    public GenLayer[] modifiedGenerators() {
        return new GenLayer[]{this, this.voronoi, this};
    }
}
