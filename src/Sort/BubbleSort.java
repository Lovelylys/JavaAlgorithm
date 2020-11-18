package Sort;

import java.util.Arrays;

/**
 * 这个类是为了实现冒泡排序
 * 冒泡就是一个个往前进去冒泡
 * 从左到右和第一个和下一个比较大小，然后交换，如果前一个比后一个大，那么交换，知道最后一个数被交换成为最大的
 * 第二轮，依旧从第一个开始交换，直到倒数第二个是从头到倒数第二中，最大的数字
 * 直到最后一轮，最后一轮就是前面只剩两个数字的时候进行交换
 * @author abs
 * @Date 2019/7/28 - 14:37
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7,2,3,5,1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if(!flag){
                break;
            }
            flag = false;
        }
    }
}