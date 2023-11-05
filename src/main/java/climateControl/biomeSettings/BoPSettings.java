//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.biomeSettings;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.init.ModBiomes;
import climateControl.api.BiomeSettings;
import climateControl.api.Climate;
import climateControl.api.ClimateControlRules;
import climateControl.api.ClimateDistribution;
import climateControl.customGenLayer.GenLayerBandedClimate;
import climateControl.generator.SubBiomeChooser;
import com.Zeno410Utils.Acceptor;
import com.Zeno410Utils.Mutable;
import com.google.common.base.Optional;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import git.jbredwards.subaquatic.api.biome.BiomeSubaquaticOcean;
import git.jbredwards.subaquatic.mod.common.init.SubaquaticBiomes;
import git.jbredwards.subaquatic.mod.common.world.biome.BiomeWarmOcean;
import net.minecraft.init.Biomes;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;

public class BoPSettings extends BiomeSettings {
    public final BiomeSettings.Element alps;
    public final BiomeSettings.Element bambooForest;
    public final BiomeSettings.Element bayou;
    public final BiomeSettings.Element bog;
    public final BiomeSettings.Element borealForest;
    public final BiomeSettings.Element brushland;
    public final BiomeSettings.Element chaparral;
    public final BiomeSettings.Element cherryBlossomGrove;
    public final BiomeSettings.Element coldDesert;
    public final BiomeSettings.Element coniferousForest;
    public final BiomeSettings.Element coralReef;
    public final BiomeSettings.Element crag;
    public final BiomeSettings.Element deadForest;
    public final BiomeSettings.Element deadSwamp;
    public final BiomeSettings.Element fen;
    public final BiomeSettings.Element flowerField;
    public final BiomeSettings.Element flowerIsland;
    public final BiomeSettings.Element glacier;
    public final BiomeSettings.Element grassland;
    public final BiomeSettings.ID gravelBeach;
    public final BiomeSettings.Element grove;
    public final BiomeSettings.Element heathland;
    public final BiomeSettings.Element highland;
    public final BiomeSettings.Element kelpForest;
    public final BiomeSettings.Element lavenderFields;
    public final BiomeSettings.Element lushDesert;
    public final BiomeSettings.Element lushSwamp;
    public final BiomeSettings.Element mangrove;
    public final BiomeSettings.Element mapleWoods;
    public final BiomeSettings.Element marsh;
    public final BiomeSettings.Element meadow;
    public final BiomeSettings.Element moor;
    public final BiomeSettings.Element mountain;
    public final BiomeSettings.Element mountainFoothills;
    public final BiomeSettings.Element mysticGrove;
    public final BiomeSettings.ID oasis;
    public final BiomeSettings.Element ominousWoods;
    public final BiomeSettings.Element orchard;
    public final BiomeSettings.Element originVally;
    public final BiomeSettings.Element outback;
    public final BiomeSettings.Element overgrownCliffs;
    public final BiomeSettings.Element pasture;
    public final BiomeSettings.Element prairie;
    public final BiomeSettings.Element quagmire;
    public final BiomeSettings.Element rainforest;
    public final BiomeSettings.Element redwoodForest;
    public final BiomeSettings.Element sacredSprings;
    public final BiomeSettings.ID savanna;
    public final BiomeSettings.ID savannaPlateau;
    public final BiomeSettings.Element seasonalForest;
    public final BiomeSettings.Element shield;
    public final BiomeSettings.Element shrubland;
    public final BiomeSettings.Element snowyConiferousForest;
    public final BiomeSettings.Element snowyForest;
    public final BiomeSettings.Element steppe;
    public final BiomeSettings.Element temperateRainforest;
    public final BiomeSettings.Element tropicalRainforest;
    public final BiomeSettings.Element tropicalIslands;
    public final BiomeSettings.Element tundra;
    public final BiomeSettings.Element volcano;
    public final BiomeSettings.Element wasteland;
    public final BiomeSettings.Element wetland;
    public final BiomeSettings.Element woodland;
    public final BiomeSettings.Element eucalyptusForest;
    public final BiomeSettings.Element landOfLakes;
    public final BiomeSettings.Element xericShrubland;

