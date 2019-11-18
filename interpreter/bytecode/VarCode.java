package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public abstract class VarCode extends ByteCode {
    private int offset;
    private String id;
    private int value;

    public abstract void init(ArrayList<String> args);

    public abstract String toString();

    public abstract void execute(VirtualMachine vm);

    public abstract int getOffset();

    public abstract void setOffset(int offset);

    public abstract String getId();

    public abstract void setId(String id);

    public abstract int getValue();

}
