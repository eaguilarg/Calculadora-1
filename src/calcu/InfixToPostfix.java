package calcu;

// Notación Polaca Inversa (RPN)

import java.util.Scanner;
import stackadt.Unchecked;
import stackadt.miPila;

// "Shunting yard" algorithm
// Dijkstra
public class InfixToPostfix {

    // Sólo revisa balanceo de paréntesis
    // (no revisa otros errores en la expresión infija)
    // Los únicos operadores válidos son: * / + -
    // Los elementos de la expresión deben estar separados por espacios
    public static String evaluate(String expr) {
        StringBuilder postfix = new StringBuilder();
        String token;
        Scanner sc;
        miPila pila = new miPila();
        
        if (pila.evaluaParentesis(expr)) {

            sc = new Scanner(expr);

            try {
                while (sc.hasNext()) {
                    token = sc.next();

                    if (isOperator(token)) {
                        while (!pila.isEmpty()
                                && !pila.peek().equals("(")
                                && opGreaterEqual((String)pila.peek(), token)) {
                            postfix.append(" " + pila.pop());
                        }
                        pila.push(token);
                    } else if (token.equals("(")) {
                        pila.push(token);
                    } else if (token.equals(")")) {
                        while (!(pila.peek().equals(")"))) {
                            postfix.append(" " + pila.pop());
                        }
                        pila.pop(); // paréntesis que abre correspondiente
                    } else // token is operand
                    {
                        postfix.append(" " + token);
                    }
                }

                while (!pila.isEmpty()) {
                    postfix.append(" " + pila.pop());
                }
            } catch (Unchecked ese) {
                postfix = new StringBuilder("Invalid Expression");
                System.err.println(ese);
            }

        } else {
            postfix = new StringBuilder("Invalid Expression");
            System.err.println("Paréntesis no balanceados");
        }

        return postfix.toString();
    }

    static boolean isOperator(String token) {
        return (token.equals("+") || token.equals("-")
                || token.equals("*") || token.equals("/"));
    }

    private static boolean opGreaterEqual(String tope, String actual) {
        boolean result = false;
        if ((tope.equals("*") || tope.equals("/"))
                && (actual.equals("+") || actual.equals("-"))) {
            result = true;
        }
        return result;
    }
    
    public static void main(String[] args) {
        String op = "( 8 / 4 + 3 ) * ( 5 - 2 * 2 )";
        String res;
        miPila pila = new miPila();
        
        res=evaluate(op);
        System.out.print(pila.evaluaParentesis(op));
        System.out.print(res);
    }
}
