//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.generator;

import climateControl.api.BiomeSettings;
import climateControl.api.ClimateControlRules;
import climateControl.api.ClimateControlSettings;
import climateControl.customGenLayer.GenLayerBiomeByClimate;
import climateControl.customGenLayer.GenLayerRandomBiomes;
import climateControl.customGenLayer.GenLayerShoreCC;
import climateControl.customGenLayer.GenLayerSmoothClimate;
import climateControl.customGenLayer.GenLayerSubBiome;
import climateControl.genLayerPack.GenLayerAddIsland;
import climateControl.genLayerPack.GenLayerHillsOneSix;
import climateControl.genLayerPack.GenLayerOneSixBiome;
import climateControl.genLayerPack.GenLayerRareBiome;
import climateControl.genLayerPack.GenLayerRiver;
import climateControl.genLayerPack.GenLayerRiverInit;
import climateControl.genLayerPack.GenLayerSmooth;
import climateControl.genLayerPack.GenLayerSwampRivers;
import climateControl.genLayerPack.GenLayerVoronoiZoom;
import climateControl.genLayerPack.GenLayerZoom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.structure.MapGenVillage;

public abstract class AbstractWorldGenerator {
    private final ClimateControlSettings settings;
    protected final SubBiomeChooser subBiomeChooser = new SubBiomeChooser();
    protected final BiomeSwapper mBiomeChooser = new BiomeSwapper();
    private ClimateControlRules rules;

    public ClimateControlSettings settings() {
        return this.settings;
    }

    public AbstractWorldGenerator(ClimateControlSettings settings) {
        this.settings = settings;
        this.setRules();
    }

    protected GenLayer smoothClimates(ClimateControlSettings settings, long worldSeed, GenLayer parent, long masterSeed) {
        return new GenLayerSmoothClimate(masterSeed, parent, settings);
    }

    protected ClimateControlRules rules() {
        return this.rules;
    }

    protected void setRules() {
        new ClimateControlRules();
        ClimateControlRules newRules = new ClimateControlRules();
        Iterator var2 = this.settings().biomeSettings().iterator();

        while(var2.hasNext()) {
            BiomeSettings biomeSettings = (BiomeSettings)var2.next();
            biomeSettings.setRules(newRules);
        }

        List<Biome> villageSpawnBiomes = new ArrayList();

        for(int i = 0; i < 256; ++i) {
            if (newRules.hasVillages(i)) {
                villageSpawnBiomes.add(Biome.getBiome(i));
            }
        }

        VillageBiomes villageBiomes = new VillageBiomes(villageSpawnBiomes);
        villageBiomes.reportMembers();
        if ((Boolean)this.settings.controlVillageBiomes.value()) {
            MapGenVillage.VILLAGE_SPAWN_BIOMES = villageBiomes;
        }

        this.rules = newRules;
    }

    abstract GenLayerRiverMix fromSeed(long var1, WorldType var3);

    public int rtgAwareRiverReduction(int baseReduction, WorldType worldType) {
        return worldType.getName().equalsIgnoreCase("RTG") ? 100 : baseReduction;
    }

    public GenLayerRiverMix vanillaExpansion(long worldSeed, WorldType par2WorldType, GenLayer genlayer3, ClimateControlSettings settings) {
        return (Boolean)settings.oneSixCompatibility.value() ? this.oneSixExpansion(worldSeed, par2WorldType, genlayer3, settings) : this.oneSevenExpansion(worldSeed, par2WorldType, genlayer3, settings);
    }

    public GenLayerRiverMix oneSevenExpansion(long worldSeed, WorldType par2WorldType, GenLayer genlayer3, ClimateControlSettings settings) {
        byte b0 = ((Integer)this.settings().biomeSize.value()).byteValue();
        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer3, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
        GenLayer object = null;
        if ((Boolean)this.settings().randomBiomes.value()) {
            object = new GenLayerRandomBiomes(worldSeed, genlayer3, settings);
        } else {
            object = new GenLayerBiomeByClimate(worldSeed, genlayer3, settings);
        }

        ((GenLayer)object).initWorldGenSeed(worldSeed);
        object = GenLayerZoom.magnify(1000L, (GenLayer)object, 2);
        object = new GenLayerBiomeEdge(1000L, object);
        GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayer genlayerhills = new GenLayerSubBiome(1000L, object, genlayer1, this.subBiomeChooser, this.mBiomeChooser, this.settings().doBoPSubBiomes());
        genlayerhills.initWorldGenSeed(worldSeed);
        genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer = GenLayerZoom.magnify(1000L, genlayer, b0);
        GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        object = new GenLayerRareBiome(1001L, genlayerhills);

        for(int j = 0; j < b0; ++j) {
            object = new GenLayerZoom((long)(1000 + j), (GenLayer)object);
            if (j == 0) {
                object = new GenLayerAddIsland(3L, (GenLayer)object);
            }

            if (j == 1) {
                object = new GenLayerShoreCC(1000L, (GenLayer)object, this.rules);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer)object);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(worldSeed);
        genlayervoronoizoom.initWorldGenSeed(worldSeed);
        genlayerrivermix.initWorldGenSeed(worldSeed);
        genlayervoronoizoom.initWorldGenSeed(worldSeed);
        return genlayerrivermix;
    }

    public GenLayerRiverMix oneSixExpansion(long worldSeed, WorldType par2WorldType, GenLayer genlayeraddmushroomisland, ClimateControlSettings settings) {
        byte b0 = ((Integer)this.settings().biomeSize.value()).byteValue();
        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayeraddmushroomisland, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
        genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, b0 + 2);
        GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayeraddmushroomisland, 0);
        GenLayer genlayerbiome = new GenLayerOneSixBiome(200L, genlayer1, WorldType.DEFAULT);
        genlayer1 = GenLayerZoom.magnify(1000L, genlayerbiome, 2);
        Object object = new GenLayerHillsOneSix(1000L, genlayer1);

        for(int j = 0; j < b0; ++j) {
            object = new GenLayerZoom((long)(1000 + j), (GenLayer)object);
            if (j == 0) {
                object = new GenLayerAddIsland(3L, (GenLayer)object);
            }

            if (j == 1) {
                object = new GenLayerShoreCC(1000L, (GenLayer)object, this.rules);
            }

            if (j == 1) {
                object = new GenLayerSwampRivers(1000L, (GenLayer)object);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer)object);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(worldSeed);
        genlayervoronoizoom.initWorldGenSeed(worldSeed);
        return genlayerrivermix;
    }
}
