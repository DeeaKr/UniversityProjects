package Model.Expressions;

import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.Value;

public class ValueExp implements Exp {
   private Value e;
   public ValueExp(Value e){this.e=e;}
    @Override
    public Value eval(MyIDictionary<String, Value> tbl,MyIDictionary<Integer,Value> heap) throws MyException {
        return e;
    }
    @Override
    public String toString()
    {
        return this.e.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }
}
