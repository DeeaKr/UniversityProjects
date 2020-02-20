package Model.Statements;

import Model.Expressions.Exp;

import Model.MyException;
import Model.ProgramState.MyDictionary;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

import java.io.IOException;
import java.util.Map;

public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;


    public IfStmt(Exp e, IStmt t, IStmt el) {exp=e; thenS=t;elseS=el;}
    public String toString(){ return "IF("+ exp.toString()+") THEN(" +thenS.toString()
            +")ELSE("+elseS.toString()+")";}

    public PrgState execute(PrgState state) throws MyException, IOException {
        Value cond = exp.eval(state.getSymTable(),state.getHeap());
        if(!cond.getType().equals(new BoolType()))
            throw new MyException("conditional expr is not boolean");
        else
            if(((BoolValue) cond).getVal())
                this.thenS.execute(state);
            else
                this.elseS.execute(state);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        MyIDictionary<String, Type> clone=new MyDictionary<String, Type>();
        for(Map.Entry<String,Type> v: typeEnv.getContent().entrySet())
        {
            clone.add(v.getKey(),v.getValue());
        }
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(clone);
            elseS.typecheck(clone);
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }

}