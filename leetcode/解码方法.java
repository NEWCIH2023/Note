public class 解码方法 {
    public static void main(String[] args) {
        解码方法 s = new 解码方法();
        System.out.println(s.numDecodings("221122112232112312342141292919041294141"));
        // System.out.println(s.numDecodings("12"));
    }

    public int numDecodings(String s) {
        return subLoop(s, 0, 1);
    }

    public int subLoop(String s, int index, int count) {
        if (index == s.length()) {
            return count;
        }

        int result = 0;
        if (s.charAt(index) != '0') {
            result = subLoop(s, index + 1, count);

            if (index + 1 < s.length() && s.charAt(index) < '3' && s.charAt(index + 1) < '7') {
                result += subLoop(s, index + 2, count + 1);
            }
        } else {
            result = subLoop(s, index + 1, count);
        }

        return result;
    }
}