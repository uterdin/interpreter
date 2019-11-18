package interpreter.bytecode;


import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * GOTO <label>
 * Go to line of label
 */
public class GotoCode extends BranchCode {
    private String label; // the symbolic address of the corresponding label
    private int addrOf; // the program index where the corresponding label is
    private int addrTo; // the program index where this branch bytecode is located

    public void init(ArrayList<String> args) { this.label = args.get(0); }

    public String toString() { return "GOTO " + this.label; }

    public void execute(VirtualMachine vm) { vm.setPC(this.addrTo-1); }



    public String getLabel() { return this.label; }

    public void setLabel(String label) { this.label = label; }

    public int getAddrOf() { return this.addrOf; }

    public void setAddrOf(int addrOf) { this.addrOf = addrOf; }

    public int getAddrTo() { return this.addrTo; }

    public void setAddrTo(int addrTo) { this.addrTo = addrTo; }
}
