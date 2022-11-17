/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:16
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given a linked list, determine if it has a cycle in it.
     * Follow up:
     * Can you solve it without using extra space?
     *
     * ��Ŀ����
     * ����һ���������ж������Ƿ��л���
     *
     * ����˼·
     * ��������ָ��(fast, slow)����ʼֵ��ָ��ͷ��slowÿ��ǰ��һ����fastÿ��ǰ�����������������ڻ���
     * ��fast�ض��Ƚ��뻷����slow����뻷������ָ��ض�����
     * </pre>
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public boolean hasCycle2(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }

        return !(fast == null || fast.next == null);
    }
}
