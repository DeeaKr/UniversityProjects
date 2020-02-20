package Model.Type;

import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class BoolType implements Type{
    @Override
    public boolean equals(Type another){
        return another instanceof BoolType;

    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public Type getInner() {
        return null;
    }

    @Override
    public String toString()
    {
        return "boolean";
    }

}

