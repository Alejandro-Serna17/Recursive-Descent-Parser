#include <fstream>
#include <iostream>
#include <string>

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

using std::cout;
using std::endl;
using std::string;
using std::ifstream;
using std::cerr;

bool A();
bool E();
bool O();
bool P();
bool U();
bool I();
bool C();
bool L();
bool D();

string s;
int i;

int main(int argc, char *argv[]) {

    ifstream fin("input.txt");

    if (!fin.is_open()) {
        cerr << "File not found" << endl;
        return 1;
    }

    while (getline(fin, s)) {
        i = 0;
        if (A() && i == s.length()) {
            cout << "The string \"" << s << "\" is in the language." << endl;
        } else {
            cout << "The string \"" << s << "\" is not in the language." << endl;
        }
    }

    fin.close();
    return 0;
}

/**
 * Checks if an assignment is valid
 * @return true if input is an assignment, false otherwise
 */
bool A() {
    int startPos = i;
    if (I()) {
        if (i < s.length() && s[i] == '=') {
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
bool E() {
    int startPos = i;
    if (P()) {
        while (i < s.length() && (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/' || (i + 1 < s.length() && s[i] == '*' && s[i + 1] == '*'))) {
            if (!O() || !P()) {
                i = startPos;
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
bool O() {
    if (i < s.length()) {
        if (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/' || (i + 1 < s.length() && s[i] == '*' && s[i + 1] == '*')) {
            if (s[i] == '*' && i + 1 < s.length() && s[i + 1] == '*') {
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
bool P() {
    int startPos = i;
    if (I() || L()) {
        return true;
    } else if (U() && (I() || L())) {
        return true;
    } else if (i < s.length() && s[i] == '(') {
        ++i;
        if (E() && i < s.length() && s[i] == ')') {
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
bool U() {
    if (i < s.length() && (s[i] == '+' || s[i] == '-' || s[i] == '!')) {
        ++i;
        return true;
    }
    return false;
}

/**
 * Checks if input is an identifier
 * @return true if input is an identifier, false otherwise
 */
bool I() {
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
bool C() {
    if (i < s.length() && 'a' <= s[i] && s[i] <= 'z') {
        ++i;
        return true;
    }
    return false;
}

/**
 * Checks if input is a valid literal
 * @return true if input is a literal, false otherwise
 */
bool L() {
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
bool D() {
    if (i < s.length() && '0' <= s[i] && s[i] <= '9') {
        ++i;
        return true;
    }
    return false;
}
