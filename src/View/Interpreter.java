package View;

import Controller.Controller;
import Model.Expressions.*;
import Model.MyException;
import Model.ProgramState.*;
import Model.Statements.*;
import Model.Type.*;
import Model.Value.*;
import Repository.Repository;
import Repository.RepositoryInterface;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) throws MyException {
        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl =
                new MyDictionary<String,Value>();
        MyIList<Value> out = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable=
                new FileTable<StringValue,BufferedReader>();
        MyIDictionary<Integer,Value> heap=new Heap<Integer, Value>();
        MyIDictionary<String,Type> typeEnv =new MyDictionary<>();

        TextMenu menu=new TextMenu();
        // int v; v=2;Print(v)
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));
        PrgState prg1=new PrgState(stk,symtbl,out,ex1,fileTable,heap,1);
        RepositoryInterface repo0=new Repository("log0.txt");
        Controller ctr0=new Controller(repo0);
        try{
            ex1.typecheck(typeEnv);
            ctr0.addProgam(prg1);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 1");
        }

        MyIStack<IStmt> stk2 = new MyStack<IStmt>();
        //int a;int b; a=2+3*5;b=a+1;Print(b)
        IStmt ex2= new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()), new CompStmt(new AssignStmt("a",new ArithmeticExp(1,new ValueExp(new IntValue(2)),
                        new ArithmeticExp(4,new ValueExp(new IntValue(3)),new ValueExp(new IntValue(5))))),new CompStmt(new AssignStmt("b",
                        new ArithmeticExp(1,new VarExp("a"),new ValueExp(new IntValue(1)))),new PrintStmt(new VarExp("b"))))));

        try{
            ex2.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 2");
        }
        PrgState prg2=new PrgState(stk2,symtbl,out,ex2,fileTable,heap,2);
        RepositoryInterface repo2=new Repository("l2.txt");
        Controller ctro2=new Controller(repo2);
        ctro2.addProgam(prg2);


        MyIStack<IStmt> stk3 = new MyStack<IStmt>();
        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3= new CompStmt(new VarDeclStmt("a", new BoolType()),new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("a",new ValueExp(new BoolValue(true))),new CompStmt(new IfStmt(new VarExp("a"),
                        new AssignStmt("v",new ValueExp(new IntValue(2))),new AssignStmt("v",new ValueExp(new IntValue(3)))),
                        new PrintStmt(new VarExp("v"))))));
        try{
            ex3.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 3");
        }

        PrgState prg3=new PrgState(stk3,symtbl,out,ex3,fileTable,heap,3);
        RepositoryInterface repo3=new Repository("lo3.txt");
        Controller ctro3=new Controller(repo3);
        ctro3.addProgam(prg3);


        MyIStack<IStmt> stk4 = new MyStack<IStmt>();
        //String varf; varf="this";openRFile(varf); int varc; readFile(varf,varc); printf(varc); closeRFile(varf)
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new IntType()), new CompStmt(
                new AssignStmt("varf", new ValueExp(new IntValue(20))), new CompStmt(new openRFile(
                new VarExp("varf")), new CompStmt(new VarDeclStmt("varc",
                new IntType()), new CompStmt(new readFile(new VarExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarExp("varc")),
                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                new CompStmt(new PrintStmt(new VarExp("varc")), new
                                        closeRFile(new VarExp("varf"))))))))));
        PrgState prg4=new PrgState(stk4,symtbl,out,ex4,fileTable,heap,4);
        RepositoryInterface repo4=new Repository("log4.txt");
        Controller ctro4=new Controller(repo4);

        try{
            ex4.typecheck(typeEnv);
            ctro4.addProgam(prg4);
            menu.addCommand(new RunExample("4",ex4.toString(),ctro4));
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 4");
        }





        IStmt ex6 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(
                new AssignStmt("varf", new ValueExp(new StringValue("this"))), new CompStmt(new openRFile(
                new VarExp("varf")), new CompStmt(new VarDeclStmt("varc",
                new IntType()), new CompStmt(new readFile(new VarExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarExp("varc")),
                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                new CompStmt(new PrintStmt(new VarExp("varc")),new CompStmt(new closeRFile(new VarExp("varf")),new closeRFile(new VarExp("varf")))
                                        ))))))));
        try{
            ex6.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 6");
        }
        MyIStack<IStmt> stk6 = new MyStack<IStmt>();
        PrgState prg6=new PrgState(stk6,symtbl,out,ex6,fileTable,heap,5);
        RepositoryInterface repo6=new Repository("log6.txt");
        Controller ctro6=new Controller(repo6);
        ctro6.addProgam(prg6);

        //int d; d=4; int f; f=5; If f>d THEN print(f) ELSE print("f smaller")
        MyIStack<IStmt> stk5 = new MyStack<IStmt>();
        IStmt ex5=new CompStmt(new VarDeclStmt("d",new IntType()),new CompStmt(new AssignStmt("d",new ValueExp(new IntValue(4))),
                new CompStmt(new VarDeclStmt("f",new IntType()),new CompStmt(new AssignStmt("f", new ValueExp(new IntValue(5))),
                        new IfStmt(new RelationalExp(new VarExp("f"),new VarExp("d"),">"),new PrintStmt(new VarExp("f")),new PrintStmt(new ValueExp(new StringValue("f smaller"))))))));
        try{
            ex5.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 5");
        }
        PrgState prg5=new PrgState(stk5,symtbl,out,ex5,fileTable,heap,6);
        RepositoryInterface repo5=new Repository("log5.txt");
        Controller ctro5=new Controller(repo5);
        ctro5.addProgam(prg5);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        MyIStack<IStmt> stk7=new MyStack<IStmt>();
        IStmt ex7 =new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new New("v",new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new New("a",new VarExp("v")),
                        new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new VarExp("a")))))));
        try{
            ex7.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 7");
        }
        PrgState prg7=new PrgState(stk7,symtbl,out,ex7,fileTable,heap,7);
        RepositoryInterface repo7=new Repository("log7.txt");
        Controller ctro7=new Controller(repo7);
        ctro7.addProgam(prg7);


        //Ref int v; new(v,20); Ref Ref int a;new(a,v); print(rH(v));print(rH(rH(a))+5)
        MyIStack<IStmt> stk8=new MyStack<IStmt>();
        IStmt ex8=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),new CompStmt(new New("v",new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new New("a",new VarExp("v")),
                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),new PrintStmt(new ArithmeticExp(1,new rH(new rH(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));
        try{
            ex8.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 8");
        }
        PrgState prg8=new PrgState(stk8,symtbl,out,ex8,fileTable,heap,8);
        RepositoryInterface repo8=new Repository("log8.txt");
        Controller ctro8=new Controller(repo8);
        ctro8.addProgam(prg8);
        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        MyIStack<IStmt> stk9=new MyStack<IStmt>();
        IStmt ex9=new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new New("v",new ValueExp(new IntValue(20))),
                new CompStmt(new PrintStmt(new rH(new VarExp("v"))),new CompStmt(new wH("v",new ValueExp(new IntValue(30))),
                        new PrintStmt(new ArithmeticExp(1,new rH(new VarExp("v")),new ValueExp(new IntValue(5))))))));

        try{
            ex9.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 9");
        }
        PrgState prg9=new PrgState(stk9,symtbl,out,ex9,fileTable,heap,9);
        RepositoryInterface repo9=new Repository("log9.txt");
        Controller ctro9=new Controller(repo9);
        ctro9.addProgam(prg9);

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        MyIStack<IStmt> stk10=new MyStack<IStmt>();
        IStmt ex10=new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),
                new CompStmt(new While(new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(0)),">"),new CompStmt(new PrintStmt(new VarExp("v")),
                        new AssignStmt("v",new ArithmeticExp(2,new VarExp("v"),new ValueExp(new IntValue(1)))))),new PrintStmt(new VarExp("v")))));

        try{
            ex10.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 10");
        }
        PrgState prg10=new PrgState(stk10,symtbl,out,ex10,fileTable,heap,10);
        RepositoryInterface repo10=new Repository("log10.txt");
        Controller ctro10=new Controller(repo10);
        ctro10.addProgam(prg10);



        //Ref int v; new(v,20); Ref Ref int a;new(a,v); new(v,30); print(rH(v));print(rH(rH(a)))
        MyIStack<IStmt> stk11=new MyStack<IStmt>();
        IStmt ex11=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),new CompStmt(new New("v",new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new New("a",new VarExp("v")),
                        new CompStmt(new New("v",new ValueExp(new IntValue(30))),new CompStmt(new PrintStmt(new rH(new VarExp("v"))),new PrintStmt(new rH(new rH(new VarExp("a"))))))))));
        try{
            ex11.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 11");
        }
        PrgState prg11=new PrgState(stk11,symtbl,out,ex11,fileTable,heap,11);
        RepositoryInterface repo11=new Repository("log11.txt");
        Controller ctro11=new Controller(repo11);
        ctro11.addProgam(prg11);

        //Ref int f; new(f,4),Ref int v;new(v,3); Ref Ref int a; new(a,f);new(v,5);print(rH(v));print(rH(rH(a)))

        MyIStack<IStmt> stk12=new MyStack<IStmt>();
        IStmt ex12=new CompStmt( new VarDeclStmt("f",new RefType(new IntType())) ,new CompStmt( new New("f",new ValueExp(new IntValue(4))),new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),new CompStmt(new New("v",new ValueExp(new IntValue(3))),
                new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new New("a",new VarExp("f")),
                        new CompStmt(new New("v",new ValueExp(new IntValue(5))),new CompStmt(new PrintStmt(new rH(new VarExp("v"))),new PrintStmt(new rH(new rH(new VarExp("a"))))))))))));
        try{
            ex12.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 12");
        }
        PrgState prg12=new PrgState(stk12,symtbl,out,ex12,fileTable,heap,12);
        RepositoryInterface repo12=new Repository("log12.txt");
        Controller ctro12=new Controller(repo12);
        ctro12.addProgam(prg12);

        //int v; v=4; (while (v) print(v);v=v-1);print(v)
        MyIStack<IStmt> stk13=new MyStack<IStmt>();
        IStmt ex13=new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),
                new CompStmt(new While(new VarExp("v"),new CompStmt(new PrintStmt(new VarExp("v")),
                        new AssignStmt("v",new ArithmeticExp(2,new VarExp("v"),new ValueExp(new IntValue(1)))))),new PrintStmt(new VarExp("v")))));
        try{
            ex13.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 13");
        }
        PrgState prg13=new PrgState(stk13,symtbl,out,ex13,fileTable,heap,13);
        RepositoryInterface repo13=new Repository("log13.txt");
        Controller ctro13=new Controller(repo13);
        ctro13.addProgam(prg13);
        //int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))
        MyIStack<IStmt> stk14=new MyStack<>();
        IStmt ex14=new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new AssignStmt(
                "v", new ValueExp(new IntValue(10))),new CompStmt(new New("a",new ValueExp(new IntValue(22))),new CompStmt( new forkStmt(
                        new CompStmt(new wH("a",new ValueExp(new IntValue(30))),new CompStmt(new AssignStmt("v",new ValueExp(new BoolValue(true))),
                                new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new rH(new VarExp("a"))))))),new CompStmt(new PrintStmt(
                                        new VarExp("v")),new CompStmt(new PrintStmt(new rH(new VarExp("a"))),new PrintStmt(new VarExp("v")))))))));
        try{
            ex14.typecheck(typeEnv);
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage()+" exercise 14");
        }
        MyIDictionary<String, Value> symtbl14 =
                new MyDictionary<String,Value>();
        MyIList<Value> out14 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable14=
                new FileTable<StringValue,BufferedReader>();
        MyIDictionary<Integer,Value> heap14=new Heap<Integer, Value>();
        PrgState prg14=new PrgState(stk14,symtbl14,out14,ex14,fileTable14,heap14,1);
        RepositoryInterface repo14=new Repository("log14.txt");
        Controller ctro14=new Controller(repo14);
        ctro14.addProgam(prg14);
        //int a; a=10; print(a); fork(a=5;print(a));
        IStmt ex15=new CompStmt(new VarDeclStmt("a", new IntType()),new CompStmt(new AssignStmt("a",new ValueExp(new BoolValue(false
        ))),new CompStmt(
                new PrintStmt(new VarExp("a")),new forkStmt(new CompStmt(new AssignStmt("a",new ValueExp(new IntValue(5))),new PrintStmt(new VarExp("a"))))
        )));
        MyIStack<IStmt> stck15=new MyStack<>();
        MyIDictionary<String, Value> symtbl15 =
                new MyDictionary<String,Value>();
        MyIList<Value> out15 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable15=
                new FileTable<StringValue,BufferedReader>();
        MyIDictionary<Integer,Value> heap15=new Heap<Integer, Value>();
        PrgState prg15=new PrgState(stck15,symtbl15,out15,ex15,fileTable15,heap15,1);
        RepositoryInterface repo15=new Repository("log14.txt");
        Controller ctro15=new Controller(repo15);
        ctro15.addProgam(prg15);

        //Ref int a;Ref int b; new(a,22),new(b,10);
        // fork(print(rH(a));fork(wH(b,14);print(rH(b)));
        // fork(wH(a,15));
        // print(rH(a))
        /*IStmt ex16=new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new AssignStmt(
                "v", new ValueExp(new IntValue(10))),new CompStmt(new New("a",new ValueExp(new IntValue(22))),new CompStmt( new New("b",
                new ValueExp(new IntValue(10))),new CompStmt(new forkStmt(new CompStmt(new PrintStmt(new rH(new VarExp("a"))),new CompStmt(new forkStmt(new CompStmt(
                        new wH("b",new ValueExp(new IntValue(14))),new PrintStmt(new rH(new VarExp("b")))))),new CompStmt(new forkStmt(
                                new wH("a",new ValueExp(new IntValue(12)))),new PrintStmt(new rH(new VarExp("a")))))))))))));

         */
        IStmt ex16=new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new VarDeclStmt("b",new RefType(new IntType())),
                new CompStmt(new New("a", new ValueExp(new BoolValue(false))),new CompStmt(new New("b",new ValueExp(new IntValue(10))),
                        new forkStmt(new CompStmt(new PrintStmt(new rH(new VarExp("a"))),new CompStmt(new forkStmt(new CompStmt(new wH("b",
                                new ValueExp(new IntValue(14))),new PrintStmt(new rH(new VarExp("b"))))),new CompStmt(new forkStmt(new wH("a",
                                new ValueExp(new IntValue(15)))),new PrintStmt(new rH(new VarExp("a"))))))) )) ));

        try {
            MyIDictionary<String, Type> typev1 = new MyDictionary<>();
            ex16.typecheck(typev1);

        }catch (MyException e)
        {
            System.out.println(e.getMessage()+" exercise 16");
        }

        try {
            MyIDictionary<String, Type> typev1 = new MyDictionary<>();
            ex15.typecheck(typev1);

        }catch (MyException e)
        {
            System.out.println(e.getMessage()+" exercise 15");
        }


        MyIStack<IStmt> stck16=new MyStack<>();
        MyIDictionary<String, Value> symtbl16 =
                new MyDictionary<String,Value>();
        MyIList<Value> out16 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable16=
                new FileTable<StringValue,BufferedReader>();
        MyIDictionary<Integer,Value> heap16=new Heap<Integer, Value>();
        PrgState prg16=new PrgState(stck16,symtbl16,out16,ex16,fileTable16,heap16,1);
        RepositoryInterface repo16=new Repository("log16.txt");
        Controller ctro16=new Controller(repo16);
        ctro16.addProgam(prg16);




        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr0));
        menu.addCommand(new RunExample("2",ex2.toString(),ctro2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctro3));

        menu.addCommand(new RunExample("5",ex5.toString(),ctro5));
        menu.addCommand(new RunExample("6",ex6.toString(),ctro6));
        menu.addCommand(new RunExample("7",ex7.toString(),ctro7));
        menu.addCommand(new RunExample("8",ex8.toString(),ctro8));
        menu.addCommand(new RunExample("9",ex9.toString(),ctro9));
        menu.addCommand(new RunExample("10",ex10.toString(),ctro10));
        menu.addCommand(new RunExample("11",ex11.toString(),ctro11));
        menu.addCommand(new RunExample("12",ex12.toString(),ctro12));
        menu.addCommand(new RunExample("13",ex13.toString(),ctro13));
        menu.addCommand(new RunExample("14",ex14.toString(),ctro14));
        //menu.addCommand(new RunExample("15",ex15.toString(),ctro15));
        menu.addCommand(new RunExample("16",ex16.toString(),ctro16));

        menu.show();
    }
}
