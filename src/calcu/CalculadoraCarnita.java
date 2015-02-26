/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcu;

import java.util.Scanner;
import java.util.Stack;
import stackadt.StackADT;
import stackadt.Unchecked;
import stackadt.miPila;

/**
 *
 * @author MRUIZGA
 */
public class CalculadoraCarnita {

    public miPila calculadorita;

    public CalculadoraCarnita() {
        this.calculadorita = new miPila();
    }
                                                   cambiar el Unchecked por una Checked.
    public double evaluaPostFijo(String operacion) throws Unchecked {
        miPila calculadorita = new miPila();
        double resultado = 0, numero1 = 0, numero2 = 0;
        char operador;
        String lector;
        Scanner sc = new Scanner(operacion);

        while (sc.hasNext()) {
            lector = sc.next();
            operador = lector.charAt(0);
            if (operador != '/' && operador != '*' && operador != '-' && operador != '+' || lector.length()>=2) {
                calculadorita.push(Double.parseDouble(lector));
            } else {
                if (calculadorita.isEmpty()) {
                    throw new Unchecked("Syntax ERROR");
                }
                numero2 = (Double) calculadorita.pop();
                numero1 = (Double) calculadorita.pop();
                switch (operador) {
                    case '/':
                        if (numero2 == 0) {
                            throw new Unchecked("Math ERROR");
                        } else {
                            resultado = numero1 / numero2;
                        }
                        break;
                    case '*':
                        resultado = numero1 * numero2;
                        break;
                    case '-':
                        resultado = numero1 - numero2;
                        break;
                    case '+':
                        resultado = numero1 + numero2;
                }
                calculadorita.push(resultado);
            }
        }
        if (calculadorita.size() != 1) {
            throw new Unchecked("Syntax ERROR");
        }

        return (Double) calculadorita.peek();
    }

    public String infijoAPostfijo(String expr) {
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
                                && opGreaterEqual((String) pila.peek(), token)) {
                            postfix.append(" " + pila.pop());
                        }
                        pila.push(token);
                    } else if (token.equals("(")) {
                        pila.push(token);
                    } else if (token.equals(")")) {
                        while (!(pila.peek().equals("("))) {
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
        CalculadoraCarnita calcu = new CalculadoraCarnita();
        String operador = calcu.infijoAPostfijo("9 / 3 + -3");

        System.out.print("\n" + calcu.evaluaPostFijo(operador) + "\n" + "\n");
    }
}
