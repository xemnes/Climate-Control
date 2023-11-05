//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import climateControl.biomeSettings.BoPSettings;
import climateControl.biomeSettings.ExternalBiomePackage;
import climateControl.biomeSettings.OceanBiomeSettings;
import climateControl.biomeSettings.VanillaBiomeSettings;
import climateControl.generator.MountainFormer;
import com.Zeno410Utils.Acceptor;
import com.Zeno410Utils.Mutable;
import com.Zeno410Utils.Named;
import com.Zeno410Utils.Settings;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.config.Configuration;

public class ClimateControlSettings extends Settings {
    private static final String halfSizeName = "Half Zone Size";
    private static final String quarterSizeName = "Quarter Zone Size";
    private static final String randomBiomesName = "Random Biomes";
    private static final String allowDerpyIslandsName = "Allow Derpy Islands";
    private static final String moreOceanName = "More Ocean";
    private static final String largeContinentFrequencyName = "Incidence of Continents,Large";
    private static final String mediumContinentFrequencyName = "Incidence of Continents,Medium";
    private static final String smallContinentFrequencyName = "Incidence of Continents,Small";
    private static final String largeIslandFrequencyName = "Incidence of Islands,Large";
    private static final String mediumIslandFrequencyName = "Incidence of Islands,Medium";
    private static final String hotIncidenceName = "Hot Zone Incidence";
    private static final String warmIncidenceName = "Warm Zone Incidence";
    private static final String coolIncidenceName = "Cool Zone Incidence";
    private static final String snowyIncidenceName = "Snowy Zone Incidence";
    private static final String vanillaBiomesOnName = "VanillaBiomesOn";
    private static final String highlandsBiomesOnName = "HighlandsBiomesOn";
    private static final String bopBiomesOnName = "BoPBiomesOn";
    private static final String thaumcraftBiomesOnName = "ThaumcraftBiomesOn";
    private static final String biomeSizeName = "Biome Size";
    private static final String vanillaStructure = "VanillaLandAndClimate";
    private static final String mushroomIslandIncidenceName = "Mushroom Island Incidence";
    private static final String percentageRiverReductionName = "PercentRiverReduction";
    private static final String widerRiversName = "WiderRivers";
    private static final String oneSixCompatibilityName = "1.6Compatibility";
    private static final String oneSixExpansionName = "1.6Expansions";
    private static final String noBoPSubBiomesName = "NoBoPSubBiomes";
    private static final String separateLandmassesName = "SeparateLandmasses";
    private static final String interveneInBOPName = "alterBoPWorlds";
    private static final String interveneInHighlandsName = "alterHighlandsWorlds";
    private static final String suppressInStandardWorldsName = "suppressInStandardWorlds";
    private static final String interveneInCustomizedName = "alterCustomWorlds";
    private static final String controlVillageBiomesName = "controlVillageBiomes";
    private static final String wideBeachesName = "wideBeaches";
    private static final String forceStartContinentName = "forceStartContinent";
    private static final String cacheSizeName = "cacheSize";
    private static final String externalBiomesListName = "externalBiomeNames";
    private static final String rescueLimitName = "rescueLimit";
    private static final String bandedClimateWidthName = "bandedClimateWidth";
    private static final String bandedClimateOffsetName = "bandedClimateOffset";
    private static final String xSpawnOffsetName = "xSpawnOffset";
    private static final String zSpawnOffsetName = "zSpawnOffset";
    private static final String mountainsChains = "Mountains in Mountain Chains";
    private static final String frozenIcecapName = "Frozen Icecaps";
    private static final String landExpansionRoundsName = "Land Expansion Rounds";
    private static final String forceIceMountainsName = "Ice Mountains in Mountain Chains";
    private static final String forceMoutainMesasName = "Mesas in Mountain Chains";
    private static final String mesaMesaBordersName = "Mesas for mesa borders";
    private static final String extremeClimateSeparationName = "ExtremeClimateSeparation";
    private WorldType worldType;
    private final Settings.Category climateZoneCategory;
    public final Mutable<Boolean> halfSize;
    public final Mutable<Boolean> quarterSize;
    public final Mutable<Boolean> randomBiomes;
    public final Mutable<Boolean> mountainChains;
    public final Mutable<Boolean> forceIceMountains;
    public final Mutable<Boolean> MesaMountains;
    public final Mutable<Integer> bandedClimateWidth;
    public final Mutable<Integer> bandedClimateOffset;
    public final Mutable<Integer> xSpawnOffset;
    public final Mutable<Integer> zSpawnOffset;
    public final Mutable<Boolean> frozenIcecaps;
    private final Settings.Category climateControlCategory;
    public final Mutable<Boolean> allowDerpyIslands;
    public final Mutable<Double> maxRiverChasm;
    public final Mutable<Boolean> testingMode;
    public final Mutable<Integer> biomeSize;
    public final Mutable<Boolean> noGenerationChanges;
    public final Mutable<Boolean> smootherCoasts;
    public final Mutable<Boolean> vanillaLandAndClimate;
    public final Mutable<Boolean> oneSixCompatibility;
    public final Mutable<Integer> climateRingsNotSaved;
    public final Mutable<Integer> biomeRingsNotSaved;
    public final Mutable<Integer> subBiomeRingsNotSaved;
    public final Mutable<Integer> mushroomIslandIncidence;
    public final Mutable<Integer> percentageRiverReduction;
    public final Mutable<Boolean> widerRivers;
    public final Mutable<Boolean> wideBeaches;
    public final Mutable<Boolean> controlVillageBiomes;
    private final Mutable<Integer> cacheSize;
    public final Mutable<Integer> rescueSearchLimit;
    public final Mutable<Boolean> mesaMesaBorders;
    public final Mutable<Boolean> extremeClimateSeparation;
    private final Settings.Category climateIncidenceCategory;
    public final Mutable<Integer> hotIncidence;
    public final Mutable<Integer> warmIncidence;
    public final Mutable<Integer> coolIncidence;
    public final Mutable<Integer> snowyIncidence;
    private final Settings.Category oceanControlCategory;
    public final Mutable<Integer> largeContinentFrequency;
    public final Mutable<Integer> mediumContinentFrequency;
    public final Mutable<Integer> smallContinentFrequency;
    public final Mutable<Integer> largeIslandFrequency;
    public final Mutable<Integer> mediumIslandFrequency;
    public final Mutable<Boolean> separateLandmasses;
    public final Mutable<Integer> landExpansionRounds;
    private OceanBiomeSettings oceanBiomeSettings;
    public final Mutable<Boolean> vanillaBiomesOn;
    private VanillaBiomeSettings vanillaBiomeSettings;
    public final Mutable<Boolean> highlandsBiomesOn;
    Acceptor<Boolean> highlandsOnTracker;
    public final Mutable<Boolean> interveneInHighlandsWorlds;
    public final Mutable<Boolean> interveneInBOPWorlds;
    public final Mutable<Boolean> interveneInCustomizedWorlds;
    public final Mutable<Boolean> suppressInStandardWorlds;
    public final Mutable<Boolean> forceStartContinent;
    public final Mutable<Boolean> noBoPSubBiomes;
    public final Mutable<String> externalBiomeNames;
    private ExternalBiomePackage.ExternalBiomeSettings externalBiomeSettings;
    private boolean vanillaMountainsForced;
    private final Collection<Named<BiomeSettings>> registeredBiomeSettings;
    private ArrayList<DistributionPartitioner> partitioners;
    private boolean interactionsDone;

