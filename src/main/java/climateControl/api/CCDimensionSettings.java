//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl.api;

import com.Zeno410Utils.Acceptor;
import com.Zeno410Utils.DimensionSet;
import com.Zeno410Utils.Mutable;
import com.Zeno410Utils.Named;
import com.Zeno410Utils.Settings;

public class CCDimensionSettings extends Settings {
    public final Mutable<String> excludeDimensions = this.general().stringSetting("excludeDimensionIDs", "-1,1", "Comma-separated list of dimension IDs, used only if include list is *");
    public final Mutable<String> includeDimensions = this.general().stringSetting("includeDimensionIDs", "0", "Comma-separated list of dimension IDs, put * to use exclude list");
    private DimensionSet.Exclude excludedDimensions;
    private DimensionSet.Include includedDimensions;
    private Acceptor<String> excludeUpdater;
    private Acceptor<String> includeUpdater;

    public final Named<CCDimensionSettings> named() {
        return Named.from("CCDimensions.cfg", this);
    }

    public CCDimensionSettings() {
        this.excludedDimensions = new DimensionSet.Exclude((String)this.excludeDimensions.value());
        this.includedDimensions = new DimensionSet.Include((String)this.includeDimensions.value());
        this.excludeUpdater = new Acceptor<String>() {
            public void accept(String accepted) {
                CCDimensionSettings.this.excludedDimensions = new DimensionSet.Exclude(accepted);
            }
        };
        this.includeUpdater = new Acceptor<String>() {
            public void accept(String accepted) {
                CCDimensionSettings.this.includedDimensions = new DimensionSet.Include(accepted);
            }
        };
        this.excludeDimensions.informOnChange(this.excludeUpdater);
        this.includeDimensions.informOnChange(this.includeUpdater);
    }

    public boolean ccOnIn(Integer dimension) {
        return this.includedDimensions.isIncluded(dimension, this.excludedDimensions);
    }
}
