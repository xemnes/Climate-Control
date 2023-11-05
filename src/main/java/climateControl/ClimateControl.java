//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

import climateControl.api.BiomePackageRegistry;
import climateControl.api.BiomeSettings;
import climateControl.api.CCDimensionSettings;
import climateControl.api.ClimateControlSettings;
import climateControl.api.DimensionalSettingsRegistry;
import climateControl.biomeSettings.BopPackage;
import climateControl.biomeSettings.ExternalBiomePackage;
import climateControl.utils.BiomeConfigManager;
import com.Zeno410Utils.Named;
import com.Zeno410Utils.PropertyManager;
import com.Zeno410Utils.Zeno410Logger;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
        modid = "geographicraft",
        name = "GeographiCraft",
        version = "0.8.7",
        acceptableRemoteVersions = "*",
        acceptedMinecraftVersions = "[1.12,1.12.2]"
)
public class ClimateControl {
    public static Logger logger = (new Zeno410Logger("ClimateControl")).logger();
    public static boolean testing = true;
    private ClimateControlSettings newSettings;
    private BiomeConfigManager addonConfigManager;
    private CCDimensionSettings dimensionSettings;
    private HashMap<Integer, WorldServer> servedWorlds = new HashMap();
    private Named<ClimateControlSettings> masterSettings;
    private File configDirectory;
    private File suggestedConfigFile;
    private ExternalBiomePackage externalBiomesPackage;
    public static final String geographicraftFolderName = "GeographiCraft";
    public static final String geographicraftConfigName = "geographicraft.cfg";
    private DimensionManager dimensionManager;

    public ClimateControl() {
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.addonConfigManager = new BiomeConfigManager("GeographiCraft");
        BiomePackageRegistry.instance = new BiomePackageRegistry(event.getSuggestedConfigurationFile().getParentFile(), this.addonConfigManager);
        this.externalBiomesPackage = new ExternalBiomePackage();
        DimensionalSettingsRegistry.instance = new DimensionalSettingsRegistry();
        this.newSettings = new ClimateControlSettings(WorldType.DEFAULT);
        this.masterSettings = Named.from("geographicraft.cfg", this.newSettings);
        this.configDirectory = event.getSuggestedConfigurationFile().getParentFile();
        this.addonConfigManager.initializeConfig(this.masterSettings, this.configDirectory);
        this.setupRegistry();
        this.newSettings.setDefaults(this.configDirectory);
        this.dimensionSettings = new CCDimensionSettings();
        this.addonConfigManager.saveConfigs(this.configDirectory, this.configDirectory, this.masterSettings);
    }

    @EventHandler
    public void load(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) throws Exception {
        this.newSettings.setDefaults(this.configDirectory);
        this.addonConfigManager.saveConfigs(this.configDirectory, this.configDirectory, this.masterSettings);
        Iterator var2 = BiomePackageRegistry.instance.biomeSettings().iterator();

        while(var2.hasNext()) {
            Named<BiomeSettings> addonSetting = (Named)var2.next();
            this.addonConfigManager.initializeConfig(addonSetting, this.configDirectory);
        }

        this.addonConfigManager.initializeConfig(this.dimensionSettings.named(), this.configDirectory);
    }

    @SubscribeEvent(
            priority = EventPriority.LOWEST
    )
    public void onWorldLoad(WorldEvent.Load event) {
        DimensionalSettingsRegistry.instance.onWorldLoad(event);
        if (this.dimensionManager == null) {
            MinecraftServer server = event.getWorld().getMinecraftServer();
            if (server != null) {
                this.dimensionManager = new DimensionManager(this.masterSettings, this.dimensionSettings, server);
            }
        }

        if (this.dimensionManager != null) {
            this.dimensionManager.onWorldLoad(event.getWorld());
        }

    }

    @SubscribeEvent
    public void onCreateSpawn(WorldEvent.CreateSpawnPosition event) {
        if (this.dimensionManager == null) {
            MinecraftServer server = event.getWorld().getMinecraftServer();
            if (server != null) {
                this.dimensionManager = new DimensionManager(this.masterSettings, this.dimensionSettings, server);
            }
        }

        if (this.dimensionManager != null) {
            this.dimensionManager.onCreateSpawn(event);
        }

    }

    @SubscribeEvent
    public void unloadWorld(WorldEvent.Unload event) {
        DimensionalSettingsRegistry.instance.unloadWorld(event);
        if (event.getWorld() instanceof WorldServer) {
            this.servedWorlds.remove(event.getWorld().provider.getDimension());
        }

    }

    @EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        this.newSettings.setDefaults(this.configDirectory);
        this.addonConfigManager.updateConfig(this.masterSettings, this.configDirectory, this.configDirectory);
        DimensionalSettingsRegistry.instance.serverStarted(event);
        File worldSaveDirectory = null;
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        String worldName = server.getFolderName();
        File worldConfigDirectory;
        if (server.isSinglePlayer()) {
            worldConfigDirectory = server.getFile("saves");
            worldSaveDirectory = new File(worldConfigDirectory, worldName);
        } else {
            PropertyManager settings = new PropertyManager(server.getFile("server.properties"));
            worldName = settings.getProperty("level-name", worldName);
            worldSaveDirectory = server.getFile(worldName);
        }

        worldConfigDirectory = new File(worldSaveDirectory, "worldSpecificConfig");
        this.addonConfigManager.updateConfig(this.dimensionSettings.named(), this.configDirectory, worldConfigDirectory);
    }

    @EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        this.addonConfigManager.updateConfig(this.masterSettings, this.configDirectory, this.configDirectory);
        Iterator var2 = BiomePackageRegistry.instance.biomeSettings().iterator();

        while(var2.hasNext()) {
            Named<BiomeSettings> addonSetting = (Named)var2.next();
            this.addonConfigManager.initializeConfig(addonSetting, this.configDirectory);
        }

        this.addonConfigManager.initializeConfig(this.dimensionSettings.named(), this.configDirectory);
        DimensionalSettingsRegistry.instance.serverStopped(event);
        this.dimensionManager = null;
    }

    @SubscribeEvent
    public void onWorldSave(WorldEvent.Save event) {
        World world = event.getWorld();
        if (!world.isRemote) {
            int dimension = world.provider.getDimension();
            if (dimension == 0) {
                ;
            }
        }
    }

    @EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        File directory = event.getServer().worlds[0].getChunkSaveLocation();
        directory = new File(directory, "worldSpecificConfig");
        directory.mkdir();
        if (this.dimensionManager == null && event.getServer() != null) {
            this.dimensionManager = new DimensionManager(this.masterSettings, this.dimensionSettings, event.getServer());
        }

    }

    @SubscribeEvent(
            priority = EventPriority.LOWEST
    )
    public void onBiomeGenInit(WorldTypeEvent.InitBiomeGens event) {
        if (this.dimensionManager == null) {
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            if (server != null) {
                this.dimensionManager = new DimensionManager(this.masterSettings, this.dimensionSettings, server);
            }
        }

        if (this.dimensionManager != null) {
            this.dimensionManager.onBiomeGenInit(event);
        }

    }

    public void setupRegistry() {
        try {
            BiomePackageRegistry.instance.register(new BopPackage());
        } catch (NoClassDefFoundError var2) {
        }

    }

    public void logBiomes() {
        Iterator<Biome> biomes = Biome.REGISTRY.iterator();

        while(biomes.equals(this)) {
            if (biomes.next() == null) {
            }
        }

    }
}
