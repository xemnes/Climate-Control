//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.api.ClimateControlSettings;
import climateControl.api.IslandClimateMaker;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.IntRandomizer;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerBandedClimate extends GenLayerPack implements IslandClimateMaker {
    private final int bandWidth;
    private final int[] bandClimate = new int[6];
    private final int offset;
    private final boolean frozenIcecaps;
    private final boolean separateLandmasses;

    public GenLayerBandedClimate(long par1, GenLayer par3GenLayer, ClimateControlSettings settings, int multiplier) {
        super(par1);
        this.parent = par3GenLayer;
        this.offset = (Integer)settings.bandedClimateOffset.value() * multiplier - multiplier / 2;
        this.bandWidth = (Integer)settings.bandedClimateWidth.value() * multiplier;
        this.bandClimate[0] = 2;
        this.bandClimate[1] = 1;
        this.bandClimate[2] = 2;
        this.bandClimate[3] = 3;
        this.bandClimate[4] = 4;
        this.bandClimate[5] = 3;
        this.frozenIcecaps = settings.frozenIcecaps.value();
        this.separateLandmasses = settings.separateLandmasses.value();
    }

    @Override
    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] aint = this.parent.getInts(par1, par2, par3, par4);
        int[] aint1 = new int[par3 * par4];

        for(int i1 = 0; i1 < par4; ++i1) {
            for(int j1 = 0; j1 < par3; ++j1) {
                int latitude = par2 + i1 + this.offset;
                int band;
                if (latitude >= 0) {
                    band = latitude / this.bandWidth;
                    band %= 6;
                } else {
                    band = (latitude - this.bandWidth + 1) / this.bandWidth;
                    band %= 6;
                    if (band < -5) {
                        throw new RuntimeException("" + latitude + " " + band);
                    }

                    if (band < 0) {
                        band += 6;
                    }
                }

                if (aint[j1 + i1 * par3] == 0) {
                    if (this.frozenIcecaps && this.bandClimate[band] == 4) {
                        aint1[j1 + i1 * par3] = Biome.getIdForBiome(Biomes.FROZEN_OCEAN);
                    } else {
                        aint1[j1 + i1 * par3] = 0;
                    }
                } else {
                    aint1[j1 + i1 * par3] = this.bandClimate[band];
                    if (this.separateLandmasses) {
                        aint1[j1 + i1 * par3] += aint[j1 + i1 * par3] * 4;
                    }
                }
            }
        }

        return aint1;
    }

    @Override
    public int climate(int x, int latitude, IntRandomizer randomizer) {
        int band;
        latitude += this.offset;
        if (latitude >= 0) {
            band = latitude / this.bandWidth;
            band %= 6;
        } else {
            band = (latitude - this.bandWidth + 1) / this.bandWidth;
            band %= 6;
            if (band < -5) {
                throw new RuntimeException("" + latitude + " " + band);
            }

            if (band < 0) {
                band += 6;
            }
        }

        return this.bandClimate[band];
    }
}
