package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 这个类是为了实现图
 * 用二位数组实现领接矩阵，用链表存储节点
 * 需要添加节点，添加边，获取节点数，获取节点的方法，还要有遍历边的方法，遍历图
 * 然后实现dfs和bfs，走完整个图
 *
 * @author abs
 * @Date 2019/8/4 - 8:47
 */
public class Graph {
    int weight;
    ArrayList<String> vertextList;
    int[][] edges;
    int numOfEdges;
    boolean[] isVisted;

    public static void main(String[] args) {
        int n = 8;  //结点的个数
        //String Vertexs[] = {"A", "B", "C", "D", "E"};
        String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertex : Vertexs) {
            graph.addVertext(vertex);
        }

        //添加边
        //A-B A-C B-C B-D B-E
//		graph.insertEdge(0, 1, 1); // A-B
//		graph.insertEdge(0, 2, 1); //
//		graph.insertEdge(1, 2, 1); //
//		graph.insertEdge(1, 3, 1); //
//		graph.insertEdge(1, 4, 1); //

        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);


        //显示一把邻结矩阵
        graph.showGraph();

        //测试一把，我们的dfs遍历是否ok
        System.out.println("深度遍历");
        graph.dfs(); // A->B->C->D->E [1->2->4->8->5->3->6->7]
//		System.out.println();
        System.out.println("广度优先!");
        graph.bfs(); // A->B->C->D-E [1->2->3->4->5->6->7->8]
    }

    public int getFirstNeighbor(int i) {
        for (int j = 0; j < vertextList.size(); j++) {
            if (edges[i][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int i, int w) {
        for (int j = w + 1; j < vertextList.size(); j++) {
            if (edges[i][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 实现深度优先遍历
     * 深度优先是将每个节点都遍历一遍，
     * 当前节点设置为已访问，获取第一个邻近节点，然后是否可走，可走则继续dfs递归，否则找下一个邻接点继续
     * 直到返回的邻接节点为null或-1
     */
    public void dfs() {
        isVisted = new boolean[vertextList.size()];
        for (int i = 0; i < vertextList.size(); i++) {
            if (!isVisted[i]) {
                dfs(isVisted,i);
            }
        }
    }

    public void dfs(boolean[] isVisted,int i) {
        System.out.print(getVertext(i) + "->");
        isVisted[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisted[w]) {
                dfs(isVisted,w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 广度优先遍历
     * 将所有的节点都来一遍，每一遍
     * 先将第一个节点的所有邻接节点访问后，从下一个邻接节点开始，访问他的所有邻接节点
     * 因此需要用到队列将每个访问后的节点放入到队列中，队列不为空就继续将下一个节点广度遍历
     */
    public void bfs(){
        isVisted = new boolean[vertextList.size()];
        for (int i = 0; i < vertextList.size(); i++) {
            if(!isVisted[i]){
                bfs(isVisted,i);
            }
        }
    }
    public void bfs(boolean[] isVisted,int i) {
        int u;
        int w;
        LinkedList<Integer> queue = new LinkedList<>();

        System.out.print(getVertext(i) + "-->");
        isVisted[i] = true;
        queue.addLast(i);

        while(!queue.isEmpty()){
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while(w != -1){
                if(!isVisted[w]){
                    System.out.print(getVertext(w) + "-->");
                    queue.addLast(w);
                    isVisted[w] = true;
                }
                w = getNextNeighbor(u,w);
            }
        }
    }

    public Graph(int n) {
        this.vertextList = new ArrayList<>();
        edges = new int[n][n];
        isVisted = new boolean[n];
    }

    public void addVertext(String string) {
        vertextList.add(string);
    }

    public void insertEdge(int i, int j, int weight) {
        edges[i][j] = weight;
        edges[j][i] = weight;
        numOfEdges++;
    }

    public String getVertext(int index) {
        return vertextList.get(index);
    }

    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
