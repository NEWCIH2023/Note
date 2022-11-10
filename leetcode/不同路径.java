import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Element;

import javafx.scene.chart.Axis.TickMark;

public class 不同路径 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public int uniquePaths(int m, int n) {
        int min = Math.min(m, n);
        if ((m + n - 2) > 2 * (min - 1))
            return (int) C(m + n - 2, min - 1);
        else
            return (int) C(m + n - 2, m + n - 2 - min + 1);
    }

    public long C(int bottom, int top) {
        long a = 1, b = 1;
        int tIndex = 0;

        for (int i = top - 1; i >= 0; i--) {
            a *= (bottom - i);

            tIndex++;
            if (a % tIndex == 0) {
                a /= tIndex;
            } else {
                b *= tIndex;
            }

            if (a % b == 0) {
                a /= b;
                b = 1;
            }
        }

        return a / b;
    }
}