import java.util.LinkedList;
import java.util.List;

/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:39
 * Declaration: All Rights Reserved !!!
 */
public class Solution {

    /**
     * <pre>
     * ԭ��
     * Given two integers n and k, return all possible combinations of k numbers out of 1 �� n.
     * For example,
     * If n = 4 and k = 2, a solution is:
     *
     * [
     *  [2,4],
     *  [3,4],
     *  [2,3],
     *  [1,2],
     *  [1,3],
     *  [1,4],
     * ]
     *
     * ��Ŀ����
     * ����������n��k�����1-n��k������������ϡ�
     *
     * ����˼·
     * ʹ�ù���Ż������㷨
     * </pre>
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> current = new LinkedList<>();

        dfs(1, n, k, current, result);

        return result;
    }

    private void dfs(int start, int end, int k, List<Integer> current, List<List<Integer>> result) {
        if (k == current.size()) {
            result.add(new LinkedList<>(current));
            return;
        }

        for (int i = start; i <= end; i++) {
            current.add(i);
            dfs(i + 1, end, k, current, result);
            current.remove((Integer) i);
        }
    }


}
