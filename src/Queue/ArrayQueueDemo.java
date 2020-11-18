package Queue;

import java.util.Scanner;

/**
 * @author abs
 * @Date 2019/7/24 - 0:48
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
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
class ArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; //头指向队列第一个数值的前一个位置
        rear = -1; //尾部指向队列的最后一个数值
    }

    private boolean isFull(){
        return rear == maxSize -1;
    }

    private boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满，不能添加");
            return;
        }
        rear ++;
        arr[rear] = n;
    }

    public int getQueue() throws  RuntimeException{
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取值");
        }
        front ++;
        return arr[front];
    }

    public int headQueue() throws RuntimeException{
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取值");
        }
        return arr[front +1];
    }

    public void showQueue()throws RuntimeException{
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取值");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n",i,arr[i]);
        }
    }
}
