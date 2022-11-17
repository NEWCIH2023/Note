/**
 * Author: ������
 * Date: 2015-06-17
 * Time: 20:54
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     * Find the median of the two sorted arrays. The overall run time complexity
     * should be O(log (m+n)).
     *
     * ��Ŀ���⣺
     * �����������飬�������������������λ����ʱ�临�Ӷ�ΪO(log(m+n))
     *
     * ���˼·��
     * �ݹ�������
     * </pre>
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 != 0) {
            return findKth(nums1, 0, nums2, 0, total / 2 + 1);
        } else {
            return (findKth(nums1, 0, nums2, 0, total / 2)
                    + findKth(nums1, 0, nums2, 0, total / 2 + 1)
            ) / 2.0;
        }
    }

    /**
     * �����������������ҵ�k���Ԫ�أ�k=1, 2, 3, ...
     *
     * @param nums1  ����1
     * @param start1 ��ʼλ��1
     * @param nums2  ����2
     * @param start2 ��ʼλ��2
     * @param k      Ҫ�ҵ���λλ��
     * @return
     */
    public int findKth(int[] nums1, final int start1,
                       int[] nums2, final int start2,
                       final int k) {
        // ��Ҫ���ҳ���С�ķ���ǰ��
        if (nums1.length - start1 > nums2.length - start2) {
            return findKth(nums2, start2, nums1, start1, k);
        }

        // ����Ԫ�ص������Ѿ��ﵽĩβ
        if (nums1.length == start1) {
            return nums2[start2 + k - 1];
        }

        // ��һ�������������бȽ�С��
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }


        // ��num1, nums2�����и���һ��������൱��ÿ�ζ���Ҫ��������1/4
        int half = Math.min(k / 2, nums1.length - start1);
        // nums2�����п��ҵ�λ��
        int ia = half + start1;
        // nums2�����п��ҵ�λ��
        int ib = k - half + start2;

        // nums1[start, ..., ia-1, ia, ..., nums1.length]
        // nums2[start, ..., ib-1, ib, ..., nums2.length]
        // ˵��������nums1[start, ..., ia-1]�������ˣ�Ҫ�ҵ�ֵ��nums1[ia, ..., nums1.length]
        // ��nums2[start, ..., ib-1, ib, ..., nums2.length]��
        if (nums1[ia - 1] < nums2[ib - 1]) {
            // k - (ia - start1) = k - (half + start1 - start1) = k - half
            return findKth(nums1, ia, nums2, start2, k - (ia - start1));
        }

        // ˵��������nums2[start, ..., ib-1]�������ˣ�Ҫ�ҵ�ֵ��nums2[ib, ..., nums2.length]
        // nums1[start, ..., ia-1, ia, ..., nums1.length]
        else if (nums1[ia - 1] > nums2[ib - 1]) {
            // k - (ib - start2) = k - (k - half + start2 - start2)
            return findKth(nums1, start1, nums2, ib, half);
        }
        // ����ֵ���˵���ҵ��ˣ�
        else {
            return nums1[ia - 1];
        }
    }
}
