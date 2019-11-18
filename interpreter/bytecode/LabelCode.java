package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * LABEL <label>
 * Target for branches (FALSEBRANCH, GOTO, and CALL)
 */
public class LabelCode extends ByteCode {
    private String label;
    private int addrOf;

    public void init(ArrayList<String> args){
        this.label = args.get(0);
    }

    public String toString(){
        return "LABEL " + this.label;
    }

    public void execute(VirtualMachine vm){}

    public int getAddrOf() { return this.addrOf; }

    public void setAddrOf(int addrOf) { this.addrOf = addrOf; }

    public String getLabel() { return this.label; }
}
