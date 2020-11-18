package binarySortTree;

/**
 * 这个类是为了实现二叉排序树
 * 也就是二叉搜索树
 * 实现递归添加
 * 首先确定节点数值是大于当前节点，还是小于当前节点，然后小于，判断当前左子节点是否为空，为空添加，否则左递归；大于则判断右子节点是否为空，为空添加
 * 否则右递归，前面判断当前节点不为null
 *
 * 删除二叉排序树节点
 * 分三种情况，删除叶子节点，删除有一个子树的节点，删除有两个子树的字节
 * 删除叶子节点，首先找到父节点，然后根据是左子节点还是右子节点，父子节点的左右为空即可
 * 删除有一个子树的，找到父节点，如果目标节点是父节点的左节点，目标节点如果有左子节点，那么搬到父节点的左子节点
 * 如果有右子节点，也搬到父节点的左子节点
 * 如果是他的右子节点，同理
 *
 * 删除有两个子树的，找到目标节点的右子节点，不断地向其左子节点寻找，直到找到最小地左节点，删除之，
 * 然后将其数值赋予到目标节点进行替换即可
 * @author abs
 * @Date 2019/8/3 - 14:56
 */
public class BinarySortTreeDemo {


    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9,2};
        BinarySortTree binarySortTree = new BinarySortTree();

        for (int i : arr) {
            Node node = new Node(i);
            binarySortTree.add(node);
        }
        binarySortTree.infixOrder();
        binarySortTree.delete(new Node(12));


        binarySortTree.delete(new Node(5));

        binarySortTree.delete(new Node(2));

        binarySortTree.delete(new Node(9));
        binarySortTree.delete(new Node(1));
        binarySortTree.delete(new Node(3));
        binarySortTree.delete(new Node(10));
//        binarySortTree.delete(new Node(7));



        System.out.println("root=" + binarySortTree.getRoot());


        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }
}
class BinarySortTree{
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
