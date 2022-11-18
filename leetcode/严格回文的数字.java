public class 严格回文的数字 {
    public static void main(String[] args) {
        严格回文的数字 s = new 严格回文的数字();
        System.out.println(s.isStrictlyPalindromic(234));
    }

    public boolean isStrictlyPalindromic(int n) {
        String temp = "";

        for (int i = 2; i <= n - 2; i++) {
            temp = Integer.toString(n, i);
            if (!isPalindromic(temp)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindromic(String a) {
        if (a.length() == 1) {
            return true;
        }

        for (int i = 0; i < a.length() / 2; i++) {
            if (a.charAt(i) != a.charAt(a.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }
}