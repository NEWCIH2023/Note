/**
 * Author: ������
 * Date: 2015-06-21
 * Time: 20:35
 * Declaration: All Rights Reserved !!!
 */
public class Solution {

    /**
     * <pre>
     * Given an array of integers, every element appears three times except for one.
     * Find that single one.
     *
     * Note:
     *   - Your algorithm should have a linear runtime complexity.
     *          Could you implement it without using extra memory?
     * ��Ŀ���⣺
     * ��һ�����飬����ֻ��һ������һ�Σ��������ֶ�����3�Σ��ҳ��������һ�ε����֣�
     * Ҫ��ʱ�临�Ӷ�ΪO(n)���ռ临�Ӷ�ΪO(1)����ò�������Ŀռ�
     *
     * ����˼·
     * ����һ������ȫ���ö����Ʊ�ʾ��������ǰ� �� i th   ��λ�����������ֵĺͶ�3ȡ�࣬
     * ��ôֻ����������� 0 �� 1 (�������⣬3��0��3��1���������Ϊ0).  ���ȡ��Ľ�
     * �������Ǹ� ��Single Number����һ��ֱ�ӵ�ʵ�־����ô�СΪ 32����������¼����
     * λ�ϵĺ͡�
     *
     * ��������ʹ�����������
     * ones    �����ithλֻ����һ�ε��������
     * twos    �����ithλֻ�������δε��������
     * threes  �����ithλֻ�������ε��������
     * ����ithλ����3��ʱ�����Ǿ�ones��twos�ĵ�ithλ����Ϊ0. ���յĴ𰸾��� ones��
     * </pre>
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {

        int[] count = new int[32];
        int result = 0;

        for (int i = 0; i < 32; i++) {
            for (int n : nums) {
                // ͳ�Ƶ�iλ��1�ĸ���
                if (((n >> i) & 1) == 1) {
                    count[i]++;
                }
            }

            result |= (count[i] % 3) << i;
        }

        return result;
    }

    // TODO δ��ȫ���
    public int singleNumber2(int[] nums) {
        // ֻ����һ�ε����������
        int ones = 0;
        // ֻ�������δε��������
        int twos = 0;
        // ֻ�������ε��������
        int threes;

        for (int n : nums) {
            twos |= ones & n;
            // ���3�� �� ��� 1�εĽ����һ����
            ones ^= n;
            // ����ones��twos�ѳ�����3�ε�λ������Ϊ0��ȡ��֮��1��λ��Ϊ0��
            threes = ones & twos;

            ones &= ~threes;
            twos &= ~threes;
        }

        return ones;
    }
}
