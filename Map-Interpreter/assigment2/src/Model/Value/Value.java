package Model.Value;

import Model.Type.Type;

public interface Value {
    Type getType();
    public int getAddress();

    @Override
    public String toString();

}
