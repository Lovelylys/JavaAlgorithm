package Recursion;

import java.util.Arrays;

/**
 * 这个类是为了解决八皇后问题
 * 首先每放置一个棋子，就要判断是否和前面所有的棋子存在冲突，冲突了就不操作，继续下一列尝试，不冲突则下一排继续，即递归，
 * 如果冲突到最后一列还是冲突，那么就结束循环，结束进程，
 * 如果一直顺利走到最后，那么当行数为8时，打印棋子位置，返回结束进程
 * @author abs
 * @Date 2019/7/28 - 15:37
 */
public class Queen8 {
    static int count = 0;

    public static void main(String[] args) {
        int[] arr = new int[8];
        setNext(arr,0);
        System.out.println(count);
    }
    public static void setNext(int[] arr,int n){
        if(n == 8){
            count++;
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[n] = i;
            if(judge(arr,n)){
                setNext(arr,n+1);
            }
        }
    }
    public static boolean judge(int[] arr,int n){
        for (int i = 0; i < n; i++) {//从第一行开始到当前这一行，判断是否出现冲突
            if(arr[i] == arr[n] || (Math.abs(n - i)==Math.abs(arr[n] - arr[i]))){//判断是在同一列上，或者是否在同一斜线上
                return false;
            }
        }
        return true;
    }
}
