/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 16:40
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given a sorted array, remove the duplicates in place such that each element
     * appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this in place
     * with constant memory.
     * For example,
     * Given input array nums = [1,1,2],
     * Your function should return length = 2, with the first two elements of nums
     * being 1 and 2 respectively. It doesn��t matter what you leave beyond the new length.
     *
     * ��Ŀ����
     * ����һ����������飬�������е��ظ�Ԫ��ȥ������ͬ��ֻ����һ�������ҷ��������µ�Ԫ�ظ�����
     * ��Ҫ����һ���µ����������������ڳ���ʱ���ڽ���������
     *
     * ����˼·
     * �ӵڶ���Ԫ�ؿ�ʼ������Ϊ��ǰ�����Ԫ�أ������ǰԪ��������ǰһ��Ԫ����ͬ��ɾ�����Ԫ�أ�
     * �����ͬ�ͽ����ƶ�����ȷ��λ�ã������������Ԫ���˸�����
     * </pre>
     *
     * @param A
     * @return
     */
    public int removeDuplicates(int[] A) {

        if (A == null) {
            return 0;
        }

        if (A.length < 2) {
            return A.length;
        }

        // ָ����һ������λ��
        int index = 1;
        for (int i = 1; i < A.length; i++) {
            // index - 1��ʾ��ǰ���һ���źõ�λ��
            if (A[index - 1] < A[i]) {
                A[index] = A[i];
                index++;
            }
        }

        return index;
    }
}
