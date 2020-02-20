package Model.Statements;

import Model.Expressions.Exp;
import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    private Exp e1;

    public closeRFile(Exp e1){
        this.e1=e1;

    }
    @Override
    public String toString(){
        return "closeRFile: "+e1.toString();
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symtbl=state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTbl=state.getFileTable();
        Value v1;
        MyIDictionary<Integer,Value> heap=state.getHeap();
        v1=e1.eval(symtbl,heap);
        if(v1.getType().equals(new StringType()))
        {
            StringValue v2 = (StringValue) v1;
            BufferedReader bf;
            if(fileTbl.isDefined(v2))
            { bf=fileTbl.lookup(v2);
            bf.close();
            fileTbl.delete(v2);
            return null;
            //maybe not good
            }
            else
                throw new MyException("its not in the file table");

        }else
            throw new MyException("the type is not string");
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
            Type t1;
            t1=e1.typecheck(typeEnv);
            if(t1.equals(new StringType()))
            {
                return typeEnv;
            }
            else
                throw new MyException("close file: the name of the file is not string");
    }
}
