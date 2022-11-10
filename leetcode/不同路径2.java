public class 不同路径2 {
    public static void main(String[] args) {
        不同路径2 s = new 不同路径2();
        System.out.println(s.uniquePathsWithObstacles(
                new int[] { { 0, 0, 0, 0 }, { 0, 1, 0, 1 }, { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 0, 0, 0 } }));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int result = 0;

        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] != 1) {
                    result++;
                }
            }
        }

        return result;
    }
}