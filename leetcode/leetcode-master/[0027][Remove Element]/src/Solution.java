/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 16:43
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
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
     * �������ֵΪelem��Ԫ�ص�λ�ã�j���ұ���ֵ��Ϊelem��Ԫ�ص�λ�ã�Ȼ��jλ�õ���ֵ�ƶ���iλ�á�
     * </pre>
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int exchange = 0; // ��¼�����Ĵ�����Ҳ����ͳ����������elemԪ��ֵ��ȵĸ���

        // �㷨˼�룺i�������ֵΪelem��Ԫ�ص�λ�ã�j���ұ���ֵ��Ϊelem��Ԫ�ص�λ�ã�
        // ȡ�Ⱥ����ó���Ϊ1��������Խ���
        for (int i = 0, j = nums.length - 1; i <= j; i++) {
            // �ҵ�Ҫ������Ԫ��
            if (nums[i] == val) {
                exchange++;

                // ��������濪ʼ��ǰ�ҵ�һ��������elem��Ԫ��
                while (j > i && nums[j] == val) {
                    // ��ֵΪelem��Ԫ��˵��Ҫ���������ǽ������̿���ʡȥ
                    exchange++;
                    j--;
                }

                // ���1��������Ϊelem��Ԫ�ص�λ�ã���jλ�õ�Ԫ�طŵ�iλ��
                // ���2��û���ҵ���elem��Ԫ�ص�λ�ã���i�������Ԫ��ֵ����e����ʱ��j=i
                // �������������j�е�ֵ����i��û�й�ϵ
                nums[i] = nums[j];
                j--; // j�Ѿ�������ʹ�������Ի�Ҫ��ǰ�ƶ���һ���µ�λ��
            }
        }

        return nums.length - exchange;
    }
}
