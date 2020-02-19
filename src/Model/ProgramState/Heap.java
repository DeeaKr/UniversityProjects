package Model.ProgramState;

import Model.MyException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Heap<T,T1> implements MyIDictionary<T,T1> {
    HashMap<T,T1> heap;
    public Heap(){
        this.heap=new HashMap<>();
        this.freeaddres=1;
    }
    private Integer freeaddres;
    public Integer getFreeAddres(){return freeaddres;}
    //public Set<Map.Entry<T,T1>> getEntrySet(){return heap.entrySet();}


    @Override
    public boolean isDefined(T id) {
        return heap.containsKey(id);
    }

    @Override
    public void add(T id, T1 val) throws MyException {
        if(id==null || val==null)
            throw new MyException("the key or the value is null");
        else {
            heap.put(id, val);
            freeaddres+=1;

        }
    }

    @Override
    public void delete(T id) throws MyException {
        if(!isDefined(id))
            throw new MyException("the key does not exist\n");
        else {
            heap.remove(id);
            freeaddres-=1;

        }
    }

    @Override
    public void update(T id, T1 val) throws MyException {
        if(!isDefined(id))
            throw new MyException("the key does not exist\n");
        heap.replace(id,val);
    }

    @Override
    public T1 lookup(T id) throws MyException {
        if(!isDefined(id))
            throw new MyException("the key does not exist\n");
        return heap.get(id);
    }
    @Override
    public String toString(){
        String dictionaryToPrint="{";
        for(T id: heap.keySet())
            dictionaryToPrint+=id.toString()+" -> "+heap.get(id).toString()+";";
        dictionaryToPrint+="}";
        return dictionaryToPrint.toString();
    }

    @Override
    public void setContent(Map<T, T1> map) {
        heap= (HashMap<T, T1>) map;
    }

    @Override
    public Map<T, T1> getContent() {
        return heap;
    }
}
