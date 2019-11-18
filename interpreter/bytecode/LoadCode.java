package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * LOAD N <id>
 * Push the value in the slot which is offset N from the start of
 * the frame onto the top of the stack; <id> is used as a comment
 * for dumping, it's the variable name where the data is loaded.
 */
public class LoadCode extends VarCode {
    private String id;
    private int offset;
    private int value;


    public void init(ArrayList<String> args) {
        this.offset = Integer.parseInt(args.get(0));
        if (args.size() > 1) this.id = args.get(1);
    }

    public String toString(){
        if (this.id == null) return "LIT " + this.offset;
        return "LIT " + this.offset + " " + this.id;
    }

    public void execute(VirtualMachine vm) { this.value = vm.loadRunTimeStack(this.offset); }

    public int getOffset() { return this.offset; }

    public void setOffset(int offset) { this.offset = offset; }

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    public int getValue() { return this.value; }
}
