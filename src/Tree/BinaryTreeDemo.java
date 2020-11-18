package Tree;

/**
 * 这个类是为了实现二叉树，实现二叉树的前中后序遍历以及搜索
 * 当前节点打印，如果左节点不为空，那么左节点递归调用，如果右节点不为空递归调用
 * @author abs
 * @Date 2019/7/31 - 21:57
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(10);
        TreeNode node5 = new TreeNode(14);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        BinaryTree tree = new BinaryTree(root);
        tree.preOrder();
        tree.delete(10);
        tree.preOrder();
    }
}
class BinaryTree{
    private TreeNode root;

    public BinaryTree(TreeNode root) {
        this.root = root;
    }
    public void delete(int no){
        if(root != null){
            if(root.getNo() == no){
                root= null;
                return;
            }
            root.delet(no);
        }else{
            return;
        }
    }
    public TreeNode postOrderSearch(int no){
        if(root == null){
            return null;
        }
        return root.postOrderSearch(no);
    }
    public TreeNode prefixOrderSearch(int no){
        if(root == null){
            return null;
        }
        return root.preOrderSearch(no);
    }
    public void preOrder(){
        if(root == null){
            return;
        }
        root.preOrder();
    }

    public void infixOrder(){
        if(root == null){
            return;
        }
        root.infixOrder();
    }

    public void postOrder(){
        if(root == null){
            return;
        }
        root.postOrder();
    }
}
class TreeNode{
    private int no;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int no) {
        this.no = no;
    }

    //删除子节点，如果是叶子节点就删掉，如果不是叶子节点，就删掉整个树
    //删除子节点需要先找到父节点，然后判断左右节点是否要删除，结束后向左递归删除，向右递归删除
    public void delet(int no){
        if(this.getLeft() != null && this.getLeft().getNo() == no){
            this.setLeft(null);
            return;
        }
        if(this.getRight() != null && this.getRight().getNo() == no){
            this.setRight(null);
            return;
        }
        if(this.getLeft() != null){
            this.getLeft().delet(no);
        }
        if(this.getRight() != null){
            this.getRight().delet(no);
        }
    }

    /**
     * 首先判断当前节点是否正确，如果是则返回
     * 不是，那么左节点是否为空，非空则左递归，如果左递归找到了，那么返回，否则右递归
     * 右递归则，其实找不找到都要返回了
     * 这里递归没有结束条件，为了继续执行下去，同时有返回值，只能在返回值确定的时候即时返回，不会向下再执行
     */
    public TreeNode preOrderSearch(int no){
        System.out.println("前序查找!");
        if (this.getNo() == no) {
            return this;
        }
        TreeNode treeNode = null;
        if(this.getLeft() != null){
            treeNode = this.getLeft().preOrderSearch(no);
        }
        if(treeNode != null){
            return treeNode;
        }//如果为null，此时不能返回，要继续要下找
        if(this.getRight() != null){
            treeNode = this.getRight().preOrderSearch(no);
        }
        return treeNode;
    }

    public TreeNode infixOrderSearch(int no){
        TreeNode treeNode = null;
        if(this.getLeft() != null){
            treeNode = this.getLeft().preOrderSearch(no);
        }
        if(treeNode != null){
            return treeNode;
        }//如果为null，此时不能返回，要继续要下找
        System.out.println("中序查找!");
        if (this.getNo() == no) {
            return this;
        }
        if(this.getRight() != null){
            treeNode = this.getRight().preOrderSearch(no);
        }
        return treeNode;
    }

    public TreeNode postOrderSearch(int no){
        TreeNode treeNode = null;
        if(this.getLeft() != null){
            treeNode = this.getLeft().preOrderSearch(no);
        }
        if(treeNode != null){
            return  treeNode;
        }

        if(this.getRight() != null){
            treeNode = this.getRight().preOrderSearch(no);
        }
        if(treeNode != null){
            return  treeNode;
        }
        System.out.println("后序查找!");
        if(this.no == no){
            return this;
        }//如果为null，此时不能返回，要继续要下找
        return  treeNode;
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

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                '}';
    }
}
