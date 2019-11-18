package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * POP n: pop the top n levels of the runtime stack
 */
public class PopCode extends ByteCode {
    private int n;

    public void init(ArrayList<String> args){
        this.n = Integer.parseInt(args.get(0));
    }

    public String toString(){
        return "POP " + this.n;
    }

    public void execute(VirtualMachine vm){
        for (int i=0; i<this.n; i++) {
            try { vm.popRunTimeStack(); }
            catch (IndexOutOfBoundsException e) { /* do nothing */ }
        }
    }
}
