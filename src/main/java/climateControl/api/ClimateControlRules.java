//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

public class ClimateControlRules {
    private boolean[] riversDisallowed = new boolean[256];
    private boolean[] stoneBeachDisallowed = new boolean[256];
    private boolean[] noBeaches = new boolean[256];
    private boolean[] hasVillages = new boolean[256];

    public ClimateControlRules() {
    }

    public final boolean riversAllowed(int biomeID) {
        return !this.riversDisallowed[biomeID];
    }

    public final boolean riversDisallowed(int biomeID) {
        return this.riversDisallowed[biomeID];
    }

    public final boolean stoneBeachAllowed(int biomeID) {
        return !this.stoneBeachDisallowed[biomeID];
    }

    public final boolean stoneBeachDisallowed(int biomeID) {
        return this.stoneBeachDisallowed[biomeID];
    }

    public final boolean noBeachesAllowed(int biomeID) {
        return this.noBeaches[biomeID];
    }

    public final boolean beachesAllowed(int biomeID) {
        return !this.noBeaches[biomeID];
    }

    public final boolean hasVillages(int biomeID) {
        return this.hasVillages[biomeID];
    }

    public void disallowRivers(int biomeID) {
        if (biomeID != -1) {
            this.riversDisallowed[biomeID] = true;
        }
    }

    public void allowVillages(int biomeID) {
        if (biomeID != -1) {
            this.hasVillages[biomeID] = true;
        }
    }

    public void disallowStoneBeach(int biomeID) {
        if (biomeID != -1) {
            this.stoneBeachDisallowed[biomeID] = true;
        }
    }

    public void noBeaches(int biomeID) {
        if (biomeID != -1) {
            this.noBeaches[biomeID] = true;
        }
    }
}
