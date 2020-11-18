package StackDemo;

import java.util.Scanner;

/**
 * @author abs
 * @Date 2019/7/26 - 22:34
 */
public class Stack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyStack myStack = new MyStack(4);
        String key ="";
        boolean loop =true;

        myStack.push(1);
        myStack.push(2);
        myStack.push(7);
        myStack.push(5);
        System.out.println(myStack.pop());
        System.out.println("间隔");
        myStack.list();
    }
}
class MyStack{
    private int maxSize;
    private int[] array;
    private int top = -1;

    public MyStack(int maxSize) {
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
}
