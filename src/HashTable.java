import sun.security.util.Length;

import java.util.logging.Level;

/**
 * 这个类是为了实现哈希表的数据结构，
 * 哈希表可以做为类似数据缓存的快速存取结构
 * 可以很方便地取出，放进数据，
 * 实现的方式是，创建多个链表做为数组，每个节点类，算出hash值，即数组的下标，放到该下标所属的链表当中即可
 * 我就写一个add的方法和list的方法
 *
 * @author abs
 * @Date 2019/7/29 - 23:23
 */
public class HashTable {
    private static int length = 8;
    //创建一个数组，长度就是length，这个是链表的数组，这个数组的每个链表都需要初始化

    public static void main(String[] args) {
        Employee employee = new Employee(1);
        Employee employee2 = new Employee(2);
        Employee employee3 = new Employee(3);
        Employee employee4 = new Employee(4);
        EmployeeLinkedList list = new EmployeeLinkedList();

        Hash hash = new Hash(8);
        hash.add(employee);
        hash.add(employee2);
        hash.add(employee3);
        hash.add(employee4);

        hash.list();
        System.out.println(hash.find(3));
    }

}
class Hash{
    private int length;
    EmployeeLinkedList[] employeeLinkedLists;

    public Hash(int length) {
        this.length = length;
        employeeLinkedLists = new EmployeeLinkedList[length];
        for (int i = 0; i < length; i++) {
            employeeLinkedLists[i] = new EmployeeLinkedList();
        }
    }

    public  int hashNum(int n){
        return n%length;
    }

    public  void add(Employee employee){
        int index = hashNum(employee.getNo());
        employeeLinkedLists[index].add(employee);
    }

    public  Employee find(int n){
        int index = hashNum(n);
        return employeeLinkedLists[index].find(n);
    }

    public  void list(){
        for (int i = 0; i < employeeLinkedLists.length; i++) {
            employeeLinkedLists[i].list();
        }
    }
}
class EmployeeLinkedList{
    private Employee head = new Employee(0);

    public void add(Employee employee){
        Employee temp =head;
        while(temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(employee);
    }

    public void list(){
        if(head.getNext() == null){
            System.out.println("链表为空");
            return;
        }
        Employee temp =head.getNext();
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    public Employee find(int no){
        if(head.getNext() == null){
            System.out.println("链表空的，没有找到");
            return null;
        }
        Employee temp = head.getNext();
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.getNo() == no){
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if(flag){
            return temp;
        }else{
            System.out.println("没有找到");
            return null;
        }
    }
}
class Employee{
    private int no;
    private String name;
    private Employee next;

    @Override
    public String toString() {
        return "Employee{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public Employee(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getNext() {
        return next;
    }

    public void setNext(Employee next) {
        this.next = next;
    }
}

