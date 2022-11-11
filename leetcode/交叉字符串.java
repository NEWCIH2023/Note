import javax.lang.model.element.Element;

/**
 * @deprecated
 */
public class 交叉字符串 {
    public static void main(String[] args) {
        交叉字符串 s = new 交叉字符串();
        System.out.println(s.isInterleave(
                "s13",
                "s24",
                "s1324s"));
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        if ((s1 + s2).equals(s3) || (s2 + s1).equals(s3)) {
            return true;
        }

        return isInterleave(s1, s2, s3, 0, 0, 0);
    }

    public boolean isInterleave(String s1, String s2, String s3, int index1, int index2, int index3) {
        while (index1 < s1.length() || index2 < s2.length()) {
            if (index1 < s1.length() && index2 < s2.length() && s3.charAt(index3) == s2.charAt(index2)
                    && s2.charAt(index2) == s1.charAt(index1)) {
                return isInterleave(s1, s2, s3, index1 + 1, index2, index3 + 1)
                        || isInterleave(s1, s2, s3, index1, index2 + 1, index3 + 1);
            } else if (index1 < s1.length() && s3.charAt(index3) == s1.charAt(index1)) {
                index1++;
                index3++;
            } else if (index2 < s2.length() && s3.charAt(index3) == s2.charAt(index2)) {
                index2++;
                index3++;
            } else {
                return false;
            }
        }

        return true;
    }
}