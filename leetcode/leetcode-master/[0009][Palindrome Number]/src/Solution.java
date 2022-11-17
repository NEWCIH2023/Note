/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 16:52
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Determine whether an integer is a palindrome. Do this without extra space.
     *
     * ��Ŀ����
     * �ж�һ�������Ƿ��ǻط���������Ҫʹ�ö���Ŀռ䡣
     *
     * ����˼·
     * Ϊ�˲�ʹ�ö���Ŀռ䣬�ο��������Ľ������Щ�ⷨ��������isPalindrome������û��ʹ�ö��������
     * ����ȴʹ���˷������ã������һ���������ĵĿռ���� ����û�дﵽ��Ŀ��Ҫ���Ǽٵ�ʵ�֣�
     * ���Ա�����Ȼ����һ������Ŀռ����ʵ�֡�
     *  ���ȣ��������ǻ������֣���ζ����ֽ�����ת��123���321����������任����������˵���ǻ������֡�
     * </pre>
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {

        // �������ǻ�������
        if (x < 0) {
            return false;
        }

        // ������ת���ֵ��Ϊ�˲�ʹ���������long
        long reverse = 0;
        int tmp = x;

        // ����ת���ֵ
        while (tmp != 0) {
            reverse = reverse * 10 + tmp % 10;
            tmp /= 10;
        }

        // �ж��Ƿ��ǻ�������
        return x == reverse;
    }
}
