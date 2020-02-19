package Model.Expressions;

import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class RelationalExp implements Exp{
    private Exp e1;
    private Exp e2;
    String op;
    public RelationalExp(Exp e1, Exp e2, String op){
        this.e1=e1;
        this.e2=e2;
        this.op=op;
    }
    public Value eval(MyIDictionary<String, Value> tbl,MyIDictionary<Integer,Value> heap) throws MyException {
        Value v1;
        v1=e1.eval(tbl,heap);
        if(v1.getType().equals(new IntType()))
        {
            Value v2;
            v2=e2.eval(tbl,heap);
            if(v2.getType().equals(new IntType()))
            {



                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
              if(op.equals("<"))
                  return new BoolValue(n1<n2);
              if(op.equals(">"))
                  return new BoolValue(n1>n2);
              if(op.equals("=="))
                  return new BoolValue(n1==n2);
              if(op.equals("<="))
                  return new BoolValue(n1<=n2);
              if(op.equals(">="))
                  return new BoolValue(n1>=n2);
              if(op.equals("!="))
                  return new BoolValue(n1!=n2);
            }
            else
                throw new MyException("second expression is not an integer\n");

        }
        else
            throw new MyException("first expression is not an integer\n");

        return new BoolValue(false);
    }
    public String toString()
    {
        String rel=e1.toString();
        switch (op) {
            case "<":
                rel += "<" + e2.toString();
                break;
            case ">":
                rel += ">" + e2.toString();
                break;
            case "==":
                rel += "==" + e2.toString();
                break;
            case "<=":
                rel += "<=" + e2.toString();
                break;
            case ">=":
                rel += ">=" + e2.toString();
                break;
            case "!=":
                rel += "!=" + e2.toString();
                break;
        }
        return rel.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }
}
