import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 16:32
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given a string containing just the characters ��(��, ��)��, ��{��, ��}��, ��[�� and ��]��,
     * determine if the input string is valid.
     * The brackets must close in the correct order, ��()�� and ��()[]{}�� are all valid
     * but ��(]�� and ��([)]�� are not.
     *
     * ��Ŀ����
     * ����һ��ֻ����(��, ��)��, ��{��, ��}��, ��[�� �͡�]�����ַ�������֤���Ƿ�����Ч�ġ�
     * ���ű�����ԣ�����Ҫ����ȷ��˳��
     *
     * ����˼·
     * ��һ��ջ������������Ŵ����д�������������ž���ջ������������ž���ջ��Ԫ�ؿ��Ƿ����һ�����ţ�
     * ��ɾ͵��������Ҵ�����һ����������ţ������ƥ���ֱ�ӷ��ؽ����
     * </pre>
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        final String left = "([{";
        final String right = ")]}";

        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);

            // ����������ž���ջ
            if (left.indexOf(c) > -1) {
                stack.push(c);
            } else {
                // ����������žͿ�ջ��Ԫ��
                // ���ջΪ�ջ���ջ��Ԫ�ز�ƥ��ͷ���false
                if (stack.isEmpty() || right.indexOf(c) != left.indexOf(stack.peek())) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }
}
