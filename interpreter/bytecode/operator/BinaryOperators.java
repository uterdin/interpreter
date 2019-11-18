package interpreter.bytecode.operator;

import java.util.HashMap;

/**
 * Binary ops are: +, −, /, ∗, ==, ! =, <=, >, >=, <, |, &
 */

public class BinaryOperators {
    private static HashMap<String,OpsObject> opsMap = new HashMap<>();

    static {
        opsMap.put("+", new AddOperator());
        opsMap.put("-", new SubtractOperator());
        opsMap.put("/", new DivideOperator());
        opsMap.put("*", new MultiplyOperator());
        opsMap.put("==", new EqualsOperator());
        opsMap.put("! =", new NotEqualsOperator()); // TODO: Because I didn't have an example of a not
        opsMap.put("!=", new NotEqualsOperator());  // BOP operation, I wasn't sure what the exact token
        opsMap.put("!", new NotEqualsOperator());   // would be, so I covered all cases. Will revisit in future.
        opsMap.put("<=", new LTEOperator());
        opsMap.put(">", new GTOperator());
        opsMap.put(">=", new GTEOperator());
        opsMap.put("<", new LTOperator());
        opsMap.put("|", new OrOperator());
        opsMap.put("&", new AndOperator());
    }

    public OpsObject getOperator(String token) { return opsMap.get(token); }
}
