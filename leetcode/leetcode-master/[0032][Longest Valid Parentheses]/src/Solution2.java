import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: ������
 * Date: 2015-06-25
 * Time: 09:12
 * Declaration: All Rights Reserved !!!
 */
public class Solution2 {
    /**
     * <pre>
     * Given a string containing just the characters '(' and ')',
     * find the length of the longest valid (well-formed) parentheses substring.
     *
     * For "(()", the longest valid parentheses substring is "()", which has length = 2.
     * Another example is ")()())", where the longest valid parentheses substring is "()()",
     * which has length = 4.
     *
     * ��Ŀ���⣺
     * ����һ���ַ�����ֻ����С���źţ�����ĺϷ���С���ŵ���Ŀ
     *
     * ����˼·��
     * ʹ��ջ��ʵ��
     * </pre>
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int result = 0;
        int start = 0;
        // ��¼�����ŵ�λ��
        Deque<Integer> m = new LinkedList<>();
        for (int i = 0; i < s.length(); ++i) {
            // �������žͼ�¼λ��
            if (s.charAt(i) == '(') {
                m.push(i);
            }
            // ��������
            else if (s.charAt(i) == ')') {
                // ֮ǰ�����Ŷ��Ѿ���ԣ����ߴ�û���������
                if (m.isEmpty()) {
                    // ��ǰ��λ���Ѿ�����Ч�ˣ���¼��һ��λ���ǿ�ʼ��Ե�λ��
                    start = i + 1;
                } else {
                    // ��ջ��Ӧ��������
                    m.pop();
                    // ���ջΪ�գ�˵����start��ʼһֱƥ�䣬û���ֶ���������ţ�
                    // ������ǣ�Math.max(result, i - start + 1)
                    // ���ջ�ǿգ�˵���ж���������ţ����һ������������ŵ�λ�þ���m.peek()��
                    // ����ƥ�����ž���: Math.max(result, i - m.peek())
                    result = m.isEmpty() ? Math.max(result, i - start + 1) : Math.max(result, i - m.peek());
                }
            }
        }
        return result;
    }
}
