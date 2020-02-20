package Model.ProgramState;

import Model.MyException;

public interface MyIList<T> {
    void add(T element);
    void delete(T element) throws MyException;
    T getElement(int index) throws MyException;
    int size();
    @Override
    String toString();
}
