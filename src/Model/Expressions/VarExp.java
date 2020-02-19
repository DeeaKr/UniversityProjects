package Model.Expressions;

import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.Type.Type;
import Model.Value.Value;

public class VarExp implements Exp {
    String id;

    public VarExp(String v) {
        this.id=v;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl,MyIDictionary<Integer,Value> heap) throws MyException {
        return tbl.lookup(id);
    }
    @Override
    public String toString()
    {
        return this.id.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }
}
