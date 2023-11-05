//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class Climate {
    public static Climate SNOWY = new Climate("Snowy");
    public static Climate COOL = new Climate("Cool");
    public static Climate WARM = new Climate("Warm");
    public static Climate HOT = new Climate("Hot");
    public static Climate OCEAN = new Climate("Ocean");
    public static Climate DEEP_OCEAN = new Climate("Deep_Ocean");
//    public static Climate WARM_OCEAN = new Climate("Warm_Ocean");
//    public static Climate WARM_DEEP_OCEAN = new Climate("Warm_Deep_Ocean");
//    public static Climate HOT_OCEAN = new Climate("Hot_Ocean");
//    public static Climate HOT_DEEP_OCEAN = new Climate("Hot_Deep_Ocean");
//    public static Climate FROZEN_OCEAN = new Climate("Frozen_Ocean");
//    public static Climate FROZEN_DEEP_OCEAN = new Climate("Frozen_Deep_Ocean");
    public final String name;

    public static boolean legitimate(int climate) {
        if (climate == 0) {
            return true;
        } else if (climate == 1) {
            return true;
        } else if (climate == 2) {
            return true;
        } else if (climate == 3) {
            return true;
        } else if (climate == 4) {
            return true;
        } else if (climate == Biome.getIdForBiome(Biomes.DEEP_OCEAN)) {
            return true;
        } else {
            return climate == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND);
        }
    }

    public Climate(String name) {
        this.name = name;
    }
}
