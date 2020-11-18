package Sort;

import java.util.Arrays;

/**
 * 这个类是为了解决插入排序，
 * 插入排序，就是将数组分为有序列表，和无序列表，将第一个数字做为有序列表，后面都是无序列表
 * 从第二个数字开始，一直到最后一个数字，都进行插入排序
 * 插入排序，采用移位方式实现，当前一个数字比当前选择的数字，就往后移动一位，而且当前位置还在0或者之后
 * 直到出现比选择数字大的情况，则当前位置就是选择数字的位置
 *
 * @author abs
 * @Date 2019/7/28 - 21:52
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {4,7,1,29,6,53,11,12};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                while(j-1 >= 0 && (arr[j-1] > temp)){//当前一个数字比当前的数字大，那么将这个数字后移一位，
                    arr[j] = arr[j-1];
                    j--;
                }
                arr[j] = temp;
        }
    }
}
