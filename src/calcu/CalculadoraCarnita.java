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

    public double Evaluapostfijo(String operador) throws Unchecked {
        miPila calcu = new miPila();
        double res = 0, num1, num2;
        char aux;
        String auxS;
        Scanner sc = new Scanner(operador);

        while (sc.hasNext()) {
            auxS = sc.next();
            aux = auxS.charAt(0);
            if (aux != '/' && aux != '*' && aux != '-' && aux != '+') {
                calcu.push(Double.parseDouble(auxS));
            } else {
                num2 = (Double) calcu.pop();
                num1 = (Double) calcu.pop();
                switch (aux) {
                    case '/':
                        if (num2 == 0) {
                            throw new Unchecked("Math ERROR");
                        } else {
                            res = num1 / num2;
                        }
                        break;
                    case '*':
                        res = num1 * num2;
                        break;
                    case '-':
                        res = num1 - num2;
                        break;
                    case '+':
                        res = num1 + num2;
                }
                calcu.push(res);
            }
        }
        if (calcu.size() != 1) {
            throw new Unchecked("Syntax ERROR");
        }

        return (Double) calcu.peek();
    }

    public static void main(String[] args) {
        String operador = "1.2 8 / 15 * 9 -";
        CalculadoraCarnita calcu = new CalculadoraCarnita();

        System.out.print("=" + calcu.Evaluapostfijo(operador));
    }
}
