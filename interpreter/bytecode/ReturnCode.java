package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * RETURN <funcname>
 * Return from the current function; <funcname> is used as a
 * comment to indicate the current function. RETURN is generated
 * for intrinsic functions.
 */

public class ReturnCode extends ByteCode {
    private String label;

    public void init(ArrayList<String> args){ if (args.size()>0) this.label = args.get(0); }

    public String toString(){
        return "RETURN " + this.label;
    }

    public void execute(VirtualMachine vm){
        int tmp = vm.popRunTimeStack(); // Value at top of current frame in RTS to be returned
        vm.setPC(vm.popReturnAddrs()); // Jump back to last known return addr
        vm.popFrameRunTimeStack(); // Remove the current frame
        vm.pushRunTimeStack(tmp); // Push the popped value to top of current frame in RTS
    }

    public void setLabel(String label) { this.label = label; }

    public String getLabel() { return this.label; }
}
