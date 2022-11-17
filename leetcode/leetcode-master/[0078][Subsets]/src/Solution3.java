import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:41
 * Declaration: All Rights Reserved !!!
 */
public class Solution3 {

    /**
     * <pre>
     * ԭ��
     * Given a set of distinct integers, nums, return all possible subsets.
     * Note:
     * Elements in a subset must be in non-descending order.
     * The solution set must not contain duplicate subsets.
     * For example,
     * If nums = [1,2,3], a solution is:
     *
     * [
     *  [3],
     *  [1],
     *  [2],
     *  [1,2,3],
     *  [1,3],
     *  [2,3],
     *  [1,2],
     *  []
     * ]
     *
     * ��Ŀ����
     * ����һ����ͬ���ֵ����飬�������������Ӽ���
     *
     * ����˼·
     * �ȶ������е�Ԫ�ؽ�������Ȼ�����õݹ���η�������⡣
     * </pre>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> curr = new LinkedList<>();
        if (nums != null) {

            // ��S����������
            Arrays.sort(nums);
            // i��ʾ�Ӽ�Ԫ�ظ���
            for (int i = 0; i <= nums.length; i++) {
                subset(nums, 0, i, result, curr);
            }
        }

        return result;
    }

    /**
     * ��Ԫ����n�����Ӽ���
     *
     * @param nums   Ԫ�ؼ���
     * @param idx    ��ȡԪ�ص���ʼλ��
     * @param n      ����Ԫ�ظ���
     * @param result �����
     * @param curr   ��ʱ��
     */
    private void subset(int[] nums, int idx, int n, List<List<Integer>> result, List<Integer> curr) {

        // �Ѿ�����ĩβ�ˣ�˵����һ����
        if (n == 0) {
            result.add(new LinkedList<>(curr));
            return;
        }

        // ��û�д����꣬�ݹ鴦����һ��Ԫ��
        for (int i = idx; i < nums.length - n + 1; i++) {
            curr.add(nums[i]);
            subset(nums, i + 1, n - 1, result, curr);
            curr.remove((Integer) nums[i]);
        }
    }


}
