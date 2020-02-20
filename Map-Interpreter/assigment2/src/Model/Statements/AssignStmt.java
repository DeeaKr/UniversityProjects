package Model.Statements;

import Model.Expressions.Exp;
import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;
    public AssignStmt(String id, Exp e){
        this.id=id;
        this.exp=e;
    }
    public String toString(){ return id+"="+ exp.toString();}
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<Integer,Value> heap=state.getHeap();
        MyIDictionary<String, Value> symTbl= state.getSymTable();

        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl,heap);
            Type typId = (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId)) {
                symTbl.update(id, val);
            } else
                throw new MyException("declared type of variable" + id + " and type of the assigned expression do not match");
        }else
            throw new MyException("the used variable" +" "+ id + " was not declared before");
            return null;
        }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");

}
}
