package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ARGS n
 * Used prior to calling a function. n = # of args. This instruction is immediately
 * followed by by the CALL instruction; the function has n args so ARGS n instructs
 * the interpreter to set up a new frame n down from the top of the runtime stack.
 * This will include the arguments in the new frame for the function.
 */
public class ArgsCode extends ByteCode {
    private int offset;

    public void init(ArrayList<String> args){ this.offset = Integer.parseInt(args.get(0)); }

    public String toString(){
        return "ARGS " + this.offset;
    }

    public void execute(VirtualMachine vm){ vm.newFrameAtRunTimeStack(this.offset); }
}
