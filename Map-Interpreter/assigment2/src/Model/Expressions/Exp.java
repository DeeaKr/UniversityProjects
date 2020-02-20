package Model.Expressions;

import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.Type.Type;
import Model.Value.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl,MyIDictionary<Integer,Value> heap) throws MyException;
    @Override
    public String toString();
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;


}
