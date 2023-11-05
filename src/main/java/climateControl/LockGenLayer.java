//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

import climateControl.customGenLayer.GenLayerCache;
import climateControl.customGenLayer.GenLayerLock;
import climateControl.genLayerPack.GenLayerPack;
import com.Zeno410Utils.Acceptor;
import com.Zeno410Utils.Accessor;
import com.Zeno410Utils.Filter;
import com.Zeno410Utils.Maker;
import com.Zeno410Utils.PlaneLocated;
import com.Zeno410Utils.SavedNumberedItems;
import com.Zeno410Utils.Streamer;
import com.Zeno410Utils.Zeno410Logger;
import java.util.logging.Logger;
import net.minecraft.world.World;
import net.minecraft.world.gen.layer.GenLayer;

public class LockGenLayer extends SavedNumberedItems<PlaneLocated<Integer>> {
    public static Logger logger = (new Zeno410Logger("LockedBiomes")).logger();
    private static Accessor<GenLayerPack, GenLayerPack> genLayerPackParent = new Accessor("field_75909_a");
    private static Accessor<GenLayer, GenLayer> genLayerParent = new Accessor("field_75909_a");
    private final Filter<GenLayer> targetLayerDetector;
    private final String targetName;
    private final Acceptor<LockGenLayer> generator;

    public LockGenLayer(String targetName, Filter<GenLayer> targetLayerDetector, Acceptor<LockGenLayer> generator) {
        super("Locked" + targetName + "Dimension", PlaneLocated.streamer(Streamer.ofInt()));
        this.targetName = targetName;
        this.targetLayerDetector = targetLayerDetector;
        this.generator = generator;
    }

    public Maker<PlaneLocated<Integer>> maker(int index) {
        return new Maker<PlaneLocated<Integer>>() {
            public PlaneLocated<Integer> item() {
                LockGenLayer.this.generator.accept(LockGenLayer.this);
                return new PlaneLocated();
            }
        };
    }

    public boolean saveOnNew(int index) {
        return true;
    }

    public static void showGenLayers(GenLayer top) {
        GenLayer parent = top;

        for(GenLayer current = null; parent != null; parent = parent(parent)) {
            logger.info(parent.toString());
        }

    }

    public boolean lock(GenLayer top, int dimension, World world, int exclusion, boolean watch) {
        GenLayer parent = top;
        GenLayer current = null;

        do {
            if (parent == null) {
                logger.info("can't find " + this.targetName + " level");
                return false;
            }

            current = parent;
            logger.info(parent.toString());
            parent = parent(parent);
        } while(!this.targetLayerDetector.accepts(parent));

        logger.info("locking with exclusion " + exclusion);
        GenLayerPack cache = new GenLayerCache(parent);
        GenLayerLock lock = new GenLayerLock(cache, (PlaneLocated)this.saved(dimension, world), exclusion);
        if (watch) {
            lock.setWatch(watch);
        }

        logger.info("setting up " + this.targetName + " watching " + watch);
        if (current instanceof GenLayerPack) {
            ((GenLayerPack)current).setParent(lock);
        } else {
            genLayerParent.setField(current, lock);
        }

        return true;
    }

    public static GenLayer parent(GenLayer child) {
        return child instanceof GenLayerPack ? ((GenLayerPack)child).getParent() : (GenLayer)genLayerParent.get(child);
    }
}
