package Controller;

import Model.MyException;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.PrgState;
import Model.Statements.IStmt;
import Model.Value.IntValue;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.RepositoryInterface;

import javax.xml.validation.Validator;
import java.io.IOException;
import java.sql.Ref;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private RepositoryInterface repo;
    private ExecutorService executor;
    public Controller(RepositoryInterface repo)
    {
        this.repo=repo;
    }

    Map<Integer,Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value>
            heap){
        Map<Integer,Value> newMp= heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Map<Integer, Value> heapp=new HashMap<>();
        heapp=heap.entrySet().stream().filter(e->symTableAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for(Map.Entry<Integer,Value> set : newMp.entrySet() )
        {
           // Integer i=set.getKey();
            Value v=set.getValue();
            while(v instanceof RefValue)
            {
                RefValue v1=(RefValue)v;
                Integer addr=v1.getAddress();
                Value newV=heap.get(addr);
                heapp.put(addr,newV);
                v=newV;
            }
        }


       // Map<Integer,Value> newMp2=heap.entrySet().stream().filter(v->v.getValue() instanceof RefValue).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));



        //Stream combined = Stream.concat(newMp.entrySet().stream(), newMp2.entrySet().stream());
      // Map<Integer,Value> result = Stream.concat(newMp.entrySet().stream(), newMp2.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
        //       (value1,value2) ->new IntValue(0)));
        return heapp;
       // for()
    }
    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }


   /* public PrgState oneStep(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk=state.getStk();
        //MyIDictionary<String, Value> symbolTable=state.getSymTable();
        if(stk.IsEmpty())
            throw new MyException("prgstate stack is empty");
        //System.out.println("in the exe stack: "+stk.toString());
        //System.out.println("in sumbol table: "+ symbolTable.toString());
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    */
   public void oneStepForAllPrg(List<PrgState> prgList) throws MyException,InterruptedException {

           prgList.forEach(prg -> {
               try {
                   repo.logPrgStateExec(prg);
               } catch (MyException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           });

           prgList.forEach(System.out::println);
           List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (p::oneStep)).collect(Collectors.toList());
           List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
               try {
                   return future.get();
               } catch (Exception e) {
                   System.out.println(e);;
               }
               return null;
           }).filter(p -> p != null).collect(Collectors.toList());
           prgList.addAll(newPrgList);
           prgList.forEach(prg -> {
               try {
                   repo.logPrgStateExec(prg);
               } catch (MyException e) {
                   e.getMessage();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           });
           prgList.forEach(System.out::println);

           repo.setPrgList(prgList);



   }
    /*public void allStep() throws MyException, IOException {
        PrgState prg = repo.getCrtPrg(); // repo is the controller field of type MyRepoInterface
        repo.logPrgStateExec();
        //System.out.println(prg);//here you can display the prg state
        while (!prg.getStk().IsEmpty()){
            System.out.println(prg.toString());
            oneStep(prg);
            repo.logPrgStateExec();
            prg.getHeap().setContent(unsafeGarbageCollector(getAddrFromSymTable(prg.getSymTable().getContent().values()), prg.getHeap().getContent()));
            //here you can display the prg state
        }
        System.out.println(prg);
    }

     */
    public void allStep() throws MyException,InterruptedException {
        executor = Executors.newFixedThreadPool(2);

            List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
            while (prgList.size() > 0) {
                prgList.forEach(p -> p.getHeap().setContent(unsafeGarbageCollector(getAddrFromSymTable(p.getSymTable().getContent().values()), p.getHeap().getContent())));
                oneStepForAllPrg(prgList);

                prgList = removeCompletedPrg(repo.getPrgList());
            }
            executor.shutdownNow();

            repo.setPrgList(prgList);


    }

    //public RepositoryInterface getRepo(){return this.repo;}
    public void addProgam(PrgState state){
        this.repo.addProgram(state);
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
    {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }


}
