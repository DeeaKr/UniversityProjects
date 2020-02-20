package Model.Value;

import Model.Type.RefType;
import Model.Type.Type;

public class RefValue implements Value{
    private Integer address;
    private Type locationType;
    public RefValue(Integer i, Type inner) {
        this.address=i;
        this.locationType=inner;
    }
    public int getAddress(){return address;}
    public void setAddress(int a){this.address=a;}

    public Type getType(){return new RefType(locationType);}
    public String toString(){
        return "("+address.toString()+","+locationType.toString()+")";
    }

}