    public void setWorldType(WorldType newType) {
        this.worldType = newType;
    }

    public boolean cachingOn() {
        return true;
    }

    public int cacheSize() {
        if (this.cachingOn()) {
            return (Integer)this.cacheSize.value() > 500 ? 500 : (Integer)this.cacheSize.value();
        } else {
            return 0;
        }
    }

    public final boolean doBoPSubBiomes() {
        return !(Boolean)this.noBoPSubBiomes.value();
    }

    public ClimateControlSettings() {
        this.worldType = WorldType.DEFAULT;
        this.climateZoneCategory = this.category("Climate Zone Parameters", "Full-size is similar to 1.7 defaults. Half-size creates zones similar to pre-1.7 snowy zones. Quarter-size creates fairly small zones but the hot and snowy incidences are limited");
        this.halfSize = this.climateZoneCategory.booleanSetting("Half Zone Size", true, "half the climate zone size from vanilla, unless quartering");
        this.quarterSize = this.climateZoneCategory.booleanSetting("Quarter Zone Size", false, "quarter the climate zone size from vanilla");
        this.randomBiomes = this.climateZoneCategory.booleanSetting("Random Biomes", false, "ignore climate zones altogether");
        this.mountainChains = this.climateZoneCategory.booleanSetting("Mountains in Mountain Chains", false, "Place mountains in chains");
        this.forceIceMountains = this.climateZoneCategory.booleanSetting("Ice Mountains in Mountain Chains", true, "Use Ice Mountains instead of Ice Plains in mountain chain areas");
        this.MesaMountains = this.climateZoneCategory.booleanSetting("Mesas in Mountain Chains", true, "Use Mesas as mountains in mountain chain areas");
        this.bandedClimateWidth = this.climateZoneCategory.intSetting("bandedClimateWidth", -1, "Width of banded climates (climate depends on latitude). 0 or less for normal rules. Width is in terms of climate zones, whatever they are  Widths below 3 will have mixed-up zones. ");
        this.bandedClimateOffset = this.climateZoneCategory.intSetting("bandedClimateOffset", 0, "Number of climate zones to shift the band from the default of the warm - to - cool transition at 0. Positive numbers shift the bands up.");
        this.xSpawnOffset = this.climateZoneCategory.intSetting("xSpawnOffset", 0, "X offset for initial spawn search in blocks");
        this.zSpawnOffset = this.climateZoneCategory.intSetting("zSpawnOffset", 0, "Z offset for initial spawn search in blocks");
        this.frozenIcecaps = this.climateZoneCategory.booleanSetting("Frozen Icecaps", false, "True freezes oceans in snowy latitudes. Only used with latitudinal climates.");
        this.climateControlCategory = this.category("Assorted Parameters");
        this.allowDerpyIslands = this.climateControlCategory.booleanSetting("Allow Derpy Islands", false, "place little islands near shore rather than in deep ocean");
        this.maxRiverChasm = this.climateControlCategory.doubleSetting("maxChasm", 10.0, "max height value for allowing rivers; 10.0 allows everything; 0.75 is plains but no hills");
        this.testingMode = this.climateControlCategory.booleanSetting("TestingMode", false, "add testing routines and crash in suspicious circumstances");
        this.biomeSize = this.climateControlCategory.intSetting("Biome Size", 4, "Biome size, exponential: 4 is regular and 6 is large biomes");
        this.noGenerationChanges = this.climateControlCategory.booleanSetting("No Generation Changes", false, "generate as if CC weren't on; for loading pre-existing worlds or just preventing chunk boundaries");
        this.smootherCoasts = this.climateControlCategory.booleanSetting("Smoother Coastlines", true, "increase smoothing steps; also shrinks unusual biomes some; changing produces occaisional chunk walls");
        this.vanillaLandAndClimate = this.climateControlCategory.booleanSetting("VanillaLandAndClimate", false, "Generate land masses and climate zones similar to vanilla 1.7");
        this.oneSixCompatibility = this.climateControlCategory.booleanSetting("1.6Compatibility", false, "Use 1.6 compatibility mode");
        this.climateRingsNotSaved = this.climateControlCategory.intSetting("climateRingsNotSaved", 3, "climate not saved on edges; -1 deactivates saving climates");
        this.biomeRingsNotSaved = this.climateControlCategory.intSetting("biomeRingsNotSaved", 3, "biomes not saved on edges; more than 3 has no effect; -1 deactivates saving biomes");
        this.subBiomeRingsNotSaved = this.climateControlCategory.intSetting("subBiomeRingsNotSaved", 3, "subbiomes not saved on edges, default 3, -1 deactivates saving sub-biomes");
        this.mushroomIslandIncidence = this.climateControlCategory.intSetting("Mushroom Island Incidence", 10, "per thousand; vanilla is 10");
        this.percentageRiverReduction = this.climateControlCategory.intSetting("PercentRiverReduction", 0, "Percentage of rivers prevented; changes cause chunk boundaries at some rivers");
        this.widerRivers = this.climateControlCategory.booleanSetting("WiderRivers", "True for triple-width rivers", false);
        this.wideBeaches = this.climateControlCategory.booleanSetting("wideBeaches", "True for double-width beaches", false);
        this.controlVillageBiomes = this.climateControlCategory.booleanSetting("controlVillageBiomes", "Have Climate Control set the biomes for village generation; may be incompatible with village mods", false);
        this.cacheSize = this.climateControlCategory.intSetting("cacheSize", 0, "Number of Chunk Biome layouts cached. Above 500 is ignored. 0 or below shuts off chunk info caching");
        this.rescueSearchLimit = this.climateControlCategory.intSetting("rescueLimit", -1, "Maximum Number of Rescue attempts. Negative numbers mean no limit");
        this.mesaMesaBorders = this.climateControlCategory.booleanSetting("Mesas for mesa borders", false, "Use red sand mesa for mesa borders. False uses desert like vanilla");
        this.extremeClimateSeparation = this.climateZoneCategory.booleanSetting("ExtremeClimateSeparation", false, "Separate climates further. Makes illegitemate junctions rarer but also makes extreme climates rarer.");
        this.climateIncidenceCategory = this.category("Climate Incidences", "Blocks of land are randomly assigned to each zone with a frequency proportional to the incidence. Smoothing eliminates some extreme climates on continents later, especially for quarter size zones. Default 2/1/1/2 is better for continents since Hot and Snowy are easier to find.With lots of islands 1/1/1/1 may be better as not so many islands have extreme climates.");
        this.hotIncidence = this.climateIncidenceCategory.intSetting("Hot Zone Incidence", 2, "relative incidence of hot zones like savanna/desert/plains/mesa");
        this.warmIncidence = this.climateIncidenceCategory.intSetting("Warm Zone Incidence", 1, "relative incidence of warm zones like forest/plains/hills/jungle/swamp");
        this.coolIncidence = this.climateIncidenceCategory.intSetting("Cool Zone Incidence", 1, "relative incidence of cool zones like forest/plains/hills/taiga/roofed forest");
        this.snowyIncidence = this.climateIncidenceCategory.intSetting("Snowy Zone Incidence", 2, "relative incidence of snowy zones");
        this.oceanControlCategory = this.category("Ocean Control Parameters", "Frequencies are x per thousand but a little goes a very long way because seeds grow a lot. SeparateLandmasses = true makes an oceanic world with these settings andSeparateLandmasses = false makes a continental world");
        this.largeContinentFrequency = this.oceanControlCategory.intSetting("Incidence of Continents,Large", 40, "frequency of large continent seeds, about 8000x16000");
        this.mediumContinentFrequency = this.oceanControlCategory.intSetting("Incidence of Continents,Medium", 100, "frequency of medium continent seeds, about 4000x8000");
        this.smallContinentFrequency = this.oceanControlCategory.intSetting("Incidence of Continents,Small", 60, "frequency of small continent seeds, about 2000x4000");
        this.largeIslandFrequency = this.oceanControlCategory.intSetting("Incidence of Islands,Large", 30, "frequency of large island seeds, about 500x1000");
        this.mediumIslandFrequency = this.oceanControlCategory.intSetting("Incidence of Islands,Medium", 15, "frequency of medium island seeds, about 250x500, but they tend to break up into archipelagos");
        this.separateLandmasses = this.oceanControlCategory.booleanSetting("SeparateLandmasses", true, "True mostly stops landmasses merging.With default settings you will get an oceanic world if true and a continental world if false");
        this.landExpansionRounds = this.oceanControlCategory.intSetting("Land Expansion Rounds", 1, "Rounds of continent and large island expansion in oceanic worlds (with separateLandmasses off). More makes continents larger and oceans narrower. Default is 1.Values above 2 will overwhelm the separate landmasses setting with an otherwise default config.");
        this.oceanBiomeSettings = new OceanBiomeSettings();
        this.vanillaBiomesOn = this.climateControlCategory.booleanSetting("VanillaBiomesOn", "Include vanilla biomes in world", true);
        this.vanillaBiomeSettings = new VanillaBiomeSettings();
        this.highlandsBiomesOn = new Mutable.Concrete(false);
        this.highlandsOnTracker = new Acceptor<Boolean>() {
            public void accept(Boolean accepted) {
                ClimateControlSettings.this.highlandsBiomesOn.set(accepted);
            }
        };
        this.interveneInHighlandsWorlds = this.climateControlCategory.booleanSetting("alterHighlandsWorlds", true, "impose GeographiCraft generation on Highlands world types");
        this.interveneInBOPWorlds = this.climateControlCategory.booleanSetting("alterBoPWorlds", true, "impose GeographiCraft generation on the Biomes o' Plenty world type");
        this.interveneInCustomizedWorlds = this.climateControlCategory.booleanSetting("alterCustomWorlds", true, "impose GeographiCraft generation on the Customized world type");
        this.suppressInStandardWorlds = this.climateControlCategory.booleanSetting("suppressInStandardWorlds", false, "suppress GeographiCraft generation in default, large biomes, and amplified worlds");
        this.forceStartContinent = this.climateControlCategory.booleanSetting("forceStartContinent", true, "force small continent near origin");
        this.noBoPSubBiomes = this.climateControlCategory.booleanSetting("NoBoPSubBiomes", false, "suppress Bop sub-biome generation in vanilla biomes");
        this.externalBiomeNames = this.climateControlCategory.stringSetting("externalBiomeNames", "", "Comma-delimited list of externalBiome Names. No quotes or line returns. You will have to reload Minecraft after changing this.Names not in the list aren't removed from the configs but they have no effect");
        this.partitioners = new ArrayList();
        this.interactionsDone = false;
        this.registeredBiomeSettings = BiomePackageRegistry.instance.freshBiomeSettings();
    }

