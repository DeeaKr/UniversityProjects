package Model.Statements;

import Model.Expressions.Exp;
import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements IStmt {
    private Exp e1;
    public openRFile(Exp e1){
        this.e1=e1;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<StringValue, BufferedReader> FileTable=state.getFileTable();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        //IStmt lat=stk.pop();
        Value v1;
        v1=e1.eval(symTbl,state.getHeap());
        if(v1.getType().equals(new StringType())) {
            StringValue stringValue=(StringValue)v1;
            if(FileTable.isDefined(stringValue))
                throw  new MyException("the filename is already defined");
            else {
                BufferedReader bufferread=new BufferedReader( new FileReader(stringValue.getVal()));
                FileTable.add(stringValue,bufferread);
                return null;
            }
        }else
            throw new MyException("the type is not string");
       // return null;
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
            throw new MyException("open file: the name of the file is not string");
    }

    @Override
    public String toString(){
        return "openRFile: "+e1.toString();
    }
}
