//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.generator;

import climateControl.ClimateChooser;
import climateControl.ClimateControl;
import climateControl.api.ClimateControlSettings;
import climateControl.api.IslandClimateMaker;
import climateControl.customGenLayer.ConfirmBiome;
import climateControl.customGenLayer.GenLayerAddBiome;
import climateControl.customGenLayer.GenLayerAdjustIsland;
import climateControl.customGenLayer.GenLayerBandedClimate;
import climateControl.customGenLayer.GenLayerBiomeByTaggedClimate;
import climateControl.customGenLayer.GenLayerBreakMergers;
import climateControl.customGenLayer.GenLayerConfirm;
import climateControl.customGenLayer.GenLayerConstant;
import climateControl.customGenLayer.GenLayerContinentalShelf;
import climateControl.customGenLayer.GenLayerForceStartLand;
import climateControl.customGenLayer.GenLayerIdentifiedClimate;
import climateControl.customGenLayer.GenLayerLandReport;
import climateControl.customGenLayer.GenLayerLessRiver;
import climateControl.customGenLayer.GenLayerLimitedCache;
import climateControl.customGenLayer.GenLayerLowlandRiverMix;
import climateControl.customGenLayer.GenLayerOceanicIslands;
import climateControl.customGenLayer.GenLayerOceanicMushroomIsland;
import climateControl.customGenLayer.GenLayerPrettyShore;
import climateControl.customGenLayer.GenLayerRandomBiomes;
import climateControl.customGenLayer.GenLayerSmoothClimate;
import climateControl.customGenLayer.GenLayerSmoothCoast;
import climateControl.customGenLayer.GenLayerSubBiome;
import climateControl.customGenLayer.GenLayerWidenRiver;
import climateControl.customGenLayer.GenLayerZoomBiome;
import climateControl.genLayerPack.GenLayerAddIsland;
import climateControl.genLayerPack.GenLayerFuzzyZoom;
import climateControl.genLayerPack.GenLayerPack;
import climateControl.genLayerPack.GenLayerRareBiome;
import climateControl.genLayerPack.GenLayerSmooth;
import climateControl.genLayerPack.GenLayerZoom;
import com.Zeno410Utils.IntRandomizer;
import com.Zeno410Utils.RandomIntUser;
import com.Zeno410Utils.StringWriter;
import java.io.File;
import java.io.IOException;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;

public class CorrectedContinentsGenerator extends AbstractWorldGenerator {
    private File serverDirectoryFile;

    public CorrectedContinentsGenerator(ClimateControlSettings settings, File serverDirectory) {
        super(settings);
        this.serverDirectoryFile = serverDirectory;
    }

    protected IslandClimateMaker islandClimates() {
        return new IslandClimateMaker() {
            RandomIntUser climateChooser;

            {
                this.climateChooser = new ClimateChooser((Integer)CorrectedContinentsGenerator.this.settings().hotIncidence.value(), (Integer)CorrectedContinentsGenerator.this.settings().warmIncidence.value(), (Integer)CorrectedContinentsGenerator.this.settings().coolIncidence.value(), (Integer)CorrectedContinentsGenerator.this.settings().snowyIncidence.value());
            }

            public int climate(int x, int z, IntRandomizer randomizer) {
                return this.climateChooser.value(randomizer);
            }
        };
    }

    protected RandomIntUser landmassIdentifier() {
        return !(Boolean)this.settings().separateLandmasses.value() ? this.justLand() : new RandomIntUser() {
            public int value(IntRandomizer randomizer) {
                return randomizer.nextInt(10000) + 256;
            }
        };
    }

    protected void setOceanSubBiomes() {
    }

