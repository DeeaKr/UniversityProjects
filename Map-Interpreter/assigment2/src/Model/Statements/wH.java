package Model.Statements;

import Model.Expressions.Exp;
import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

import java.io.IOException;

public class wH implements IStmt {
    private String Var_name;
    private Exp e;
    public wH(String var_name,Exp e1){
        this.Var_name=var_name;
        this.e=e1;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symtable=state.getSymTable();
        MyIDictionary<Integer,Value> heap=state.getHeap();
        if(symtable.isDefined(Var_name))
        {
            if(symtable.lookup(Var_name).getType() instanceof RefType) {
                RefValue rf=(RefValue) symtable.lookup(Var_name);
                Integer add=rf.getAddress();
                if(heap.isDefined(add))
                {
                    Value v3=symtable.lookup(Var_name);
                    RefValue refValue=(RefValue) v3;
                    RefType refT=(RefType) refValue.getType();

                    Value v=e.eval(symtable,heap);
                    if(refT.getInner().equals(v.getType()))
                    {
                        heap.update(add,v);
                        return null;
                    }else
                        throw new MyException("the types are not equal");
                }else
                    throw new MyException("the key is not in the heap");

            }else
                throw new MyException("the type is not ref type");

        }else
            throw new MyException("the variable is not defined");
        //return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type t1,t2;
        t1=e.typecheck(typeEnv);
        t2=typeEnv.lookup(Var_name);
        if(t2 instanceof RefType)
        {
            RefType reft =(RefType) t2;
            if(reft.getInner().equals(t1))
            {
                return typeEnv;
            }
            else
                throw new MyException("write heap: the types are not equal");
        }
        else
            throw new MyException("write heap: the type is not ref type");
    }

    public String toString(){
        return "wH("+Var_name+","+e.toString()+")";
    }
}
