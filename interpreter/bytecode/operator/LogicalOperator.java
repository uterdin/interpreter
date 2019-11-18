package interpreter.bytecode.operator;

public abstract class LogicalOperator extends OpsObject {
    private boolean op1 = false, op2 = false;

    public abstract int execute(int op1, int op2);
}
