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

    public Stack<Double> pila;

    public CalculadoraCarnita() {
        this.pila = new Stack<Double>();
    }

    public double Evaluapostfijo(String operacion) throws Unchecked {
        miPila calculadorita = new miPila();
        double resultado = 0, numero1 = 0, numero2 = 0;
        char operador;
        String lector;
        Scanner sc = new Scanner(operacion);

        while (sc.hasNext()) {
            lector = sc.next();
            operador = lector.charAt(0);
            if (operador != '/' && operador != '*' && operador != '-' && operador != '+') {
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

    public static void main(String[] args) {
        String operador = "1.2 8 / 15 * 9 -";
        CalculadoraCarnita calcu = new CalculadoraCarnita();

        System.out.print("=" + calcu.Evaluapostfijo(operador));
    }
}
