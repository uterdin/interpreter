package interpreter;

import java.util.ArrayList;
import java.util.Stack;

// This object manages and stores all objects in the program

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack; // Used to represent the runtime stack. It will be an AL because we need access to ALL location of the runtime stack
    private Stack<Integer>     framePointer; // Used to record the beginning of each activation record (frame) when calling funcs


    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>(); // last framepointer is ALWAYS runTimeStack.size()-1
        framePointer.add(0);
    }

    /**
     * Used for dumping the current state of the runTimeStack.
     * It will be print portions of the stack based on respective frame markers.
     * Example:                [1,2,3][4,5,6][7,8]
     * Frame pointers would be 0,     3,     6
     */
    public void dump(){
        String dump = "";
        Object[] fpsArr = this.framePointer.toArray();

        for (int i=0; i<fpsArr.length-1; i++) {
            dump += "[";
            for (int j=(int)fpsArr[i]; j<(int)fpsArr[i+1]; j++) {
                dump += this.runTimeStack.get(j);
                if ((j+1)<(int)fpsArr[i+1]) dump += ",";
            }
            dump += "]";
        }
        dump += "[";
        for (int i=(int)fpsArr[fpsArr.length-1]; i<this.runTimeStack.size(); i++) {
            dump += this.runTimeStack.get(i);
            if ((i+1)<this.runTimeStack.size()) dump += ",";
        }
        dump += "]";



        System.out.println(dump);
    }

    /**
     * DEBUG PURPOSES ONLY!!!
     * We use this just to test dump()
     */
    public static void main(String[] args) {
        RunTimeStack rs = new RunTimeStack();
        rs.framePointer.push(0);
        rs.framePointer.push(2);
        rs.framePointer.push(4); // These are to help test empty frames
        rs.framePointer.push(4); // eg: [1,2][][3,4] ...
        rs.framePointer.push(7);

        for (int i=1; i < 10; i++) {
            rs.runTimeStack.add(i);
        }

        rs.dump();
    }

    /**
     * Returns the top of the runtime stack but does not remove
     * @return copy of the top of the runtime stack
     */
    public int peek(){
        return this.runTimeStack.get(this.runTimeStack.size()-1);
    }

    /**
     * Push the value i to the top of the stack
     * @param i value to be pushed
     * @return value pushed
     */
    public int push(int i) {
        this.runTimeStack.add(i);
        return i;
    }

    /**
     * Removes the top of the runtime stack
     * @return the value popped
     */
    public int pop() {
        int tmp = runTimeStack.get(this.runTimeStack.size()-1);
        this.runTimeStack.remove(this.runTimeStack.size()-1);
        return tmp;
    }

    /**
     * Takes the top item of the runtime stack and stores it into an offset starting from the current frame.
     * @param offset starting from the current frame
     * @return the item just stored
     */
    public int store(int offset) {
        int tmp = this.pop();
        this.runTimeStack.add(this.framePointer.peek()+offset,tmp);
        return tmp;
    }

    /**
     * Takes the value from the runtime stack that is offset from the current frame marker and
     * pushes it to the top of the stack.
     * @param offset number of slots above the current frame marker
     * @return item just loaded into the offset
     */
    public int load(int offset) {
        this.runTimeStack.add(this.runTimeStack.get(this.framePointer.peek() + offset));
        return this.runTimeStack.get(this.runTimeStack.size()-1);
    }

    /**
     * Create a new frame pointer at the index offset slots down from the top of the runtime stack.
     * @param offset slots down from the top of the runtime stack
     */
    public void newFrameAt(int offset) {
        if (this.runTimeStack.size() > 0) {
            this.framePointer.push(this.runTimeStack.size()-offset);
        } else {
            this.framePointer.push(0);
        }
    }

    /**
     * Pop the current frame off of the runtime stack.
     * Also removes the frame pointer value from the FramePointer stack.
     */

    public void popFrame() {
        int fp = this.framePointer.pop();
        for (int i=this.runTimeStack.size()-1; i >= fp; i--) this.runTimeStack.remove(i);
    }



}
