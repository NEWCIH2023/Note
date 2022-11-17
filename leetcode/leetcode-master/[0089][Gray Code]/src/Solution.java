import java.util.LinkedList;
import java.util.List;

/**
 * Author: ������
 * Date: 2015-06-22
 * Time: 13:16
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * The nums[c] is a binary numeral system where two successive values differ in only one bit.
     * Given a non-negative integer n representing the total number of bits in the code, print the
     * sequence of gray code. A gray code sequence must begin with 0.
     *
     * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     *
     * Note:
     *   - For a given n, a gray code sequence is not uniquely defined.
     *   - For example, [0,2,3,1] is also a valid gray code sequence according to the above
     *          definition.
     *   - For now, the judge is able to judge based on one instance of gray code sequence.
     *          Sorry about that.
     *
     * ��Ŀ���⣺
     * ����n���������Ϊn�ĸ������
     *
     * ������(Gray Code) �Ķ�����ο�http://en.wikipedia.org/wiki/Gray_code��
     * ��Ȼ��������ת��Ϊ�����룺g0 = b0; gi = bi^b(i-1)
     * ������Ȼ������������λ��Ϊ����������λ��������θ�λΪ��������ĸ�λ��θ�λ��
     * �������λ��θ�λ�������ơ����磬����Ȼ��������1001��ת��Ϊ������Ĺ����ǣ�������
     * ��λ��Ȼ�󽫵�1 λ��1 �͵�2 λ��0 ��򣬵õ�1����Ϊ������ĵ�2 λ������2 λ��0 �͵�3 λ
     * ��0 ��򣬵õ�0����Ϊ������ĵ�3 λ������3 λ��0 �͵�4 λ��1 ��򣬵õ�1����Ϊ�������
     * ��4 λ�����գ�������Ϊ1101��
     * ������ת��Ϊ��Ȼ�������룺b0 = g0; bi = gi^b(i-1)
     * ��������������λ��Ϊ��Ȼ������������λ���θ�λΪ��Ȼ�����Ƹ�λ�������θ�λ��
     * �������λ��θ�λ�������ơ����磬��������1000 ת��Ϊ��Ȼ��������Ĺ����ǣ��������
     * λ1����Ϊ��Ȼ������������λ��Ȼ����Ȼ��������ĵ�1 λ1 �͸�����ĵ�2 λ0 ��򣬵�
     * ��1����Ϊ��Ȼ��������ĵ�2 λ������Ȼ��������ĵ�2 λ1 �͸�����ĵ�3 λ0 ��򣬵õ�1��
     * ��Ϊ��Ȼ��������ĵ�3 λ������Ȼ��������ĵ�3 λ1 �͸�����ĵ�4 λ0 ��򣬵õ�1����Ϊ
     * ��Ȼ��������ĵ�4 λ�����գ���Ȼ��������Ϊ1111��
     * ����������ѧ��ʽ������n �ĸ�������n^(n/2)��
     * ����Ҫ������n ���ص����и����롣
     * ����1����򵥵ķ�����������ѧ��ʽ���Դ�0 ~2^n-1 ������������ת��Ϊ�����롣
     * ����2��n���صĸ����룬���Եݹ�ش�n - 1 ���صĸ��������ɡ���
     *
     * ����˼·��
     * �ݹ��������
     *  ���ַ������ڸ������Ƿ��������ʵ�����õݹ�����¹��������죺
     *  1λ����������������
     *  (n+1)λ�������е�ǰ2^n�����ֵ���nλ����������֣���˳����д����ǰ׺0
     *  (n+1)λ�������еĺ�2^n�����ֵ���nλ����������֣���������д����ǰ׺1
     * </pre>
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new LinkedList<>();
        if (n >= 0) {
            // �������ǰ�벿��
            result.add(0);
            // ���������λ��ֵ����0ʱ��
            int t = 1;
            // ÿһ����ѭ���������λ��i+1λ�ĸ���������൱�ڳ���Ϊi+1λ�ĸ�������ǰ�벿��
            for (int i = 0; i < n; i++) {
                // ����ĳ���Ϊi+1λ�������ĺ�벿�֣�ǰ�벿���ɳ���Ϊiλ�ĸ���������
                for (int j = result.size() - 1; j >= 0; j--) {
                    result.add(result.get(j) ^ t);
                }
                // ���λ����
                t <<= 1;
            }
        }
        return result;
    }
}
