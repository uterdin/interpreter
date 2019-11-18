// Feel free to add stuff to this, and you should. Just don't break any abstractions

package interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interpreter.bytecode.*;

public class Program {

    private ArrayList<ByteCode> program;
    private HashMap<Integer,String> branchAddrMap; // To store each instance of a branch code in the program
    private HashMap<String,Integer> labelAddrMap;  // To store each instance of a label code in the program

    public Program() {
        program = new ArrayList<>();
        branchAddrMap = new HashMap<>();
        labelAddrMap = new HashMap<>();
    }

    protected ByteCode getCode(int pc) { return this.program.get(pc); }

    /**
     * This function goes through each entry in the branches hashmap and
     * modifies the branch bytecode within the program to record the address
     * that it must branch to
     */
    public void resolveAddrs() {
        for (Map.Entry<Integer,String> entry : branchAddrMap.entrySet()) {
            BranchCode bc = (BranchCode) program.get(entry.getKey());
            bc.setAddrTo(labelAddrMap.get(entry.getValue()));
        }
    }

    public void add(ByteCode bc) {
        program.add(bc);

        if (bc instanceof BranchCode) {
            ((BranchCode) bc).setAddrOf(program.size()-1);
            branchAddrMap.put(((BranchCode) bc).getAddrOf(),((BranchCode) bc).getLabel());
        } else if (bc instanceof LabelCode) {
            ((LabelCode) bc).setAddrOf(program.size()-1);
            labelAddrMap.put(((LabelCode) bc).getLabel(),((LabelCode) bc).getAddrOf());
        }
    }




}