    //subaquatic
    public final BiomeSettings.Element subaquaticWarmOcean;
    public final BiomeSettings.Element subaquaticLukeWarmOcean;
    public final BiomeSettings.Element subaquaticColdOcean;
    public final BiomeSettings.Element subaquaticDeepWarmOcean;
    public final BiomeSettings.Element subaquaticDeepLukeWarmOcean;
    public final BiomeSettings.Element subaquaticDeepColdOcean;
    public final BiomeSettings.Element subaquaticDeepFrozenOcean;
    Acceptor<Integer> crashOnZero;
    Acceptor<Integer> crashOnNegative;
    private HashMap<BiomeSettings.ID, BiomeReplacer.Variable> subBiomeSets;
    static final String biomesOnName = "BoPBiomesOn";
    public final Mutable<Boolean> biomesFromConfig;
    static final String configName = "BoP";
    public final Mutable<Boolean> biomesInNewWorlds;

    public BoPSettings() {
        super("BoP");
        this.alps = new BiomeSettings.Element("Alps", 177, 5, Climate.SNOWY.name);
        this.bambooForest = new BiomeSettings.Element("Bamboo Forest", 180, 5, true, "WARM");
        this.bayou = new BiomeSettings.Element("Bayou", 181, 10, true, "WARM");
        this.bog = new BiomeSettings.Element("Bog", 183, 7, true, "COOL");
        this.borealForest = new BiomeSettings.Element("Boreal Forest", 184, 10, true, "COOL");
        this.brushland = new BiomeSettings.Element("Brushland", 185, 7, true, "WARM");
        this.chaparral = new BiomeSettings.Element("Chaparral", 187, 10, true, "WARM");
        this.cherryBlossomGrove = new BiomeSettings.Element( "Cherry Blossom Grove", 188, 3, true, "COOL");
        this.coldDesert = new BiomeSettings.Element( "Cold Desert", 255, 10, true, "SNOWY");
        this.coniferousForest = new BiomeSettings.Element( "Coniferous Forest", 189, 10, true, "WARM");
        this.coralReef = new BiomeSettings.Element( "Coral Reef", 94, 0, "OCEAN");
        this.crag = new BiomeSettings.Element( "Crag", 191, 3, "MEDIUM");
        this.deadForest = new BiomeSettings.Element( "Dead Forest", 192, 7, true, "COOL");
        this.deadSwamp = new BiomeSettings.Element( "Dead Swamp", 194, 7, true, "WARM");
        this.fen = new BiomeSettings.Element( "Fen", 198, true, "WARM");
        this.flowerField = new BiomeSettings.Element( "Flower Field", 199, 3, true, "WARM");
        this.flowerIsland = new BiomeSettings.Element( "Flower Island", 255, 1, true, "DEEP_OCEAN");
        this.glacier = new BiomeSettings.Element( "Glacier", 203, 0, "SNOWY");
        this.grassland = new BiomeSettings.Element( "Grassland", 204, true, "COOL");
        this.gravelBeach = new BiomeSettings.ID("Gravel Beach", 75);
        this.grove = new BiomeSettings.Element( "Grove", 205, 5, "COOL");
        this.heathland = new BiomeSettings.Element( "Heathland", 206, "COOL");
        this.highland = new BiomeSettings.Element( "Highland", 210, "COOL");
        this.kelpForest = new BiomeSettings.Element( "Kelp Forest", 95, 0, "OCEAN");
        this.lavenderFields = new BiomeSettings.Element( "Lavender Fields", 56, 3, "WARM");
        this.lushDesert = new BiomeSettings.Element( "Lush Desert", 214, 3, true, "HOT");
        this.lushSwamp = new BiomeSettings.Element( "Lush Swamp", 215, true, "WARM");
        this.mangrove = new BiomeSettings.Element( "Mangrove", 216, 3, "WARM");
        this.mapleWoods = new BiomeSettings.Element( "Maple Woods", 217, true, "COOL");
        this.marsh = new BiomeSettings.Element( "Marsh", 218, 7, true, "COOL");
        this.meadow = new BiomeSettings.Element( "Meadow", 219, true, "COOL");
        this.moor = new BiomeSettings.Element( "Moor", 221, true, "COOL");
        this.mountain = new BiomeSettings.Element( "Mountain", 222, 8, "WARM");
        this.mountainFoothills = new BiomeSettings.Element( "Mountain Foothills", 93, 2, "WARM");
        this.mysticGrove = new BiomeSettings.Element( "Mystic Grove", 223, 3, "MEDIUM");
        this.oasis = new BiomeSettings.ID("Oasis", 224);
        this.ominousWoods = new BiomeSettings.Element( "Ominous Woods", 225, 3, "COOL");
        this.orchard = new BiomeSettings.Element( "Orchard", 226, 5, ClimateDistribution.MEDIUM.name());
        this.originVally = new BiomeSettings.Element( "Origin Valley", 227, 1, "MEDIUM");
        this.outback = new BiomeSettings.Element( "Outback", 228, 7, true, "HOT");
        this.overgrownCliffs = new BiomeSettings.Element( "Overgrown Cliffs", 255, 7, true, ClimateDistribution.MEDIUM.name());
        this.pasture = new BiomeSettings.Element( "Pasture", 230, true, "WARM");
        this.prairie = new BiomeSettings.Element( "Prairie", 231);
        this.quagmire = new BiomeSettings.Element( "Quagmire", 232, 3, "COOL");
        this.rainforest = new BiomeSettings.Element( "Rainforest", 233, 5, true, "WARM");
        this.redwoodForest = new BiomeSettings.Element( "Redwood Forest", 234, 7, true, "COOL");
        this.sacredSprings = new BiomeSettings.Element( "Sacred Springs", 235, 3, ClimateDistribution.MEDIUM.name());
        this.savanna = new BiomeSettings.ID("Savanna", 236);
        this.savannaPlateau = new BiomeSettings.ID("Savanna Plateau (Sub-Biome)", 61);
        this.seasonalForest = new BiomeSettings.Element( "Seasonal Forest", 238, true, "COOL");
        this.shield = new BiomeSettings.Element( "Shield", 239, 7, true, "COOL");
        this.shrubland = new BiomeSettings.Element( "Shrubland", 241, true, "COOL");
        this.snowyConiferousForest = new BiomeSettings.Element( "Snowy Coniferous Forest", 51, true, "SNOWY");
        this.snowyForest = new BiomeSettings.Element( "Snowy Forest", 51, true, "SNOWY");
        this.steppe = new BiomeSettings.Element( "Steppe", 244, 7, true, Climate.COOL.name);
        this.temperateRainforest = new BiomeSettings.Element( "Temperate Rainforest", 245, true, "WARM");
        this.tropicalRainforest = new BiomeSettings.Element( "Tropical Rainforest", 248, 5, true, "HOT");
        this.tropicalIslands = new BiomeSettings.Element( "Tropical Islands", 249, 1, true, "DEEP_OCEAN");
        this.tundra = new BiomeSettings.Element( "Tundra", 250, 7, true, "SNOWY");
        this.volcano = new BiomeSettings.Element( "Volcanic Island", 251, 1, true, "DEEP_OCEAN");
        this.wasteland = new BiomeSettings.Element( "Wasteland", 252, 3, "HOT");
        this.wetland = new BiomeSettings.Element( "Wetland", 253, 7, true, "WARM");
        this.woodland = new BiomeSettings.Element( "Woodland", 254, true, "WARM");
        this.eucalyptusForest = new BiomeSettings.Element( "Eucalyptus Forest", 57, false, "WARM");
        this.landOfLakes = new BiomeSettings.Element( "Land of Lakes", 68, false, "COOL");
        this.xericShrubland = new BiomeSettings.Element( "Xeric Shrubland", 98, false, "HOT");

        //subaquatic
        this.subaquaticWarmOcean = new BiomeSettings.Element( "Warm Ocean", 118, 0, "HOT");
        this.subaquaticLukeWarmOcean = new BiomeSettings.Element( "Lukewarm Ocean", 119, 0, "WARM");
        this.subaquaticColdOcean = new BiomeSettings.Element( "Cold Ocean", 120, 0, "SNOWY");
        this.subaquaticDeepWarmOcean = new BiomeSettings.Element( "Deep Warm Ocean", 114, 0, "HOT");
        this.subaquaticDeepLukeWarmOcean = new BiomeSettings.Element( "Deep Lukewarm Ocean", 115, 0, "WARM");
        this.subaquaticDeepColdOcean = new BiomeSettings.Element( "Deep Cold Ocean", 116, 0, "SNOWY");
        this.subaquaticDeepFrozenOcean = new BiomeSettings.Element( "Deep Frozen Ocean", 117, 0, "SNOWY");

        this.crashOnZero = new Acceptor<Integer>() {
            public void accept(Integer accepted) {
                throw new RuntimeException("Thicket incidence " + accepted);
            }
        };
        this.crashOnNegative = new Acceptor<Integer>() {
            public void accept(Integer accepted) {
                throw new RuntimeException("Thicket ID " + accepted);
            }
        };
        this.subBiomeSets = new HashMap();
        this.biomesFromConfig = this.climateControlCategory.booleanSetting("BoPBiomesOn", "", false);
        this.biomesInNewWorlds = this.climateControlCategory.booleanSetting(this.startBiomesName("BoP"), "Use biome in new worlds and dimensions", true);
    }

