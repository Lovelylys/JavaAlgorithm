package HuffManTree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 这个类是为了实现霍夫曼树，霍夫曼树，就是wpl最小的树结构
 * 将所有的节点排序后，将第一个和第二个节点取出，增加父节点，父节点左右是这两个节点，然后将父节点加入到节点列表中
 * 重新排序后，重复上面的步骤，直到所有的节点都被处理过，也就是列表最后只剩下一个节点，就是所有人的父节点
 * 这个过程可以通过循环就实现
 * @author abs
 * @Date 2019/8/2 - 22:18
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = { 13, 7, 8, 3, 29, 6, 1 };
        TreeNode node = createHuffmanTree(arr);
        preOrder(node);
    }
    public static void preOrder(TreeNode node){
        node.preOrder();
    }
    public static TreeNode createHuffmanTree(int[] arr){
        ArrayList<TreeNode> list = new ArrayList<>();
        for (int i : arr) {
            TreeNode node = new TreeNode(i);
            list.add(node);
        }
        Collections.sort(list);
        while(list.size() > 1){
            TreeNode leftNode = list.get(0);
            TreeNode rightNode = list.get(1);

            TreeNode parent = new TreeNode(leftNode.getWeight() + rightNode.getWeight());
            parent.setLeftNode(leftNode);
            parent.setRightNode(rightNode);

            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);

            Collections.sort(list);
        }
        return list.get(0);
    }
}
class
TreeNode implements Comparable<TreeNode>{
    private int weight;
    TreeNode leftNode;
    TreeNode rightNode;

    public void preOrder(){
        System.out.println(this);
        if(this.getLeftNode() != null){
            this.getLeftNode().preOrder();
        }
        if(this.getRightNode() != null){
            this.getRightNode().preOrder();
        }
    }
    @Override
    public int compareTo(TreeNode o) {
        return this.weight - o.getWeight();
    }

    public TreeNode(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "weight=" + weight +
                '}';
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
}

