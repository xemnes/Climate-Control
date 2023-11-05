//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.biomeSettings;

import climateControl.api.BiomeSettings;
import climateControl.api.Climate;
import climateControl.api.ClimateControlRules;
import climateControl.api.ClimateDistribution;
import com.Zeno410Utils.Mutable;
import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class VanillaBiomeSettings extends BiomeSettings {
    public static int birchForestDefault = 10;
    public static int coldTaigaDefault = 10;
    public static int desertDefault = 30;
    public static int extremeHillsDefault = 20;
    public static int forestDefault = 20;
    public static int icePlainsDefault = 30;
    public static int jungleDefault = 5;
    public static int megaTaigaDefault = 5;
    public static int mesaPlateauDefault = 1;
    public static int mesaPlateau_FDefault = 4;
    public static int plainsDefault = 30;
    public static int roofedForestDefault = 10;
    public static int savannaDefault = 10;
    public static int swamplandDefault = 10;
    public static int taigaDefault = 20;
    public static final String birchForestName = "Birch Forest";
    public static final String coldTaigaName = "Cold Taiga";
    public static final String desertName = "Desert";
    public static final String extremeHillsName = "Extreme Hills";
    public static final String forestName = "Forest";
    public static final String icePlainsName = "Ice Plains";
    public static final String iceMountainsName = "Ice Mountains";
    public static final String jungleName = "Jungle";
    public static final String megaTaigaName = "Mega Taiga";
    public static final String mesaPlateauName = "Mesa Plateau";
    public static final String mesaPlateau_FName = "Mesa Plateau F";
    public static final String plainsName = "Plains";
    public static final String roofedForestName = "Roofed Forest";
    public static final String savannaName = "Savanna";
    public static final String swamplandName = "Swampland";
    public static final String taigaName = "Taiga (snowless)";
    public static final String biomeCategory = "VanillaBiome";
    BiomeSettings.Element birchForest;
    BiomeSettings.Element coldTaiga;
    BiomeSettings.Element desert;
    BiomeSettings.Element extremeHills;
    BiomeSettings.Element forest;
    BiomeSettings.Element icePlains;
    BiomeSettings.Element jungle;
    BiomeSettings.Element megaTaiga;
    BiomeSettings.Element mesaPlateau;
    BiomeSettings.Element mesaPlateau_F;
    BiomeSettings.Element plains;
    BiomeSettings.Element roofedForest;
    BiomeSettings.Element savanna;
    BiomeSettings.Element swampland;
    BiomeSettings.Element taiga;
    BiomeSettings.Element iceMountains;
    BiomeSettings.ID mushroomIsland;
    BiomeSettings.ID desertHills;
    BiomeSettings.ID forestHills;
    BiomeSettings.ID taigaHills;
    BiomeSettings.ID jungleHills;
    BiomeSettings.ID birchForestHills;
    BiomeSettings.ID coldTaigaHills;
    BiomeSettings.ID megaTaigaHills;
    BiomeSettings.ID extremeHillsPlus;
    BiomeSettings.ID savannaPlateau;
    BiomeSettings.ID mesa;
    BiomeSettings.ID sunflowerPlains;
    BiomeSettings.ID desertM;
    BiomeSettings.ID extremeHillsM;
    BiomeSettings.ID flowerForest;
    BiomeSettings.ID taigaM;
    BiomeSettings.ID swamplandM;
    BiomeSettings.ID icePlainsSpikes;
    BiomeSettings.ID jungleM;
    BiomeSettings.ID birchForestM;
    BiomeSettings.ID birchForestHillsM;
    BiomeSettings.ID roofedForestM;
    BiomeSettings.ID coldTaigaM;
    BiomeSettings.ID megaSpruceTaiga;
    BiomeSettings.ID extremeHillsPlusM;
    BiomeSettings.ID savannaM;
    BiomeSettings.ID savannaPlateauM;
    BiomeSettings.ID bryceMesa;
    BiomeSettings.ID mesaPlateauM;
    BiomeSettings.ID mesaPlateau_FM;
    static final String biomesOnName = "VanillaBiomesOn";
    public final Mutable<Boolean> biomesFromConfig;

    public VanillaBiomeSettings() {
        super("VanillaBiome");
        this.birchForest = new BiomeSettings.Element( "Birch Forest", 27, 10, Climate.WARM.name);
        this.coldTaiga = new BiomeSettings.Element( "Cold Taiga", 30, 10, Climate.SNOWY.name);
        this.desert = new BiomeSettings.Element( "Desert", 2, 30, true, Climate.HOT.name);
        this.extremeHills = new BiomeSettings.Element( "Extreme Hills", 3, 20, ClimateDistribution.MEDIUM.name());
        this.forest = new BiomeSettings.Element( "Forest", 4, 20, ClimateDistribution.MEDIUM.name());
        this.icePlains = new BiomeSettings.Element( "Ice Plains", 12, 30, Climate.SNOWY.name);
        this.jungle = new BiomeSettings.Element( "Jungle", 21, 5, Climate.WARM.name);
        this.megaTaiga = new BiomeSettings.Element( "Mega Taiga", 32, 5, Climate.COOL.name);
        this.mesaPlateau = new BiomeSettings.Element( "Mesa Plateau", 39, 1, Climate.HOT.name);
        this.mesaPlateau_F = new BiomeSettings.Element( "Mesa Plateau F", 38, 4, Climate.HOT.name);
        this.plains = new BiomeSettings.Element( "Plains", 1, 30, true, ClimateDistribution.PLAINS.name());
        this.roofedForest = new BiomeSettings.Element( "Roofed Forest", 29, 10, Climate.COOL.name);
        this.savanna = new BiomeSettings.Element( "Savanna", 35, 20, true, Climate.HOT.name);
        this.swampland = new BiomeSettings.Element( "Swampland", 6, 10, Climate.WARM.name);
        this.taiga = new BiomeSettings.Element( "Taiga (snowless)", 5, 10, Climate.COOL.name);
        this.iceMountains = new BiomeSettings.Element( "Ice Mountains", 13, 0, Climate.SNOWY.name);
        this.mushroomIsland = new BiomeSettings.ID( "Mushroom Island", 14);
        this.desertHills = new BiomeSettings.ID( "Desert Hills", 17);
        this.forestHills = new BiomeSettings.ID( "Forest Hills", 18);
        this.taigaHills = new BiomeSettings.ID( "Taiga Hills", 19);
        this.jungleHills = new BiomeSettings.ID( "Jungle Hills", 22);
        this.birchForestHills = new BiomeSettings.ID( "Birch Forest Hills", 28);
        this.coldTaigaHills = new BiomeSettings.ID( "Cold Taiga Hills", 31);
        this.megaTaigaHills = new BiomeSettings.ID( "Mega Taiga Hills", 33);
        this.extremeHillsPlus = new BiomeSettings.ID( "Extreme Hills+", 34);
        this.savannaPlateau = new BiomeSettings.ID( "Savanna Plateau", 36);
        this.mesa = new BiomeSettings.ID( "Mesa", 37);
        this.sunflowerPlains = new BiomeSettings.ID( "Sunflower Plains", 129);
        this.desertM = this.M(this.desert);
        this.extremeHillsM = this.M(this.extremeHills);
        this.flowerForest = this.M(this.forest, "Flower Forest");
        this.taigaM = this.M(this.taiga);
        this.swamplandM = this.M(this.swampland);
        this.icePlainsSpikes = this.M(this.icePlains, "Ice Plains Spikes");
        this.jungleM = this.M(this.jungle);
        this.birchForestM = this.M(this.birchForest);
        this.birchForestHillsM = this.M(this.birchForestHills);
        this.roofedForestM = this.M(this.roofedForest);
        this.coldTaigaM = this.M(this.coldTaiga);
        this.megaSpruceTaiga = this.M(this.megaTaiga, "Mega Spruce Taiga");
        this.extremeHillsPlusM = this.M(this.extremeHillsPlus);
        this.savannaM = this.M(this.savanna);
        this.savannaPlateauM = this.M(this.savannaPlateau);
        this.bryceMesa = this.M(this.mesa, "Mesa (Bryce)");
        this.mesaPlateauM = this.M(this.mesaPlateau);
        this.mesaPlateau_FM = this.M(this.mesaPlateau_F);
        this.biomesFromConfig = this.climateControlCategory.booleanSetting("VanillaBiomesOn", "", true);
        this.desert.setSubBiome(this.desertHills);
        this.forest.setSubBiome(this.forestHills);
        this.birchForest.setSubBiome(this.birchForestHills);
        this.roofedForest.setSubBiome(this.plains);
        this.taiga.setSubBiome(this.taigaHills);
        this.megaTaiga.setSubBiome(this.megaTaigaHills);
        this.coldTaiga.setSubBiome(this.coldTaigaHills);
        BiomeReplacer.Variable plainsSubBiomes = new BiomeReplacer.Variable();
        plainsSubBiomes.add(this.forestHills, 1);
        plainsSubBiomes.add(this.forest, 2);
        this.plains.setSubBiomeChooser(plainsSubBiomes);
        this.icePlains.setSubBiome(this.iceMountains);
        this.jungle.setSubBiome(this.jungleHills);
        this.extremeHills.setSubBiome(this.extremeHillsPlus);
        this.savanna.setSubBiome(this.savannaPlateau);
        this.mesaPlateau_F.setSubBiome(this.mesa);
    }

    public void setNativeBiomeIDs(File configDirectory) {
    }

    public void setRules(ClimateControlRules rules) {
        this.setVillages(rules);
        rules.disallowStoneBeach((Integer)this.mesaPlateau.biomeID().value());
        rules.disallowStoneBeach((Integer)this.mesaPlateau_F.biomeID().value());
    }

    public boolean biomesAreActive() {
        return (Boolean)this.biomesFromConfig.value();
    }

    public void onNewWorld() {
    }

    public void stripIDsFrom(Configuration config) {
    }

    public void forceIceMountains() {
        if ((Integer)this.iceMountains.biomeIncidences().value() < 10) {
            this.iceMountains.biomeIncidences().set(10);
        }

    }
}
