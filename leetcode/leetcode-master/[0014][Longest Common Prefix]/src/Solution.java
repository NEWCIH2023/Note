/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 16:19
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     * ��Ŀ����
     * дһ�������ҳ�һ���ִ��������е���Ĺ���ǰ׺��
     *
     * ����˼·
     * ��һ�����ҳ�������С���ַ�����Ȼ������ַ������������ַ�������ҳ���̵����ǰ׺��
     * </pre>
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return null;
        }

        if (strs.length == 0) {
            return "";
        }

        // ��¼��̵��ַ����ĳ���
        int min = Integer.MAX_VALUE;

        // �Ҷ��ַ����ĳ���
        String result = "";
        for (String str : strs) {

            if (str == null || str.length() == 0) {
                return "";
            }

            if (min > str.length()) {
                min = str.length();
                result = str;
            }
        }

        for (String s : strs) {
            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) != s.charAt(i)) {
                    result = result.substring(0, i);
                }
            }
        }

        return result;
    }
}
