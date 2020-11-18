package Sort;

import java.util.Arrays;

/**
 * 桶排序，桶排序就是要创建多个桶，创建十个桶，然后将数组从个位，到最高位，每一轮都根据那一位放进桶中
 * 然后再依次从桶中取出数字，重新这么重复操作，
 * 直到最后一轮数组就是正常顺序了
 * 其中每个桶需要标记有多少个数字被放进，这个可以用一个十位长度的数组存储进行标记
 *
 * @author abs
 * @Date 2019/7/29 - 22:31
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] arr = {4, 7, 1, 30, 6, 53, 11, 16};
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void bucketSort(int[] arr){
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        int maxSize = (max + "").length();

        int[][] bucket = new int[10][arr.length]; // 保证每个桶可以放进去整个数组
        int[] bucketNum =new int[10];

        //根据这个最长的数字，从个位开始轮，重复；每一次将数组放进桶中，又取出来
        for(int i = 0,n = 1; i < maxSize; i++,n*=10){
            for (int j = 0; j < arr.length; j++) {
                int index = arr[j] /n %10;
                bucket[index][bucketNum[index]] = arr[j];
                bucketNum[index]++;
            }

            //然后每个数字还要进行取出
            int index = 0;
            for (int j = 0; j < bucket.length; j++) {
                for (int k = 0; k < bucketNum[j]; k++) {
                    arr[index++] = bucket[j][k];
                }
                //关键是，每一轮结束后，需要将对桶计数的数组清零
                bucketNum[j] = 0;
            }


        }

    }
}
