import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 电话号码的字母组合 {
    public static void main(String[] args) {
        电话号码的字母组合 s = new 电话号码的字母组合();
        System.out.println(s.letterCombinations("22"));
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits.length() > 0) {
            result.add("");
        }

        for (int i = 0; i < digits.length(); i++) {
            switch (digits.charAt(i)) {
                case '2':
                    result = result.stream().flatMap(s -> {
                        return Stream.of(s + "a", s + "b", s + "c");
                    }).collect(Collectors.toList());
                    break;
                case '3':
                    result = result.stream().flatMap(s -> {
                        return Stream.of(s + "d", s + "e", s + "f");
                    }).collect(Collectors.toList());
                    break;

                case '4':
                    result = result.stream().flatMap(s -> {
                        return Stream.of(s + "g", s + "h", s + "i");
                    }).collect(Collectors.toList());
                    break;
                case '5':
                    result = result.stream().flatMap(s -> {
                        return Stream.of(s + "j", s + "k", s + "l");
                    }).collect(Collectors.toList());
                    break;
                case '6':
                    result = result.stream().flatMap(s -> {
                        return Stream.of(s + "m", s + "n", s + "o");
                    }).collect(Collectors.toList());
                    break;
                case '7':
                    result = result.stream().flatMap(s -> {
                        return Stream.of(s + "p", s + "q", s + "r", s + "s");
                    }).collect(Collectors.toList());
                    break;
                case '8':
                    result = result.stream().flatMap(s -> {
                        return Stream.of(s + "t", s + "u", s + "v");
                    }).collect(Collectors.toList());
                    break;
                case '9':
                    result = result.stream().flatMap(s -> {
                        return Stream.of(s + "w", s + "x", s + "y", s + "z");
                    }).collect(Collectors.toList());
                    break;
                default:
            }
        }

        return result;
    }
}