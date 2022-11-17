/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 18:51
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given a binary tree, find its minimum depth.
     * The minimum depth is the number of nodes along the shortest path from
     * the root node down to the nearest leaf node.
     *
     * ��Ŀ����
     * ����һ����������������С��ȡ�
     *
     * ����˼·
     * �������������������б������ҳ���С����ȡ�
     * </pre>
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        return minDepth(root, false);
    }

    public int minDepth(TreeNode root, boolean hasBrother) {
        if (root == null) {
            // �Լ�Ϊnull���ֵܲ�Ϊnull���ϲ����ӽ�㣬˵����ǰ��û���ҵ���С��
            // û���ֵܣ�˵����������ǰʱ���ֲ���С���Ѿ��ҵ�
            return hasBrother ? Integer.MAX_VALUE : 0;
        }

        return 1 + Math.min(minDepth(root.left, root.right != null),
                minDepth(root.right, root.left != null));
    }

}