    public GenLayerRiverMix fromSeed(long worldSeed, WorldType worldType) {
        this.subBiomeChooser.clear();
        this.subBiomeChooser.set(this.settings().biomeSettings());
        this.setOceanSubBiomes();
        this.mBiomeChooser.set(this.settings().biomeSettings());
        this.setRules();
        boolean climatesAssigned = false;
        ClimateControl.logger.info("Small Continents" + this.settings().smallContinentFrequency.value());
        ClimateControl.logger.info("Cool Zone" + this.settings().coolIncidence.value());
        GenLayer emptyOcean = new GenLayerConstant(0L);
        GenLayerPack genlayerisland = new GenLayerOceanicIslands(1L, emptyOcean, (Integer)this.settings().largeContinentFrequency.value(), this.landmassIdentifier(), this.separating(), "Large Continent");
        GenLayerPack genlayeraddisland = this.growRound(genlayerisland, 2L, 3L, climatesAssigned);
        if (this.separating()) {
            genlayeraddisland = new GenLayerBreakMergers(1002L, (GenLayer)genlayeraddisland);
        }


        if ((Boolean)this.settings().testingMode.value()) {
            genlayeraddisland = new GenLayerConfirm((GenLayer)genlayeraddisland);
            genlayeraddisland = this.reportOn(genlayeraddisland, "large.txt");
        }

        genlayeraddisland = new GenLayerFuzzyZoom(2000L, (GenLayerPack)genlayeraddisland);
        genlayeraddisland = new GenLayerSmooth(2004L, genlayeraddisland);
        GenLayerPack mediumContinents = new GenLayerOceanicIslands(4L, genlayeraddisland, (Integer)this.settings().mediumContinentFrequency.value(), this.landmassIdentifier(), this.separating(), "Medium Continent");
        genlayeraddisland = this.growRound(mediumContinents, 5L, 7L, climatesAssigned);
        if (this.separating()) {
            genlayeraddisland = new GenLayerBreakMergers(1005L, (GenLayer)genlayeraddisland);
        }

        if ((Boolean)this.settings().testingMode.value()) {
            genlayeraddisland = new GenLayerConfirm((GenLayer)genlayeraddisland);
            genlayeraddisland = this.reportOn(genlayeraddisland, "medium.txt");
        }

        GenLayer genlayerzoom = new GenLayerFuzzyZoom(2001L, (GenLayerPack)genlayeraddisland);
        genlayerzoom = new GenLayerSmooth(2008L, genlayerzoom);
        GenLayerPack smallContinents = new GenLayerOceanicIslands(8L, genlayerzoom, (Integer)this.settings().smallContinentFrequency.value(), this.landmassIdentifier(), this.separating(), "Small Continent");

        if ((Boolean)this.settings().testingMode.value()) {
            smallContinents = new GenLayerConfirm((GenLayer)smallContinents);
            smallContinents = this.reportOn(smallContinents, "forcedbefore.txt");
        }

        if ((Boolean)this.settings().forceStartContinent.value()) {
            smallContinents = new GenLayerForceStartLand((GenLayer)smallContinents, this.islandClimates(2));
        }

        if ((Boolean)this.settings().testingMode.value()) {
            smallContinents = new GenLayerConfirm((GenLayer)smallContinents);
            smallContinents = this.reportOn(smallContinents, "forcedafter.txt");
        }

        genlayeraddisland = this.growRound((GenLayerPack)smallContinents, 2L, 3L, climatesAssigned);
        if (this.settings().doFull()) {
            genlayeraddisland = this.climateLayer(1014L, genlayeraddisland, this.settings());
            climatesAssigned = true;
        }

        if ((Boolean)this.settings().testingMode.value()) {
            genlayeraddisland = new GenLayerConfirm(genlayeraddisland);
            genlayeraddisland = this.reportOn(genlayeraddisland, "smallGrown.txt");
        }

        genlayeraddisland = new GenLayerZoom(2002L, genlayeraddisland);
        if (this.separating()) {
            genlayeraddisland = new GenLayerBreakMergers(3012L, (GenLayer)genlayeraddisland);
        }

        if (climatesAssigned) {
            genlayeraddisland = new GenLayerOceanicIslands(11L, (GenLayer)genlayeraddisland, (Integer)this.settings().largeIslandFrequency.value(), this.islandClimates(1), (Boolean)this.settings().separateLandmasses.value(), "Large Island");
        } else {
            genlayeraddisland = new GenLayerOceanicIslands(11L, (GenLayer)genlayeraddisland, (Integer)this.settings().largeIslandFrequency.value(), this.landmassIdentifier(), (Boolean)this.settings().separateLandmasses.value(), "Large Island");
        }

        genlayeraddisland = this.growRound(genlayeraddisland, 13L, 15L, climatesAssigned);
        if ((Boolean)this.settings().testingMode.value()) {
            genlayeraddisland = this.reportOn(genlayeraddisland, "largeIslands.txt");
        }

        if (this.settings().doHalf()) {
            genlayeraddisland = this.climateLayer(1014L, genlayeraddisland, this.settings());
            climatesAssigned = true;
        }

        if ((Boolean)this.settings().testingMode.value()) {
            genlayeraddisland = new GenLayerConfirm(genlayeraddisland);
            genlayeraddisland = this.reportOn(genlayeraddisland, "smoothed.txt");
        }

        genlayeraddisland = new GenLayerZoom(2003L, genlayeraddisland);
        if (this.separating()) {
            genlayeraddisland = new GenLayerBreakMergers(3017L, (GenLayer)genlayeraddisland);
        }

        if (climatesAssigned) {
            genlayeraddisland = new GenLayerOceanicIslands(17L, (GenLayer)genlayeraddisland, (Integer)this.settings().mediumIslandFrequency.value(), this.islandClimates(0), (Boolean)this.settings().separateLandmasses.value(), "Medium Island");
        } else {
            genlayeraddisland = new GenLayerOceanicIslands(17L, (GenLayer)genlayeraddisland, (Integer)this.settings().mediumIslandFrequency.value(), this.landmassIdentifier(), (Boolean)this.settings().separateLandmasses.value(), "Medium Island");
        }

        genlayeraddisland = new GenLayerAdjustIsland(21L, genlayeraddisland, 3, 12, 12, false);
        if ((Boolean)this.settings().quarterSize.value()) {
            genlayeraddisland = this.climateLayer(1014L, (GenLayer)genlayeraddisland, this.settings());
            climatesAssigned = true;
        }

        genlayeraddisland = new GenLayerSmoothClimate(22L, (GenLayer)genlayeraddisland, this.settings());
        if (this.separating()) {
            genlayeraddisland = new GenLayerBreakMergers(3017L, (GenLayer)genlayeraddisland);
        }

        if ((Boolean)this.settings().testingMode.value()) {
            genlayeraddisland = this.reportOn((GenLayerPack)genlayeraddisland, "mediumIslands.txt");
        }

        if ((Boolean)this.settings().testingMode.value()) {
            genlayeraddisland = new GenLayerConfirm((GenLayer)genlayeraddisland);
        }

        genlayeraddisland = new GenLayerLimitedCache((GenLayer)genlayeraddisland, 100);
        GenLayer genlayerdeepocean = new GenLayerContinentalShelf(23L, genlayeraddisland);
        GenLayer genlayeraddmushroomisland = new GenLayerOceanicMushroomIsland(24L, genlayerdeepocean, (Integer)this.settings().mushroomIslandIncidence.value());
        GenLayer genlayer3 = GenLayerZoom.magnify(1002L, genlayeraddmushroomisland, 0);
        genlayer3.initWorldGenSeed(worldSeed);
        if ((Boolean)this.settings().testingMode.value()) {
            this.reportOn(genlayeraddisland, "preBiome.txt");
        }

        return (Boolean)this.settings().smootherCoasts.value() ? this.climateControlExpansion(worldSeed, worldType, genlayer3, this.settings()) : this.vanillaExpansion(worldSeed, worldType, genlayer3, this.settings());
    }

