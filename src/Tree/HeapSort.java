package Tree;

import java.util.Arrays;
import java.util.logging.Level;

/**
 * 这个类是为了实现堆排序，堆排序就是先将无序数组排为最大堆，这里是为了实现升序，所以选最大堆，从最小的非子节点开始
 * 然后将堆顶元素和数组的最后一个进行交换，此时因为堆顶的交换，乱序，所以重新堆排序，这时只需要从堆顶开始排序即可
 * 然后继续堆排序，一直到第二个元素和堆顶交换成功
 * @author abs
 * @Date 2019/8/1 - 22:12
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 7, 1, 29, 6, 53, 11, 16};
        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjustHeap(arr,i, arr.length);
        }

        for (int i = arr.length-1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
        System.out.println(Arrays.toString(arr));
    }
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];
        for (int k = 2*i+1; k < length; k=2*k+1) {
            if(k+1 <length && arr[k] < arr[k+1]){
                k++;
            }
            if(temp < arr[k]){
                arr[i] =arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }
}
