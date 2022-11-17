import java.util.LinkedList;
import java.util.List;

/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 16:23
 * Declaration: All Rights Reserved !!!
 */
public class Solution {

    private final static String[] S = {
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz",
    };


    /**
     * <pre>
     * ԭ��
     * Given a digit string, return all possible letter combinations that the number could represent.
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     *
     * Input:Digit string "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     *
     * Note: Although the above answer is in lexicographical order, your answer
     * could be in any order you want.
     *
     * ��Ŀ����
     * ����һ�����ִ������������������ַ���������ϣ����ֵ��ַ���ӳ������ͼ��ʾ��
     * ע�⣺ ��������Ľ�����ַ�˳�����еģ���������κ�˳�򷵻ؽ����
     *
     * ����˼·
     * ��һ�����鱣�����ֺ��ֵ�ӳ���ϵ���������ִ������룬�ҵ���Ӧ���ַ�����Ͻ����
     * </pre>
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<>();

        if (digits == null || digits.length() < 1) {
            return result;
        }

        StringBuilder builder = new StringBuilder();
        letterCombinations(digits, 0, builder, result);

        return result;
    }

    private void letterCombinations(String digits, int index, StringBuilder builder, List<String> result) {
        if (index == digits.length()) {
            result.add(builder.toString());
            return;
        }

        String t = S[digits.charAt(index) - '2'];

        for (int i = 0; i < t.length(); i++) {
            builder.append(t.charAt(i));
            letterCombinations(digits, index + 1, builder, result);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
