package HuffManTree;

import jdk.internal.util.xml.impl.Input;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 这个类是为了实现霍夫曼编码，将一段文字，文件，转化为霍夫曼编码
 * 将字符串转换为byte数组，计算每个字母的权重，然后创建节点，将节点排序后，就开始建立霍夫曼树
 * 获取霍夫曼编码表：通过路径，路径向左为0，向右为1，寻找每个叶子节点，如果找到了叶子节点，那么就记录其对应的编码表，否则
 * 不断地递归，左节点，再递归右节点，同时记录每次的路径
 * 将字符串对应的byte数组，通过编码表转化为二进制字符串；再将字符串转化为对应的byte数组，压缩
 *
 * 解码过程：
 * 将byte[]数组，转化为二进制字符串，然后将二进制字符串对比霍夫曼编码表，转化为byte[] 字符数组，最后字符数组打印出来即可，或者是拼接成字符串
 * 将byte，数字转化为二进制串，可以直接使用String的toBitString尝试，但是会遇到正数需要补位的情况，因此采用或操作补位
 * 同时最后一个byte不需要补位，因此还需要判断是否是最后一个byte，然后返回每个byte对应的字符串
 *
 * 将字符串和编码表，先将编码表反转，然后对应编码表，将字符串一位一位的在map中匹配，如果此时可以拿到byte 字符，那么就记录字符，从新的位置上，再次一位位的加
 * 推进，直到最后走完整个二进制字符串
 *
 * 下一步就是读取文件，压缩文件
 * 将压缩文件解压
 *
 * @author abs
 * @Date 2019/8/2 - 22:37
 */
public class HuffManCode {
    public static void main(String[] args) {
//        String input = "i like like like java do you like a javasdasda";
//        byte[] bytes = zip(input.getBytes());
//        byte[] source = decode(bytes,huffmanCodes);
//        System.out.println(new String(source) + source.length);
        String srcFile = "G:\\1.jpg";
        String destFile = "G:\\1.zip";
        zipFile(srcFile,destFile);

        String  destFile2 = "G:\\4.jpg";
        unZipFile(destFile,destFile2);
    }

