import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: ������
 * Date: 2015-06-17
 * Time: 20:46
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * Given a string, find the length of the longest substring without repeating characters.
     * For example, the longest substring without repeating letters for "abcabcbb" is "abc",
     * which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
     *
     * ��Ŀ���⣺
     * ����һ���ַ��������ַ��е������ظ��Ӵ�
     *
     * ����˼·��
     * ��start��¼��������Ŀ�ʼλ��
     * �����ַ���������ǰ�ַ��ӿ�ʼλ��start��ʼ�Ѿ����ֹ���ʱ���Ӵ���ʼλ��+1���������map�е�hashֵΪ��ǰλ�á�
     *
     * ���Դ������е�UTF-8�ַ�
     * </pre>
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // �ַ������벻�Ϸ�
        if (s == null) {
            return 0;
        }

        // ��ǰ����Ŀ�ʼλ��
        int start = 0;
        // ������
        int result = 0;
        // ���ʱ�ǣ���¼����һ�η��ʵ��ַ���λ��
        Map<Character, Integer> map = new HashMap<>(s.length());

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // ����ַ��Ѿ����ֹ�(�ڱ�ǿ�λ������)�������±��start
            if (map.containsKey(ch) && map.get(ch) >= start) {
                start = map.get(ch) + 1;
            }
            // ���û�г��ֹ��������ķ��ظ��Ӵ��ĳ���
            else {
                result = Math.max(result, i - start + 1);
            }

            // ���·��ʼ�¼
            map.put(ch, i);
        }
        return result;
    }

    /**
     * ֻ����ASCII�ַ�
     */
    public int lengthOfLongestSubstring2(String s) {
        // �ַ������벻�Ϸ�
        if (s == null) {
            return 0;
        }

        // ����ַ��Ƿ���ֹ������Ҽ�¼�ǵ�����һ�η��ʵ�Ԫ�ص�λ��
        int[] appear = new int[256];
        // ��ʼ��Ϊ-1
        Arrays.fill(appear, -1);
        // ��ǰ����Ŀ�ʼλ��
        int start = 0;
        // ������
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            // ����ַ��Ѿ����ֹ�(�ڱ�ǿ�λ��)�������±��start
            if (appear[s.charAt(i)] >= start) {
                start = i + 1;
            }
            // ���û�г��ֹ��������ķ��ظ��Ӵ��ĳ���
            else {
                result = Math.max(result, i - start + 1);
            }
            // ��ǵ�i���ַ��Ѿ������ʹ��������ǵ�i��λ�ã�
            appear[s.charAt(i)] = i;
        }

        return result;
    }
}
