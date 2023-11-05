//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.customGenLayer;

import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.Zeno410Logger;
import java.util.logging.Logger;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerNoPlains extends GenLayerPack {
    private static int mushroomIsland;
    Logger logger = (new Zeno410Logger("NoPlains")).logger();

    public GenLayerNoPlains(long par1, GenLayer par3GenLayer) {
        super(par1);
        super.parent = par3GenLayer;
        if (super.parent == null) {
            throw new RuntimeException();
        }
    }

    public int[] getInts(int par1, int par2, int par3, int par4) {
        int parentSpan = par3;
        int[] aint = this.parent.getInts(par1, par2, par3, par4);

        for(int z = 0; z < par4; ++z) {
            for(int x = 0; x < par3; ++x) {
                int center = aint[x + z * parentSpan];
                if (center == Biome.getIdForBiome(Biomes.PLAINS)) {
                    throw new RuntimeException("at " + (x + z * parentSpan));
                }
            }
        }

        return aint;
    }
}