    public boolean activeIn(WorldType worldType) {
        if (worldType.getName().equalsIgnoreCase("BIOMESOP")) {
            return true;
        } else {
            return worldType.getName().equalsIgnoreCase("RTG");
        }
    }

    private int bopID(Optional<Biome> bopBiome) {
        try {
            return Biome.getIdForBiome((Biome)bopBiome.get());
        } catch (IllegalStateException var3) {
            return -1;
        }
    }

    public void setNativeBiomeIDs(File configDirectory) {
        try {
            this.alps.biomeID().set(this.bopID(BOPBiomes.alps));
            this.bambooForest.biomeID().set(this.bopID(BOPBiomes.bamboo_forest));
            this.bayou.biomeID().set(this.bopID(BOPBiomes.bayou));
            this.bog.biomeID().set(this.bopID(BOPBiomes.bog));
            this.borealForest.biomeID().set(this.bopID(BOPBiomes.boreal_forest));
            this.brushland.biomeID().set(this.bopID(BOPBiomes.brushland));
            this.chaparral.biomeID().set(this.bopID(BOPBiomes.chaparral));
            this.cherryBlossomGrove.biomeID().set(this.bopID(BOPBiomes.cherry_blossom_grove));
            this.coniferousForest.biomeID().set(this.bopID(BOPBiomes.coniferous_forest));
            this.coldDesert.biomeID().set(this.bopID(BOPBiomes.cold_desert));
            this.coralReef.biomeID().set(this.bopID(BOPBiomes.coral_reef));
            this.crag.biomeID().set(this.bopID(BOPBiomes.crag));
            this.deadForest.biomeID().set(this.bopID(BOPBiomes.dead_forest));
            this.deadSwamp.biomeID().set(this.bopID(BOPBiomes.dead_swamp));
            this.fen.biomeID().set(this.bopID(BOPBiomes.fen));
            this.flowerField.biomeID().set(this.bopID(BOPBiomes.flower_field));
            this.flowerIsland.biomeID().set(this.bopID(BOPBiomes.flower_island));
            this.grassland.biomeID().set(this.bopID(BOPBiomes.grassland));
            this.glacier.biomeID().set(this.bopID(BOPBiomes.glacier));
            this.gravelBeach.biomeID().set(this.bopID(BOPBiomes.gravel_beach));
            this.grove.biomeID().set(this.bopID(BOPBiomes.grove));
            this.highland.biomeID().set(this.bopID(BOPBiomes.highland));
            this.kelpForest.biomeID().set(this.bopID(BOPBiomes.kelp_forest));
            this.lavenderFields.biomeID().set(this.bopID(BOPBiomes.lavender_fields));
            this.lushDesert.biomeID().set(this.bopID(BOPBiomes.lush_desert));
            this.lushSwamp.biomeID().set(this.bopID(BOPBiomes.lush_swamp));
            this.mangrove.biomeID().set(this.bopID(BOPBiomes.mangrove));
            this.mapleWoods.biomeID().set(this.bopID(BOPBiomes.maple_woods));
            this.marsh.biomeID().set(this.bopID(BOPBiomes.marsh));
            this.meadow.biomeID().set(this.bopID(BOPBiomes.meadow));
            this.mysticGrove.biomeID().set(this.bopID(BOPBiomes.mystic_grove));
            this.moor.biomeID().set(this.bopID(BOPBiomes.moor));
            this.mountain.biomeID().set(this.bopID(BOPBiomes.mountain));
            this.mountainFoothills.biomeID().set(this.bopID(BOPBiomes.mountain_foothills));
            this.ominousWoods.biomeID().set(this.bopID(BOPBiomes.ominous_woods));
            this.oasis.biomeID().set(this.bopID(BOPBiomes.oasis));
            this.orchard.biomeID().set(this.bopID(BOPBiomes.orchard));
            this.originVally.biomeID().set(this.bopID(BOPBiomes.origin_island));
            this.outback.biomeID().set(this.bopID(BOPBiomes.outback));
            this.overgrownCliffs.biomeID().set(this.bopID(BOPBiomes.overgrown_cliffs));
            this.pasture.biomeID().set(this.bopID(BOPBiomes.pasture));
            this.prairie.biomeID().set(this.bopID(BOPBiomes.prairie));
            this.quagmire.biomeID().set(this.bopID(BOPBiomes.quagmire));
            this.rainforest.biomeID().set(this.bopID(BOPBiomes.rainforest));
            this.redwoodForest.biomeID().set(this.bopID(BOPBiomes.redwood_forest));
            this.sacredSprings.biomeID().set(this.bopID(BOPBiomes.sacred_springs));
            this.savanna.setIDFrom(Biomes.SAVANNA);
            this.savannaPlateau.setIDFrom(Biomes.SAVANNA_PLATEAU);
            this.seasonalForest.biomeID().set(this.bopID(BOPBiomes.seasonal_forest));
            this.shield.biomeID().set(this.bopID(BOPBiomes.shield));
            this.shrubland.biomeID().set(this.bopID(BOPBiomes.shrubland));
            this.snowyConiferousForest.biomeID().set(this.bopID(BOPBiomes.snowy_coniferous_forest));
            this.snowyForest.biomeID().set(this.bopID(BOPBiomes.snowy_forest));
            this.steppe.biomeID().set(this.bopID(BOPBiomes.steppe));
            this.temperateRainforest.biomeID().set(this.bopID(BOPBiomes.temperate_rainforest));
            this.tropicalRainforest.biomeID().set(this.bopID(BOPBiomes.tropical_rainforest));
            this.tropicalIslands.biomeID().set(this.bopID(BOPBiomes.tropical_island));
            this.tundra.biomeID().set(this.bopID(BOPBiomes.tundra));
            this.volcano.biomeID().set(this.bopID(BOPBiomes.volcanic_island));
            this.wasteland.biomeID().set(this.bopID(BOPBiomes.wasteland));
            this.wetland.biomeID().set(this.bopID(BOPBiomes.wetland));
            this.woodland.biomeID().set(this.bopID(BOPBiomes.woodland));
            this.eucalyptusForest.biomeID().set(this.bopID(BOPBiomes.eucalyptus_forest));
            this.landOfLakes.biomeID().set(this.bopID(BOPBiomes.land_of_lakes));
            this.xericShrubland.biomeID().set(this.bopID(BOPBiomes.xeric_shrubland));

            //subaquatic
            this.subaquaticWarmOcean.biomeID().set(Biome.getIdForBiome(SubaquaticBiomes.WARM_OCEAN));
            this.subaquaticLukeWarmOcean.biomeID().set(Biome.getIdForBiome(SubaquaticBiomes.LUKEWARM_OCEAN));
            this.subaquaticColdOcean.biomeID().set(Biome.getIdForBiome(SubaquaticBiomes.COLD_OCEAN));
            this.subaquaticDeepWarmOcean.biomeID().set(Biome.getIdForBiome(SubaquaticBiomes.DEEP_WARM_OCEAN));
            this.subaquaticDeepLukeWarmOcean.biomeID().set(Biome.getIdForBiome(SubaquaticBiomes.DEEP_LUKEWARM_OCEAN));
            this.subaquaticDeepColdOcean.biomeID().set(Biome.getIdForBiome(SubaquaticBiomes.DEEP_COLD_OCEAN));
            this.subaquaticDeepFrozenOcean.biomeID().set(Biome.getIdForBiome(SubaquaticBiomes.DEEP_FROZEN_OCEAN));

        } catch (NoClassDefFoundError var4) {
        }

    }

