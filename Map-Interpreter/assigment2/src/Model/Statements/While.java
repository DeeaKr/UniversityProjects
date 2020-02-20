package Model.Statements;

import Model.Expressions.Exp;
import Model.MyException;
import Model.ProgramState.MyDictionary;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

import java.io.IOException;
import java.time.chrono.IsoChronology;
import java.util.Map;

public class While implements IStmt {
    private Exp e;
    private IStmt doS;
    public While(Exp e1, IStmt doS){
        this.e=e1;
        this.doS=doS;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String,Value> tbl=state.getSymTable();
        MyIDictionary<Integer,Value> heap=state.getHeap();
        MyIStack<IStmt> stk=state.getStk();
        Value cond=e.eval(tbl,heap);
        if(cond.getType().equals(new BoolType()))
        {
            BoolValue b=(BoolValue)cond;
            if(b.getVal())
            {
                stk.push(this);
                stk.push(doS);


                return null;

            }
            else{
                return null;
            }

        }else
            throw new MyException("the condition is not boolean");

        //return state;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type t1;
        MyIDictionary<String, Type> clone=new MyDictionary<String, Type>();
        for(Map.Entry<String,Type> v: typeEnv.getContent().entrySet())
        {
            clone.add(v.getKey(),v.getValue());
        }
        t1=e.typecheck(typeEnv);
        if(t1.equals(new BoolType()))
        {
            doS.typecheck(clone);
            return typeEnv;
        }
        else
            throw new MyException("the condition of the While is not boolean");

    }

    public String toString(){
        return "while("+e.toString()+")"+"do {"+doS.toString()+"}";
    }

}
