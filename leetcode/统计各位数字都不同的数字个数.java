public class 统计各位数字都不同的数字个数 {
    public static void main(String[] args) {
        统计各位数字都不同的数字个数 s = new 统计各位数字都不同的数字个数();
        System.out.println(s.countNumbersWithUniqueDigits(7));
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }

        int result = 10;

        for (int i = 1; i < n; i++) {
            result += 9 * a(i, 9);
        }

        return result;
    }

    public int a(int top, int bottom) {
        int result = 1;
        for (int i = 0; i < top; i++) {
            result *= (bottom--);
        }
        return result;
    }

}