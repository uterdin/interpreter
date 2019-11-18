package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * CALL <funcname>
 * Transfer control to the indicated function. Make sure to save
 * where the function should return to.
 */

public class CallCode extends BranchCode {
    private String label; // the symbolic address of the corresponding label
    private int addrOf; // the program index where the corresponding label is
    private int addrTo; // the program index where this branch bytecode is located

    // For Call codes, we want the label to just be the function name, not with the <<X>>
    public void init(ArrayList<String> args) { this.label = args.get(0); }

    public String toString() { return "CALL " + this.label; }

    public void execute(VirtualMachine vm) {
        if (this.label.contains("<<")) this.label = this.label.substring(0,this.label.indexOf('<'));
        vm.setReturnAddrs(this.addrOf);
        vm.setPC(this.addrTo-1);
    }



    public String getLabel() { return this.label; }

    public void setLabel(String label) { this.label = label; }

    public int getAddrOf() { return this.addrOf; }

    public void setAddrOf(int addrOf) { this.addrOf = addrOf; }

    public int getAddrTo() { return this.addrTo; }

    public void setAddrTo(int addrTo) { this.addrTo = addrTo; }
}
