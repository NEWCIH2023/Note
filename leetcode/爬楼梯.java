public class 爬楼梯 {

    public static void main(String[] args) {
        爬楼梯 s = new 爬楼梯();
        System.out.println(s.climbStairs(3));
    }

    public int climbStairs(int n) {
        int[] temp = new int[n + 1];
        temp[0] = 0;
        temp[1] = 1;

        for (int i = 2; i <= n; i++) {
            temp[i] = temp[i - 1] + temp[i - 2];
        }

        return temp[n];
    }

}