package Model.Statements;

import Model.Expressions.Exp;
import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;

public class readFile implements IStmt {
    private Exp e1;
    private String var_name;
    public readFile(Exp e1, String var_name){
        this.e1=e1;
        this.var_name=var_name;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        Value v1;
        int lineN=0;
        IntValue lin;
        MyIDictionary<StringValue, BufferedReader> fileTbl=state.getFileTable();
        MyIDictionary<String, Value> symtable=state.getSymTable();
        if(!symtable.isDefined(var_name))
            throw new MyException("not in the symbol table");
        if(symtable.lookup(var_name).getType().equals(new IntType())) {
            v1 = e1.eval(symtable,state.getHeap());
            if(v1.getType().equals(new StringType())) {
                StringValue v2=(StringValue)v1;
                if(fileTbl.isDefined(v2))
                {
                    BufferedReader bf=fileTbl.lookup(v2);
                    String line=bf.readLine();
                    if(line==null)
                        lin=new IntValue(0);
                    else {
                       // System.out.println(line);
                        lin = new IntValue(Integer.parseInt(line));

                    }//lin=new IntValue(lineN);
                    symtable.update(var_name,lin);
                    return null;

                }
                else
                    throw new MyException("not in the file table");
            }
            else
                throw new MyException("the type is not string");
        }
        else
            throw new MyException("the type is not int");

    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type t1,t2;
        t1=typeEnv.lookup(var_name);
        t2=e1.typecheck(typeEnv);
        if(t1.equals(new IntType()))
        {
            if(t2.equals(new StringType()))
            {
                return typeEnv;
            }
            else
                throw new MyException("read file: the type is not string");
        }
        else
            throw new MyException("read file: the type is not int");
    }

    @Override
    public String toString(){
        return "readFile: "+e1.toString();
    }
}
