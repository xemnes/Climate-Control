//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

import climateControl.api.ClimateControlSettings;
import climateControl.customGenLayer.GenLayerBiomeByClimate;
import climateControl.customGenLayer.GenLayerBiomeByTaggedClimate;
import climateControl.customGenLayer.GenLayerRandomBiomes;
import climateControl.customGenLayer.GenLayerSmoothClimate;
import climateControl.genLayerPack.GenLayerHillsOneSix;
import climateControl.genLayerPack.GenLayerOneSixBiome;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.Acceptor;
import com.Zeno410Utils.Accessor;
import com.Zeno410Utils.ChunkLister;
import com.Zeno410Utils.Filter;
import com.Zeno410Utils.PlaneLocation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.IntCache;

public class LockGenLayers {
    private HashSet<LockGenLayer> toGenerateFor = new HashSet();
    public static final int LOCATIONS_BEFORE_RESET = 1;
    LockGenLayer biomeLock;
    LockGenLayer subBiomeLock;
    private Accessor<GenLayer, GenLayer> genLayerParent;

    public LockGenLayers() {
        this.biomeLock = new LockGenLayer("Biomes", this.isBiomeLayer(), Acceptor.to(this.toGenerateFor));
        this.subBiomeLock = new LockGenLayer("SubBiomes", this.isSubBiomeLayer(), Acceptor.to(this.toGenerateFor));
        this.genLayerParent = new Accessor("field_75909_a");
    }

    public void showGenLayers(GenLayer top) {
        LockGenLayer var10000 = this.biomeLock;
        LockGenLayer.showGenLayers(top);
    }

    public void lock(GenLayer top, int dimension, World world, ClimateControlSettings settings) {
        ClimateControl.logger.info("locking generation on " + top.toString());
        boolean fixed;
        if ((Integer)settings.climateRingsNotSaved.value() > -1 && !(Boolean)settings.oneSixCompatibility.value()) {
            LockGenLayer climateLock = new LockGenLayer("Climate", this.smoothClimateParent(), Acceptor.to(this.toGenerateFor));
            fixed = climateLock.lock(top, dimension, world, (Integer)settings.climateRingsNotSaved.value(), false);
            if (!fixed) {
                climateLock = new LockGenLayer("Climate", this.mushroomIslandParent(), Acceptor.to(this.toGenerateFor));
                climateLock.lock(top, dimension, world, (Integer)settings.climateRingsNotSaved.value(), false);
            }
        }

        if ((Integer)settings.biomeRingsNotSaved.value() > -1) {
            this.biomeLock.lock(top, dimension, world, (Integer)settings.biomeRingsNotSaved.value(), false);
        }

        if ((Integer)settings.subBiomeRingsNotSaved.value() > -1) {
            this.subBiomeLock.lock(top, dimension, world, (Integer)settings.subBiomeRingsNotSaved.value(), false);
        }

        if (world instanceof WorldServer) {
            if (this.toGenerateFor.size() > 0) {
                ArrayList<PlaneLocation> existingChunks = (new ChunkLister()).savedChunks(this.levelSavePath((WorldServer)world));
                LockGenLayer.logger.info("chunk count" + existingChunks.size());
                fixed = false;
                int sinceLastReset = 0;

                PlaneLocation chunkLocation;
                for(Iterator var8 = existingChunks.iterator(); var8.hasNext(); top.getInts(chunkLocation.x() << 2, chunkLocation.z() << 2, 4, 4)) {
                    chunkLocation = (PlaneLocation)var8.next();
                    ++sinceLastReset;
                    if (sinceLastReset > 1) {
                        IntCache.resetIntCache();
                        sinceLastReset = 0;
                    }
                }

                IntCache.resetIntCache();
            }

        }
    }

    private String levelSavePath(WorldServer world) {
        String result = "";
        result = world.getChunkSaveLocation().getAbsolutePath();
        return result;
    }

    private Filter<GenLayer> isBiomeLayer() {
        return new Filter<GenLayer>() {
            public boolean accepts(GenLayer tested) {
                if (tested == null) {
                    return false;
                } else if (tested instanceof GenLayerBiome) {
                    return true;
                } else if (tested instanceof GenLayerBiomeByClimate) {
                    return true;
                } else if (tested instanceof GenLayerBiomeByTaggedClimate) {
                    return true;
                } else if (tested instanceof GenLayerRandomBiomes) {
                    return true;
                } else if (tested instanceof GenLayerOneSixBiome) {
                    return true;
                } else if (tested.getClass().getCanonicalName().contains("BiomeLayerBiomes")) {
                    return true;
                } else if (tested.getClass().getCanonicalName().contains("GenLayerBiomeEdgeHL")) {
                    return true;
                } else {
                    return tested.getClass().getCanonicalName().contains("GenLayerBiomeByTaggedClimate");
                }
            }
        };
    }

    private Filter<GenLayer> isSubBiomeLayer() {
        return new Filter<GenLayer>() {
            public boolean accepts(GenLayer tested) {
                if (tested == null) {
                    return false;
                } else if (tested.getClass().getCanonicalName().contains("GenLayerRareBiome")) {
                    return true;
                } else if (tested.getClass().getCanonicalName().contains("BiomeLayerSub")) {
                    return true;
                } else {
                    return tested instanceof GenLayerHillsOneSix;
                }
            }
        };
    }

    private Filter<GenLayer> smoothClimateParent() {
        return new Filter<GenLayer>() {
            private GenLayer smoothClimateLayer = null;

            public boolean accepts(GenLayer tested) {
                if (tested == null) {
                    return false;
                } else if (!(tested instanceof GenLayerSmoothClimate) && !(tested instanceof GenLayerBiomeByTaggedClimate)) {
                    if (this.smoothClimateLayer == null) {
                        return false;
                    } else {
                        return tested.equals(LockGenLayers.this.parent(this.smoothClimateLayer));
                    }
                } else {
                    this.smoothClimateLayer = tested;
                    return false;
                }
            }
        };
    }

    private Filter<GenLayer> mushroomIslandParent() {
        return new Filter<GenLayer>() {
            private GenLayer mushroomIslandLayer = null;

            public boolean accepts(GenLayer tested) {
                if (tested == null) {
                    return false;
                } else if (tested.getClass().getName().contains("GenLayerAddMushroomIsland")) {
                    this.mushroomIslandLayer = tested;
                    return false;
                } else if (this.mushroomIslandLayer == null) {
                    return false;
                } else {
                    return tested.equals(LockGenLayers.this.parent(this.mushroomIslandLayer));
                }
            }
        };
    }

    public GenLayer parent(GenLayer child) {
        return child instanceof GenLayerPack ? ((GenLayerPack)child).getParent() : (GenLayer)this.genLayerParent.get(child);
    }
}
