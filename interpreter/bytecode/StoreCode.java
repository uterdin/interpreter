package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * STORE N <id>
 * Pop the top of stack; Store the value into the offset N from the
 * start of the frame; <id> is used as a comment and for dumping,
 * it's the variable name where the data is stored.
 */
public class StoreCode extends VarCode {
    private String id;
    private int offset;
    private int value;

    public void init(ArrayList<String> args){
        this.offset = Integer.parseInt(args.get(0));
        this.id = args.get(1);
    }

    public String toString(){
        return "STORE " + this.offset + " " + this.id;
    }

    public void execute(VirtualMachine vm) { this.value = vm.storeRunTimeStack(offset); }

    public int getOffset() { return this.offset; }

    public void setOffset(int offset) { this.offset = offset; }

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    public int getValue() { return this.value; }
}
