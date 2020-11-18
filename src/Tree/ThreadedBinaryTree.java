package Tree;

import com.sun.media.sound.SoftTuning;

/**
 * 这个类是为了实现线索化二叉树
 * 线索化二叉树，就是将没有左右节点的节点，左右指向前驱结点，后驱节点
 * 线索化分为前序、中序、后序线索化二叉树
 * 需要一个前置节点
 * 中序线索化二叉树，先递归到左边，如果此时节点的左节点为null，那么设置左子节点为pre，type为1
 * 然后处理pre的右子节点，是否为null，而且pre不为null，则将pre的右子节点设为当前node，pre后移为当前节点，
 * 继续向右递归线索化
 * 线索化后的遍历方法，
 * 中序遍历方法，因为有线索化，所以不需要用到递归，使得遍历变得效率
 * 首先是不断循环左节点，得到节点的左子节点类型为1，那么打印左子节点，然后循环右子节点如果为1.那么就一直打印，且往下拿到右子节点
 * 直到不是1，那么将子节点向右移动，为右子节点
 * @author abs
 * @Date 2019/7/31 - 23:29
 */
public class ThreadedBinaryTree {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        Node root = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(6);
        Node node4 = new Node(8);
        Node node5 = new Node(10);
        Node node6 = new Node(14);

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadBinaryTree threadedBinaryTree = new ThreadBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadTree(root);
        //前序列化
//        threadedBinaryTree.preThreadTree(root);

        //测试: 以10号节点测试
        Node leftNode = node5.getLeft();
        Node rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 ="  + leftNode); //3
        System.out.println("10号结点的后继结点是="  + rightNode); //1
        //threadedBinaryTree.threadList();

        threadedBinaryTree.preThreadList();
    }
}
class ThreadBinaryTree{
    private Node pre;
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    public void threadList(){
        Node node = root;

        while(node != null){
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }

            System.out.println(node);

            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    public void preThreadList(){
        Node node = root;
        while(node != null){
            System.out.println(node);
            while(node.getLeftType() == 0){
                node = node.getLeft();
                System.out.println(node);
            }
            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
    public void preThreadTree(Node node){
        //结束条件
        if(node == null){
            return;
        }

        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        threadTree(node.getLeft());

        threadTree(node.getRight());
    }
    public void threadTree(Node node){
        //结束条件
        if(node == null){
            return;
        }

        threadTree(node.getLeft());

        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        threadTree(node.getRight());
    }
}
class Node{
    private int no;
    private Node left;
    private Node right;
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public Node(int no) {
        this.no = no;
    }

    public void preOrder(){
        System.out.println(this);
        if(this.getLeft() != null){
            this.getLeft().preOrder();
        }
        if(this.getRight() != null){
            this.getRight().preOrder();
        }
    }

    public void infixOrder(){
        if(this.getLeft() != null){
            this.getLeft().infixOrder();
        }
        System.out.println(this);
        if(this.getRight() != null){
            this.getRight().infixOrder();
        }
    }

    public void postOrder(){
        if(this.getLeft() != null){
            this.getLeft().postOrder();
        }
        if(this.getRight() != null){
            this.getRight().postOrder();
        }
        System.out.println(this);
    }
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                '}';
    }
}