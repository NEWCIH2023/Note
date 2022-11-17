/**
 * Author: ������
 * Date: 2015-06-22
 * Time: 12:05
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * Given an integer array nums, find the contiguous subarray (containing at least one number)
     * which has the largest sum and return its sum.
     *
     * Example:
     *
     * Input: [-2,1,-3,4,-1,2,1,-5,4],
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     * Follow up:
     *
     * If you have figured out the O(n) solution, try coding another solution using the divide and
     * conquer approach, which is more subtle.
     *
     * ��Ŀ���⣺
     * ����������������ĺ�
     * ����˼·��
     * ��̬�滮���⣬��֪��ǰk��Ԫ�ص���������к�ΪmaxSub���Ѿ�����¼�����ˣ����Լ�һ����ʱ��sum��
     * �������˵�k+1���Ԫ�أ�����������������������ƣ��������k+1���Ԫ��֮ǰ�ĺ���С��0�ģ�
     * ��ô��������k+1���Ԫ�شӶ�ȥ��������������û�й��׵ģ����Կ��԰�sum ��0��
     *
     * �����Ǵ�ͷ��β������������ʱ�򣬶����������һ�����������м���ѡ���أ���ֻ������
     * ѡ��1������֮ǰ��SubArray��2. �Լ�����һ��SubArray����ʲôʱ����������������أ�
     * ���֮ǰSubArray ������ʹ���0 �Ļ���������Ϊ��Ժ���������й��׵ġ��������������
     * ѡ�����֮ǰ��SubArray
     * ���֮ǰSubArray �������Ϊ0 ����С��0 �Ļ���������Ϊ��Ժ��������û�й��ף�������
     * �к��ģ�С��0 ʱ�����������������ѡ����������ֿ�ʼ������һ��SubArray��
     * ��״̬Ϊf[j]����ʾ��S[j] ��β��������������кͣ���״̬ת�Ʒ������£�
     * f[j] = max {f[j - 1] + S[j]; S[j]} ; ����1 <= j <= n
     * target = max {f[j]}; ����1 <= j <= n
     * �������£�
     * ���һ��S[j] ����������ǰ���ĳЩ�����һ�����������У���������������к�Ϊf[j - 1] + S[j]��
     * �������S[j] �������ֳ�Ϊһ�Σ������������н�����һ����S[j]����������������к�ΪS[j]��
     *
     * </pre>
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // ����У��
        if (nums == null || nums.length < 1) {
            throw new IllegalArgumentException();
        }

        int max = Integer.MIN_VALUE;
        int curSum = 0;

        for (int i : nums) {
            // ��ǰ��С��0���ͽ���ǰֵ����curSum
            if (curSum <= 0) {
                curSum = i;
            }
            // ��������ۼ�
            else {
                curSum += i;
            }

            // ����ϴ��ֵ
            if (max < curSum) {
                max = curSum;
            }
        }

        return max;
    }
}
