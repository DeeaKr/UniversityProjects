package Model.Expressions;

import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class LogicExp implements Exp {
    private Exp e1;
    private Exp e2;
    int op;//1-and, 2-or
    @Override
    public Value eval(MyIDictionary<String, Value> tbl,MyIDictionary<Integer,Value> heap) throws MyException {
        Value v1;
        Value v2;
        v1=e1.eval(tbl,heap);
        if(v1.getType().equals(new BoolType())){
            v2=e2.eval(tbl,heap);
            if(v2.getType().equals(new BoolType()))
            {
                BoolValue b1=(BoolValue)v1;
                BoolValue b2=(BoolValue)v2;
                boolean n1,n2;
                n1=b1.getVal();
                n2=b2.getVal();
                if(op==1) return new BoolValue(n1 && n2);
                if(op==2) return new BoolValue(n1 || n2);

            }else
                throw new MyException("second operand is not boolean");

        }else
            throw new MyException("first operand is not boolean");
        return new BoolValue(false);
    }
    @Override
    public String toString(){
        String logicexptoPrint=e1.toString();
        if(op==1)
            logicexptoPrint+=" "+"&&"+e2.toString();
        if(op==2)
            logicexptoPrint+=" "+"||"+e2.toString();
        return logicexptoPrint.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type t1,t2;
        t1=e1.typecheck(typeEnv);
        t2=e2.typecheck(typeEnv);
        if(t1.equals(new BoolType()))
        {
            if(t2.equals(new BoolType()))
                return new BoolType();
            else
                throw new MyException("the second operand is not boolean");
        }
        else
            throw new MyException("the first operand is not boolean");
    }
}
