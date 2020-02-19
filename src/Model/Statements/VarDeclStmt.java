package Model.Statements;

import Model.MyException;
import Model.ProgramState.MyDictionary;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.PrgState;
import Model.Statements.IStmt;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class VarDeclStmt implements IStmt {
    private String name;
    private Type typ;
    public VarDeclStmt(String name, Type type)
    {
        this.name=name;
        this.typ=type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        //MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        if(symTbl.isDefined(name))
            throw new MyException("variable is already declared");
        else {

            symTbl.add(this.name, this.typ.defaultValue());


        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.add(name,typ);
        return typeEnv;
    }

    @Override
    public String toString(){
        return this.name+" "+this.typ.toString();
    }
}
