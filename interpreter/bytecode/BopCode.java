package interpreter.bytecode;

import interpreter.VirtualMachine;
import interpreter.bytecode.operator.*;

import java.util.ArrayList;

/**
 * BOP <binary op>
 * Pop top 2 levels of the stack and perform the indicated operation.
 * Binary ops are: +, −, /, ∗, ==, ! =, <=, >, >=, <, |, &
 * The operators | and & are logical operators, not bitwise operators.
 * Lower level is the first operand: ie: <second-level> + <top-level>
 */

public class BopCode extends ByteCode {
    private String operator;

    public void init(ArrayList<String> args) { this.operator = args.get(0); }

    public String toString(){
        return "BOP " + this.operator;
    }

    public void execute(VirtualMachine vm) {
        // First we pull the two operators off the stack
        int op2 = vm.popRunTimeStack();
        int op1 = vm.popRunTimeStack();

        // Next we resolve whether we are performing a mathematical or logical operation
        // We will execute based on which of the two it is.
        BinaryOperators binOps = new BinaryOperators();
        OpsObject opsObj = binOps.getOperator(this.operator);
        if (opsObj instanceof MathOperator) {
            vm.pushRunTimeStack(((MathOperator) opsObj).execute(op1,op2));
        } else if (opsObj instanceof LogicalOperator) {
            vm.pushRunTimeStack(((LogicalOperator) opsObj).execute(op1,op2));
        }
    }
}
