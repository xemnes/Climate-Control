//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.generator;

import climateControl.api.DistributionPartitioner;
import climateControl.api.IncidenceModifier;
import climateControl.customGenLayer.GenLayerConstant;
import climateControl.customGenLayer.GenLayerLandReport;
import climateControl.customGenLayer.GenLayerLimitedCache;
import climateControl.customGenLayer.GenLayerMountainChains;
import climateControl.genLayerPack.GenLayerRiverInit;
import climateControl.genLayerPack.GenLayerSmooth;
import climateControl.genLayerPack.GenLayerZoom;
import com.Zeno410Utils.Numbered;
import com.Zeno410Utils.StringWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class MountainFormer extends DistributionPartitioner {
    private GenLayer mountainGens;
    private final boolean mesaMountains;

    public MountainFormer(boolean mesaMountains) {
        super(incidenceModifiers(mesaMountains));
        this.mesaMountains = mesaMountains;
        this.mountainGens = this.mountainGenLayers();
    }

    public void initWorldGenSeed(long par1) {
        this.mountainGens.initWorldGenSeed(par1);
    }

    protected IncidenceModifier modifier(int x, int z) {
        int index = this.mountainGens.getInts(x, z, 1, 1)[0];
        return (IncidenceModifier)super.modifiers.get(index);
    }

    private GenLayer mountainGenLayers() {
        GenLayer result = new GenLayerConstant(1L, 1);
        result = new GenLayerRiverInit(3001L, result);

        for(int i = 0; i < 3; ++i) {
            result = new GenLayerZoom(3001L + (long)i, (GenLayer)result);
            result = new GenLayerSmooth(3001L + (long)i, result);
        }

        result = new GenLayerMountainChains(3005L, (GenLayer)result);
        result = new GenLayerLimitedCache(result, 64);
        result = this.reportOn(result, "mountains.txt");
        return result;
    }

    GenLayer reportOn(GenLayer reportedOn, String fileName) {
        try {
            StringWriter target = new StringWriter(new File(fileName));
            reportedOn = new GenLayerLandReport(reportedOn, 40, target);
            return reportedOn;
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    private static ArrayList<IncidenceModifier> incidenceModifiers(boolean mesaMountains) {
        ArrayList<IncidenceModifier> result = new ArrayList();
        result.add(new LowlandModifier(mesaMountains));
        result.add(new MountainModifier(mesaMountains));
        return result;
    }

    private static Method getBiomeTypeMethod() {
        Method method = null;

        try {
            method = BiomeDictionary.class.getMethod("isBiomeOfType", Biome.class, BiomeDictionary.Type.class);
        } catch (NoSuchMethodException var5) {
            try {
                method = BiomeDictionary.class.getMethod("hasType", Biome.class, BiomeDictionary.Type.class);
            } catch (NoSuchMethodException var3) {
                Logger.getLogger(MountainFormer.class.getName()).log(Level.SEVERE, (String)null, var3);
            } catch (SecurityException var4) {
                Logger.getLogger(MountainFormer.class.getName()).log(Level.SEVERE, (String)null, var4);
            }
        } catch (SecurityException var6) {
        }

        return method;
    }

    private static class LowlandModifier implements IncidenceModifier {
        private final boolean mesaMountains;
        Method method = MountainFormer.getBiomeTypeMethod();

        LowlandModifier(boolean mesaMountains) {
            this.mesaMountains = mesaMountains;
        }

        public int modifiedIncidence(Numbered<Biome> biomeIncidence) {
            try {
                Biome biome = (Biome)biomeIncidence.item();
                Boolean isMountain = (Boolean)this.method.invoke((Object)null, biome, Type.MOUNTAIN);
                if (isMountain) {
                    return 0;
                } else if (biomeIncidence.item() == Biomes.EXTREME_HILLS) {
                    return 0;
                } else if (this.mesaMountains && (biomeIncidence.item() == Biomes.MESA_CLEAR_ROCK || biomeIncidence.item() == Biomes.MESA_ROCK)) {
                    return 0;
                } else {
                    Boolean isHill = (Boolean)this.method.invoke((Object)null, biome, Type.HILLS);
                    if (isHill) {
                        return biomeIncidence.count();
                    } else {
                        Boolean isOcean = (Boolean)this.method.invoke((Object)null, biome, Type.OCEAN);
                        return isOcean ? biomeIncidence.count() : biomeIncidence.count() * 4 / 3;
                    }
                }
            } catch (IllegalAccessException var6) {
                throw new RuntimeException(var6);
            } catch (IllegalArgumentException var7) {
                throw new RuntimeException(var7);
            } catch (InvocationTargetException var8) {
                throw new RuntimeException(var8);
            }
        }
    }

    private static class MountainModifier implements IncidenceModifier {
        Method method = MountainFormer.getBiomeTypeMethod();
        private final boolean mesaMountains;

        MountainModifier(boolean mesaMountains) {
            this.mesaMountains = mesaMountains;
        }

        public int modifiedIncidence(Numbered<Biome> biomeIncidence) {
            try {
                Biome biome = (Biome)biomeIncidence.item();
                Boolean isMountain = (Boolean)this.method.invoke((Object)null, biome, Type.MOUNTAIN);
                if (isMountain) {
                    return biomeIncidence.count() * 4;
                } else if (biomeIncidence.item() == Biomes.EXTREME_HILLS) {
                    return biomeIncidence.count() * 4;
                } else if (this.mesaMountains && (biomeIncidence.item() == Biomes.MESA_CLEAR_ROCK || biomeIncidence.item() == Biomes.MESA_ROCK)) {
                    return biomeIncidence.count() * 4;
                } else {
                    Boolean isHill = (Boolean)this.method.invoke((Object)null, biome, Type.HILLS);
                    if (isHill) {
                        return biomeIncidence.count();
                    } else {
                        Boolean isOcean = (Boolean)this.method.invoke((Object)null, biome, Type.OCEAN);
                        return isOcean ? biomeIncidence.count() : 0;
                    }
                }
            } catch (IllegalAccessException var6) {
                throw new RuntimeException(var6);
            } catch (IllegalArgumentException var7) {
                throw new RuntimeException(var7);
            } catch (InvocationTargetException var8) {
                throw new RuntimeException(var8);
            }
        }
    }
}
