package View;

import Controller.Controller;
import Model.MyException;

import java.io.IOException;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key,String desc,Controller ctr){
        super(key,desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try {
            ctr.allStep();

        } catch (InterruptedException | MyException e) {
            e.getMessage();
        }
    }
}
