/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:18
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * Follow up:
     * Can you solve it without using extra space?
     *
     * ��Ŀ����
     * ����һ��������������л������ػ���ڵĵ�һ���ڵ㣬���򷵻�null
     *
     * ����˼·
     * ���ж������Ƿ��л���ʹ�ÿ죨fast����ָ�루slow�����ⷨ����141����LinkedListCycle����
     * ���û�л��ͷ���null������л�����fast=slow��������slow����ָ������ͷ��Ȼ������ָ��ÿ��ͬʱ�ƶ�һ��λ�ã�
     * ֱ����������������������ǻ�����ڽ�㡣
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
