//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.genLayerPack;

import com.Zeno410Utils.Receiver;
import com.Zeno410Utils.StringWriter;
import java.io.File;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Biomes;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public abstract class GenLayerPack extends GenLayer {
    public static final int undefined = -2;
    private long worldGenSeed;
    protected GenLayer parent;
    private long chunkSeed;
    protected long baseSeed;
    private static final String __OBFID = "CL_00000559";

    public GenLayer getParent() {
        return this.parent;
    }

    public void setParent(GenLayer newParent) {
        this.parent = newParent;
    }

    public GenLayerPack(long par1) {
        super(par1);
        this.baseSeed = par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
    }

    public void initWorldGenSeed(long par1) {
        super.initWorldGenSeed(par1);
        this.worldGenSeed = par1;
        if (this.parent != null) {
            this.parent.initWorldGenSeed(par1);
        }

        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
    }

    public void initChunkSeed(long par1, long par3) {
        this.chunkSeed = this.worldGenSeed;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
    }

    public long savedChunkSeed() {
        return this.chunkSeed;
    }

    public void restoreChunkSeed(long restored) {
        this.chunkSeed = restored;
    }

    protected int nextInt(int par1) {
        int j = (int)((this.chunkSeed >> 24) % (long)par1);
        if (j < 0) {
            j += par1;
        }

        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += this.worldGenSeed;
        return j;
    }

    public abstract int[] getInts(int var1, int var2, int var3, int var4);

    protected static boolean compareBiomesById(final int p_151616_0_, final int p_151616_1_) {
        if (p_151616_0_ == p_151616_1_) {
            return true;
        } else if (p_151616_0_ != Biome.getIdForBiome(Biomes.MESA_ROCK) && p_151616_0_ != Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK)) {
            try {
                return Biome.getBiome(p_151616_0_) != null && Biome.getBiome(p_151616_1_) != null ? Biome.getBiome(p_151616_0_).equals(Biome.getBiome(p_151616_1_)) : false;
            } catch (Throwable var5) {
                CrashReport crashreport = CrashReport.makeCrashReport(var5, "Comparing biomes");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Biomes being compared");
                crashreportcategory.addCrashSection("Biome A ID", p_151616_0_);
                crashreportcategory.addCrashSection("Biome B ID", p_151616_1_);
                crashreportcategory.addCrashSection("Biome A", new Callable() {
                    private static final String __OBFID = "CL_00000560";

                    public String call() {
                        return String.valueOf(Biome.getBiome(p_151616_0_));
                    }
                });
                crashreportcategory.addCrashSection("Biome B", new Callable() {
                    private static final String __OBFID = "CL_00000561";

                    public String call() {
                        return String.valueOf(Biome.getBiome(p_151616_1_));
                    }
                });
                throw new ReportedException(crashreport);
            }
        } else {
            return p_151616_1_ == Biome.getIdForBiome(Biomes.MESA_ROCK) || p_151616_1_ == Biome.getIdForBiome(Biomes.MESA_CLEAR_ROCK);
        }
    }

    protected static boolean isBiomeOceanic(int id) {
        if (id > 255) {
            return false;
        } else {
            return id == Biome.getIdForBiome(Biomes.OCEAN) || id == Biome.getIdForBiome(Biomes.DEEP_OCEAN) || id == Biome.getIdForBiome(Biomes.FROZEN_OCEAN);
        }
    }

    protected static boolean isOceanic(int id) {
        if (id == Biome.getIdForBiome(Biomes.FROZEN_OCEAN)) {
            return true;
        } else if (id > 255) {
            return false;
        } else {
            return id == Biome.getIdForBiome(Biomes.OCEAN) || id == Biome.getIdForBiome(Biomes.DEEP_OCEAN) || id == Biome.getIdForBiome(Biomes.FROZEN_OCEAN);
        }
    }

    protected int selectRandom(int... p_151619_1_) {
        return p_151619_1_[this.nextInt(p_151619_1_.length)];
    }

    protected int selectModeOrRandom(int p_151617_1_, int p_151617_2_, int p_151617_3_, int p_151617_4_) {
        return p_151617_2_ == p_151617_3_ && p_151617_3_ == p_151617_4_ ? p_151617_2_ : (p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_3_ ? p_151617_1_ : (p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_4_ ? p_151617_1_ : (p_151617_1_ == p_151617_3_ && p_151617_1_ == p_151617_4_ ? p_151617_1_ : (p_151617_1_ == p_151617_2_ && p_151617_3_ != p_151617_4_ ? p_151617_1_ : (p_151617_1_ == p_151617_3_ && p_151617_2_ != p_151617_4_ ? p_151617_1_ : (p_151617_1_ == p_151617_4_ && p_151617_2_ != p_151617_3_ ? p_151617_1_ : (p_151617_2_ == p_151617_3_ && p_151617_1_ != p_151617_4_ ? p_151617_2_ : (p_151617_2_ == p_151617_4_ && p_151617_1_ != p_151617_3_ ? p_151617_2_ : (p_151617_3_ == p_151617_4_ && p_151617_1_ != p_151617_2_ ? p_151617_3_ : this.selectRandom(p_151617_1_, p_151617_2_, p_151617_3_, p_151617_4_))))))))));
    }

    public static byte getModdedBiomeSize(WorldType worldType, byte original) {
        WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return (byte)event.getNewSize();
    }

    public void report(File file, int[] toReport, int length, int width) {
        Receiver<String> reportee = StringWriter.from(file);

        int i;
        String report;
        int j;
        for(i = 0; i < width; ++i) {
            report = "";

            for(j = 0; j < length; ++j) {
                int value = toReport[i * length + j];
                if (value == 24) {
                    value = 0;
                }

                report = report + value + " ";
            }

            reportee.accept(report);
        }

        reportee.accept("");

        for(i = 0; i < width; ++i) {
            report = "";

            for(j = 0; j < length; ++j) {
                report = report + i + " ";
            }

            reportee.accept(report);
        }

        reportee.accept("");

        for(i = 0; i < width; ++i) {
            report = "";

            for(j = 0; j < length; ++j) {
                report = report + j + " ";
            }

            reportee.accept(report);
        }

        reportee.done();
    }

    public void poison(int[] toPoison, int length) {
        for(int i = 0; i < length; ++i) {
            toPoison[i] = -2;
        }

    }

    public void taste(int[] toPoison, int length) {
        for(int i = 0; i < length; ++i) {
            if (toPoison[i] == -2) {
                throw new RuntimeException("" + i);
            }
        }

    }
}
