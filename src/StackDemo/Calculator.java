package StackDemo;

/**
 * 实现中缀表达式的计算
 * 创建一个数栈，操作栈
 * 从左到右扫描字符串，如果是数字，则推入数栈
 * 如果是操作符，那么此时如果操作栈是空的，入操作栈
 * 如果不是空的，比较栈顶和其的优先级，如果优先级高于栈顶，入栈
 * 如果低于栈顶，则将数栈弹出两个数字，操作栈弹出一个操作符，运算后将结果推入数栈，将字符
 * 串读取的操作符入操作栈
 * 直到读取到最后一个字符，依次弹出数栈两个，和操作栈运算后入数栈，知道操作栈空
 *
 * 给栈添加一个优先级函数  运算函数    判断字符函数
 * @author abs
 * @Date 2019/7/27 - 20:31
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "70*2*2-5+1-5+3-4";
        IntStack numStack = new IntStack(10);
        IntStack operStack = new IntStack(10);
        int index = 0;
        int num1;
        int num2;
        int oper;
        int res;
        char c = ' ';
        String keepNum = "";
        while(true){
            c = expression.substring(index,index+1).charAt(0);
            if(numStack.isOper(c)){//是操作符
                if(operStack.isEmpty()){
                    operStack.push(c);
                }else{//当操作栈不是空的，比较优先级
                    if(operStack.priority(c) > operStack.priority(operStack.peek())){//优先级高，入站
                        operStack.push(c);
                    }else{//优先级低
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.calcu(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(c);
                    }
                }
            }else{//不是操作符,是数字的时候，考虑多位的情况
                //numStack.push(c - 48);
                keepNum += c;
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }else if(numStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }
            }
            if(index == expression.length()-1){
                break;
            }
            index++;
        }
        while (true){
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.calcu(num1,num2,oper);
            numStack.push(res);
        }
        System.out.println(numStack.pop());
    }
}
class IntStack{
    private int maxSize;
    private int[] array;
    private int top = -1;

    public IntStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    public boolean isEmpty(){
        return (top==-1);
    }

    public boolean isFull(){
        return (top == maxSize -1);
    }

    public void push(int n){
        if(isFull()){
            System.out.println("栈已经满了");
            return;
        }
        top++;
        array[top] = n;
    }

    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("栈是空的");
        }
        return array[top];
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈是空的");
        }
        return array[top--];
    }

    public void list(){
        if(isEmpty()){
            System.out.println("空的栈");
            return;
        }
        while(top > -1){
            System.out.println(array[top]);
            top--;
        }
    }

    public boolean isOper(char input){
        return input == '+'||input == '-'||input == '*'||input == '/';
    }

    public int priority(int chara){
       if(chara == '+'||chara == '-'){
           return 0;
       }else if(chara == '*'||chara == '/'){
           return 1;
       }else{
           return -1;
       }
    }

    public int calcu(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}