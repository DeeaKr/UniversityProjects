package Model.Statements;

import Model.MyException;
import Model.ProgramState.MyDictionary;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyStack;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Map;

public class forkStmt implements IStmt{
    private IStmt stm;

    public forkStmt(IStmt st){
        stm=st;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> clone=new MyDictionary<String, Value>();
        for(Map.Entry<String,Value> v: state.getSymTable().getContent().entrySet())
        {
            clone.add(v.getKey(),v.getValue());
        }
        return new PrgState(new MyStack<>(),clone,state.getOut(),stm,state.getFileTable(),state.getHeap(),state.newId());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        MyIDictionary<String, Type> clone=new MyDictionary<String, Type>();
        for(Map.Entry<String,Type> v: typeEnv.getContent().entrySet())
        {
            clone.add(v.getKey(),v.getValue());
        }
        stm.typecheck(clone);
        return typeEnv;
    }

    public String toString()
    {
        return "Fork("+stm.toString()+")";
    }
}
