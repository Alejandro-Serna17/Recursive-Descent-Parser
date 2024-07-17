# Recursive Descent Parser<br />
This program is implemented in both Java and C++<br />
Below is some information and samples<br />


## BNF Grammar<br />
- Assignment&emsp;&emsp;->&ensp; I = E<br />
- Expression&emsp;&emsp;&ensp;->&ensp; P O P | P<br />
- Operator&emsp;&emsp;&emsp;&thinsp;->&ensp; + | - | * | / | **<br />
- Primary&emsp;&emsp;&ensp;&ensp;&ensp;&thinsp;&thinsp;->&ensp; I | L | UI | UL | (E)<br />
- Unary Operator  ->&ensp; + | - | !<br />
- Identifier&emsp;&emsp;&emsp;&thinsp;->&ensp; C | CI<br />
- Character&emsp;&emsp;&emsp;->&ensp; a | b | ... | y | z<br />
- Literal&emsp;&emsp;&emsp;&emsp;&ensp;->&ensp; D | DL<br />
- Digit&emsp;&emsp;&emsp;&emsp;&emsp;->&ensp; 0 | 1 | ... | 8 | 9<br />

## Sample Input<br />
- a=1+2<br />
- value=-10<br />
- name=john<br />
- formula=x*(y+z)/2<br />
- variable=1 2<br />
- x=(5+6<br />
- value=-<br />
- number=42+<br />

## Sample Output<br />
- The string "a=1+2" is in the language.<br />
- The string "value=-10" is in the language.<br />
- The string "name=john" is in the language.<br />
- The string "formula=x*(y+z)/2" is in the language.<br />
- The string "variable=1 2" is not in the language.<br />
- The string "x=(5+6" is not in the language.<br />
- The string "value=-" is not in the language.<br />
- The string "number=42+" is not in the language.<br />
