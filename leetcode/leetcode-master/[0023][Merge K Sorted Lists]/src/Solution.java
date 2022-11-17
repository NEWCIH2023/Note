import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: ������
 * Date: 2015-06-30
 * Time: 19:21
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * Merge k sorted linked lists and return it as one sorted list.
     * Analyze and describe its complexity.
     *
     * ��Ŀ���⣺
     * �ϲ�k���źõĵĵ�����
     *
     * ����˼·��
     * ʹ��һ��С�������в������Ƚ�k��������ĵ�һ�������ѣ���ȡ���е���С�أ���Ϊ��С��Ԫ�أ�
     * �����Ԫ�ص���һ�����ѣ���ȡ������С�ģ����β���ֱ����Ϊ��
     * </pre>
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        // Ϊ�ջ���û��Ԫ��
        if (lists == null || lists.length < 1) {
            return null;
        }

        // ֻ��һ��Ԫ��
        if (lists.length == 1) {
            return lists[0];
        }

        // ����һ��С���ѣ�����ʹ��һ�������ڲ�����Ϊ�Ƚ���
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1 == null) {
                    return -1;
                }

                if (o2 == null) {
                    return 1;
                }

                return o1.val - o2.val;
            }
        });

        // ������������ĵ�һ��������
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        // ͷ��㣬������ʹ��
        ListNode head = new ListNode(0);
        // ��ǰ����Ľ��
        ListNode curr = head;

        while (!queue.isEmpty()) {
            ListNode node = queue.remove();

            // ������һ����㲻Ϊ�վͽ���һ��������
            if (node.next != null) {
                queue.add(node.next);
            }

            curr.next = node;
            curr = node;
        }

        return head.next;
    }

}
