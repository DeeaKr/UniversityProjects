package Model.Type;

import Model.Value.Value;

public interface Type {
    boolean equals(Type another);
    Value defaultValue();
    Type getInner();
    @Override
    public String toString();
}
