package Tree;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author abs
 * @Date 2019/8/1 - 22:57
 */
public class Test {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
