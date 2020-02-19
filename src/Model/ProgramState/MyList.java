package Model.ProgramState;

import Model.MyException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> lista;
    public MyList(){
        this.lista=new ArrayList<T>();
    }
    @Override
    public void add(T element){
            this.lista.add(element);
    }

    @Override
    public void delete(T element) throws MyException {
        if(!this.lista.contains(element))
            throw new MyException("This object does not exist!\n");
        this.lista.remove(element);
    }

    @Override
    public T getElement(int index) throws MyException {
        if(index<0 || index>=lista.size())
            throw new MyException("Not a valid index!\n");
        return this.lista.get(index);
    }


    @Override
    public int size() {
        return this.lista.size();
    }

    @Override
    public String toString()
    {
        String listTobeprinted ="[";
        for(T element :this.lista)
        {
            listTobeprinted+=element.toString()+" , ";

        }
        listTobeprinted+="]";
        return listTobeprinted.toString();

    }
}
