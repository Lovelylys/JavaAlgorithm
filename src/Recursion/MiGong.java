package Recursion;

/**
 * 这个类是为了解决迷宫的问题
 * 创一个数组做为迷宫，周围墙壁设为1
 * 约定一下，如果是走过的通道则是2，如果是没走过的则是0，如果是不可行的位置则为3
 * 要达到最后的地点
 * 分析一下，在每一个位置上首先判断是否可行，路线制定是 下 左 上 右
 * 首先判断当前的点是否能走，如果是为走过的点，那么设置为2，走过，接下来就要走下一个点，判断下一个点是否能走
 * 如果不能则返回错误，换下一个方向，直到所有都返回错误则设置该点不可行，结束
 * 如果不是走过的点，或者不能走的点，直接返回错误
 * 下一个点一样 要先判断是否是未走过的点，设置为2，走过，然后走下一个点，所以是重复上一步，可以递归
 * 直到走到最后终点的位置，那么打印路径，返回，结束进程
 * @author abs
 * @Date 2019/7/28 - 15:03
 */
public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[2][1] = 1;
        map[2][2] = 1;
        print(map);
        System.out.println("走迷宫的路线");
        setWay(map,1,1);
    }
    public static boolean setWay(int[][] map,int x,int y){//要有迷宫，和开始的位置
        //先写结束条件
        if(map[6][5] == 2){
            print(map);
            return true;
        }
        if(map[x][y] == 0){
            map[x][y] = 2;
            if(setWay(map,x+1,y)){
                return true;
            }else if(setWay(map,x,y+1)){
                return true;
            }else if(setWay(map,x-1,y)){
                return true;
            }else if(setWay(map,x,y-1)){
                return true;
            }else{
                map[x][y] = 3;
                return  false;
            }
        }else{
            return false;
        }
    }
    public static void print(int[][] map){
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
    }
}
