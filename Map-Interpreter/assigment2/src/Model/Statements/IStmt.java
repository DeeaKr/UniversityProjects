package Model.Statements;

import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.PrgState;
import Model.Type.Type;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt{

    PrgState execute(PrgState state) throws MyException, IOException;
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}

