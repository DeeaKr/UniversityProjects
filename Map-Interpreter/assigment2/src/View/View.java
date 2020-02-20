package View;

import Controller.Controller;
import Model.Expressions.ArithmeticExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.MyException;
import Model.ProgramState.*;
import Model.Statements.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class View {
    private Controller controller;
    private PrgState state;
    private int FlagToPrint;
    public View(Controller controller)
    {
        this.controller=controller;
        this.FlagToPrint=1;
    }

 /*   // int v; v=2;Print(v)
    IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
            new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new
                    VarExp("v"))));
    //int a;int b; a=2+3*5;b=a+1;Print(b)

    IStmt ex2= new CompStmt(new VarDeclStmt("a", new IntType()),
            new CompStmt(new VarDeclStmt("b",new IntType()), new CompStmt(new AssignStmt("a",new ArithmeticExp(1,new ValueExp(new IntValue(2)),
                    new ArithmeticExp(4,new ValueExp(new IntValue(3)),new ValueExp(new IntValue(0))))),new CompStmt(new AssignStmt("b",
                    new ArithmeticExp(1,new VarExp("a"),new ValueExp(new IntValue(1)))),new PrintStmt(new VarExp("b"))))));

    //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)

    IStmt ex3= new CompStmt(new VarDeclStmt("a", new BoolType()),new CompStmt(new VarDeclStmt("v",new IntType()),
            new CompStmt(new AssignStmt("a",new ValueExp(new BoolValue(true))),new CompStmt(new IfStmt(new VarExp("a"),
                    new AssignStmt("v",new ValueExp(new IntValue(2))),new AssignStmt("v",new ValueExp(new IntValue(3)))),
                    new PrintStmt(new VarExp("v"))))));
    //String varf; varf="test.in";openRFile(varf); int varc; readFile(varf,varc); printf(varc); closeRFile(varf)

    IStmt fileTable = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(
            new AssignStmt("varf", new ValueExp(new StringValue("this"))), new CompStmt(new openRFile(
            new VarExp("varf")), new CompStmt(new VarDeclStmt("varc",
            new IntType()), new CompStmt(new readFile(new VarExp("varf"), "varc"),
            new CompStmt(new PrintStmt(new VarExp("varc")),
                    new CompStmt(new readFile(new VarExp("varf"), "varc"),
                            new CompStmt(new PrintStmt(new VarExp("varc")), new
                                    closeRFile(new VarExp("varf"))))))))));

    /*    IStmt fileTable= new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("test.in"))),
            new CompStmt(new openRFile(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc",new IntType()),
                    new CompStmt(new readFile(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")),new closeRFile(new VarExp("varf"))))))));
*/
/*
    private void initialise(IStmt statment)
    {
        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl =
                new MyDictionary<String,Value>();
        MyIList<Value> out = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable=
                new FileTable<StringValue,BufferedReader>();
        MyIDictionary<Integer,Value> heap=new Heap<>();
        //PrgState crtPrgState= new PrgState(stk,symtbl,out,statment);
        //this.controller.addProgam(crtPrgState);
        state=new PrgState(stk,symtbl,out,statment,fileTable,heap,1);
        this.controller.addProgam(state);

    }

    private void menu() throws MyException, IOException {
        System.out.println("Choose your option:");
        System.out.println("1.int v; v=2;Print(v)");
        System.out.println("2.int a;int b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("3.bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("4.string varf;\n" +
                "varf=\"this\";\n" +
                "openRFile(varf);\n" +
                "int varc;\n" +
                "readFile(varf,varc);print(varc);\n" +
                "readFile(varf,varc);print(varc)\n" +
                "closeRFile(varf)");
        System.out.println("5.one step");
        System.out.println("6.all steps");
        System.out.println("0.Exit");
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter option: ");
        int option = myObj.nextInt();
       // try {
            if (option == 1) {
                initialise(ex1);
            } else if (option == 2) {
                initialise(ex2);
            } else if (option == 3) {
                initialise(ex3);
            }else if(option==4) {
                initialise(fileTable);
            } else if (option == 5) {
                {
                    this.controller.oneStep(state);

                    System.out.println(state.toString());
                    String p=state.toString();
                    if(state.getStk().IsEmpty())
//                    if(p.equals("prgstate stack is empty"))
                        throw new MyException("Exit");

                }

            } else if (option == 6) {
                //this.controller.allStep();
                throw new MyException("Exit");
            } else if (option == 0) {
                throw new MyException("Exit");
            }
//        } catch (MyException exeption) {
//            throw exeption;
//        }
    }

    public void run() {

            while(true){
                try{
                    menu();

                } catch (MyException | IOException e) {
                    System.out.println(e.getMessage());
                    if(e.getMessage().equals("Exit"))
                        return;
                }
    }
    }
    */
}
