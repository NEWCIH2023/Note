public class 去除重复的字母 {
    public static void main(String[] args) {
        去除重复的字母 s = new 去除重复的字母();
        System.out.println(s.removeDuplicateLetters("cbacdcbc"));
    }

    public String removeDuplicateLetters(String s) {
        int[] flag = new int[128];
        boolean[] result = new boolean[s.length()];

        for (int i = 0; i < flag.length; i++) {
            flag[i] = -1;
        }

        for (int i = 0; i < s.length(); i++) {
            if (flag[s.charAt(i)] != -1) {
                for (char index = 'a'; index <= 'z'; index++) {
                    if (flag[index] < i) {
                        if (index > s.charAt(i)) {
                            result[i] = true;
                            break;
                        }
                    }
                }
            } else {
                flag[s.charAt(i)] = i;
            }
        }

        return s;
    }
}