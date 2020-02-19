package Model.Type;

import Model.Value.StringValue;
import Model.Value.Value;

public class StringType implements Type {
    @Override
    public boolean equals(Type another) {
        return another instanceof StringType;
    }
    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public Type getInner() {
        return null;
    }

    @Override
    public String toString(){
        return "String";
    }
}
