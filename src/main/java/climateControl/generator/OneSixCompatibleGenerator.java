//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.generator;

import climateControl.api.ClimateControlSettings;
import climateControl.customGenLayer.GenLayerBiomeByClimate;
import climateControl.customGenLayer.GenLayerRandomBiomes;
import climateControl.customGenLayer.GenLayerShoreCC;
import climateControl.customGenLayer.GenLayerSubBiome;
import climateControl.genLayerPack.GenLayerAddSnowOneSix;
import climateControl.genLayerPack.GenLayerHillsOneSix;
import climateControl.genLayerPack.GenLayerOneSixBiome;
import climateControl.genLayerPack.GenLayerSwampRivers;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkGeneratorSettings.Factory;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.GenLayerEdge.Mode;

public class OneSixCompatibleGenerator extends AbstractWorldGenerator {
    public OneSixCompatibleGenerator(ClimateControlSettings settings) {
        super(settings);
    }

    public GenLayerRiverMix fromSeed(long worldSeed, WorldType worldType) {
        return this.strictVanilla(worldSeed);
    }

    public GenLayerRiverMix likeVanilla(long worldSeed) {
        this.subBiomeChooser.clear();
        this.subBiomeChooser.set(this.settings().biomeSettings());
        this.setOceanSubBiomes();
        this.mBiomeChooser.set(this.settings().biomeSettings());
        this.setRules();
        boolean flag = false;
        GenLayerIsland genlayerisland = new GenLayerIsland(1L);
        GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
        GenLayer genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(50L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(70L, genlayeraddisland);
        GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);
        GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
        genlayeraddisland = new GenLayerAddIsland(3L, genlayeraddsnow);
        GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland, Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, Mode.SPECIAL);
        genlayerzoom = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(4L, genlayerzoom);
        genlayeraddisland = this.smoothClimates(this.settings(), worldSeed, genlayeraddisland, 0L);
        genlayeraddisland.initWorldGenSeed(worldSeed);
        GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
        GenLayer genlayer3 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        byte b0 = ((Integer)this.settings().biomeSize.value()).byteValue();
        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer3, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
        GenLayer object = null;
        if ((Boolean)this.settings().randomBiomes.value()) {
            object = new GenLayerRandomBiomes(worldSeed, genlayer3, this.settings());
        } else {
            object = new GenLayerBiomeByClimate(worldSeed, genlayer3, this.settings());
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
                object = new GenLayerShoreCC(1000L, (GenLayer)object, this.rules());
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

    protected void setOceanSubBiomes() {
    }

    public GenLayerRiverMix strictVanilla(long par0) {
        GenLayerIsland genlayerisland = new GenLayerIsland(1L);
        GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
        GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
        GenLayer genlayeraddsnow = new GenLayerAddSnowOneSix(2L, genlayeraddisland);
        genlayerzoom = new GenLayerZoom(2002L, genlayeraddsnow);
        genlayeraddisland = new GenLayerAddIsland(3L, genlayerzoom);
        genlayerzoom = new GenLayerZoom(2003L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(4L, genlayerzoom);
        GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
        byte b0 = 0;
        b0 = 4;
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
                object = new GenLayerShore(1000L, (GenLayer)object);
            }

            if (j == 1) {
                object = new GenLayerSwampRivers(1000L, (GenLayer)object);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer)object);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(par0);
        genlayervoronoizoom.initWorldGenSeed(par0);
        return genlayerrivermix;
    }

    public GenLayerRiverMix strict17Vanilla(long worldSeed) {
        this.subBiomeChooser.clear();
        this.subBiomeChooser.set(this.settings().biomeSettings());
        this.setOceanSubBiomes();
        this.mBiomeChooser.set(this.settings().biomeSettings());
        boolean flag = false;
        GenLayerIsland genlayerisland = new GenLayerIsland(1L);
        GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
        GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(50L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(70L, genlayeraddisland);
        GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);
        GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
        genlayeraddisland = new GenLayerAddIsland(3L, genlayeraddsnow);
        GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland, Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, Mode.SPECIAL);
        genlayerzoom = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(4L, genlayerzoom);
        GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
        GenLayer genlayer3 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        byte b0 = 4;
        if (flag) {
            b0 = 4;
        }

        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer3, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
        Object object = new GenLayerBiome(worldSeed, genlayer3, WorldType.DEFAULT, Factory.jsonToFactory("").build());
        GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayerHills genlayerhills = new GenLayerHills(1000L, (GenLayer)object, genlayer1);
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
                object = new GenLayerShore(1000L, (GenLayer)object);
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
