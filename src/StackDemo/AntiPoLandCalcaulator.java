package StackDemo;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 实现逆波兰计算器
 * 从左到右扫描，如果是数字
 * 那么推入栈中
 * 如果是操作符，那么从栈中弹出两个数字，进性运算，将结果推入栈中
 * 一直到字符串被扫描结束，此时栈中的数字就是结果
 * @author abs
 * @Date 2019/7/27 - 21:31
 */
public class AntiPoLandCalcaulator {

    public static void main(String[] args) {
        String experssion = "3 4 + 5 * 6 -";
        polandCalculate(experssion);
    }
    public static void polandCalculate(String experssion){
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        int oper = 0;
        Stack<Integer> intStack = new Stack<>();
        String[] expressions = experssion.split(" ");
        for (String e : expressions) {
            if(e.matches("\\d+")){
                intStack.push(Integer.parseInt(e));
            }else{
                num1 = intStack.pop();
                num2 = intStack.pop();
                res = calculate(num1,num2,e.charAt(0));
                intStack.push(res);
            }
        }
        System.out.println(intStack.pop());
    }
    public static int calculate(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 +num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
