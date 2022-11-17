import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class 灯泡开关 {
    public static void main(String[] args) {
        灯泡开关 s = new 灯泡开关();
        System.out.println(s.bulbSwitch(100000000));
    }

    private static final long[] temp = new long[34000];
    static {
        for (int i = 2; i < 34000; i++) {
            temp[i] = i * i;
        }
    }

    public int bulbSwitch(int n) {

        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return n;
        }

        int i = 0;
        while (temp[i] < n) {
            i++;
        }

        return i;
    }

}
