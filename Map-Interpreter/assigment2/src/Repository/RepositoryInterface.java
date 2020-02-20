package Repository;

import Model.MyException;
import Model.ProgramState.PrgState;

import java.io.IOException;
import java.util.List;

public interface RepositoryInterface {
    //PrgState getCrtPrg();
    void addProgram(PrgState program);
    void logPrgStateExec(PrgState State) throws MyException, IOException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> state);
}
