package Sort;

import java.util.Arrays;

/**
 * 这个算法好像有带你问题
 * 这个类是为了解决归并排序
 * 归并排序的核心是分而治之算法
 * 先将数组不断地分组，分组下去，直到分到最小单位，递归的结束条件是当左边指针位置大于右边则结束
 * 然后将数组合并，不断地合并，合并地过程，将左右地数组进行对比，将其中小的数字按序不断地添加进临时数组当中，当其中一个数组添加完毕后
 * 将另外一个数组不断地添加进去临时数组到空
 * 将临时数组复制回数组中
 * 将临时数组从当前的左右长度，左到右，复制回去
 *
 * @author abs
 * @Date 2019/7/29 - 22:05
 */
public class MergerSort {
    public static void main(String[] args) {
        int[] arr = {5,4,3,1,23,6,7,8};
        int[] temp = new int[arr.length];
        mergerSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }
    public static void mergerSort(int[] arr,int left,int right,int[] temp){
        if(left < right){
            int mid = (left + right) /2;
            mergerSort(arr,left,mid,temp);
            mergerSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {//将两个数组合并为一个数组的过程
        int l = left;
        int r = mid + 1;
        int t = 0;
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                temp[t] = arr[l];
                t++;
                l++;
            } else {
                temp[t++] = arr[r++];
            }
        }

        while(l <= mid){
            temp[t++] = arr[l++];
        }
        while(r <= right){
            temp[t++] = arr[r++];
        }

        int tempLeft = left;// 这里为什么还要赋值一个临时变量呢
        t = 0;
        while(tempLeft <= right){
            arr[tempLeft++] = temp[t++];
        }
    }
}
