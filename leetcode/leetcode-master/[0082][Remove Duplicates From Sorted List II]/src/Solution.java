/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:43
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given a sorted linked list, delete all nodes that have duplicate numbers,
     * leaving only distinct numbers from the original list.
     * For example,
     * Given 1->2->3->3->4->4->5, return 1->2->5.
     * Given 1->1->1->2->3, return 2->3.
     *
     * ��Ŀ����
     * ����һ���ź���ĵ�����ɾ�������ظ���Ԫ�ء�ֻ����ֻ��һ��ֵ��Ԫ�ء�
     *
     * ����˼·
     * ���������һ���������root��root�ӵ�ԭ����ͷ�����������ͷ���͸�����ָ�룬�����ص�Ԫ�ؽ���ɾ����
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {

        // ͷ���
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode p = head;
        // ��¼���һ��û���ظ���Ԫ�أ���ʼָ��ͷ���
        ListNode q = root;

        // Ԫ���ظ�����
        int delta = 0;

        while (p != null && p.next != null) {
            // ���������������ͬ
            if (p.val == p.next.val) {
                delta++;
                // �ƶ�����һ�����
                p = p.next;
            }
            // �������������㲻��ͬ
            else {
                // ֵΪp.val�Ľ��û���ظ�
                if (delta == 0) {
                    // ���ӵ�û�и���Ԫ��
                    q.next = p;
                    // ָ�����һ��δ�ظ���Ԫ��
                    q = p;
                    // �ƶ�����һ�����
                    p = p.next;
                }
                // ֵΪp.val�Ľ�����ظ�
                else {
                    // �ƶ�����һ��Ԫ��
                    p = p.next;
                    // ȥ���ظ���Ԫ��
                    q.next = p.next;
                    // Ԫ���ظ���������Ϊ0
                    delta = 0;
                }
            }
        }

        // ������һ��Ԫ���Ǹ��ľ�ȥ��
        if (delta != 0) {
            q.next = null;
        }
        // ���û���ظ��Ϳ����ӵ���β
        else {
            q.next = p;
        }

        return root.next;
    }
}