    public ClimateControlSettings(WorldType worldtype) {
        this();
        this.setWorldType(worldtype);
    }

    public boolean doFull() {
        return !(Boolean)this.halfSize.value() && !(Boolean)this.quarterSize.value();
    }

    public boolean doHalf() {
        return (Boolean)this.halfSize.value() && !(Boolean)this.quarterSize.value();
    }

    public ArrayList<BiomeSettings> biomeSettings() {
        if (!this.vanillaMountainsForced) {
            this.vanillaMountainsForced = true;
            if ((Boolean)this.mountainChains.value() && (Boolean)this.forceIceMountains.value()) {
                this.vanillaBiomeSettings.forceIceMountains();
            }
        }

        ArrayList<BiomeSettings> result = new ArrayList();
        result.add(this.oceanBiomeSettings);
        if ((Boolean)this.vanillaBiomesOn.value()) {
            result.add(this.vanillaBiomeSettings);
        }

        if (this.externalBiomeSettings != null) {
            result.add(this.externalBiomeSettings);
        }

        Iterator var2 = this.registeredBiomeSettings().iterator();

        while(var2.hasNext()) {
            Named<BiomeSettings> namedSettings = (Named)var2.next();
            result.add(namedSettings.object);
        }

        return result;
    }

    public ArrayList<BiomeSettings> generalBiomeSettings() {
        ArrayList<BiomeSettings> result = new ArrayList();
        result.add(this.oceanBiomeSettings);
        if ((Boolean)this.vanillaBiomesOn.value()) {
            result.add(this.vanillaBiomeSettings);
        }

        return result;
    }

