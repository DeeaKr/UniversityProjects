package Model.Statements;

import Model.Expressions.Exp;
import Model.MyException;
import Model.ProgramState.Heap;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

import java.io.IOException;
import java.sql.Ref;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.StreamHandler;

public class New implements IStmt {
    private String var_name;
    private Exp e;
    public New(String var_name, Exp e){
        this.var_name=var_name;
        this.e=e;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symtable=state.getSymTable();
        MyIDictionary<Integer,Value> heap=state.getHeap();
        //heap=new Heap<Integer,Value>();
        Value v1;
        v1=e.eval(symtable,heap);
        Integer addr=symtable.lookup(var_name).getAddress();
        if(symtable.isDefined(var_name))
        {
            Value v;
            v=symtable.lookup(var_name);
            //System.out.println(v1.getType().toString());
            //System.out.println(v.getType().toString());
            if(v.getType() instanceof RefType)
            {
                RefValue r=(RefValue)v;
                RefType rtype=(RefType) r.getType();



                if(rtype.getInner().equals(v1.getType()))
                {
                    Integer free=heap.getFreeAddres();
                    Map<Integer,Value> mp=new HashMap<>();
                    //heap=(Heap)heap;
 /*
                    for(Map.Entry<Integer,Value> set : ((Heap<Integer, Value>) heap).getEntrySet())
                    {
                        // Integer i=set.getKey();
                        Value vvv=set.getValue();
                        while(vvv instanceof RefValue)
                        {
                            RefValue v11=(RefValue)vvv;
                            Integer addr1=v11.getAddress();
                            if(addr1==addr)
                            {
                                ((RefValue) vvv).setAddress(free);
                                break;
                            }
                            Value newV=heap.lookup(addr1);
                            //heapp.put(addr,newV);
                            vvv=newV;
                        }
                    }

  */
                    heap.add(free,v1);
                    symtable.update(var_name,new RefValue(free,v1.getType()));


                    return null;

                }
                else throw new MyException("the types are not equal");
            }
            else throw new MyException("the type is not RefType");

        }
        else throw new MyException("the variable is not defined\n");
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = e.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    public String toString(){
        return "new("+var_name+","+ e.toString()+")";
    }
}
