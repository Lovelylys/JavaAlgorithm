package Tree;

import java.util.Arrays;

/**
 * 这个类是为了实现顺序化二叉树
 * 所谓顺序化二叉树就是以数组的形式存储二叉树的数值，然后
 * 打印的时候还是按照二叉树的形式打印
 * @author abs
 * @Date 2019/7/31 - 23:06
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        tree.preOrder(0);
    }
}
class  ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int i){
        if(arr.length == 0){
            return;
        }
        System.out.println(arr[i]);
        if(2*i+1 < arr.length){
            preOrder(2*i+1);
        }
        if(2*i+2 < arr.length){
            preOrder(2*i+2);
        }
    }
}

