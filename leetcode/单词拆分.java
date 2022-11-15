import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 单词拆分 {
    public static void main(String[] args) {
        单词拆分 s = new 单词拆分();
        System.out.println(s.wordBreak("saaa", Arrays.asList("aa", "s", "s"), true));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        return subLoop(s, new HashSet(wordDict), 0);
    }

    public boolean wordBreak(String s, List<String> wordDict, boolean mark) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public boolean subLoop(String s, Set<String> wordDict, int index) {
        if (index == s.length()) {
            return true;
        }

        int base = index;
        while (base < s.length()) {
            if (wordDict.contains(s.substring(index, ++base))) {
                if (subLoop(s, wordDict, base))
                    return true;
            }
        }

        return false;
    }
}