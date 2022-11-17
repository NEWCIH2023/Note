/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:32
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given a non-negative number represented as an array of digits, plus one to the number.
     * The digits are stored such that the most significant digit is at the head of the list.
     *
     * ��Ŀ����
     * ����һ���������ʾ��һ�������������м�һ������
     * ÿһ����λ���洢�������һ��λ���ϡ������±�Ӵ�С��ʾ��λ�ӵ�λ����λ��
     *
     * ����˼·
     * ֱ����⣬����һ����λ��־carry����ֵΪ1����ʾ��1�������λ��ʼtmp = a[x] + carry��
     * a[x] = tmp%10��carry = tmp/10�����carry��Ϊ0����һλ�ٽ��в�����ֱ�����е���λ���������carray
     * Ϊ0���˳�����������carray��Ϊ0˵����������Ҫ��չһ����λ��
     * </pre>
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

        // ��λ��־����һλ���Ľ�λ��־
        int carry = 1;
        int tmp;
        for (int i = digits.length - 1; i >= 0; i--) {
            tmp = digits[i];
            // ���㵱ǰλ����ֵ
            digits[i] = (tmp + carry) % 10;
            // �����µĽ�λ
            carry = (tmp + carry) / 10;

            // û�н�λ�˾Ϳ����˳���
            if (carry == 0) {
                break;
            }
        }

        // �����һ����λ
        if (carry == 1) {
            int[] result = new int[digits.length + 1];
            System.arraycopy(digits, 0, result, 1, digits.length);
            result[0] = carry;
            return result;
        } else {
            return digits;
        }
    }
}
