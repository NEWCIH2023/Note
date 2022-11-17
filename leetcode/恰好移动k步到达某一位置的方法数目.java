public class 恰好移动k步到达某一位置的方法数目 {
    public static void main(String[] args) {
        恰好移动k步到达某一位置的方法数目 s = new 恰好移动k步到达某一位置的方法数目();
        System.out.println(s.numberOfWays(2, 99, 3));
    }

    public int numberOfWays(int startPos, int endPos, int k) {
        int period = Math.abs(startPos - endPos);

        if ((k < period) || (k - period) % 2 != 0) {
            return 0;
        }

        long result = 0;

        return (int) (result % 1000000007);
    }
}