/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:45
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     * For example,
     * Given 1->1->2, return 1->2.
     * Given 1->1->2->3->3, return 1->2->3.
     *
     * ��Ŀ����
     * ����һ��������ɾ���ظ���Ԫ�أ���ͬ��ֻ����һ����
     *
     * ����˼·
     * ʹ��һ��ָ��ָ�������ͷ�������һ���뵱ǰ�Ľ�������ɾ����ֱ������һ������ͬ�ģ���ָ��ָ����
     * ���µĽ�㣬�ظ�������ֱ�����еĽ�㶼�����ꡣ
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode point;
        // ָ���½���β������ʼʱ����ֻ��һ��Ԫ�أ�������ͷ
        ListNode tail = head;

        if (head != null) {
            // ָ����ͷ����һ��Ԫ��
            point = head.next;
            // ��δ��ĩβ
            while (point != null) {

                // �����β�ڵ㲻��ͬ���ͽ�����ͬ�Ľڵ����ӵ�tail����һ��λ��
                if (tail.val != point.val) {
                    tail.next = point;
                    // ����ָ����β
                    tail = tail.next;
                }

                // �ƶ�����һ��λ��
                point = point.next;
            }

            // ��βָ���
            tail.next = null;
        }

        return head;
    }
}
