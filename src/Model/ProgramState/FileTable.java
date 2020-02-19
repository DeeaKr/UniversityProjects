package Model.ProgramState;

import Model.MyException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FileTable<T,T1> implements MyIDictionary<T,T1>{
    private HashMap<T,T1> fileTable;

    public FileTable()
    {
        this.fileTable=new HashMap<T,T1>();
    }


    @Override
    public boolean isDefined(T id) {
        return fileTable.containsKey(id);
    }

    @Override
    public void add(T id, T1 val) throws MyException {
        if(id==null || val==null)
            throw new MyException("the key or the value is null");
        else {
            fileTable.put(id, val);

        }
    }

    @Override
    public void delete(T id) throws MyException {
        if(!isDefined(id))
            throw new MyException("the key does not exist\n");
        else {
            fileTable.remove(id);

        }
    }

    @Override
    public Integer getFreeAddres() {
        return null;
    }

    @Override
    public void update(T id, T1 val) throws MyException {
        if(!isDefined(id))
            throw new MyException("the key does not exist\n");
        fileTable.replace(id,val);
    }

    @Override
    public T1 lookup(T id) throws MyException {
        if(!isDefined(id))
            throw new MyException("the key does not exist\n");
        return fileTable.get(id);
    }
    @Override
    public String toString(){
        String dictionaryToPrint="{";
        for(T id: fileTable.keySet())
            dictionaryToPrint+=id.toString()+" -> "+fileTable.get(id).toString()+";";
        dictionaryToPrint+="}";
        return dictionaryToPrint.toString();
    }

    @Override
    public void setContent(Map<T, T1> map) {

    }

    @Override
    public Map<T, T1> getContent() {
        return null;
    }
}