    public static void zipFile(String srcFile,String destFile){// 读取文件后将文件进性压缩，
        InputStream fis = null;
        ObjectOutputStream oss = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(srcFile);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            byte[] huffmanbytes = zip(bytes);
            //将霍夫曼压缩的编码放进去目标文件
            os = new FileOutputStream(destFile);
            oss = new ObjectOutputStream(os);

            oss.writeObject(huffmanbytes);
            oss.writeObject(huffmanCodes);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close();
                oss.close();;
                os.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void unZipFile(String srcFile,String destFile){
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);
            os = new FileOutputStream(destFile);
            byte[] huffmanBytes = (byte[])ois.readObject();
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

            byte[] source = decode(huffmanBytes,huffmanCodes);
            os.write(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static byte[] zip(byte[] source){
        ArrayList<Node> list = createNodeList(source);
        Collections.sort(list);
        Node node = createHuffmanNode(list);
        getCodes(node);
        byte[] huffmanBytes = zip(source,huffmanCodes);
        return huffmanBytes;
    }

//    public static byte[] decode(byte[] huffmanBytes,Map<Byte,String> huffmanCodes){
//        StringBuilder huffmanCode = new StringBuilder();
//        for (int i = 0; i < huffmanBytes.length; i++) {
//            if(i == huffmanBytes.length - 1){
//                huffmanCode.append(byteToBitString(false,huffmanBytes[i]));
//            }else{
//                huffmanCode.append(byteToBitString(true,huffmanBytes[i]));
//            }
//        }
////        System.out.println("解码生成的字符串： " + huffmanCode);
//
//        Map<String,Byte> antiHuffmanCodes = new HashMap<>();
//        for(Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
//            antiHuffmanCodes.put(entry.getValue(),entry.getKey());
//        }
//
//        //反转后，处理二进制字符串
//        ArrayList<Byte> list = new ArrayList<>();
//        for (int i = 0; i < huffmanCode.length(); ) {
//            int count = 1;
//            boolean flag = true;
//            Byte b = null;
//
//            while(flag){
//                String strByte = huffmanCode.substring(i,i+count);
//                b = antiHuffmanCodes.get(strByte);
//                if(b == null){
//                    count++;
//                }else {
//                    flag = false;
//                }
//            }
//            list.add(b);
//            i += count;
//        }
//        byte[] bytes = new byte[list.size()];
//        int index = 0;
//        for (Byte aByte : list) {
//            bytes[index++] = aByte;
//        }
//        return bytes;
//    }
private static byte[] decode( byte[] huffmanBytes,Map<Byte,String> huffmanCodes) {

    //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
    StringBuilder stringBuilder = new StringBuilder();
    //将byte数组转成二进制的字符串
    for(int i = 0; i < huffmanBytes.length; i++) {
        byte b = huffmanBytes[i];
        //判断是不是最后一个字节
        boolean flag = (i == huffmanBytes.length - 1);
        stringBuilder.append(byteToBitString(!flag, b));
    }
    System.out.println("解壓字符串L");
    //把字符串安装指定的赫夫曼编码进行解码
    //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
    Map<String, Byte>  map = new HashMap<String,Byte>();
    for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
        map.put(entry.getValue(), entry.getKey());
    }

    //创建要给集合，存放byte
    List<Byte> list = new ArrayList<>();
    //i 可以理解成就是索引,扫描 stringBuilder
    for(int  i = 0; i < stringBuilder.length(); ) {
        int count = 1; // 小的计数器
        boolean flag = true;
        Byte b = null;

        while(flag) {
            //1010100010111...
            //递增的取出 key 1
            String key = stringBuilder.substring(i, i+count);//i 不动，让count移动，指定匹配到一个字符
            b = map.get(key);
            if(b == null) {//说明没有匹配到
                count++;
            }else {
                //匹配到
                flag = false;
            }
        }
        list.add(b);
        i += count;//i 直接移动到 count
    }
    //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
    //把list 中的数据放入到byte[] 并返回
    byte b[] = new byte[list.size()];
    for(int i = 0;i < b.length; i++) {
        b[i] = list.get(i);
    }
    return b;

}


    public static String byteToBitString(byte b,boolean flag){
        String string = "";
        int temp = b;
        if(flag){//如果不是最后一位的话
            temp = 256 | temp;
        }
        string = Integer.toBinaryString(temp);
        if(flag){
            string = string.substring(string.length()-8);
        }
        return string;
    }
    /**
     * 将一个byte 转成一个二进制的字符串, 如果看不懂，可以参考我讲的Java基础 二进制的原码，反码，补码
     * @param b 传入的 byte
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @return 是该b 对应的二进制的字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int
        //如果是正数我们还存在补高位
        if(flag) {
            temp |= 256; //按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if(flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    public static byte[] zip(byte[] source,Map<Byte,String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : source) {
            stringBuilder.append(huffmanCodes.get(b));
        }//转化为二进制字符串了
        System.out.println("压缩生成的字符串： " + stringBuilder);

        int length = 0;
        if(stringBuilder.length()%8 == 0){
            length = stringBuilder.length()/8;
        }else{
            length = stringBuilder.length()/8+1;
        }


        byte[] huffmanBytes = new byte[length];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i+=8) {//每次取八个bit，做为字符，然后通过Integer转为byte
            String strByte = "";
            if(i+8 > stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i,i+8);
            }
            //如果这里不是使用二进制去转换的话，就会默认使用别的，转换，就不是将二进制转换为整数了
            Byte b = (byte)Integer.parseInt(strByte,2);
            huffmanBytes[index++] = b;
        }
        return huffmanBytes;
    }

    static StringBuilder stringBuilder = new StringBuilder();
    static HashMap<Byte,String> huffmanCodes = new HashMap<>();

    public static Map<Byte,String> getCodes(Node node){
        if(node == null){
            return null;
        }else{
            getCodes(node.getLeftNode(),"0",stringBuilder);
            getCodes(node.getRightNode(),"1",stringBuilder);
        }
        return huffmanCodes;
    }
    public static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);

        if(node != null){
            if(node.getData() == null){// 不是叶子节点的情况下
                getCodes(node.getLeftNode(),"0",stringBuilder2);
                getCodes(node.getRightNode(),"1",stringBuilder2);
            }else{
                //这就表示已经找到某个叶子节点了
                huffmanCodes.put(node.getData(),stringBuilder2.toString());
            }
        }
    }
    public static Node createHuffmanNode(ArrayList<Node> nodes){
        while(nodes.size() > 1){
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.getWeight() + rightNode.getWeight());
            parent.setLeftNode(leftNode);
            parent.setRightNode(rightNode);

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
            Collections.sort(nodes);
        }
        return nodes.get(0);
    }

    public void preOrder(Node node){
        node.preOrder();
    }

    public static ArrayList<Node> createNodeList(byte[] arr){
        HashMap<Byte,Integer> map = new HashMap<>();

        for (byte b : arr) {
            Integer count = map.get(b);
            if(count == null){
                map.put(b,1);
            }else{
                map.put(b,++count);
            }
        }
        ArrayList<Node> list = new ArrayList<>();
        for(Map.Entry entry : map.entrySet()){
            Node node = new Node((Integer)entry.getValue(),(Byte)entry.getKey());
            list.add(node);
        }
        return list;
    }
}
class Node implements Comparable<Node>{
    Integer weight;
    Byte data;
    Node leftNode;
    Node rightNode;

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
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", data=" + data +
                '}';
    }

    public Node(Integer weight) {
        this.weight = weight;
    }

    public Node(int weight, byte data) {
        this.weight = weight;
        this.data = data;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
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

    @Override
    public int compareTo(Node o) {
        return this.weight - o.getWeight();
    }
}
