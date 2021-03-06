package linkedLIst;

import java.awt.*;
import java.util.Stack;

/**
 * @author abs
 * @Date 2019/7/25 - 6:51
 */
public class LinkedListDemo {
    public static void main(String[] args) {
         HeroNode hero1 = new HeroNode(1,"xxx","xxx");
         HeroNode hero2 = new HeroNode(2,"yyy","yyy");
         HeroNode hero3 = new HeroNode(3,"zzz","zzz");
         HeroNode hero4 = new HeroNode(11,"aaa","aaa");

         LinkedList linkedList = new LinkedList();
         /*linkedList.add(hero1);
        linkedList.add(hero3);
         linkedList.add(hero2);
         linkedList.add(hero4);
         linkedList.list();*/

        linkedList.addByOrde(hero1);
        linkedList.addByOrde(hero3);
        linkedList.addByOrde(hero2);
        linkedList.addByOrde(hero4);
        linkedList.list();

        System.out.println("更新之后");
        linkedList.update(new HeroNode(2,"和德焘","二货"));
        linkedList.list();


//        System.out.println("删除之后");
//        linkedList.delete(1);
//        linkedList.delete(2);
//        linkedList.delete(3);
//        linkedList.delete(4);
//        linkedList.list();
//
//        System.out.println("逆序打印");
//        reversePrint(linkedList.getHead());
//        System.out.println("反转链表");
//        reverse(linkedList.getHead());
//        linkedList.list();

        HeroNode hero5 = new HeroNode(5,"xxx","xxx");
        HeroNode hero6 = new HeroNode(6,"yyy","yyy");
        HeroNode hero7 = new HeroNode(7,"zzz","zzz");
        HeroNode hero9 = new HeroNode(9,"aaa","aaa");

        LinkedList linkedList2 = new LinkedList();
        linkedList2.add(hero5);
        linkedList2.add(hero6);
        linkedList2.add(hero7);
        linkedList2.add(hero9);
        /*System.out.println("结合两个列表");
        combineTwoList(linkedList.getHead(),linkedList2.getHead());
        linkedList.list();*/

        combineTwoList2(linkedList.getHead(),linkedList2.getHead());
    }
    //完成一下将链表反转
    public static void reverse(HeroNode head){
         HeroNode next;
         HeroNode curr = head.next;
         HeroNode reverseHead = new HeroNode(0,"","");
        if(head.next == null || head.next.next == null){
            return;
        }
        while(curr != null){
            next = curr.next;
            curr.next = reverseHead.next;
            reverseHead.next = curr;
            curr = next;
        }
        head.next = reverseHead.next;
    }
    //将链表逆序打印   采用statck的方式实现
    public static void reversePrint(HeroNode head){
        HeroNode temp = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    //将两个有序的链表 组合 一个新的有序链表  b
    //另一个实现方式，我觉得可以，直接循环另一个链表，将每个节点通过addByOrder 加入 第一个链表了
    public static void combineTwoList2(HeroNode head1,HeroNode head2) {
        HeroNode temp =head2.next; //拆出来就没有了，应该先保存后面的节点
        HeroNode next;
        LinkedList linkedList = new LinkedList();
        linkedList.getHead().next = head1.next;
        System.out.println("测试链表1");
        linkedList.list();
        while(temp != null){
            next = temp.next;
            linkedList.addByOrde(temp);
            temp = next;
        }
        linkedList.list();
    }
    public static void combineTwoList(HeroNode head1,HeroNode head2){
        HeroNode resultHead = new HeroNode(0,"","");
        HeroNode temp1 = head1.next;
        HeroNode temp2 = head2.next;
        HeroNode curr = resultHead;
        while(true){
            if(temp1 == null){
                curr.next = temp2;
                break;
            }
            if(temp2 == null){
                curr.next =temp1;
                break;
            }
            if(temp1.no < temp2.no){
                curr.next = temp1;
                temp1 = temp1.next;
            }else{
                curr.next = temp2;
                temp2 = temp2.next;
            }
            curr = curr.next;
        }
        head1.next = resultHead.next;
    }
}
class LinkedList{
    private HeroNode head = new HeroNode(0,"","");

    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void addByOrde(HeroNode heroNode){
        HeroNode temp = head;
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
            temp.next = heroNode;
        }
    }

    public void update(HeroNode heroNode){
        HeroNode temp = head.next;
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
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.println("没找到节点");
        }
    }
    public void list(){
        HeroNode temp = head.next;
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

    public HeroNode getHead() {
        return head;
    }
}
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
