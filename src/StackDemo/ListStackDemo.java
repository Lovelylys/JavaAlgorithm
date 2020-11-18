package StackDemo;

/**
 * @author abs
 * @Date 2019/7/26 - 22:55
 */
public class ListStackDemo {
    public static void main(String[] args) {

    }
}
class ListStack{
    private Node head = new Node(0);
    private int maxSize;

    public ListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(int n){
        Integer value = new Integer(n);

    }
}
class Node{
    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