    public Collection<Named<BiomeSettings>> registeredBiomeSettings() {
        Collection<Named<BiomeSettings>> result = new ArrayList();
        Iterator var2 = this.registeredBiomeSettings.iterator();

        while(var2.hasNext()) {
            Named<BiomeSettings> namedSettings = (Named)var2.next();
            if (((BiomeSettings)namedSettings.object).activeIn(this.worldType)) {
                result.add(namedSettings);
            }
        }

        return result;
    }

    public ArrayList<DistributionPartitioner> partitioners() {
        ArrayList<DistributionPartitioner> result = new ArrayList();
        Iterator var2 = this.partitioners.iterator();

        DistributionPartitioner partitioner;
        while(var2.hasNext()) {
            partitioner = (DistributionPartitioner)var2.next();
            result.add(partitioner);
        }

        var2 = DistributionPartitioner.registeredPartitioners().iterator();

        while(var2.hasNext()) {
            partitioner = (DistributionPartitioner)var2.next();
            result.add(partitioner);
        }

        return result;
    }

    public void onNewWorld() {
        Iterator var1 = this.biomeSettings().iterator();

        while(var1.hasNext()) {
            BiomeSettings settings = (BiomeSettings)var1.next();
            settings.onNewWorld();
        }

    }

    public void setDefaults(File configDirectory) {
        Iterator var2 = this.registeredBiomeSettings().iterator();

        while(var2.hasNext()) {
            Named<BiomeSettings> registered = (Named)var2.next();
            ((BiomeSettings)registered.object).setNativeBiomeIDs(configDirectory);
        }

        this.doBiomeSettingsInteractions();
    }

