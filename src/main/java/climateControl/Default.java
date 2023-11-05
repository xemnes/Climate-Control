//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package climateControl;

public abstract class Default<Type> {
    public Default() {
    }

    abstract Type item();

    public static class Self<Type extends PublicallyCloneable<Type>> extends Default<Type> {
        private final Type self;

        public Self(Type toSend) {
            this.self = toSend;
        }

        public Type item() {
            return (Type) this.self.clone();
        }
    }
}
