package com.company;

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
import Repository.Repository;
import Repository.RepositoryInterface;
import View.View;






      /*  Scanner sc = new Scanner(System.in);
        System.out.println("enter a filepath:\n");
        String filePath = sc.next();
        RepositoryInterface repo = new Repository(filePath);
        Controller contr = new Controller(repo);
        View view = new View(contr);
        view.run();

       */


        // IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
        //       new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new
        //              VarExp("v"))));
        //int a;int b; a=2+3*5;b=a+1;Print(b)
//
//        IStmt ex2= new CompStmt(new VarDeclStmt("a", new IntType()),
//                new CompStmt(new VarDeclStmt("b",new IntType()), new CompStmt(new AssignStmt("a",new ArithmeticExp(1,new ValueExp(new IntValue(2)),
//                        new ArithmeticExp(3,new ValueExp(new IntValue(3)),new ValueExp(new IntValue(5))))),new CompStmt(new AssignStmt("b",
//                        new ArithmeticExp(1,new VarExp("a"),new ValueExp(new IntValue(1)))),new PrintStmt(new VarExp("b"))))));
//
//        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
//
//        IStmt ex3= new CompStmt(new VarDeclStmt("a", new BoolType()),new CompStmt(new VarDeclStmt("v",new IntType()),
//                new CompStmt(new AssignStmt("a",new ValueExp(new BoolValue(true))),new CompStmt(new IfStmt(new VarExp("a"),
//                        new AssignStmt("v",new ValueExp(new IntValue(2))),new AssignStmt("v",new ValueExp(new IntValue(3)))),
//                        new PrintStmt(new VarExp("v"))))));
 /*       IStmt fileTable = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(
                new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(new openRFile(
                new VarExp("varf")), new CompStmt(new VarDeclStmt("varc",
                new IntType()), new CompStmt(new readFile(new VarExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarExp("varc")),
                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                new CompStmt(new PrintStmt(new VarExp("varc")), new
                                        closeRFile(new VarExp("varf"))))))))));
       MyIStack stack=new MyStack<>();
       MyIDictionary dict= new MyDictionary<>();
       MyIDictionary ft=new FileTable<>();
       MyIList list= new MyList<>();
       PrgState program= new PrgState(stack,dict,list,fileTable,ft);
        RepositoryInterface repo=new Repository(filePath);
        repo.addProgram(program);
       Controller contl=new Controller(repo);
        try {
            contl.allStep();
        }
        catch(MyException | IOException e){
            System.out.println(e.getMessage());
        }
    }



  */




