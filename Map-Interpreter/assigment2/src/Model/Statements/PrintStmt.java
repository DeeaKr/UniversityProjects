package Model.Statements;

import Model.Expressions.Exp;
import Model.Expressions.VarExp;
import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIList;
import Model.ProgramState.PrgState;
import Model.Statements.IStmt;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp v) {
        this.exp=v;
    }

    public String toString(){ return "print(" +exp.toString()+")";}
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> output=state.getOut();
        output.add(this.exp.eval(state.getSymTable(),state.getHeap()));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;

    }

}
