public class 飞机座位分配概率 {
    public static void main(String[] args) {
        飞机座位分配概率 s = new 飞机座位分配概率();
        System.out.println(s.nthPersonGetsNthSeat(534));
    }

    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) {
            return 1D;
        }

        return 0.5d;
    }
}