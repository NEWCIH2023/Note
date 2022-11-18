import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class 去除重复的字母 {
    public static void main(String[] args) {
        去除重复的字母 s = new 去除重复的字母();
        System.out.println(s.removeDuplicateLetters("cbacdcbc"));
    }

    public String removeDuplicateLetters(String s) {
        char currentChar = s.charAt(0);
        int[] allCharR = new int[128];

        for (int i = 0; i < allCharR.length; i++) {
            allCharR[i] = -1;
        }

        for (int i = 0; i < s.length(); i++) {
            currentChar = s.charAt(i);
            if (allCharR[currentChar] != -1) {
                char temp = s.charAt(allCharR[currentChar] + 1);
                if (temp < currentChar) {
                    allCharR[currentChar] = i;
                }
            } else {
                allCharR[currentChar] = i;
            }
        }

        return Arrays.stream(allCharR)
                .sorted()
                .filter(i -> i != -1)
                .mapToObj(i -> s.charAt(i) + "")
                .collect(Collectors.joining());
    }
}