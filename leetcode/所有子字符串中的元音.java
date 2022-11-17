public class 所有子字符串中的元音 {
    public static void main(String[] args) {

    }

    public long countVowels(String word) {
        long result = 0;

        for (int i = 0; i < word.length(); i++) {
            if ("aeiou".indexOf(word.charAt(i)) > -1) {
                result += countSub(word, i);
            }
        }

        return result;
    }

    public long countSub(String word, int index) {
        long left = index + 1;
        long right = word.length() - index;
        return right * left;
    }
}