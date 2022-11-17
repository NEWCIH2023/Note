/**
 * Author: ������
 * Date: 2015-06-19
 * Time: 17:30
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * A peak element is an element that is greater than its neighbors.
     *
     * Given an input array where num[i] �� num[i+1], find a peak element and return its index.
     *
     * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
     *
     * You may imagine that num[-1] = num[n] = -��.
     *
     * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return
     * the index number 2.
     *
     * Note:
     * Your solution should be in logarithmic complexity.
     *
     * ��Ŀ���⣺
     * ����ֵԪ�ء���ֵָ�����ھӵ�Ԫ��
     *
     * ����һ������num[i] �� num[i+1]���ҵ�һ����ֵԪ�ز��������±ꡣ
     *
     * ������ܰ��������ֵ����������·�����������һ�����ɡ�
     *
     * ���Լ���num[-1] = num[n] = -�ޣ���ʼԪ�ص�����ĩβԪ�ص��Ҳ��Ϊ�������
     *
     * ���磬����[1, 2, 3, 1]�У�3�Ƿ�ֵԪ�أ���ĺ���Ӧ�÷����±�2��
     *
     * ע�⣺
     * ����������Ϊ�������Ӷȡ�
     * </pre>
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {

        if (nums == null || nums.length < 1) {
            return -1;
        }

        if (nums.length == 1) {
            return 0;
        }

        if (nums[0] > nums[1]) {
            return 0;
        } else if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }


        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }

        return -1;
    }

}