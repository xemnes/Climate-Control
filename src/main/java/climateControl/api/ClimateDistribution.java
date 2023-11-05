//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ClimateDistribution {
    public static final ArrayList<ClimateDistribution> list = new ArrayList();
    private ArrayList<Climate> climates = new ArrayList();
    private final String name;
    public static ClimateDistribution SNOWY;
    public static ClimateDistribution COOL;
    public static ClimateDistribution WARM;
    public static ClimateDistribution HOT;
    public static ClimateDistribution MEDIUM;
    public static ClimateDistribution PLAINS;
    public static ClimateDistribution OCEAN;
    public static ClimateDistribution WARM_OCEAN;
    public static ClimateDistribution HOT_OCEAN;
    public static ClimateDistribution FROZEN_OCEAN;
    public static ClimateDistribution DEEP_OCEAN;
    public static ClimateDistribution WARM_DEEP_OCEAN;
    public static ClimateDistribution HOT_DEEP_OCEAN;
    public static ClimateDistribution FROZEN_DEEP_OCEAN;
    public static ClimateDistribution LAND;
    public static ClimateDistribution SEA;

    public ClimateDistribution(Climate base, String name) {
        this.climates.add(base);
        this.name = name;
        list.add(this);
    }

    private ClimateDistribution(Climate one, Climate two, String name) {
        this.climates.add(one);
        this.climates.add(two);
        this.name = name;
        list.add(this);
    }

    private ClimateDistribution(Climate one, Climate two, Climate three, String name) {
        this.climates.add(one);
        this.climates.add(two);
        this.climates.add(three);
        this.name = name;
        list.add(this);
    }

    private ClimateDistribution(Climate one, Climate two, Climate three, Climate four, String name) {
        this.climates.add(one);
        this.climates.add(two);
        this.climates.add(three);
        this.climates.add(four);
        this.name = name;
        list.add(this);
    }

    public String name() {
        return this.name;
    }

    public Collection<Incidence> incidences(BiomeSettings.Element element) {
        ArrayList<Incidence> result = new ArrayList();
        int doneSoFar = 0;
        int remainingIncidence = (Integer)element.biomeIncidences().value();

        int thisIncidence;
        for(Iterator var5 = this.climates.iterator(); var5.hasNext(); remainingIncidence -= thisIncidence) {
            Climate climate = (Climate)var5.next();
            thisIncidence = remainingIncidence / (this.climates.size() - doneSoFar);
            ++doneSoFar;
            result.add(new Incidence((Integer)element.biomeID().value(), thisIncidence, climate));
        }

        return result;
    }

    static {
        SNOWY = new ClimateDistribution(Climate.SNOWY, "SNOWY");
        COOL = new ClimateDistribution(Climate.COOL, "COOL");
        WARM = new ClimateDistribution(Climate.WARM, "WARM");
        HOT = new ClimateDistribution(Climate.HOT, "HOT");
        MEDIUM = new ClimateDistribution(Climate.COOL, Climate.WARM, "MEDIUM");
        PLAINS = new ClimateDistribution(Climate.COOL, Climate.WARM, Climate.HOT, "PLAINS");
        OCEAN = new ClimateDistribution(Climate.OCEAN, "OCEAN");
        WARM_OCEAN = new ClimateDistribution(Climate.OCEAN, Climate.WARM, "WARM_OCEAN");
        HOT_OCEAN = new ClimateDistribution(Climate.OCEAN, Climate.HOT, "HOT_OCEAN");
        FROZEN_OCEAN = new ClimateDistribution(Climate.OCEAN, Climate.SNOWY, "FROZEN_OCEAN");
        DEEP_OCEAN = new ClimateDistribution(Climate.DEEP_OCEAN, "DEEP_OCEAN");
        WARM_DEEP_OCEAN = new ClimateDistribution(Climate.DEEP_OCEAN, Climate.WARM, "WARM_DEEP_OCEAN");
        HOT_DEEP_OCEAN = new ClimateDistribution(Climate.DEEP_OCEAN, Climate.HOT, "HOT_DEEP_OCEAN");
        FROZEN_DEEP_OCEAN = new ClimateDistribution(Climate.DEEP_OCEAN, Climate.SNOWY, "FROZEN_DEEP_OCEAN");
        LAND = new ClimateDistribution(Climate.SNOWY, Climate.COOL, Climate.WARM, Climate.HOT, "LAND");
        SEA = new ClimateDistribution(Climate.OCEAN, Climate.DEEP_OCEAN, "SEA");
    }

    public class Incidence {
        public final int biome;
        public final int incidence;
        public final Climate climate;

        private Incidence(int biome, int incidence, Climate climate) {
            this.biome = biome;
            this.incidence = incidence;
            this.climate = climate;
        }
    }
}
