package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * Tell VM to stop executing program
 */

public class HaltCode extends ByteCode {

    public void init(ArrayList<String> args){}

    public String toString(){ return "HALT"; }

    public void execute(VirtualMachine vm){
        vm.setIsRunning(false);
    }
}
