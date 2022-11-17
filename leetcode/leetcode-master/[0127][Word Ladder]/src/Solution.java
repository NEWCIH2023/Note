import java.util.*;

/**
 * Author: ������
 * Date: 2015-06-20
 * Time: 08:11
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * Given two words (beginWord and endWord), and a dictionary, find the length
     * of shortest transformation sequence from beginWord to endWord, such that:
     *
     *   - Only one letter can be changed at a time
     *   - Each intermediate word must exist in the dictionary
     *
     * For example,
     *
     * Given:
     * start = "hit"
     * end = "cog"
     * dict = ["hot","dot","dog","lot","log"]
     * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     *
     * Note:
     *   - Return 0 if there is no such transformation sequence.
     *   - All words have the same length.
     *   - All words contain only lowercase alphabetic characters.
     *
     * ��Ŀ���⣺
     * ���������ʣ�beginWord��endWord������һ���ֵ䣬���Ҵ�beginWordת�͵�endWord����̳��ȣ�
     *   - һ��ֻ��һ����ĸ���Ա��ı�
     *   - ÿ���м��ֱ�������ڴʵ���
     *
     * ע�⣺
     *   - ��������������ı任���У�����0��
     *   - ���е��ʾ�����ͬ�ĳ��ȡ�
     *   - ���е���ֻ����Сд��ĸ�ַ���
     *
     * ����˼·��
     * ����Ż�����
     * </pre>
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordDict = new HashSet<>(wordList);

        char[] chars;
        Deque<String> deque = new LinkedList<>();
        deque.addLast(beginWord);
        int result = 0;
        String s;
        String t;
        while (!deque.isEmpty()) {

            // ����ǰ��
            for (int k = deque.size(); k > 0; k--) {
                s = deque.removeFirst();

                if (s.equalsIgnoreCase(endWord)) {
                    return result + 1;
                }

                // �ҳ���ǰ���ÿ��Ԫ�ؾ���һ�α仯���Ƿ���ʣ���wordDict���ҵ���
                // ����ҵ��ͷŵ���һ��Ĵ�����
                chars = s.toCharArray();
                for (int i = 0; i < beginWord.length(); i++) {
                    for (char j = 'a'; j <= 'z'; j++) {
                        char temp = chars[i];
                        chars[i] = j;
                        t = new String(chars);
                        // һ�α任������ҵ����ʣ��ŵ���һ�㴦���У�������wordDict��ɾ����¼
                        if (wordDict.contains(t) && !t.equals(s)) {
                            deque.addLast(t);
                            wordDict.remove(t);
                        }
                        // ��ԭ
                        chars[i] = temp;
                    }
                }
            }


            result++;
        }

        return 0;
    }
}
