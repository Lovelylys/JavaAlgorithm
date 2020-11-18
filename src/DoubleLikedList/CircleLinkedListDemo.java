package DoubleLikedList;

/**
 * 这个功能是为了解决Joshefu问题，约瑟夫问题，从第一个小孩开始报数，报到一个特定的数字则出列，下一个孩子从头
 * 开始报数，到这个数字出列，循环到最后一个孩子出列
 * @author abs
 * @Date 2019/7/27 - 19:20
 */
public class CircleLinkedListDemo {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.initList(5);
        circleLinkedList.countBoy(1,2,5);
    }
}
class CircleLinkedList{
    private Boy first;

    /*
    * 输入要初始化的小孩节点个数
    * 由于头节点不能动，要创建一个临时的节点
    * 头节点先设置环形，临时节点指向头节点
    * 然后循环n，不断新建接点，接到临时节点后，新节点指向头节点，临时节点再后移一位到新节点上
    * */
    public void initList(int n){
        Boy currBoy = null;
        for (int i = 1; i <= n; i++) {
            if(i == 1){
                Boy newBoy = new Boy(i);
                first = newBoy;
                first.setNext(first);
                currBoy = first;
            }else{
                Boy newBoy = new Boy(i);
                currBoy.setNext(newBoy);
                newBoy.setNext(first);
                currBoy = currBoy.getNext();
            }
        }
    }

    public void showList(){
        Boy currBoy = first;
        if(currBoy == null){
            System.out.println(("这是空链表"));
            return;
        }
        while(true){
            System.out.println(currBoy);
            if(currBoy.getNext() == first){
                break;
            }
            currBoy = currBoy.getNext();
        }
    }

    /*
    *  实现孩子出圈
    *  输入 开始的位置，出圈的数字，孩子的个数
    *  判断入参是否合理
    *  建立helper，帮助最后定位只剩一个孩子，跟在头节点之后
    *  将头节点和helper都移动到开始的位置
    *  循环出圈，直到helper 等于 头节点first，跳出循环；里面再一个小的循环，按照出圈的数字循环，然后出圈，
    *  头节点和helper一直指向下一个，然后打印first为出圈节点，helper指向头节点
     */
    public void countBoy(int startNo,int move,int nums){
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("输入参数有误");
            return;
        }
        Boy helper = first;
        while(helper.getNext() != first){
            helper = helper.getNext();
        }

        //移动到开始的位置
        for(int i = 0; i < startNo - 1; i++){
            first = first.getNext();
            helper = helper.getNext();
        }

        //开始让孩子出圈
        while (true){
            if(helper == first){
                break;
            }
            for(int i = 0; i < move -1; i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("出圈的孩子编号为" + first);
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后一个出圈的孩子是" + first);
    }
}
class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}