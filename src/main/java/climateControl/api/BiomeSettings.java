//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import climateControl.biomeSettings.BiomeReplacer;
import climateControl.generator.BiomeSwapper;
import climateControl.generator.SubBiomeChooser;
import com.Zeno410Utils.Mutable;
import com.Zeno410Utils.Settings;
import com.Zeno410Utils.Zeno410Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

public abstract class BiomeSettings extends Settings {
    public static final Logger logger = (new Zeno410Logger("BiomeSettings")).logger();
    private final ArrayList<Element> elements = new ArrayList();
    private final ArrayList<ID> ids = new ArrayList();
    private final Settings.Category idCategory;
    private final Settings.Category incidenceCategory;
    private final Settings.Category villagesCategory;
    private final Settings.Category climateCategory;
    public final Settings.Category climateControlCategory = this.category("Assorted Parameters");

    public BiomeSettings(String categoryName) {
        this.idCategory = this.category(categoryName + "IDs");
        this.incidenceCategory = this.category(categoryName + "Incidences");
        this.villagesCategory = this.category(categoryName + "Villages");
        this.climateCategory = this.category(categoryName + "Climates", "Climate Types are: SNOWY,COOL,WARM,HOT,OCEAN,DEEP_OCEAN,MEDIUM,PLAINS,LAND, and SEA.  MEDIUM is COOL and WARM, PLAINS is COOL and WARM and HOT, LAND is all four land, SEA is both oceans");
    }

    public ArrayList<Element> elements() {
        return this.elements;
    }

    public boolean activeIn(WorldType worldType) {
        return true;
    }

    protected ID M(ID original, String name) {
        ID result = new ID(name, (Integer)original.biomeID().value() + 128);
        original.setMBiome(result);
        return result;
    }

    protected ID M(ID original) {
        return this.M(original, original.name + " M");
    }

    public ArrayList<ClimateDistribution.Incidence> incidences() {
        ArrayList<ClimateDistribution.Incidence> result = new ArrayList();
        Iterator var2 = this.elements.iterator();

        while(var2.hasNext()) {
            Element element = (Element)var2.next();
            if (element.active()) {
                try {
                    result.addAll(element.distribution().incidences(element));
                } catch (NoTempSetting var5) {
                }
            }
        }

        return result;
    }

    private ClimateDistribution climateDistribution(Element element) {
        return element.distribution();
    }

    public void update(SubBiomeChooser subBiomeChooser) {
        Iterator var2 = this.ids.iterator();

        while(var2.hasNext()) {
            ID id = (ID)var2.next();
            if (id.active()) {
                subBiomeChooser.set((Integer)id.biomeID().value(), id.subBiomeChooser);
            }
        }

    }

    public void updateMBiomes(BiomeSwapper mBiomes) {
        Iterator var2 = this.ids.iterator();

        while(var2.hasNext()) {
            ID id = (ID)var2.next();
            if (id.MBiome != null) {
                mBiomes.set((Integer)id.biomeID().value(), (Integer)id.MBiome.biomeID().value());
            }
        }

    }

    protected ID externalBiome(String name, int biomeID) {
        return new ID(name, new Mutable.Concrete(biomeID));
    }

    private void stripCategory(Settings.Category category, Configuration config) {
        ConfigCategory toRemove = config.getCategory(category.name.toLowerCase());
        config.removeCategory(toRemove);
        if (config.hasCategory(category.name.toLowerCase())) {
            throw new RuntimeException();
        }
    }

    private void stripFrom(Configuration config) {
        this.stripCategory(this.idCategory, config);
        this.stripCategory(this.incidenceCategory, config);
        this.stripCategory(this.villagesCategory, config);
        this.stripCategory(this.climateControlCategory, config);
        this.stripCategory(this.climateCategory, config);
    }

    public void stripIDsFrom(Configuration config) {
        this.stripCategory(this.idCategory, config);
    }

    public void readForeignConfigs(File generalConfigDirectory) {
        this.setNativeBiomeIDs(generalConfigDirectory);
    }

    public abstract void setRules(ClimateControlRules var1);

    public abstract void setNativeBiomeIDs(File var1);

    public abstract void onNewWorld();

    public String startBiomesName(String configName) {
        return configName + "InNewDimensons";
    }

    public void setVillages(ClimateControlRules rules) {
        Iterator var2 = this.ids.iterator();

        while(var2.hasNext()) {
            ID id = (ID)var2.next();
            if ((Boolean)id.hasVillages.value()) {
                rules.allowVillages((Integer)id.biomeID.value());
            }
        }

    }

    public final boolean equalsByIdentity(BiomeSettings compared) {
        return super.equals(compared);
    }

    public abstract boolean biomesAreActive();

    public void report() {
    }

    public void arrangeInteractions(ArrayList<BiomeSettings> biomeSettings) {
    }

    public void nameDefaultClimates() {
        Iterator var1 = this.ids.iterator();

        while(var1.hasNext()) {
            ID element = (ID)var1.next();
            if (((String)element.climate.value()).equalsIgnoreCase("DEFAULT")) {
                int biomeID = (Integer)element.biomeID().value();
                if (biomeID >= 0 && biomeID <= 255) {
                    Biome biome = Biome.getBiome(biomeID);

                    try {
                        ClimateDistribution distribution = element.defaultDistribution();
                        element.setDistribution(distribution);
                    } catch (NoTempSetting var6) {
                    }
                }
            }
        }

    }

    public class NoTempSetting extends RuntimeException {
        public NoTempSetting() {
        }
    }

