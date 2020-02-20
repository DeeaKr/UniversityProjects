package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value {
    boolean val;
    public BoolValue(boolean v){val=v;}
    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public int getAddress() {
        return 0;
    }

    public boolean getVal() {
        return val;
    }
    @Override
    public String toString(){
        return " "+this.val+" ";

    }

}
