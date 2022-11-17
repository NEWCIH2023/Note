/**
 * Author: ������
 * Date: 2015-06-19
 * Time: 14:51
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /***
     * Description:
     * Count the number of prime numbers less than a non-negative number, n.
     * <p>
     * ��Ŀ���⣺
     * ͳ��С�ڷǸ�����n�������ĸ���
     * <p>
     * ����˼·��
     * ʹ�ü�������ɫ��ɸ����
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {

        if (n <= 1) {
            return 0;
        }

        // Ĭ�����е�Ԫ��ֵ��������Ϊfalse
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;

        for (int i = 2; i * i < n; i++) {
            // ���i��һ��������i��i�ı�������Ϊ������
            // ����i��һ�������������ض��Ѿ�����Ϊtrue�ˣ���Ϊ�Ǵ�2��ʼ�����
            if (!notPrime[i]) {
                for (int j = 2 * i; j < n; j += i) {
                    notPrime[j] = true;
                }
            }
        }

        // ͳ�������ĸ���
        int result = 0;
        for (boolean b : notPrime) {
            if (!b) {
                result++;
            }
        }

        return result;
    }
}
