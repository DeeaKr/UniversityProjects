package Model.ProgramState;

import Model.MyException;
import Model.Statements.IStmt;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String,Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private IStmt original;
    private MyIDictionary<Integer,Value> heap;
    static private int curentid=1;
    private int id;

    public int getId(){
        return this.id;
    }


    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl,
                    MyIList<Value> ot, IStmt prg,MyIDictionary<StringValue,BufferedReader> fileTable,MyIDictionary<Integer,Value> heap,int id){
        exeStack=stk;
        symTable=symtbl;
        out=ot;
        original=prg;
        this.fileTable=fileTable;
        this.heap=heap;
        this.id=id;
        //optional field, but good to have
        stk.push(prg);
    }

    public MyIDictionary<StringValue,BufferedReader> getFileTable(){
        return fileTable;
    }
    public MyIStack<IStmt> getStk() {

        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {

        return symTable;
    }
    public MyIDictionary<Integer,Value> getHeap(){
        return heap;
    }


    public MyIList<Value> getOut() {
        return out;
    }
    public String toString(){
        String prg="Start: ";
        prg+="id: "+id+"\n";
        prg+="symbtable "+id+"\n"+symTable.toString()+"\n"+"exestack: "+this.exeStack.toString()+"\n"+"out: "+this.out.toString();
        prg+="\n"+"file table: "+fileTable.toString();
        prg+="\n"+"heap table: "+heap.toString();
        return prg;
    }
    public boolean isNotCompleted(){
        return !exeStack.IsEmpty();
    }
    public PrgState oneStep() throws MyException, IOException {
        if(exeStack.IsEmpty()) {
            throw new MyException("prgstate stack is empty");
        }
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
    static public int newId()
    {
        curentid++;
        return curentid;
    }


}


