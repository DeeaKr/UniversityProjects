package Model.ProgramState;

import Model.MyException;

public interface MyIStack<T> {
    T pop() throws MyException;
    void push(T v);
    boolean IsEmpty();

    @Override
    String toString();
}