    private final boolean separating() {
        return (Boolean)this.settings().separateLandmasses.value();
    }

    private GenLayerPack growRound(GenLayerPack genlayeraddisland, long firstSeed, long secondSeed, boolean climatesAssigned) {

        if ((Boolean)this.settings().separateLandmasses.value()) {
            for(int round = 0; round < (Integer)this.settings().landExpansionRounds.value(); ++round) {
                int adjust = round * 50;
                genlayeraddisland = new GenLayerAdjustIsland(firstSeed + (long)adjust, (GenLayer)genlayeraddisland, 3, 11, 12, false);
                genlayeraddisland = new GenLayerBreakMergers(firstSeed + (long)adjust + 1000L, genlayeraddisland);
                genlayeraddisland = new GenLayerAdjustIsland(secondSeed + (long)adjust, genlayeraddisland, 3, 11, 12, false);
                genlayeraddisland = new GenLayerBreakMergers(secondSeed + (long)adjust + 1000L, genlayeraddisland);
                if ((Boolean)this.settings().testingMode.value()) {
                    genlayeraddisland = new GenLayerConfirm((GenLayer)genlayeraddisland);
                }
            }
        } else {
            genlayeraddisland = new GenLayerAdjustIsland(firstSeed, (GenLayer)genlayeraddisland, 3, 11, 12, false);
            genlayeraddisland = new GenLayerAdjustIsland(secondSeed, genlayeraddisland, 3, 11, 12, false);
        }

        return (GenLayerPack)genlayeraddisland;
    }

    private IslandClimateMaker islandClimates(int level) {
        if ((Integer)this.settings().bandedClimateWidth.value() > 0) {
            int multiplier = 1;
            if (this.settings().doFull()) {
                if (level == 0) {
                    multiplier = 4;
                }

                if (level == 1) {
                    multiplier = 2;
                }
            }

            if (this.settings().doHalf() && level == 0) {
                multiplier = 2;
            }

            return new GenLayerBandedClimate(0L, (GenLayer)null, this.settings(), multiplier);
        } else {
            return (Boolean)this.settings().separateLandmasses.value() ? this.identifiedClimate() : this.islandClimates();
        }
    }

    GenLayerPack reportOn(GenLayerPack reportedOn, String fileName) {
        if (this.serverDirectoryFile != null) {
            try {
                StringWriter target = new StringWriter(new File(this.serverDirectoryFile, fileName));
                reportedOn = new GenLayerLandReport(reportedOn, 40, target);
                return reportedOn;
            } catch (IOException var4) {
                throw new RuntimeException(var4);
            }
        } else {
            return reportedOn;
        }
    }

