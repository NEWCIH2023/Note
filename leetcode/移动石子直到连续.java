import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 移动石子直到连续 {
    public static void main(String[] args) {
        移动石子直到连续 s = new 移动石子直到连续();
        System.out.println(s.numMovesStones(4, 5, 6));
    }

    public int[] numMovesStones(int a, int b, int c) {
        List<Integer> temp = Arrays.asList(a, b, c);
        temp = temp.stream().sorted().collect(Collectors.toList());

        int leftSpace = Math.abs(temp.get(0) - temp.get(1)) - 1;
        int rightSpace = Math.abs(temp.get(1) - temp.get(2)) - 1;

        if (leftSpace == 0 && rightSpace == 0) {
            return new int[] { 0, 0 };
        } else if (leftSpace <= 1 || rightSpace <= 1) {
            return new int[] { 1, leftSpace + rightSpace };
        } else {
            return new int[] { 2, leftSpace + rightSpace };
        }
    }
}
