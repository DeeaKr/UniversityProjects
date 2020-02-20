package Model.Value;
import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value{
    int val;
    public IntValue(int v){val=v;}

    public int getVal() {return val;}
    public String toString() {
        return ""+this.val+"";
    }
    public Type getType() { return new IntType();}

    @Override
    public int getAddress() {
        return 0;
    }
}

