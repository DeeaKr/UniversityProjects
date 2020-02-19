package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value {
    private String val;
    public StringValue(String v){val=v;}
    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public int getAddress() {
        return 0;
    }

    public String getVal() {
        return val;
    }
    @Override
    public String toString(){
        return " "+this.val+" ";

    }
}
