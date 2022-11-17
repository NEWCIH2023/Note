/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 16:43
 * Declaration: All Rights Reserved !!!
 */
public class Solution2 {
    /**
     * <pre>
     * ԭ��
     * Given an array and a value, remove all instances of that value in place and return the new length.
     *  The order of elements can be changed. It doesn��t matter what you leave beyond the new length.
     *
     * ��Ŀ����
     * ����һ�������һ��ֵ��ɾ�������������ֵ��ȵ�Ԫ�أ����ҷ��������������µĳ��ȡ�
     *
     * ����˼·
     *
     * </pre>
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {

        // [0, ..., index - 1]��ʾ�Ѿ��ų�val��ֵ�������飬index��ʾ֮����Է���Ԫ�ص�λ�ã�
        // Ҳ��ʾ������ĳ���
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // ����Ⱦ��ƶ�
            if (nums[i] != val) {
                nums[index] = nums[i];
                ++index;
            }
        }

        return index;
    }
}
