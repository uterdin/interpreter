package interpreter.bytecode.operator;

public class DivideOperator extends MathOperator {
    public int execute(int op1, int op2) { return op1 / op2; }
}
