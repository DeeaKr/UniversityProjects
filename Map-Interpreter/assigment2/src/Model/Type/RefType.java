package Model.Type;

import Model.Value.RefValue;
import Model.Value.Value;

public class RefType implements Type {
    private Type inner;
    public RefType(){};
    public RefType(Type inner){this.inner=inner;}
    public Type getInner(){return inner;}
    public boolean equals(Type another){
        if (another instanceof RefType)
            return inner.equals(another.getInner());
        else
            return false;
    }
    public String toString() { return "Ref(" +inner.toString()+")";}
    @Override
    public Value defaultValue() {
        return new RefValue(0,inner);
    }

}
