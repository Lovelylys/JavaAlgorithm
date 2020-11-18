package Queue;

import java.util.Scanner;

/**
 * @author abs
 * @Date 2019/7/24 - 1:14
 */
public class CircleQueueDemo {
    public static void main(String[] args) {
        CircleQueue arrayQueue = new CircleQueue(4);
        char key =' ';
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("s(show) 显示队列");
            System.out.println("e(exit) 退出程序");
            System.out.println("a(add) 添加数据到队列");
            System.out.println("g(get) 从队列取出数据");
            System.out.println("h(head) 查看队列的首个数字");
            key =scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int n = scanner.nextInt();
                    arrayQueue.addQueue(n);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\t",res);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    break;
            }
        }
    }
}
class CircleQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0; //头指向队列的第一个数值
        rear = 0; //尾部指向队列最后一个数值的后一个位置，这里保留一个空间
    }

    private boolean isFull(){
        return (rear+1)%maxSize == front;
    }

    private boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满，不能添加");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1)%maxSize;
    }

    public int getQueue() throws  RuntimeException{
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取值");
        }
        int temp = arr[front];
        front = (front +1)%maxSize;
        return temp;
    }

    public int headQueue() throws RuntimeException{
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取值");
        }
        return arr[front];
    }

    public void showQueue()throws RuntimeException{
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取值");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    private int size(){
        return (rear + maxSize - front)%maxSize;
    }
}
