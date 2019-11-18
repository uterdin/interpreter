package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.CallCode;

import java.util.Stack;

// Bytecode -> VM -> RunStack
// You need to hide important parts of your code from things like code injection:
// Code injection is when a user gets access to a critical data structure (in this case through a bytecode object of their own)
// to exploit over-exposure from the medium interface to manipulate

// vm.executeProgram() is in the PDF, need to copy it in to here

public class VirtualMachine {

    // TODO: Implement a working solution for frames and stacks to be able to resolve remaining ByteCodes

    private RunTimeStack   runStack; // Used to store all variables in program
    private Stack<Integer> returnAddrs; // Used to store return addr's for each called func (excluding main)
    private Program        program; // Ref to program obj where all bytecodes are stored
    private int            pc; // program counter (current bytecode being executed)

    // Added vars
    private boolean        isRunning; // used to determine whether VM should be executing bytecodes
    private boolean        isDumping; // used to determine whether VM should be dumping state of runtime stack to console

    protected VirtualMachine(Program program) {
        this.program = program;
    }


    public void executeProgram() {
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<>();

        isRunning = true;
        isDumping = false;

        while (isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            if (code instanceof CallCode) {
                try {
                    System.out.printf("%s\t%s(%d)\n", code.toString(), ((CallCode) code).getLabel(), runStack.peek());
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("%s\t%s()\n", code.toString(), ((CallCode) code).getLabel());
                }
            } else {
                System.out.println(code.toString());
            }
            if (isDumping) this.dumpRunTimeStack();

            pc++;
        }

    }

    /**
     * Creating some getters & setters for private vars of VM, which
     * will need to be accessed by certain bytecodes.
     */

    public boolean getIsRunning() {
        return this.isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean getIsDumping() {
        return this.isDumping;
    }

    public void setIsDumping(boolean isDumping) {
        this.isDumping = isDumping;
    }

    public int getPC() { return this.pc; }

    public void setPC(int pc) { this.pc = pc; }

    /**
     * The methods below serve as encapsulation between the ByteCodes and the RunTimeStack.
     * Rather than the ByteCodes being able to directly call RunTimeStack.pop() through the VM,
     * we have intermediary methods (below) that call respective RunTimeStack methods to prevent injections.
     */


    /**
     * Used for dumping the current state of the runTimeStack.
     * It will be print portions of the stack based on respective frame markers.
     * Example:                [1,2,3][4,5,6][7,8]
     * Frame pointers would be 0,     3,     6,  8
     */
    public void dumpRunTimeStack() { runStack.dump(); }

    /**
     * Returns the top of the runtime stack but does not remove
     * @return copy of the top of the runtime stack
     */
    public int peekRunTimeStack() { return runStack.peek(); }

    /**
     * Push the value i to the top of the stack
     * @param i value to be pushed
     * @return value pushed
     */
    public int pushRunTimeStack(int i) {
        return runStack.push(i);
    }

    /**
     * Removes the top of the runtime stack
     * @return the value popped
     */
    public int popRunTimeStack() {
        return runStack.pop();
    }

    /**
     * Takes the top item of the runtime stack and stores it into an offset starting from the current frame.
     * @param offset starting from the current frame
     * @return the item just stored
     */
    public int storeRunTimeStack(int offset) { return runStack.store(offset); }

    /**
     * Takes the value from the runtime stack that is offset from the current frame marker and
     * pushes it to the top of the stack.
     * @param offset number of slots above the current frame marker
     * @return item just loaded into the offset
     */
    public int loadRunTimeStack(int offset) { return runStack.load(offset); }

    /**
     * Create a new frame pointer at the index offset slots down from the top of the runtime stack.
     * @param offset slots down from the top of the runtime stack
     */
    public void newFrameAtRunTimeStack(int offset) { runStack.newFrameAt(offset); }

    /**
     * Pop the current frame off of the runtime stack.
     * Also removes the frame pointer value from the FramePointer stack.
     */
    public void popFrameRunTimeStack() { runStack.popFrame(); }

    /**
     * Add a return address for the most recent branching statement
     * @param returnAddr the address in the program for which the VM will return to at the next RETURN addr
     * @return the value pushed on to the returnAddrs stack
     */
    public int setReturnAddrs(int returnAddr) { return this.returnAddrs.push(returnAddr); }

    public int popReturnAddrs() { return this.returnAddrs.pop(); }
}