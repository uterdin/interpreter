package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * This bytecode is used to set the state of dumping on the VM.
 * When dump is on, after the execution of each bytecode, the state
 * of the runtime stack is dumped on to the console.
 */
public class DumpCode extends ByteCode {
    private String state;

    public void init(ArrayList<String> args){ this.state = args.get(0); }

    public String toString() {
        return "DUMP " + this.state;
    }

    public void execute(VirtualMachine vm) {
        if (this.state.equals("ON")) {
            vm.setIsDumping(true);
        } else {
            vm.setIsDumping(false);
        }
    }
}
