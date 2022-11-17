import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:41
 * Declaration: All Rights Reserved !!!
 */
public class Solution {

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
     * �ȶ������е�Ԫ�ؽ�������Ȼ��ʹ�ù���Ż�������
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
            dfs(nums, 0, result, curr);
        }

        return result;
    }

    private void dfs(int[] nums, int index, List<List<Integer>> result, List<Integer> curr) {


        // ��ӵ�����У�˵����һ���µĽ����
        result.add(new LinkedList<>(curr));


        for (int j = index; j < nums.length; j++) {
            // ���Ԫ��
            curr.add(nums[j]);
            dfs(nums, j + 1, result, curr);
            // ��ԭ
            curr.remove((Integer) nums[j]);
        }
    }
}
