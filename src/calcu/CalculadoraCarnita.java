/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcu;

import java.util.Stack;
import stackadt.Unchecked;

/**
 *
 * @author MRUIZGA
 */
public class CalculadoraCarnita {
    public Stack<Double> pila;
    
    public CalculadoraCarnita() {
        this.pila=new Stack <Double>();
    }
    
    public int Evaluapostfijo(String operador)throws Unchecked{
         Stack <Integer> calcu=new Stack<Integer>();
        int res=0,num1,num2,auxadd;
        char aux;
        
        for(int i=0; i<operador.length();i++){
            aux=operador.charAt(i);
            if(aux!='/' && aux!='*' && aux!='-' && aux!='+'){
                auxadd=Integer.parseInt(aux+"");
                calcu.add(auxadd);
            }
            else{
                num2=calcu.peek();
                calcu.pop();
                num1=calcu.peek();
                calcu.pop();
                switch (aux){
                    case '/':
                        res=num1/num2;
                        break;
                    case '*':
                        res=num1*num2;
                        break;
                    case '-':
                        res=num1-num2;
                        break;
                    case '+':
                        res=num1+num2;
                }
                 calcu.add(res);
            }
        }
        if(calcu.size()!=1){
            throw new Unchecked ("La operacion no tiene el formato correcto.");
        }
                
        return calcu.peek();
    }
    
    public static void main(String[] args) {
        String operador="12*6+5-";
        CalculadoraCarnita calcu=new CalculadoraCarnita();
        
        System.out.print(calcu.Evaluapostfijo(operador));
    }
    
}
