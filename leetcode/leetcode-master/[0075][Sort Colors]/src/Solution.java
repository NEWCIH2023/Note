/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:37
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given an array with n objects colored red, white or blue, sort them so that objects
     * of the same color are adjacent, with the colors in the order red, white and blue.
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
     * and blue respectively.
     * Note:
     * You are not suppose to use the library��s sort function for this problem.
     *
     * ��Ŀ����
     * ����һ���������飬�����Ǻ�ɫ����ɫ����ɫ������ɫ�������򣬺죬�ף�����
     * ʹ��0��1��2�ֱ����죬�ף�����
     * ע�⣺����ʹ�ÿ⺯����������
     *
     * ����˼·
     * ��һ������ֱ����0��1��2���ֵĴ������ٽ����鸳ֵ
     * </pre>
     *
     * @param nums
     */
    public void sortColors(int[] nums) {

        if (nums == null) {
            return;
        }

        int[] count = new int[3];

        for (int n : nums) {
            count[n]++;
        }

        int start = 0;
        int end = 0;
        for (int i = 0; i < count.length; i++) {
            if (i == 0) {
                start = 0;
            } else {
                start += count[i - 1];
            }
            end += count[i];
            for (int j = start; j < end; j++) {
                nums[j] = i;
            }
        }

        // �����϶�����ĸĽ�
//        for (int i = 0; i < count[0]; i++) {
//            nums[i] = 0;
//        }
//
//        for (int i = count[0]; i < count[0] + count[1]; i++) {
//            nums[i] = 1;
//        }
//
//        for (int i = count[0] + count[1]; i < nums.length; i++) {
//            nums[i] = 2;
//        }
    }
}
