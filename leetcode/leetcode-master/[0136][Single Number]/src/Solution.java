/**
 * Author: ������
 * Date: 2015-06-18
 * Time: 10:29
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * Given an array of integers, every element appears twice except for one.
     * Find that single one.
     * <p>
     * Note: Your algorithm should have a linear runtime complexity.
     * Could you implement it without using extra memory?
     * <p>
     * ����һ�����飬ÿ��Ԫ�ض�����2�γ������е�һ�����ҳ�ֻ����һ�ε�����
     * ע�⣺�㷨����������ʱ�临�Ӷȣ����Բ�ʹ�ö���ռ�ʵ����
     * <p>
     * ����˼·��ʹ���������
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {

        if (nums == null || nums.length < 1) {
            throw new IllegalArgumentException("nums should contain at least one element");
        }


        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }
}
