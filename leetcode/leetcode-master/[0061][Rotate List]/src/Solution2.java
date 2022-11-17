/**
 * Author: ������
 * Date: 2015-07-21
 * Time: 18:46
 * Declaration: All Rights Reserved !!!
 */
public class Solution2 {
    public ListNode rotateRight(ListNode head, int n) {

        if (head == null || n < 1) {
            return head;
        }

        ListNode root = new ListNode(0);
        root.next = head;
        ListNode p = root;
        ListNode q = root;

        // ��ת��λ�ÿ��ܱ�����
        int count = 0;
        for (int i = 0; i <= n; i++) {
            p = p.next;
            count++;
            if (p == null) {
                // �����г�ͷ�������ݸ���
                count--;
                // ʵ��Ҫλ�õ�λ��
                n = n % count;
                // Ϊ���¿�ʼλ����׼��
                i = 0;
                p = head;
            }
        }

        // �ҵ���һ��Ҫ�����Ľ���ǰ��
        // qΪ��һ��Ҫ�����Ľ���ǰ��
        while (p != null) {
            p = p.next;
            q = q.next;

        }

        p = q;
        q = root;
        // ��Ҫλ�ƵĽ��
        if (p != null && p.next != null) {
            ListNode node;
            while (p.next != null) {
                // ժ�����
                node = p.next;
                p.next = node.next;
                // ���Ͻ��
                node.next = q.next;
                q.next = node;
                // ���һ���ƶ��Ľڵ�
                q = node;
            }
        }

        return root.next;
    }
}
