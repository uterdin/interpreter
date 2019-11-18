package interpreter.bytecode.operator;

public abstract class MathOperator extends OpsObject {
    public abstract int execute(int op1, int op2);
}
