//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import com.Zeno410Utils.IntRandomizer;
import com.Zeno410Utils.Numbered;
import com.Zeno410Utils.Zeno410Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class BiomeRandomizer {
    public static Logger logger = (new Zeno410Logger("BiomeRandomizer")).logger();
    public BiomeList global;
    private BiomeList hot;
    private BiomeList warm;
    private BiomeList cool;
    private BiomeList snowy;
    private BiomeList ocean;
    private BiomeList deepOcean;

    private BiomeRandomizer() {
    }

    public BiomeRandomizer(Collection<BiomeSettings> settings) {
        this.global = new BiomeList();
        this.hot = new BiomeList();
        this.warm = new BiomeList();
        this.cool = new BiomeList();
        this.snowy = new BiomeList();
        this.ocean = new BiomeList();
        this.deepOcean = new BiomeList();
        Iterator var2 = settings.iterator();

        while(true) {
            BiomeSettings setting;
            do {
                if (!var2.hasNext()) {
                    return;
                }

                setting = (BiomeSettings)var2.next();
            } while(!setting.biomesAreActive());

            Iterator var4 = setting.incidences().iterator();

            while(var4.hasNext()) {
                ClimateDistribution.Incidence incidence = (ClimateDistribution.Incidence)var4.next();
                this.add(incidence);
            }
        }
    }

    private BiomeList randomizer(Climate climate) {
        if (climate == Climate.DEEP_OCEAN) {
            return this.deepOcean;
        } else if (climate == Climate.SNOWY) {
            return this.snowy;
        } else if (climate == Climate.COOL) {
            return this.cool;
        } else if (climate == Climate.WARM) {
            return this.warm;
        } else if (climate == Climate.HOT) {
            return this.hot;
        } else if (climate == Climate.OCEAN) {
            return this.ocean;
        } else {
            throw new RuntimeException("No such climate");
        }
    }

    public int size() {
        return this.hot.biomes.length + this.ocean.biomes.length + this.deepOcean.biomes.length + this.snowy.biomes.length + this.cool.biomes.length + this.warm.biomes.length;
    }

    private void add(ClimateDistribution.Incidence incidence) {
        if (incidence.climate != Climate.DEEP_OCEAN && incidence.climate != Climate.OCEAN) {
            this.global.append(incidence.incidence, Biome.getBiome(incidence.biome));
        }

        this.randomizer(incidence.climate).append(incidence.incidence, Biome.getBiome(incidence.biome));
    }

    public PickByClimate pickByClimate() {
        return new PickByClimate();
    }

    private BiomeRandomizer modifiedBy(IncidenceModifier modifier) {
        if (this.ocean.biomes.length < 1) {
            throw new RuntimeException();
        } else {
            BiomeRandomizer result = new BiomeRandomizer();
            result.cool = this.cool.modifiedSubRandomizer(modifier);
            result.deepOcean = this.deepOcean.modifiedSubRandomizer(modifier);
            result.hot = this.hot.modifiedSubRandomizer(modifier);
            result.ocean = this.ocean.modifiedSubRandomizer(modifier);
            result.snowy = this.snowy.modifiedSubRandomizer(modifier);
            result.warm = this.warm.modifiedSubRandomizer(modifier);
            return result;
        }
    }

    public class BiomeList {
        private Biome[] biomes = new Biome[0];
        private int nextIndex = 0;

        public BiomeList() {
        }

        private void append(int count, Biome biome) {
            int lastIndex = this.nextIndex;
            Biome[] newArray = new Biome[this.nextIndex + count];

            for(int i = 0; i < lastIndex; ++i) {
                newArray[i] = this.biomes[i];
            }

            this.biomes = newArray;

            for(this.nextIndex = lastIndex; this.nextIndex < lastIndex + count; ++this.nextIndex) {
                this.biomes[this.nextIndex] = biome;
            }

        }

        public Biome choose(IntRandomizer source) {
            return this.biomes[source.nextInt(this.biomes.length)];
        }

        private BiomeList modifiedSubRandomizer(IncidenceModifier modifier) {
            BiomeList result = BiomeRandomizer.this.new BiomeList();
            Iterator var3 = this.incidences().iterator();

            while(var3.hasNext()) {
                Numbered<Biome> oldIncidence = (Numbered)var3.next();
                int newIncidence = modifier.modifiedIncidence(oldIncidence);
                if (newIncidence > 0) {
                    result.append(newIncidence, (Biome)oldIncidence.item());
                }
            }

            return result;
        }

        private ArrayList<Numbered<Biome>> incidences() {
            ArrayList<Numbered<Biome>> result = new ArrayList();
            Biome current = null;
            int occurances = 0;
            Biome[] var4 = this.biomes;
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Biome biome = var4[var6];
                if (biome == current) {
                    ++occurances;
                } else {
                    if (occurances > 0) {
                        result.add(Numbered.from(occurances, current));
                        occurances = 1;
                    }

                    current = biome;
                }
            }

            result.add(Numbered.from(occurances, current));
            BiomeRandomizer.logger.info("" + this.biomes.length + " biomes " + result.size() + " incidencese ");
            return result;
        }
    }

    public class PickByClimate {
        public PickByClimate() {
        }

        public int biome(int climate, IntRandomizer source) {
            if (climate == 0) {
                return Biome.getIdForBiome(BiomeRandomizer.this.ocean.choose(source));
            } else if (climate == 1) {
                return Biome.getIdForBiome(BiomeRandomizer.this.hot.choose(source));
            } else if (climate == 2) {
                return Biome.getIdForBiome(BiomeRandomizer.this.warm.choose(source));
            } else if (climate == 3) {
                return Biome.getIdForBiome(BiomeRandomizer.this.cool.choose(source));
            } else if (climate == 4) {
                return Biome.getIdForBiome(BiomeRandomizer.this.snowy.choose(source));
            } else if (climate == Biome.getIdForBiome(Biomes.DEEP_OCEAN)) {
                return Biome.getIdForBiome(BiomeRandomizer.this.deepOcean.choose(source));
            } else {
                return climate == Biome.getIdForBiome(Biomes.MUSHROOM_ISLAND) ? climate : 0;
            }
        }

        public boolean hasBiomes(int climate) {
            if (climate == 0) {
                return BiomeRandomizer.this.ocean.biomes.length > 0;
            } else if (climate == 1) {
                return BiomeRandomizer.this.hot.biomes.length > 0;
            } else if (climate == 2) {
                return BiomeRandomizer.this.warm.biomes.length > 0;
            } else if (climate == 3) {
                return BiomeRandomizer.this.cool.biomes.length > 0;
            } else if (climate == 4) {
                return BiomeRandomizer.this.snowy.biomes.length > 0;
            } else if (climate == Biome.getIdForBiome(Biomes.DEEP_OCEAN)) {
                return BiomeRandomizer.this.deepOcean.biomes.length > 0;
            } else {
                return false;
            }
        }

        public HashMap<IncidenceModifier, PickByClimate> modifiedDistributions(ArrayList<IncidenceModifier> comprehensiveModifiers) {
            HashMap<IncidenceModifier, PickByClimate> result = new HashMap();
            ArrayList<BiomeRandomizer> randomizers = new ArrayList();
            Iterator var4 = comprehensiveModifiers.iterator();

            while(var4.hasNext()) {
                IncidenceModifier modifier = (IncidenceModifier)var4.next();
                BiomeRandomizer.logger.info(modifier.toString());
                BiomeRandomizer randomizer = BiomeRandomizer.this.modifiedBy(modifier);
                result.put(modifier, randomizer.pickByClimate());
                randomizers.add(randomizer);
            }

            var4 = randomizers.iterator();

            while(true) {
                BiomeRandomizer changed;
                BiomeRandomizer randomizerx;
                Iterator var9;
                do {
                    if (!var4.hasNext()) {
                        return result;
                    }

                    randomizerx = (BiomeRandomizer)var4.next();
                    if (randomizerx.ocean.biomes.length == 0) {
                        var9 = randomizers.iterator();

                        while(var9.hasNext()) {
                            changed = (BiomeRandomizer)var9.next();
                            changed.ocean = BiomeRandomizer.this.ocean;
                        }
                    }

                    if (randomizerx.cool.biomes.length == 0) {
                        var9 = randomizers.iterator();

                        while(var9.hasNext()) {
                            changed = (BiomeRandomizer)var9.next();
                            changed.cool = BiomeRandomizer.this.cool;
                        }
                    }

                    if (randomizerx.deepOcean.biomes.length == 0) {
                        var9 = randomizers.iterator();

                        while(var9.hasNext()) {
                            changed = (BiomeRandomizer)var9.next();
                            changed.deepOcean = BiomeRandomizer.this.deepOcean;
                        }
                    }

                    if (randomizerx.hot.biomes.length == 0) {
                        var9 = randomizers.iterator();

                        while(var9.hasNext()) {
                            changed = (BiomeRandomizer)var9.next();
                            changed.hot = BiomeRandomizer.this.hot;
                        }
                    }

                    if (randomizerx.snowy.biomes.length == 0) {
                        var9 = randomizers.iterator();

                        while(var9.hasNext()) {
                            changed = (BiomeRandomizer)var9.next();
                            changed.snowy = BiomeRandomizer.this.snowy;
                        }
                    }
                } while(randomizerx.warm.biomes.length != 0);

                var9 = randomizers.iterator();

                while(var9.hasNext()) {
                    changed = (BiomeRandomizer)var9.next();
                    changed.warm = BiomeRandomizer.this.warm;
                }
            }
        }
    }
}
