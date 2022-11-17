import java.util.LinkedList;
import java.util.List;

/**
 * Author: ������
 * Date: 2015-07-25
 * Time: 18:19
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        // ���ڼ�¼��ǰ��Ҫ�����Ԫ��
        TreeNode node = root;

        // node�����������1��root��㣬2���������Һ���
        while (node != null || !stack.isEmpty()) {
            // ������������ջ
            while (node != null) {
                stack.addLast(node);
                node = node.left;
            }

            // ��ʱջ�е�ջ��Ԫ��һ����û�����ӵ�
            if (!stack.isEmpty()) {
                // ɾ��ջ��Ԫ��
                node = stack.removeLast();
                result.add(node.val);
                // ָ�Һ���
                node = node.right;
            }
        }
        return result;
    }
}
