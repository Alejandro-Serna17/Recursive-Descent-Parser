import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Recursive Descent Parser
 * @author Alejandro Serna
 */

/*
Assignment     -> I = E
Expression     -> P O P | P
Operator       -> + | - | * | / | **
Primary        -> I | L | UI | UL | (E)
Unary Operator -> + | - | !
Identifier     -> C | CI
Character      -> a | b | ... | y | z
Literal        -> D | DL
Digit          -> 0 | 1 | ... | 8 | 9
 */

public class Parser {
    private static String s;
    private static int i;

    public static void main(String[] args) {

        File in = new File("input.txt");

        try (Scanner scan = new Scanner(in)) {
            while (scan.hasNextLine()) {
                s = scan.nextLine();
                i = 0;
                if (A() && i == s.length()) {
                    System.out.println("The string \"" + s + "\" is in the language.");
                } else {
                    System.out.println("The string \"" + s + "\" is not in the language.");
                }
            }
        } catch (IOException e) {
            System.exit(0);
        }
    }

    /**
     * Checks if an assignment is valid
     * @return true if input is an assignment, false otherwise
     */
    private static boolean A() {
        int startPos = i;
        if (I()) {
            if (i < s.length() && s.charAt(i) == '=') {
                ++i;
                if (E()) {
                    return true;
                }
            }
        }
        i = startPos; // Set value back if not matched
        return false;
    }

    /**
     * Checks if an expression is valid
     * @return true if input is an expression, false otherwise
     */
    private static boolean E() {
        int startPos = i;
        if (P()) {
            while (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/' || s.substring(i).startsWith("**"))) {
                if (!O() || !P()) {
                    i = startPos; // Sets value back if an operator or primary fails
                    return false;
                }
            }
            return true;
        }
        i = startPos; // Set value back if not matched
        return false;
    }

    /**
     Checks if an operation is valid
     @return true if input is an operation, false otherwise
     */
    private static boolean O() {
        if (i < s.length()) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/' || s.substring(i).startsWith("**")) {
                if (s.charAt(i) == '*' && i + 1 < s.length() && s.charAt(i + 1) == '*') {
                    i += 2; // Handle the "**" operator
                } else {
                    i++; // Handle single character operators
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a valid primary
     * @return true if input is a primary, false otherwise
     */
    private static boolean P() {
        int startPos = i;
        if (I() || L()) {
            return true;
        } else if (U() && (I() || L())) {
            return true;
        } else if (i < s.length() && s.charAt(i) == '(') {
            ++i;
            if (E() && i < s.length() && s.charAt(i) == ')') {
                ++i;
                return true;
            }
            i = startPos; // Set value back if not matched
        }
        return false;
    }

    /**
     * Checks if input is a unary operation
     * @return true if input is a unary operation, false otherwise
     */
    private static boolean U() {
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '!')) {
            ++i;
            return true;
        }
        return false;
    }

    /**
     * Checks if input is an identifier
     * @return true if input is an identifier, false otherwise
     */
    private static boolean I() {
        int startPos = i;
        if (C()) {
            // Empty loop because it continues to check for additional Characters (C) to form CI
            while (i < s.length() && C()) {}
            return true;
        }
        i = startPos; // Set value back if not matched
        return false;
    }

    /**
     * Checks if input is a valid character
     * @return true if input is a character, false otherwise
     */
    private static boolean C() {
        if (i < s.length() && 'a' <= s.charAt(i) && s.charAt(i) <= 'z') {
            ++i;
            return true;
        }
        return false;
    }

    /**
     * Checks if input is a valid literal
     * @return true if input is a literal, false otherwise
     */
    private static boolean L() {
        int startPos = i;
        if (D()) {
            // Empty loop because it continues to check for additional Characters (D) to form DL
            while (i < s.length() && D()) {}
            return true;
        }
        i = startPos; // Set value back if not matched
        return false;
    }

    /**
     * Checks if input is a valid digit
     * @return true if input is a digit, false otherwise
     */
    private static boolean D() {
        if (i < s.length() && '0' <= s.charAt(i) && s.charAt(i) <= '9') {
            ++i;
            return true;
        }
        return false;
    }
}
