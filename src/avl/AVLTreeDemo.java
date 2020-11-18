package avl;

/**
 * 这个类是为了实现AVL树
 * AVL树其实和二叉平衡树差别不大，但是多了左右旋转，双旋
 * 可以帮助将二叉平衡树平衡左右子树的高度
 *
 * @author abs
 * @Date 2019/8/4 - 8:14
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = { 10, 12, 8, 9, 7, 6 };
                int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个 AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0; i <arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); //3
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
        System.out.println("当前的根结点=" + avlTree.getRoot());//8

    }
}
class AVLTree{
    Node root;

    public  Node getRoot(){
        return root;
    }

    public Node findNode(Node node){
        if(root == null){
            return null;
        }else{
            return root.findNode(node);
        }
    }

    public Node findParentNode(Node node){
        if(root == null){
            return  null;
        }else{
            return root.findParentNode(node);
        }
    }

    public void delete(Node node){//删除节点的方法，
        Node targetNode = findNode(node);
        if(targetNode == null){
            System.out.println("没有找到节点，无法删除");
        }
        if(root.getLeftNode() == null && root.getRightNode() == null){
            root = null;
            return;
        }
        Node parentNode = findParentNode(node);
        if(targetNode.getLeftNode() == null && targetNode.getRightNode() == null){
            if(parentNode.getLeftNode() != null && parentNode.getLeftNode().getValue() == node.getValue()){
                parentNode.setLeftNode(null);
            }else if(parentNode.getRightNode()!= null && parentNode.getRightNode().getValue() == node.getValue()){
                parentNode.setRightNode(null);
            }
        }else if(targetNode.getLeftNode() != null && targetNode.getRightNode() != null){//删除两个子树的
            int value = delRightTreeMin(targetNode.rightNode);
            targetNode.setValue(value);
        }else{// 删除有一个子树的节点
            if(targetNode.getLeftNode() != null) {
                if(parentNode != null) {
                    //如果 targetNode 是 parent 的左子结点
                    if(parentNode.getLeftNode().value == node.getValue()) {
                        parentNode.setLeftNode(targetNode.getLeftNode());
                    } else { //  targetNode 是 parent 的右子结点
                        parentNode.setRightNode(targetNode.getLeftNode());
                    }
                } else {
                    root = targetNode.getLeftNode();
                }
            } else { //如果要删除的结点有右子结点
                if(parentNode != null) {
                    //如果 targetNode 是 parent 的左子结点
                    if(parentNode.getLeftNode().value == node.getValue()) {
                        parentNode.setLeftNode(targetNode.getRightNode());
                    } else { //如果 targetNode 是 parent 的右子结点
                        parentNode.setRightNode(targetNode.getRightNode());
                    }
                } else {
                    root = targetNode.getRightNode();
                }
            }
        }
    }

    private int delRightTreeMin(Node rightNode) {
        Node target = rightNode;
        while(target.getLeftNode() != null){
            target = target.getLeftNode();
        }
        delete(target);
        return target.getValue();
    }

    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }
    public void infixOrder(){
        if(root == null){
            System.out.println("二叉树是空的，无法遍历");
        }else{
            root.infixOrder();
        }
    }
}
class Node{
    int value;
    Node leftNode;
    Node rightNode;

    public void leftRotate(){
        Node node = new Node(this.getValue());
        node.setLeftNode(this.getLeftNode());
        node.setRightNode(this.getRightNode().getLeftNode());
        this.setValue(this.getRightNode().getValue());
        this.setRightNode(this.getRightNode().getRightNode());
        this.setLeftNode(node);
    }

    public void rightRotate(){
        Node node = new Node(this.getValue());
        node.setRightNode(this.getRightNode());
        node.setLeftNode(this.getLeftNode().getRightNode());
        this.setValue(this.getLeftNode().getValue());
        this.setLeftNode(this.getLeftNode().getLeftNode());
        this.setRightNode(node);
    }

    public int leftHeight(){
        if(this.getLeftNode() == null){
            return 0;
        }else{
            return this.getLeftNode().height();
        }
    }

    public int rightHeight(){
        if(this.getRightNode() == null){
            return 0;
        }else{
            return this.getRightNode().height();
        }
    }
    public int height(){
        return Math.max(this.getLeftNode() == null?0:this.getLeftNode().height(),this.getRightNode() == null ? 0:this.getRightNode().height()) +1;
    }
    public Node findNode(Node node){
        if(this.getValue() == node.getValue()){
            return this;
        }else if(this.getValue() > node.getValue()){
            if(this.getLeftNode() != null){
                return this.getLeftNode().findNode(node);
            }else{
                return  null;
            }
        }else{
            if(this.getRightNode() != null){
                return this.getRightNode().findNode(node);
            }else{
                return null;
            }
        }
    }

    public Node findParentNode(Node node){
        if(this.getLeftNode()!= null && this.getLeftNode().getValue() == node.getValue()
                || this.getRightNode() != null && this.getRightNode().getValue() == node.getValue()){
            return this;
        }else{
            if(node.getValue() < this.getValue() && this.getLeftNode() != null){
                return this.getLeftNode().findParentNode(node);
            }else if(node.getValue() >= this.getValue() && this.getRightNode() != null){
                return this.getRightNode().findParentNode(node);
            }else{
                return null;
            }
        }
    }

    public void infixOrder(){
        if(this.getLeftNode() != null){
            this.getLeftNode().infixOrder();
        }
        System.out.println(this);
        if(this.getRightNode() != null){
            this.getRightNode().infixOrder();
        }
    }
    public void add(Node node){
        if(node == null){
            return;
        }
        if(node.getValue() < this.getValue()){
            if(this.getLeftNode() == null){
                this.setLeftNode(node);
            }else{
                this.getLeftNode().add(node);
            }
        }else{
            if(this.getRightNode() == null){
                this.setRightNode(node);
            }else{
                this.getRightNode().add(node);
            }
        }

        //每次添加完一个节点之后如果发生了左子树和右子树的高度差超过1，那么就进行旋转
        if(this.rightHeight() - this.leftHeight() > 1){
            //左旋转之前判断右子树的左子树和右子树高度
            if(this.getRightNode() != null && this.getRightNode().leftHeight() > this.getRightNode().rightHeight()){
                this.getRightNode().rightRotate();
            }
            this.leftRotate();
            return;
        }
        if(this.leftHeight() - this.rightHeight() > 1){
            if(this.getLeftNode() != null && this.getLeftNode().rightHeight() > this.getLeftNode().leftHeight()){
                this.getLeftNode().leftRotate();
            }
            this.rightRotate();
        }
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}