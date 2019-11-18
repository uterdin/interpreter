package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * WRITE
 * Write the value at the top of the stack to output.
 * Leave the value on the top of the stack.
 */
public class WriteCode extends ByteCode {
    public void init(ArrayList<String> args){}

    public String toString(){
        return "WRITE";
    }

    public void execute(VirtualMachine vm){
        System.out.println(vm.peekRunTimeStack());
    }
}
