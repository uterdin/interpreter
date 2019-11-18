// Design ByteCode abstraction first - create all objects, then go on to loadCodes()
package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.LabelCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    private StringTokenizer tokenizer;
    private final String DELIMITERS = " ";

    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN LOADCODES.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */

    public Program loadCodes() {
        Program program = new Program();

        try {
            while(this.byteSource.ready()) {
                ArrayList<String> args = new ArrayList<>();
                String line = this.byteSource.readLine();
                this.tokenizer = new StringTokenizer(line, DELIMITERS, false);

                String token = this.tokenizer.nextToken(); // First token will always be the bytecode
                String className = CodeTable.getClassName(token);

                Class c = Class.forName("interpreter.bytecode."+className); // This will throw class exception for bad classnames
                ByteCode bc = (ByteCode) c.getDeclaredConstructor().newInstance();

                while (this.tokenizer.hasMoreTokens()) {
                    token = this.tokenizer.nextToken();
                    args.add(token);
                }

                bc.init(args); // initialize bytecode
                program.add(bc); // add to program arraylist


            } // end while
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println(e);
            System.exit(1);
        } catch (InstantiationException e) {
            System.out.println(e);
            System.exit(1);
        } catch (InvocationTargetException e) {
            System.out.println(e);
            System.exit(1);
        }

        program.resolveAddrs(); // Once all of our bytecodes are loaded, we want to resolve symbolic addr's
        return program;
    }
}
