package interpreter.bytecode.operator;

public class EqualsOperator extends MathOperator {
    public int execute(int op1, int op2) {
        if (op1 == op2) return 1;
        return 0;
    }
}