    public BiomeReplacer.Variable getCommonSubBiome(BiomeSettings.ID biome) {
        List<Integer> subBiomeIds = (List)ModBiomes.subBiomesMap.get(biome.biomeID());
        BiomeReplacer.Variable result = new BiomeReplacer.Variable();
        if (subBiomeIds != null && subBiomeIds.size() != 0) {
            int n = subBiomeIds.size();

            for(int i = 0; i < n; ++i) {
                result.add(biome, 1);
                result.addByNumber((Integer)subBiomeIds.get(i), 1);
            }
        } else {
            result.add(biome, 1);
        }

        return result;
    }

    private BiomeReplacer.Variable subBiomeSet(BiomeSettings.ID biome) {
        BiomeReplacer.Variable result = (BiomeReplacer.Variable)this.subBiomeSets.get(biome);
        if (result == null) {
            result = this.getCommonSubBiome(biome);
            this.subBiomeSets.put(biome, result);
        }

        return result;
    }

    private void addSubBiome(BiomeSettings.ID biome, BiomeSettings.ID subBiome) {
        if (subBiome.active()) {
            this.subBiomeSet(biome).add(subBiome, 1);
            biome.setSubBiomeChooser(this.subBiomeSet(biome));
        }

    }

    public void arrangeInteractions(ArrayList<BiomeSettings> biomeSettings) {
        this.subBiomeSets = new HashMap();
        Iterator var2 = biomeSettings.iterator();

        while(var2.hasNext()) {
            BiomeSettings biomeSetting = (BiomeSettings)var2.next();
            BiomeReplacer icePlainsReplacer;
            BiomeReplacer.Variable newReplacer;
            int band = 0;
            if (biomeSetting instanceof OceanBiomeSettings) {
                OceanBiomeSettings oceanSettings = (OceanBiomeSettings)biomeSetting;
                icePlainsReplacer = oceanSettings.coastalOcean.subBiomeChooser();
                newReplacer = new BiomeReplacer.Variable();
                if (this.coralReef.active()) {
                    newReplacer.add(this.coralReef, 1);
                    newReplacer.add(oceanSettings.coastalOcean, 1);
                }

                if (this.kelpForest.active()) {
                    newReplacer.add(this.kelpForest, 1);
                    newReplacer.add(oceanSettings.coastalOcean, 1);
                }

                if (this.subaquaticColdOcean.active()) {
                    newReplacer.add(this.subaquaticColdOcean, 1);
                    newReplacer.add(oceanSettings.coastalOcean, 1);
                }

                if (this.subaquaticLukeWarmOcean.active()) {
                    newReplacer.add(this.subaquaticLukeWarmOcean, 1);
                    newReplacer.add(oceanSettings.coastalOcean, 1);
                }

                if (this.subaquaticWarmOcean.active()) {
                    newReplacer.add(this.subaquaticWarmOcean, 1);
                    newReplacer.add(oceanSettings.coastalOcean, 1);
                }

                if (this.subaquaticDeepFrozenOcean.active()) {
                    newReplacer.add(this.subaquaticDeepFrozenOcean, 1);
                    newReplacer.add(oceanSettings.deepOcean, 1);
                }

                if (this.subaquaticDeepColdOcean.active()) {
                    newReplacer.add(this.subaquaticDeepColdOcean, 1);
                    newReplacer.add(oceanSettings.deepOcean, 1);
                }

                if (this.subaquaticDeepLukeWarmOcean.active()) {
                    newReplacer.add(this.subaquaticDeepLukeWarmOcean, 1);
                    newReplacer.add(oceanSettings.deepOcean, 1);
                }

                if (this.subaquaticDeepWarmOcean.active()) {
                    newReplacer.add(this.subaquaticDeepWarmOcean, 1);
                    newReplacer.add(oceanSettings.deepOcean, 1);
                }

                if (this.coralReef.active() || this.kelpForest.active()
                || this.subaquaticColdOcean.active() || this.subaquaticLukeWarmOcean.active() || this.subaquaticWarmOcean.active() ) {
                    oceanSettings.coastalOcean.setSubBiomeChooser(newReplacer);
                }

                if (this.subaquaticDeepFrozenOcean.active() || this.subaquaticDeepColdOcean.active() || this.subaquaticDeepLukeWarmOcean.active() || this.subaquaticDeepWarmOcean.active()) {
                    oceanSettings.deepOcean.setSubBiomeChooser(newReplacer);
                }
            }

            if (biomeSetting instanceof VanillaBiomeSettings) {
                VanillaBiomeSettings vanillaSettings = (VanillaBiomeSettings)biomeSetting;
                icePlainsReplacer = vanillaSettings.icePlains.subBiomeChooser();
                if (icePlainsReplacer instanceof BiomeReplacer.Fixed) {
                    newReplacer = new BiomeReplacer.Variable();
                    newReplacer.add(this.glacier, 1);
                    newReplacer.add(vanillaSettings.iceMountains, 1);
                    vanillaSettings.icePlains.setSubBiomeChooser(newReplacer);
                }

                BiomeReplacer desertReplacer = vanillaSettings.desert.subBiomeChooser();
                if (desertReplacer instanceof BiomeReplacer.Fixed) {
                    newReplacer = new BiomeReplacer.Variable();
                    newReplacer.add(this.oasis, 1);
                    newReplacer.add(vanillaSettings.desertHills, 3);
                    vanillaSettings.desert.setSubBiomeChooser(newReplacer);
                }
            }
        }

    }

