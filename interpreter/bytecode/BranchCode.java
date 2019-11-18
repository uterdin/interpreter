package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public abstract class BranchCode extends ByteCode {
    private String label; // the symbolic address of the corresponding label
    private int addrOf; // the program index where this bytecode is
    private int addrTo; // the program index where the corresponding label is

    public abstract void init(ArrayList<String> args);

    public abstract String toString();

    public abstract void execute(VirtualMachine vm);

    public abstract String getLabel();

    public abstract void setLabel(String label);

    public abstract int getAddrOf();

    public abstract void setAddrOf(int addrOf);

    public abstract int getAddrTo();

    public abstract void setAddrTo(int addrTo);
}
