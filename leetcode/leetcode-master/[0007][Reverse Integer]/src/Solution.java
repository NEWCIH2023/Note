/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 16:10
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Reverse digits of an integer.
     * Example1: x = 123, return 321
     * Example2: x = -123, return -321
     *
     * ��Ŀ����
     * ����һ������������з�ת
     *
     * ����˼·
     * ͨ�����������̷����в�����
     * </pre>
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        long tmp = x;
        // ��ֹ������
        long result = 0;

        while (tmp != 0) {
            result = result * 10 + tmp % 10;
            tmp = tmp / 10;
        }

        // ����ж�
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            result = 0;
        }

        return (int) result;
    }
}
