/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:21
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * Given a singly linked list L: L0��L1������Ln-1��Ln,
     * reorder it to: L0��Ln��L1��Ln-1��L2��Ln-2����
     *
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     *
     * Example 1:
     *
     * Given 1->2->3->4, reorder it to 1->4->2->3.
     * Example 2:
     *
     * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
     *
     * ����˼·��
     * 1���ҵ�������м���
     * 2������������������㣬�ͽ��м���֮���������в�֣������
     *
     * </pre>
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode fast = head;
        // ���м����ǰ�����
        ListNode slow = root;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // ˵����ż�����ڵ�
        if (fast == null) {
            root.next = slow.next;
            slow.next = null;

        } else { // ����������
            slow = slow.next;
            root.next = slow.next;
            slow.next = null;
        }

        // ��һ������Ҫ����ͷ�巨����
        fast = root.next;
        ListNode tmp;
        // ����벿������
        while (fast.next != null) {
            tmp = fast.next;
            fast.next = tmp.next;
            tmp.next = root.next;
            root.next = tmp;
        }

        slow = head;
        fast = root.next;

        while (fast != null) {
            tmp = fast.next;
            fast.next = slow.next;
            slow.next = fast;
            slow = slow.next.next;
            fast = tmp;
        }
    }
}
