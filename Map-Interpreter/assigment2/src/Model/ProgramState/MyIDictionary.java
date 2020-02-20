package Model.ProgramState;

import Model.MyException;
import Model.Value.Value;
import org.w3c.dom.ls.LSOutput;

import java.util.Map;

public interface MyIDictionary<T, T1> {
    boolean isDefined(T id);
    void add(T id, T1 val) throws MyException;
    void delete(T id) throws MyException;
    Integer getFreeAddres();

    //public Value getValue(T id);

    void update(T id, T1 val) throws MyException;

    public T1 lookup(T id) throws MyException;


    @Override
    String toString();


    void setContent(Map<T, T1> map) ;
    Map<T, T1> getContent() ;
}
