import java.math.BigInteger;

public class 恰好移动k步到达某一位置的方法数目 {
    public static void main(String[] args) {
        恰好移动k步到达某一位置的方法数目 s = new 恰好移动k步到达某一位置的方法数目();
        System.out.println(s.numberOfWays(2, 5, 999));
    }

    public int numberOfWays(int startPos, int endPos, int k) {
        int period = Math.abs(startPos - endPos);

        if ((k < period) || (k - period) % 2 != 0) {
            return 0;
        }

        int halfArmLength = (k - period) / 2;
        long result = c(k, period + halfArmLength);
        return (int) (result );
    }

    public long c(long bottom, long top) {
        BigInteger result = BigInteger.ONE;
        BigInteger temp = BigInteger.ONE;

        for (int i = 1; i <= top; i++) {
            result = result.multiply(BigInteger.valueOf(bottom - i + 1));
            temp = temp.multiply(BigInteger.valueOf(i));

            if (result.mod(BigInteger.valueOf(i)) == BigInteger.ZERO) {
                result = result.divide(BigInteger.valueOf(i));
                temp = temp.divide(BigInteger.valueOf(i));
            } else if (result.mod(temp) == BigInteger.ZERO) {
                result = result.divide(temp);
                temp = BigInteger.ONE;
            }
        }

        return (result.divide(temp).mod(BigInteger.valueOf(1000000007))).longValue();
    }
}