package Sort;

import java.util.Arrays;


/**
 * 这个类要实现插入排序的功能
 * 插入排序是从左到右，第一个位置，假定当前第一个数是最小值，从下一位不断比较，找出最小值，在交换过程中将最小值定为新的比较值的位置
 * 然后将数组的第一位和其交换，
 * 第二轮，从数组第二位开始，重复以上的操作
 * 直到最后一轮，最后一轮就是倒数第二位数字，
 * @author abs
 *
 *
 * @Date 2019/7/28 - 14:50
 */
public class SelecteSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7,2,3,5,1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void selectSort(int[] arr){
        int min = 0;
        int minIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];
            minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(min > arr[j]){
                    min = arr[j];//重设最小值的位置
                    minIndex = j;
                }
            }
            if(minIndex != i){//假如下标被更新了，那么就交换数值
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