    public void update(SubBiomeChooser subBiomeChooser) {
        super.update(subBiomeChooser);
        Iterator var2 = this.elements().iterator();

        while(var2.hasNext()) {
            BiomeSettings.Element element = (BiomeSettings.Element)var2.next();
            subBiomeChooser.set((Integer)element.biomeID().value(), this.subBiomeSet(element));
        }

    }

    public void setRules(ClimateControlRules rules) {
        rules.noBeaches((Integer)this.coralReef.biomeID().value());
        rules.noBeaches((Integer)this.kelpForest.biomeID().value());

        rules.noBeaches((Integer)this.subaquaticWarmOcean.biomeID().value());
        rules.noBeaches((Integer)this.subaquaticLukeWarmOcean.biomeID().value());
        rules.noBeaches((Integer)this.subaquaticColdOcean.biomeID().value());
        rules.noBeaches((Integer)this.subaquaticDeepWarmOcean.biomeID().value());
        rules.noBeaches((Integer)this.subaquaticDeepLukeWarmOcean.biomeID().value());
        rules.noBeaches((Integer)this.subaquaticDeepColdOcean.biomeID().value());
        rules.noBeaches((Integer)this.subaquaticDeepFrozenOcean.biomeID().value());
        rules.disallowRivers((Integer)this.subaquaticWarmOcean.biomeID().value());
        rules.disallowRivers((Integer)this.subaquaticLukeWarmOcean.biomeID().value());
        rules.disallowRivers((Integer)this.subaquaticColdOcean.biomeID().value());
        rules.disallowRivers((Integer)this.subaquaticDeepWarmOcean.biomeID().value());
        rules.disallowRivers((Integer)this.subaquaticDeepLukeWarmOcean.biomeID().value());
        rules.disallowRivers((Integer)this.subaquaticDeepColdOcean.biomeID().value());
        rules.disallowRivers((Integer)this.subaquaticDeepFrozenOcean.biomeID().value());

        this.setVillages(rules);
    }

    public void onNewWorld() {
        this.biomesFromConfig.set(this.biomesInNewWorlds);
    }

    public boolean biomesAreActive() {
        return (Boolean)this.biomesFromConfig.value();
    }
}
