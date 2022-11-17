/**
 * Author: ������
 * Date: 2015-07-21
 * Time: 18:46
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int n) {

        if (head == null || n < 1) {
            return head;
        }

        int length = 1;
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
            length++;
        }

        // ��������Ҫ�������Ľڵ��������Ϊ��head��ǰһ���ڵ�
        n = length - n % length;

        // ��β����
        p.next = head;
        for (int i = 0; i < n; i++) {
            p = p.next;
        }

        head = p.next;
        p.next = null;
        return head;
    }
}
