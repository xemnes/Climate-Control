//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

import climateControl.api.BiomeRandomizer;
import climateControl.api.Climate;
import climateControl.api.ClimateControlSettings;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class SettingsTester {
    public SettingsTester() {
    }

    public void test(ClimateControlSettings tested) {
        int highestActive;
        if (tested.biomeSettings().size() < 2) {
            ArrayList messages = new ArrayList();
            messages.add("There are no land biomes groups");
            messages.add("Vanilla biomes are off but no mod biomes are on");
            messages.add("Running will almost certainly crash the system");
            Minecraft minecraft = Minecraft.getMinecraft();
            if (minecraft == null) {
                throw new RuntimeException("Climate Control: Vanilla biomes are inactive but no mod biomes are on");
            }

            for(highestActive = 1; highestActive < 4; ++highestActive) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException var8) {
                    Logger.getLogger(SettingsTester.class.getName()).log(Level.SEVERE, (String)null, var8);
                }
            }
        }

        if (!(Boolean)tested.randomBiomes.value()) {
            Climate[] climates = new Climate[]{null, Climate.HOT, Climate.WARM, Climate.COOL, Climate.SNOWY};
            boolean[] active = new boolean[5];
            if ((Integer)tested.hotIncidence.value() < 0) {
                throw new RuntimeException("Climate Incidences must all be positive");
            }

            if ((Integer)tested.warmIncidence.value() < 0) {
                throw new RuntimeException("Climate Incidences must all be positive");
            }

            if ((Integer)tested.coolIncidence.value() < 0) {
                throw new RuntimeException("Climate Incidences must all be positive");
            }

            if ((Integer)tested.snowyIncidence.value() < 0) {
                throw new RuntimeException("Climate Incidences must all be positive");
            }

            active[1] = (Integer)tested.hotIncidence.value() > 0;
            active[2] = (Integer)tested.warmIncidence.value() > 0;
            active[3] = (Integer)tested.coolIncidence.value() > 0;
            active[4] = (Integer)tested.snowyIncidence.value() > 0;
            highestActive = -1;

            int i;
            for(i = 1; i < 5; ++i) {
                if (active[i]) {
                    highestActive = i;
                }
            }

            if (highestActive == -1) {
                throw new RuntimeException("Climate Control: All Climate incidences set to 0. At least one much be positive");
            }

            for(i = 0; i < highestActive; ++i) {
                if (active[i]) {
                    for(int j = i + 1; j < highestActive; ++j) {
                        active[j] = true;
                    }
                }
            }

            BiomeRandomizer.PickByClimate testClimatePicker = (new BiomeRandomizer(tested.biomeSettings())).pickByClimate();
            String activeWithoutBiomes = "";
            if (!testClimatePicker.hasBiomes(0)) {
                activeWithoutBiomes = activeWithoutBiomes.concat(Climate.OCEAN.name + " ");
            }

            if (!testClimatePicker.hasBiomes(Biome.getIdForBiome(Biomes.DEEP_OCEAN))) {
                activeWithoutBiomes = activeWithoutBiomes.concat(Climate.DEEP_OCEAN.name + " ");
            }

            for(i = 1; i < 5; ++i) {
                if (active[i] && !testClimatePicker.hasBiomes(i)) {
                    activeWithoutBiomes = activeWithoutBiomes.concat(climates[i].name + " ");
                }
            }

            if (activeWithoutBiomes.length() > 0) {
                throw new RuntimeException("Climate Control: No Biomes present for climates " + activeWithoutBiomes);
            }
        }

    }
}
