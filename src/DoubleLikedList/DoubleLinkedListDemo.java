package DoubleLikedList;

/**
 * @author abs
 * @Date 2019/7/25 - 23:49
 */
public class
DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1,"xxx","xxx");
        HeroNode2 hero2 = new HeroNode2(2,"yyy","yyy");
        HeroNode2 hero3 = new HeroNode2(3,"zzz","zzz");
        HeroNode2 hero4 = new HeroNode2(11,"aaa","aaa");

        LinkedList linkedList = new LinkedList();
//        linkedList.add(hero1);
//        linkedList.add(hero3);
//        linkedList.add(hero2);
//        linkedList.add(hero4);
//        linkedList.list();

        linkedList.addByOrde(hero1);
        linkedList.addByOrde(hero3);
        linkedList.addByOrde(hero2);
        linkedList.addByOrde(hero4);
        linkedList.list();

        System.out.println("更新之后");
        linkedList.update(new HeroNode2(2,"和德焘","二货"));
        linkedList.list();


        System.out.println("删除之后");
        linkedList.delete(1);
        linkedList.delete(2);
        linkedList.delete(3);
        linkedList.delete(4);
        linkedList.list();

    }
}
class LinkedList{
    private HeroNode2 head = new HeroNode2(0,"","");

    public void add(HeroNode2 heroNode){
        HeroNode2 temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void addByOrde(HeroNode2 heroNode){
        HeroNode2 temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.printf("已经有同个序号的节点了%d，无法插入\n",heroNode.no);
        }else{
            heroNode.next = temp.next;
            if(temp.next != null){
                temp.next.pre = heroNode;
            }
            temp.next = heroNode;
            heroNode.pre = temp;
        }
    }

    public void update(HeroNode2 heroNode){
        HeroNode2 temp = head.next;
        boolean flag =false;
        if(temp == null){
            System.out.println("链表是空的，找不到该节点");
            return;
        }
        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        }else{
            System.out.println("没有找到该节点");
        }
    }

    public void delete(int no){
        if(head.next == null){
            System.out.println("列表是空的，不用删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            if(temp.next != null){ //如果这是最后一个节点了，
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.println("没找到节点");
        }
    }
    public void list(){
        HeroNode2 temp = head.next;
        if(temp == null){
            System.out.println("链表为空");
            return;
        }
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public HeroNode2 getHead() {
        return head;
    }
}
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
