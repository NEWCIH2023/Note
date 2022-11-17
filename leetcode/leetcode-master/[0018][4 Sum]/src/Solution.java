import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 16:28
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given an array S of n integers, are there elements a, b, c, and d in S
     * such that a + b + c + d = target? Find all unique quadruplets in the array
     * which gives the sum of target.
     * Note:
     * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a �� b �� c �� d)
     * The solution set must not contain duplicate quadruplets.
     *
     * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
     *
     * A solution set is:
     * (-1,  0, 0, 1)
     * (-2, -1, 1, 2)
     * (-2,  0, 0, 2)
     *
     * ��Ŀ����
     * ����һ���������飬�ҳ�a + b + c + d = target��Ψһ�⡣
     *
     * ����˼·
     * ��ȷ��a��d��������������a��d������������ͬʱ�ظ�ʹ�á�Ȼ����ȷ��b��c��ͬ����������Ҳ����
     * ͬʱ�ظ�ʹ�á��ҳ��������������Ľ⣬ͬʱ���Ա�֤�ⲻ�ظ���
     * </pre>
     *
     * @param num
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> result = new LinkedList<>();
        if (num == null || num.length < 4) {
            return result;
        }

        // �������������
        Arrays.sort(num);

        // ��һ������
        for (int i = 0; i < num.length - 3; ) {
            // ���ĸ�����
            for (int j = num.length - 1; j > i + 2; j--) {
                // ���ĸ�����ʹ�ò��ظ�
                if (j < num.length - 1 && num[j] == num[j + 1]) {
                    continue;
                }

                // �ڶ�������
                int start = i + 1;
                // ����������
                int end = j - 1;
                int n = target - num[i] - num[j];

                while (start < end) {
                    if (num[start] + num[end] == n) {
                        List<Integer> four = new ArrayList<>(4);
                        four.add(num[i]);
                        four.add(num[start]);
                        four.add(num[end]);
                        four.add(num[j]);

                        result.add(four);

                        do {
                            start++;
                            // ��֤�ٴ�ʹ�õڶ��������ظ�
                        } while (start < end && num[start] == num[start - 1]);
                        do {
                            end--;
                            // ��֤�ٴ�ʹ�õ����������ظ�
                        } while (start < end && num[end] == num[end + 1]);
                    } else if (num[start] + num[end] < n) {
                        do {
                            start++;
                            // ��֤�ٴ�ʹ�õڶ��������ظ�
                        } while (start < end && num[start] == num[start - 1]);
                    } else {
                        do {
                            end--;
                            // ��֤�ٴ�ʹ�õ����������ظ�
                        } while (start < end && num[end] == num[end + 1]);
                    }
                }
            }

            // ��ͬ��������Ϊ����ֻ��ʹ��һ��
            do {
                i++;
            } while (i < num.length - 2 && num[i - 1] == num[i]);
        }
        return result;
    }
}
