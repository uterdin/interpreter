package interpreter.bytecode.operator;

public class AndOperator extends LogicalOperator {
    private boolean op1 = false, op2 = false;

    public int execute(int op1, int op2) {
        if (op1 == 1) this.op1 = true;
        if (op2 == 1) this.op2 = true;

        if (this.op1 & this.op2) return 1;
        return 0;
    }
}
