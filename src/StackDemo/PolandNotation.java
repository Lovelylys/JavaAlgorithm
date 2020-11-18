package StackDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 这个类的目的在于将中缀表达式转换为后缀表达式，
 * 创建两个栈，一个放运算符，一个放中间操作结果
 * 从左到右扫描字符串
 * 如果遇到了数字，那么直接压入中间操作栈
 * 如果是运算符，那么判断是否是括号，如果是左括号，那么直接压入运算符栈
 * 如果是右括号，那么将操作符栈弹出，一直弹到左括号，将弹出地符号压入中间结果栈
 * 如果不是括号，那么操作符栈栈顶如果为空，则压入操作符栈
 * 否则如果栈顶优先级低或者是左括号，压入操作符栈
 * 否则，弹出一个操作符栈到中间结果栈，重新再判断
 * @author abs
 * @Date 2019/7/27 - 22:23
 */
public class PolandNotation {
    public static void main(String[] args) {
        String input = "1+((2+3)*4)-20";
        List<String> infixExpressionList = toInfixExpressionList(input);
        //接下来就是将其转化为后缀表达式
        List<String> suffixExpressionList = parseSuffixExpression(infixExpressionList);
        StringBuilder sb = new StringBuilder();
        for (String s : suffixExpressionList) {
            sb.append(" ");
            sb.append(s);
        }
        AntiPoLandCalcaulator.polandCalculate(sb.toString().trim());
    }

    public static List<String> toInfixExpressionList(String expression){
        char c = ' ';
        String keepString = "";
        int index = 0;
        ArrayList<String> list = new ArrayList<>();
        while(true) {
            c = expression.charAt(index);
            if(c > 57 || c < 48){// 当不是数字的时候
                list.add(""+c);
            }else{//做为数字,有可能下一个也是数字来着
                keepString += c;
                if(index == expression.length()-1){
                    list.add(keepString);
                    keepString = ""; //可能这一条也不需要了，因为到最后一个节点后，keepString也不会再用到了
                }else if(expression.charAt(index+1) > 57 || expression.charAt(index+1) < 48){
                    list.add(keepString);
                    keepString = "";
                }
            }
            if(index == expression.length()-1){
                break;
            }
            index++;
        }
        return list;
    }

    public static List<String> parseSuffixExpression(List<String> list){
        Stack<String> s1 = new Stack<>();// 运算符栈
        ArrayList<String> arrayList = new ArrayList<>();// 中间结果栈

        for (String s : list) {
            if(s.matches("\\d+")){//如果是数字
                arrayList.add(s);
            }else if(s.equals("(")){
                s1.push(s);
            }else if(s.equals(")")){
                while(!s1.peek().equals("(")){
                    arrayList.add(s1.pop());
                }
                s1.pop();
            }else{
                while(s1.size()> 0 && Operation.priority(s) <= Operation.priority(s1.peek())){//当栈顶不为空，或者优先级比栈顶低，或者不是左括号，这里左括号优先级低也会破坏条件
                    arrayList.add(s1.pop());
                }
                s1.push(s);
            }
        }
        //当字符串扫描结束的时候，将运算符栈所有弹出到中间操作数栈里,然后将中间操作数栈逆序打印
        while(s1.size() > 0){
            arrayList.add(s1.pop());
        }
        return arrayList;
    }
}
class Operation{
    private static final int ADD = 1;
    private static final int MUL =2;

    public static int priority(String s){
        if(s.equals("*") || s.equals("/")){
            return MUL;
        }else if(s.equals("+") || s.equals("_")){
            return ADD;
        }else{
            return 0;
        }
    }
}
