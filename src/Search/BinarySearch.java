package Search;

/**
 * 这个类是为了实现二分查找
 * 二分查找是在数组有序的情况下，才可以使用的
 * 确定一个中间的数值，如果比其大，那么就左递归，如果小就向右递归，当找到数值就可以返回，
 * 递归的结束条件是找到数值，或者没有找到的时候，左边大于右边则结束
 * @author abs
 * @Date 2019/7/29 - 22:55
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 7, 11, 16, 30, 53};
        System.out.println(binearySearch(arr,16,0,arr.length-1));
    }
    public static int binearySearch(int[] arr,int findVal,int left,int right){
        int mid = (left + right) /2;
        if(left > right){
            return -1;
        }
        if(arr[mid] > findVal){
            return binearySearch(arr,findVal,left,mid-1);// 应该是返回上一层，而不会直接返回到第一层的函数啊
        }else if(arr[mid] < findVal){
            return binearySearch(arr,findVal,mid+1,right);
        }else{
            return mid;
        }
    }
}
