package Model.Expressions;

import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class rH implements Exp {
    private Exp e;
    public rH( Exp e){
        this.e=e;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl,MyIDictionary<Integer,Value> heap) throws MyException {
        Value v1=e.eval(tbl,heap);
        if(v1.getType() instanceof RefType)
        {
            RefValue rv=(RefValue) v1;
            Integer address=rv.getAddress();
            if(!heap.isDefined(address))
                throw new MyException("the adrees is not a key");
            Value v3=heap.lookup(address);
            return v3;

        }
        throw new MyException("the type is not Ref Type");


        //return null;
    }
    public String toString(){
        return "rH("+e.toString()+")";
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
            Type typ=e.typecheck(typeEnv);
            if (typ instanceof RefType) {
                RefType reft =(RefType) typ;
                return reft.getInner();
            } else
                throw new MyException("the rH argument is not a Ref Type");
        }
    }

