//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.biomeSettings;

import climateControl.api.BiomeSettings;
import com.Zeno410Utils.IntRandomizer;

public abstract class BiomeReplacer {
    public static final BiomeReplacer noChange = new NoChange();

    public BiomeReplacer() {
    }

    public abstract int replacement(int var1, IntRandomizer var2, int var3, int var4);

    public static class Multiple extends BiomeReplacer {
        private final BiomeReplacer first;
        private final BiomeReplacer second;

        public Multiple(BiomeReplacer first, BiomeReplacer second) {
            this.first = first;
            this.second = second;
        }

        public int replacement(int biome, IntRandomizer randomizer, int x, int y) {
            int result = this.first.replacement(biome, randomizer, x, y);
            return biome != result ? result : this.second.replacement(biome, randomizer, x, y);
        }
    }

    private static class NoChange extends BiomeReplacer {
        private NoChange() {
        }

        public int replacement(int biome, IntRandomizer randomizer, int x, int y) {
            return biome;
        }
    }

    public static class Fixed extends BiomeReplacer {
        private final int replacement;

        public Fixed(int replacement) {
            this.replacement = replacement;
        }

        public int replacement(int biome, IntRandomizer randomizer, int x, int y) {
            return this.replacement;
        }
    }

    public static class Variable extends BiomeReplacer {
        private int totalPossibilities;
        private int[] alternatives = new int[0];

        public Variable() {
        }

        public void add(BiomeSettings.ID biome, int count) {
            this.totalPossibilities += count;
            int[] newAlternatives = new int[this.alternatives.length + count];

            int i;
            for(i = 0; i < this.alternatives.length; ++i) {
                newAlternatives[i] = this.alternatives[i];
            }

            for(i = this.alternatives.length; i < newAlternatives.length; ++i) {
                newAlternatives[i] = (Integer)biome.biomeID().value();
            }

            this.alternatives = newAlternatives;
        }

        public void addByNumber(int biome, int count) {
            this.totalPossibilities += count;
            int[] newAlternatives = new int[this.alternatives.length + count];

            int i;
            for(i = 0; i < this.alternatives.length; ++i) {
                newAlternatives[i] = this.alternatives[i];
            }

            for(i = this.alternatives.length; i < newAlternatives.length; ++i) {
                newAlternatives[i] = biome;
            }

            this.alternatives = newAlternatives;
        }

        public int replacement(int biome, IntRandomizer randomizer, int x, int y) {
            if (this.totalPossibilities == 0) {
                return biome;
            } else {
                int choice = randomizer.nextInt(this.totalPossibilities);
                return choice >= this.alternatives.length ? biome : this.alternatives[choice];
            }
        }
    }
}