    public void doBiomeSettingsInteractions() {
        if (!this.interactionsDone) {
            Iterator var1 = this.biomeSettings().iterator();

            while(true) {
                BiomeSettings setting;
                do {
                    if (!var1.hasNext()) {
                        this.interactionsDone = true;
                        return;
                    }

                    setting = (BiomeSettings)var1.next();
                } while(setting instanceof BoPSettings && !this.doBoPSubBiomes());

                setting.arrangeInteractions(this.biomeSettings());
            }
        }
    }

    public void readFrom(Configuration source) {
        super.readFrom(source);
        this.externalBiomeSettings = null;
        if (((String)this.externalBiomeNames.value()).length() > 0) {
            this.externalBiomeSettings = new ExternalBiomePackage.ExternalBiomeSettings((String)this.externalBiomeNames.value());
            this.externalBiomeSettings.readFrom(source);
        }

        Iterator var2 = this.generalBiomeSettings().iterator();

        while(var2.hasNext()) {
            BiomeSettings setting = (BiomeSettings)var2.next();
            setting.readFrom(source);
        }

        this.partitioners = new ArrayList();
        if ((Boolean)this.mountainChains.value()) {
            this.partitioners.add(new MountainFormer((Boolean)this.MesaMountains.value()));
        }

    }

