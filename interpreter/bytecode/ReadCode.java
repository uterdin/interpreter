package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * READ
 * Read an int; prompt the user for input and push the value to the stack.
 * Make sure the input is validated.
 */
public class ReadCode extends ByteCode {
    private int input;

    public void init(ArrayList<String> args){}

    public String toString(){
        return "READ";
    }

    public void execute(VirtualMachine vm){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        this.input = in.nextInt();
        vm.pushRunTimeStack(this.input);
        in.close();
    }
}
