package Sort;

import java.util.Arrays;

/**
 * 这个类是为了实现快速排序，
 * 快速排序是指定左右两个指针，制定中间一个数字，
 * 然后左指针找出比中值大的数字，右指针一直往左移动，直到找到比中值小的数字的位置，
 * 如果此时左指针仍然小于右指针，那么交换两个数值
 * 如果交换后左边的数字和中值相等，那么右指针--
 * 如果交换后右边的数字和中值相等，那么左指针++，这是为了防止再次交换，死循环了
 * 结束后，如果左右指针相等，那么左++，右--
 * 然后开始左右递归
 * 递归的结束条件就是左边小于右边
 *
 * @author abs
 * @Date 2019/7/29 - 21:41
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {4, 7, 1, 29, 6, 53, 11, 16};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left+right)/2];
        while(l < r){
            while(arr[l] < pivot){
                l+=1;
            }

            while(arr[r] > pivot){
                r--;
            }

            if(l >= r){
                break;
            }

            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if(arr[l] == pivot){
                r--;
            }
            if(arr[r] == pivot){
                l++;
            }
        }
        if(l == r){
            l++;
            r--;
        }
        if(left < r){
            quickSort(arr,left,r);
        }
        if(l < right){
            quickSort(arr,l,right);
        }
    }
}