    public class Element extends ID {
        private Mutable<Integer> biomeIncidence;

        public Element(String name, int ID, int incidence, boolean hasVillages, String climate) {
            super(name, ID, hasVillages, climate);
            this.biomeIncidence = BiomeSettings.this.incidenceCategory.intSetting(name + " Incidence", incidence);
            this.biomeIncidence.set(incidence);
            BiomeSettings.this.elements.add(this);
        }

        public Element(String name, int ID, int incidence, boolean hasVillages) {
            this(name, ID, incidence, hasVillages, "DEFAULT");
        }

        public Element(String name, int ID, int incidence, String climate) {
            this(name, ID, incidence, false, climate);
        }

        public Element(String name, int ID, int incidence) {
            this(name, ID, incidence, false);
        }

        public Element(String name, int ID) {
            this(name, ID, 10, false);
        }

        public Element(String name, int ID, String climate) {
            this(name, ID, 10, false, climate);
        }

        public Element(String name, int ID, boolean hasVillages) {
            this(name, ID, 10, false);
        }

        public Element(String name, int ID, boolean hasVillages, String climate) {
            this(name, ID, 10, false, climate);
        }


        public Mutable<Integer> biomeIncidences() {
            return this.biomeIncidence;
        }
    }

    public class ID {
        private Mutable<Integer> biomeID;
        private Mutable<Boolean> hasVillages;
        protected final Mutable<String> climate;
        public final String name;
        private ID MBiome;
        private BiomeReplacer subBiomeChooser;
        private ClimateDistribution distribution;

        public ID(String name, int ID, boolean hasVillages, String climate) {
            this(name, BiomeSettings.this.idCategory.intSetting(name + " ID", ID), hasVillages, climate);
        }

        public ID(String name, int ID, boolean hasVillages) {
            this(name, ID, hasVillages, "DEFAULT");
        }

        public ID(String name, int ID) {
            this(name, ID, false);
        }

        public ID(String name, Mutable<Integer> ID, boolean hasVillages, String climate) {
            this.MBiome = null;
            this.subBiomeChooser = BiomeReplacer.noChange;
            this.name = name;
            this.biomeID = ID;
            this.hasVillages = BiomeSettings.this.villagesCategory.booleanSetting(name + " hasVillages", hasVillages);
            this.climate = BiomeSettings.this.climateCategory.stringSetting(name + " climate", climate);
            BiomeSettings.this.ids.add(this);
            this.distribution = null;
        }

        public ID(String name, Mutable<Integer> ID) {
            this(name, ID, false, "DEFAULT");
        }

        public Mutable<Integer> biomeID() {
            return this.biomeID;
        }

        public void setMBiome(ID MBiome) {
            if (this.MBiome != null) {
                throw new RuntimeException("accidental reset of M Biome for " + this.name);
            } else {
                this.MBiome = MBiome;
            }
        }

        public void setIDFrom(Biome biome) {
            try {
                if (Biome.getBiome(Biome.getIdForBiome(biome)).equals(biome)) {
                    this.biomeID().set(Biome.getIdForBiome(biome));
                } else {
                    this.biomeID().set(-1);
                }
            } catch (NullPointerException var3) {
                this.biomeID().set(-1);
            } catch (IllegalStateException var4) {
                this.biomeID().set(-1);
            }

        }

        public boolean active() {
            if ((Integer)this.biomeID.value() < 0) {
                return false;
            } else {
                return Biome.getBiome((Integer)this.biomeID.value()) != null;
            }
        }

        public BiomeReplacer subBiomeChooser() {
            return this.subBiomeChooser;
        }

        public void setSubBiomeChooser(BiomeReplacer subBiomeChooser) {
            this.subBiomeChooser = subBiomeChooser;
        }

        public void setSubBiome(ID subBiome) {
            this.subBiomeChooser = new BiomeReplacer.Fixed((Integer)subBiome.biomeID.value());
        }

        private ClimateDistribution defaultDistribution() {
            Biome.TempCategory temp = null;

            try {
                temp = Biome.getBiome((Integer)this.biomeID().value()).getTempCategory();
                if (temp == TempCategory.COLD) {
                    return ClimateDistribution.SNOWY;
                }

                if (temp == TempCategory.MEDIUM) {
                    return ClimateDistribution.MEDIUM;
                }

                if (temp == TempCategory.WARM) {
                    return ClimateDistribution.HOT;
                }
            } catch (Exception var3) {
            }

            throw BiomeSettings.this.new NoTempSetting();
        }

        public void setDistribution(ClimateDistribution newDistribution) {
            this.distribution = newDistribution;
            this.climate.set(newDistribution.name());
        }

        public ClimateDistribution distribution() {
            return this.distribution != null ? this.distribution : this.configDistribution();
        }

        public ClimateDistribution configDistribution() {
            if (((String)this.climate.value()).equalsIgnoreCase("DEFAULT")) {
                return this.defaultDistribution();
            } else if (((String)this.climate.value()).equalsIgnoreCase("")) {
                return this.defaultDistribution();
            } else {
                Iterator var1 = ClimateDistribution.list.iterator();

                ClimateDistribution testedDistribution;
                do {
                    if (!var1.hasNext()) {
                        throw new RuntimeException("Climate " + (String)this.climate.value() + " not recognized");
                    }

                    testedDistribution = (ClimateDistribution)var1.next();
                } while(!((String)this.climate.value()).equalsIgnoreCase(testedDistribution.name()));

                return testedDistribution;
            }
        }
    }
}