    public void copyTo(Configuration target) {
        super.copyTo(target);
        Iterator var2 = this.generalBiomeSettings().iterator();

        while(var2.hasNext()) {
            BiomeSettings setting = (BiomeSettings)var2.next();
            setting.copyTo(target);
        }

        if (this.externalBiomeSettings != null) {
            this.externalBiomeSettings.copyTo(target);
        }

    }

    public void readFrom(DataInput input) throws IOException {
        super.readFrom(input);
        Iterator var2 = this.generalBiomeSettings().iterator();

        while(var2.hasNext()) {
            BiomeSettings setting = (BiomeSettings)var2.next();
            setting.readFrom(input);
        }

        this.externalBiomeSettings = null;
        if (((String)this.externalBiomeNames.value()).length() > 0) {
            this.externalBiomeSettings = new ExternalBiomePackage.ExternalBiomeSettings((String)this.externalBiomeNames.value());
            this.externalBiomeSettings.readFrom(input);
        }

    }

    public void writeTo(DataOutput output) throws IOException {
        super.writeTo(output);
        Iterator var2 = this.generalBiomeSettings().iterator();

        while(var2.hasNext()) {
            BiomeSettings setting = (BiomeSettings)var2.next();
            setting.writeTo(output);
        }

        if (this.externalBiomeSettings != null) {
            this.externalBiomeSettings.writeTo(output);
        }

    }
}
