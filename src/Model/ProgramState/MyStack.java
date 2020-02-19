package Model.ProgramState;

import Model.MyException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {

    private Stack<T> stack;
    public MyStack(){
        this.stack= new Stack<T>();
    }

    @Override
    public T pop() throws MyException {
        return stack.pop();
    }

    @Override
    public boolean IsEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }
    @Override
    public String toString(){
        String stackToprint="{";
        for(T element:this.stack){
            stackToprint += element.toString()+"|";
        }
        stackToprint+="}";
        return stackToprint.toString();
    }
}
