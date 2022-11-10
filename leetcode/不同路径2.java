import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Element;

import javafx.scene.chart.Axis.TickMark;

public class 不同路径2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.uniquePathsWithObstacles(new int[][] {
                { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }
        }));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int total = uniquePaths(m, n);
        int badTotal = 0;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    badTotal += uniquePaths(i + 1, j + 1) * uniquePaths(m - i, n - j);
                }
            }
        }

        int result = total - badTotal;
        return result >= 0 ? result : 0;
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