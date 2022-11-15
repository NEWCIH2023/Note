import java.util.ArrayList;
import java.util.List;

public class 分割回文串 {
    public static void main(String[] args) {
        分割回文串 s = new 分割回文串();
        System.out.println(s.partition("afa"));
    }

    private static List<List<String>> result;

    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        subLoop(s, 0, new ArrayList<>());
        return result;
    }

    public void subLoop(String s, int index, List<String> tempResult) {
        if (index == s.length()) {
            result.add(tempResult);
        }

        int base = index;
        while (index < s.length()) {
            if (isPar(s, base, index)) {
                List<String> temp = new ArrayList<>(tempResult);
                temp.add(s.substring(base, ++index));
                subLoop(s, index, temp);
            } else {
                index++;
            }
        }
    }

    public boolean isPar(String s, int begin, int end) {
        for (int index = 0; index <= (end - begin) / 2; index++) {
            if (s.charAt(index + begin) != s.charAt(end - index)) {
                return false;
            }
        }
        return true;
    }
}