    private GenLayerPack climateLayer(long seed, GenLayer parent, ClimateControlSettings settings) {
        return (GenLayerPack)((Integer)settings.bandedClimateWidth.value() > 0 ? new GenLayerBandedClimate(seed, parent, settings, 1) : new GenLayerIdentifiedClimate(seed, parent, this.settings()));
    }

    private RandomIntUser justLand() {
        return new RandomIntUser() {
            public int value(IntRandomizer randomizer) {
                return 1;
            }
        };
    }

    private IslandClimateMaker identifiedClimate() {
        return new IslandClimateMaker() {
            IslandClimateMaker island = CorrectedContinentsGenerator.this.islandClimates();
            RandomIntUser identifier = CorrectedContinentsGenerator.this.landmassIdentifier();

            public int climate(int x, int z, IntRandomizer randomizer) {
                return this.island.climate(x, z, randomizer) + 4 * this.identifier.value(randomizer);
            }
        };
    }

    public GenLayerRiverMix climateControlExpansion(long par0, WorldType par2WorldType, GenLayer genlayer3, ClimateControlSettings settings) {
        byte b0 = 0;
        if (par2WorldType == WorldType.LARGE_BIOMES) {
            b0 = 6;
        } else {
            b0 = ((Integer)settings.biomeSize.value()).byteValue();
        }

        GenLayer genlayer = GenLayerZoom.magnify(1003L, genlayer3, 0);
        GenLayer genlayerriverinit = new GenLayerLessRiver(102L, genlayer, this.rtgAwareRiverReduction((Integer)this.settings().percentageRiverReduction.value(), par2WorldType));
        GenLayer subBiomeFlags = new GenLayerLessRiver(102L, genlayer, 0);
        GenLayerPack biomes = null;
        if ((Boolean)settings.randomBiomes.value()) {
            biomes = new GenLayerRandomBiomes(par0, genlayer3, settings);
        } else {
            biomes = new GenLayerBiomeByTaggedClimate(par0, genlayer3, settings);
        }

        if ((Boolean)this.settings().testingMode.value()) {
            biomes = this.reportOn((GenLayerPack)biomes, "Biomes.txt");
        }

        GenLayer object = new GenLayerZoom(1004L, (GenLayer)biomes);
        object = new GenLayerAddBiome(1005L, object);
        object = new GenLayerSmooth(103L, object);
        object = new GenLayerZoomBiome(1006L, object);
        object = new GenLayerAddBiome(1007L, object);
        object = new GenLayerSmoothCoast(104L, object);
        subBiomeFlags = GenLayerZoom.magnify(1008L, subBiomeFlags, 2);
        GenLayerPack genlayerhills = null;
        genlayerhills = new GenLayerSubBiome(1009L, object, subBiomeFlags, this.subBiomeChooser, this.mBiomeChooser, this.settings().doBoPSubBiomes());
        genlayer = GenLayerZoom.magnify(1010L, genlayerriverinit, 2);
        genlayer = GenLayerZoom.magnify(1010L, genlayer, b0);
        GenLayer genlayerriver = new GenLayerRiver(1L, genlayer);
        if ((Boolean)settings.widerRivers.value()) {
            genlayerriver = new GenLayerWidenRiver(1L, (GenLayer)genlayerriver);
        }

        GenLayer genlayersmooth = new GenLayerSmoothCoast(1000L, (GenLayer)genlayerriver);
        object = new GenLayerRareBiome(1001L, genlayerhills);

        for(int j = 0; j < b0; ++j) {
            object = new GenLayerZoom((long)(1000 + j), (GenLayer)object, true);
            if (j == 0) {
                object = new GenLayerAddIsland(3L, (GenLayer)object);
                object = new GenLayerSmoothCoast(100L, object);
            }

            if (j == 1) {
                object = new GenLayerSmoothCoast(100L, (GenLayer)object);
            }

            if (settings.wideBeaches.value()) {
                if (j == 0) {
                    object = new GenLayerPrettyShore(1000L, object, 1.0F, this.rules(), this.settings().mesaMesaBorders.value());
                }
            } else if (j == 1) {
                object = new GenLayerPrettyShore(1000L, object, 1.0F, this.rules(), this.settings().mesaMesaBorders.value());
            }
        }

        GenLayer genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer)object);
        if ((Boolean)this.settings().testingMode.value()) {
            genlayersmooth1 = new ConfirmBiome((GenLayer)genlayersmooth1);
        }

        if (this.settings().cachingOn()) {
        }

        GenLayerRiverMix genlayerrivermix = new GenLayerLowlandRiverMix(100L, (GenLayer)genlayersmooth1, genlayersmooth, ((Double)this.settings().maxRiverChasm.value()).floatValue(), this.rules());
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(par0);
        genlayervoronoizoom.initWorldGenSeed(par0);
        return genlayerrivermix;
    }
}
