package Sort;

import java.util.Arrays;

/**
 * 实现希尔排序
 * 实现希尔排序，可以是交换式或者是位移式，交换式效率比较低，因为每次都要交换，和冒泡一样，但是可能稍微好理解一些，虽然我不觉得
 * 希尔排序是分成多个组，每个组都要排序
 * 每个组内再进行插入排序，每个组内的数字根据间隔/组数，确定是同一组内的数字，然后进行排序，插入，冒泡交换
 *
 * @author abs
 * @Date 2019/7/28 - 22:08
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {4, 7, 1, 29, 6, 53, 11, 16};
//        shellSort(arr);
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {//分组，多次分组，，每次分组插入排序可以快速将大的数字整理到后面；直到为1组，间隔为1，和插入排序无区别
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {//前一个数字大于当前数字，交换
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }


    public static void shellSort2(int[] arr) {//使用移位的插入排序解决问题
        int temp;
        int j;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                j = i;
                while (j - gap >= 0 && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }
}
