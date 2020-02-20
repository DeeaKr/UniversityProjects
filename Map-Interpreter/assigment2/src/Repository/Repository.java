package Repository;

import Model.MyException;
import Model.ProgramState.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface {
    private List<PrgState> state;
    private int currentInd;
    private String logFilePath;
    public Repository(String path){
        this.state=new ArrayList<>();
        this.currentInd=0;
        this.logFilePath=path;
    }
    //@Override
    //public PrgState getCrtPrg() {
    //    return this.state.get(currentInd);
    //}

    @Override
    public void addProgram(PrgState program) {
        this.state.add(program);

    }

    @Override
    public void logPrgStateExec(PrgState State) throws MyException, IOException {
        PrintWriter logFile;
        //logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        //revenim aici
        FileWriter flwri=new FileWriter(this.logFilePath,true);
       // System.out.println("HAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        //System.out.println(this.state.get(currentInd).toString());
        flwri.write(State.toString());
        flwri.close();
    }

    @Override
    public List<PrgState> getPrgList() {
        return state;
    }

    @Override
    public void setPrgList(List<PrgState> state) {
        this.state=state;
    }

}
