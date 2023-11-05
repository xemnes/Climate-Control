//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

import climateControl.api.BiomeSettings;
import climateControl.api.CCDimensionSettings;
import climateControl.api.ClimateControlSettings;
import climateControl.api.DimensionalSettingsRegistry;
import climateControl.customGenLayer.GenLayerLowlandRiverMix;
import climateControl.customGenLayer.GenLayerRiverMixWrapper;
import climateControl.genLayerPack.GenLayerPack;
import climateControl.generator.CorrectedContinentsGenerator;
import climateControl.generator.OneSixCompatibleGenerator;
import climateControl.generator.TestGeneratorPair;
import climateControl.generator.VanillaCompatibleGenerator;
import climateControl.utils.BiomeConfigManager;
import com.Zeno410Utils.Accessor;
import com.Zeno410Utils.Maybe;
import com.Zeno410Utils.Named;
import com.Zeno410Utils.PlaneLocation;
import com.Zeno410Utils.Zeno410Logger;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.init.Biomes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class DimensionManager {
    public static Logger logger = (new Zeno410Logger("DimensionManager")).logger();
    private Accessor<GenLayerRiverMix, GenLayerPack> riverMixBiome = new Accessor("field_75910_b");
    private final ClimateControlSettings newSettings;
    private final CCDimensionSettings dimensionSettings;
    private final Named<ClimateControlSettings> masterSettings;
    private HashMap<Integer, ClimateControlSettings> dimensionalSettings = new HashMap();
    private HashMap<Integer, GenLayerRiverMixWrapper> wrappers = new HashMap();
    private final File suggestedConfigFile;
    private final File configDirectory;
    private BiomeConfigManager addonConfigManager = new BiomeConfigManager("GeographiCraft");
    private GenLayer original;
    private HashSet<Integer> dimensionsDone = new HashSet();
    PlaneLocation lastTry = new PlaneLocation(Integer.MIN_VALUE, Integer.MIN_VALUE);

    public DimensionManager(Named<ClimateControlSettings> masterSettings, CCDimensionSettings dimensionSettings, MinecraftServer server) {
        this.masterSettings = masterSettings;
        this.newSettings = (ClimateControlSettings)masterSettings.object;
        this.dimensionSettings = dimensionSettings;
        if (server == null) {
            this.configDirectory = null;
            this.suggestedConfigFile = null;
        } else {
            this.configDirectory = server.getFile("config");
            this.suggestedConfigFile = new File(this.configDirectory, "geographicraft.cfg");
        }
    }

    private GenLayerRiverMix patchedGenLayer(ClimateControlSettings settings, WorldType worldType, long worldSeed) {
        Iterator newMix;
        BiomeSettings oldGen;
        for(newMix = settings.biomeSettings().iterator(); newMix.hasNext(); oldGen = (BiomeSettings)newMix.next()) {
        }

        if (this.ignore(worldType, settings)) {
            return null;
        } else if ((Boolean)settings.noGenerationChanges.value()) {
            return (Boolean)settings.oneSixCompatibility.value() ? (new OneSixCompatibleGenerator(settings)).fromSeed(worldSeed, worldType) : null;
        } else {
            (new SettingsTester()).test(settings);


            if ((Boolean)settings.vanillaLandAndClimate.value()) {
                newMix = (Iterator) (new VanillaCompatibleGenerator(settings)).fromSeed(worldSeed, worldType);
            } else {
                newMix = (Iterator) (new CorrectedContinentsGenerator(settings, this.configDirectory.getParentFile())).fromSeed(worldSeed, worldType);
            }

            oldGen = null;
            GenLayer newGen = (GenLayer)this.riverMixBiome.get((GenLayerRiverMix) newMix);

            for(TestGeneratorPair pair = new TestGeneratorPair(oldGen, newGen); pair.hasNext(); pair = pair.next()) {
            }

            if (newMix instanceof GenLayerLowlandRiverMix) {
                ((GenLayerLowlandRiverMix)newMix).setMaxChasm(((Double)settings.maxRiverChasm.value()).floatValue());
            }

            BiomeSettings var10;
            for(Iterator var9 = settings.biomeSettings().iterator(); var9.hasNext(); var10 = (BiomeSettings)var9.next()) {
            }

            return (GenLayerRiverMix) newMix;
        }
    }

    private ClimateControlSettings newSettings() {
        return this.newSettings;
    }

    private ClimateControlSettings defaultSettings(MinecraftFilesAccess dimension, boolean newWorld, WorldType worldType) {
        ClimateControlSettings result = this.defaultSettings(newWorld, worldType);
        if (!dimension.baseDirectory().exists()) {
            dimension.baseDirectory().mkdir();
            if (!dimension.baseDirectory().exists()) {
            }
        }

        Named<ClimateControlSettings> dimensionSetting = Named.from(this.masterSettings.name, result);
        this.addonConfigManager.updateConfig(dimensionSetting, this.configDirectory, dimension.configDirectory());
        this.addonConfigManager.saveConfigs(this.configDirectory, dimension.configDirectory(), dimensionSetting);
        Iterator var6 = result.registeredBiomeSettings().iterator();

        while(var6.hasNext()) {
            Named<BiomeSettings> addonSetting = (Named)var6.next();
            this.addonConfigManager.initializeConfig(addonSetting, this.configDirectory);
            this.addonConfigManager.updateConfig(addonSetting, this.configDirectory, dimension.configDirectory());
            if (newWorld) {
                ((BiomeSettings)addonSetting.object).onNewWorld();
                this.addonConfigManager.saveConfigs(this.configDirectory, dimension.configDirectory(), addonSetting);
            }
        }

        return result;
    }

    private ClimateControlSettings defaultSettings(boolean newWorld, WorldType worldType) {
        ClimateControlSettings result = new ClimateControlSettings(worldType);
        Named<ClimateControlSettings> namedResult = Named.from("geographicraft.cfg", result);
        this.addonConfigManager.initializeConfig(namedResult, this.configDirectory);
        result.setDefaults(this.configDirectory);

        Named addonSetting;
        for(Iterator var5 = result.registeredBiomeSettings().iterator(); var5.hasNext(); ((BiomeSettings)addonSetting.object).setNativeBiomeIDs(this.configDirectory)) {
            addonSetting = (Named)var5.next();
            if (newWorld) {
                ((BiomeSettings)addonSetting.object).onNewWorld();
            }

            this.addonConfigManager.initializeConfig(addonSetting, this.configDirectory);
            if (newWorld) {
                ((BiomeSettings)addonSetting.object).onNewWorld();
            }
        }

        return result;
    }

    private ClimateControlSettings dimensionalSettings(DimensionAccess dimension, boolean newWorld, WorldType worldType) {
        ClimateControlSettings result = (ClimateControlSettings)this.dimensionalSettings.get(dimension.dimension);
        if (result == null) {
            result = this.defaultSettings(dimension, newWorld, worldType);
            DimensionalSettingsRegistry.instance.modify(dimension.dimension, result);
            this.dimensionalSettings.put(dimension.dimension, result);
        }

        return result;
    }

    public void onBiomeGenInit(WorldTypeEvent.InitBiomeGens event) {
        ClimateControlSettings generationSettings = this.defaultSettings(true, event.getWorldType());
        generationSettings.onNewWorld();
        if (!this.ignore(event.getWorldType(), this.newSettings)) {
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            if (server == null) {
                this.original = event.getOriginalBiomeGens()[0];
            } else {
                boolean newWorld = true;
                WorldServer[] var5 = server.worlds;
                int var6 = var5.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    WorldServer worldServer = var5[var7];
                    if (worldServer.getTotalWorldTime() > 0L) {
                        newWorld = false;
                    }
                }

                this.original = event.getOriginalBiomeGens()[0];
                GenLayerRiverMixWrapper result = new GenLayerRiverMixWrapper(event.getSeed(), this.original, this);
                if ((Boolean)generationSettings.noGenerationChanges.value()) {
                    event.setNewBiomeGens(result.modifiedGenerators());
                    event.setResult(Result.ALLOW);
                } else {
                    if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
                        this.patchedGenLayer(generationSettings, event.getWorldType(), event.getSeed());
                        event.setNewBiomeGens(result.modifiedGenerators());
                        event.setResult(Result.ALLOW);
                    }

                }
            }
        }
    }

    public void serverStopped(FMLServerStoppedEvent event) {
        new HashMap();
        this.dimensionalSettings = new HashMap();
    }

    private boolean ignore(WorldType considered, ClimateControlSettings settings) {
        if (considered == null) {
            return true;
        } else {
            if (!(Boolean)settings.suppressInStandardWorlds.value()) {
                if (considered.equals(WorldType.AMPLIFIED)) {
                    return false;
                }

                if (considered.equals(WorldType.DEFAULT)) {
                    return false;
                }

                if (considered.equals(WorldType.DEFAULT_1_1)) {
                    return false;
                }

                if (considered.equals(WorldType.LARGE_BIOMES)) {
                    return false;
                }
            }

            if ((Boolean)settings.interveneInCustomizedWorlds.value() && considered.equals(WorldType.CUSTOMIZED)) {
                return false;
            } else if (considered.equals(WorldType.FLAT)) {
                return true;
            } else if (considered.getName().equalsIgnoreCase("TerrainControl")) {
                return false;
            } else if ((Boolean)settings.interveneInBOPWorlds.value() && considered.getName().equalsIgnoreCase("BIOMESOP")) {
                return false;
            } else {
                if ((Boolean)settings.interveneInHighlandsWorlds.value()) {
                    if (considered.getName().equalsIgnoreCase("Highlands")) {
                        return false;
                    }

                    if (considered.getName().equalsIgnoreCase("HighlandsLB")) {
                        return false;
                    }
                }

                if (considered.getName().equalsIgnoreCase("FWG")) {
                    return false;
                } else {
                    return !considered.getName().equalsIgnoreCase("RTG");
                }
            }
        }
    }

    public void onCreateSpawn(WorldEvent.CreateSpawnPosition event) {
        WorldServer world = (WorldServer)((WorldServer)event.getWorld());
        if (!this.ignore(world.getWorldType(), this.newSettings)) {
            int dimension = world.provider.getDimension();
            if (this.dimensionSettings.ccOnIn(dimension) || DimensionalSettingsRegistry.instance.useCCIn(dimension)) {
                this.onWorldLoad(event.getWorld());
                this.salvageSpawn(event.getWorld());
                if (event.getSettings().isBonusChestEnabled()) {
                    Random rand = new Random(world.getSeed());
                    WorldGeneratorBonusChest worldgeneratorbonuschest = new WorldGeneratorBonusChest();

                    for(int i = 0; i < 100; ++i) {
                        int j = world.getWorldInfo().getSpawnX() + rand.nextInt(6 + i / 10) - rand.nextInt(6 + i / 10);
                        int k = world.getWorldInfo().getSpawnZ() + rand.nextInt(6 + i / 10) - rand.nextInt(6 + i / 10);

                        BlockPos topBlockSpot;
                        for(topBlockSpot = new BlockPos(j, world.getActualHeight() - 1, k); !world.getBlockState(topBlockSpot).isSideSolid(world, topBlockSpot, EnumFacing.UP); topBlockSpot = topBlockSpot.down()) {
                        }

                        BlockPos above = topBlockSpot.up();
                        if (world.getBlockState(above).getBlock().isAir(world.getBlockState(above), world, above) && worldgeneratorbonuschest.generate(world, rand, above)) {
                            break;
                        }
                    }
                }

                event.setCanceled(true);
            }
        }
    }

    public void onWorldLoad(World world) {
    }

    public Maybe<GenLayerRiverMix> getGeographicraftGenlayers(WorldServer world, int dimension, GenLayer original) {
        if (this.ignore(world.getWorldType(), this.newSettings)) {
            return Maybe.unknown();
        } else if (!this.dimensionSettings.ccOnIn(dimension) && !DimensionalSettingsRegistry.instance.useCCIn(dimension)) {
            return Maybe.unknown();
        } else {
            DimensionAccess dimensionAccess = new DimensionAccess(dimension, world);
            long worldSeed = world.getSeed();
            if (world instanceof WorldServer && worldSeed != 0L) {
                ClimateControlSettings currentSettings = null;
                boolean newWorld = false;
                if (world.getWorldInfo().getWorldTotalTime() < 100L) {
                    newWorld = true;
                }

                currentSettings = this.dimensionalSettings(dimensionAccess, newWorld, world.getWorldType());

                try {
                    GenLayerRiverMix patched = this.patchedGenLayer(currentSettings, world.getWorldType(), worldSeed);
                    if (patched != null) {
                        Accessor<GenLayerRiverMix, GenLayer> riverMixBiome = new Accessor("field_75910_b");
                        GenLayer lockable = (GenLayer)riverMixBiome.get(patched);
                        (new LockGenLayers()).lock(lockable, dimension, world, currentSettings);
                        return Maybe.definitely(patched);
                    } else {
                        LockGenLayers biomeLocker = new LockGenLayers();
                        Accessor<GenLayerRiverMix, GenLayer> riverMixBiome = new Accessor("field_75910_b");
                        original = (GenLayer)riverMixBiome.get((GenLayerRiverMix)original);
                        biomeLocker.lock(original, dimension, world, currentSettings);
                        return Maybe.unknown();
                    }
                } catch (Exception var12) {
                    logger.info(var12.toString());
                    logger.info(var12.getMessage());
                    return Maybe.unknown();
                } catch (Error var13) {
                    logger.info(var13.toString());
                    logger.info(var13.getMessage());
                    return Maybe.unknown();
                }
            } else {
                return Maybe.unknown();
            }
        }
    }

    private String controllingGenLayer(World world) {
        BiomeProvider chunkManager = world.getBiomeProvider();
        Accessor<BiomeProvider, GenLayer> worldGenLayer = new Accessor("field_76944_d");
        return ((GenLayer)worldGenLayer.get(chunkManager)).toString();
    }

    private void salvageSpawn(World world) {
        WorldInfo info = world.getWorldInfo();
        int x = info.getSpawnX() / 16 * 16 + (Integer)this.newSettings().xSpawnOffset.value();
        int z = info.getSpawnZ() / 16 * 16 + (Integer)this.newSettings().zSpawnOffset.value();
        int move = 0;
        int ring = 0;
        int xMove = 0;
        int zMove = 0;
        int spawnX = 0;
        int spawnZ = 0;
        int spawnY = 0;
        world.getBiomeForCoordsBody(new BlockPos(x, 64, z));
        int nextTry = 50;
        int nextTryIncrement = 80;
        int nextTryStretch = 20;
        IChunkProvider chunkManager = world.getChunkProvider();
        ChunkProviderServer chunkServer = null;

        try {
            chunkServer = (ChunkProviderServer)chunkManager;
        } catch (ClassCastException var22) {
            throw var22;
        }

        int checked = 0;
        int rescueTries = 0;

        while(true) {
            if (spawnY < 64) {
                if ((Integer)this.newSettings.rescueSearchLimit.value() == rescueTries++) {
                    return;
                }

                if (checked > 50) {
                    if (chunkServer != null) {
                        chunkServer.queueUnloadAll();
                        chunkServer.chunkLoader.flush();
                    }

                    checked = 0;
                }

                if (chunkServer != null) {
                }

                ++checked;
                ++move;
                if (move > ring * (ring + 1) * 4) {
                    ++ring;
                }

                if (move < nextTry) {
                    continue;
                }

                nextTryIncrement += nextTryStretch++;
                nextTry += nextTryIncrement;
                int inRing = move - (ring - 1) * ring * 4;
                if (inRing > ring * 6) {
                    xMove = -ring;
                    zMove = ring - (inRing - ring * 6) + 1;
                } else if (inRing > ring * 4) {
                    zMove = ring;
                    xMove = ring - (inRing - ring * 4) + 1;
                } else if (inRing > ring * 2) {
                    xMove = ring;
                    zMove = -ring + (inRing - ring * 2) - 1;
                } else {
                    zMove = -ring;
                    xMove = -ring + inRing - 1;
                }

                IntCache.resetIntCache();
                logger.info("checking for spawn at " + (x + xMove * 16) + "," + (z + zMove * 16) + "move " + move + " ring " + ring + " inRing " + inRing + " caches " + IntCache.getCacheSizes() + " dimension " + world.provider.getDimension());
                Biome checkSpawn = world.getBiomeForCoordsBody(new BlockPos(x + xMove * 16, 64, z + zMove * 16));
                spawnX = x + xMove * 16;
                spawnZ = z + zMove * 16;
                logger.info("setting spawn at " + spawnX + "," + spawnZ);
                if (checkSpawn == Biomes.MUSHROOM_ISLAND) {
                    continue;
                }

                spawnY = world.getTopSolidOrLiquidBlock(new BlockPos(spawnX, 64, spawnZ)).getY() + 1;
                PlaneLocation newLocation = new PlaneLocation(spawnX, spawnZ);
                if (!newLocation.equals(this.lastTry)) {
                    continue;
                }
            }

            world.setSpawnPoint(new BlockPos(spawnX, spawnY, spawnZ));
            return;
        }
    }

    private String levelSavePath(WorldServer world) {
        String result = "";
        result = world.getChunkSaveLocation().getAbsolutePath();
        return result;
    }

    private boolean hasOnlySea(Chunk tested) {
        byte[] biomes = tested.getBiomeArray();
        byte[] var3 = biomes;
        int var4 = biomes.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte biome = var3[var5];
            if (biome != 0 && biome != Biome.getIdForBiome(Biomes.DEEP_OCEAN)) {
                return false;
            }
        }

        return true;
    }

    private boolean isSea(Biome tested) {
        if (tested == Biomes.OCEAN) {
            return true;
        } else if (tested == Biomes.DEEP_OCEAN) {
            return true;
        } else {
            return tested == Biomes.MUSHROOM_ISLAND;
        }
    }
}
