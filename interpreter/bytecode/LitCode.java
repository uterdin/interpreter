package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * LIT n
 * Load the literal value n
 *
 * LIT 0 i
 * Load 0 on the stack to initialize the variable i to the value 0
 * and resolve space on the RunTimeStack for i.
 */

public class LitCode extends VarCode {
    private int offset; // Offset in the context of LIT refers to the literal value to be loaded
    private String id;
    private int value;

    public void init(ArrayList<String> args){
        this.offset = Integer.parseInt(args.get(0));
        if (args.size() > 1) this.id = args.get(1);
    }

    public String toString(){
        if (this.id == null) return "LIT " + this.offset;
        return "LIT " + this.offset + " " + this.id;
    }

    // int offset will always contain the value to be loaded on to the RTS
    public void execute(VirtualMachine vm) { this.value = vm.pushRunTimeStack(this.offset); }

    public int getOffset() { return this.offset; }

    public void setOffset(int offset) { this.offset = offset; }

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    public int getValue() { return this.value; }
}
