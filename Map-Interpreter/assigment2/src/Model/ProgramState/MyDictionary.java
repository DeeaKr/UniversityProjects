package Model.ProgramState;

import Model.MyException;
import Model.Value.Value;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MyDictionary<T,T1> implements MyIDictionary<T,T1> {
    private HashMap<T,T1> dict;
    public MyDictionary()
    {
        this.dict=new HashMap<T,T1>();
    }
    @Override
    public boolean isDefined(T id) {
        return dict.containsKey(id);
    }


    public HashMap<T,T1> getDict(){return this.dict;}
    @Override
    public void add(T id, T1 val) throws MyException {

        if(id==null || val==null)
            throw new MyException("the key or the value is null");
        else {
            dict.put(id, val);
            //System.out.println(dict.toString());
        }
    }

    @Override
    public void delete(T id) throws MyException {
        if(!isDefined(id))
            throw new MyException("the key does not exist\n");
        else
            dict.remove(id);
    }

    @Override
    public Integer getFreeAddres() {
        return null;
    }

    //@Override
    //public Value getValue(T id) {
      //  return null;
    //}

    @Override
    public void update(T id, T1 val) throws MyException {
        if(!isDefined(id))
            throw new MyException("the key does not exist\n");
        dict.replace(id,val);
    }

    @Override
    public T1 lookup(T id) throws MyException {
        if(!isDefined(id))
            throw new MyException("the key does not exist\n");
        return dict.get(id);
    }
    @Override
    public String toString()
    {
        HashMap<T,T1> copydict;
        copydict=dict;
        String dictionaryToPrint="{";
        for(T id: dict.keySet())
            dictionaryToPrint+=id.toString()+" -> "+dict.get(id).toString()+";";
//        Iterator it = copydict.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//            dictionaryToPrint+=pair.getKey()+" -> "+pair.getValue();
//            it.remove();}
        dictionaryToPrint+="}";
        return dictionaryToPrint;

    }

    @Override
    public void setContent(Map<T, T1> map) {

    }

    @Override
    public Map<T, T1> getContent() {
        return dict;
    }
}
