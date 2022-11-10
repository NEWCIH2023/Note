public class 最小路径和 {
    public static void main(String[] args) {
        最小路径和 s = new 最小路径和();
        System.out.println(s.minPathSum(new int[][] {
                { 3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3 },
                { 0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2 },
                { 8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9 },
                { 1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7 },
                { 8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8 },
                { 4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9 },
                { 6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1 },
                { 8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3 },
                { 9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3 },
                { 0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8 },
                { 4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3 },
                { 2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3 },
                { 0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3 },
                { 0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5 },
                { 6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2 },
                { 7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0 },
                { 3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0 },
                { 5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7 }
        }, true));
    }

    public int minPathSum(int[][] grid, boolean mark) {
        int x = grid[0].length, y = grid.length, temp = 0;

        for (int yIndex = 0; yIndex < grid.length; yIndex++) {
            for (int xIndex = 0; xIndex < grid[0].length; xIndex++) {
                if (xIndex == 0 && yIndex == 0) {
                    temp = 0;
                } else if (xIndex == 0) {
                    temp = grid[yIndex - 1][xIndex];
                } else if (yIndex == 0) {
                    temp = grid[yIndex][xIndex - 1];
                } else {
                    temp = Math.min(grid[yIndex][xIndex - 1], grid[yIndex - 1][xIndex]);
                }

                grid[yIndex][xIndex] += temp;
                temp = 0;
            }
        }

        return grid[y - 1][x - 1];
    }

    public int minPathSum(int[][] grid) {
        return subMin(grid, 0, 0, 0);
    }

    public int subMin(int[][] grid, int x, int y, int steps) {
        if (x < grid[0].length && y < grid.length) {
            steps += grid[y][x];
        }

        if (x == grid[0].length - 1 && y == grid.length - 1) {
            return steps;
        } else if (x == grid[0].length || y == grid.length) {
            return Integer.MAX_VALUE;
        }

        return Math.min(subMin(grid, x + 1, y, steps), subMin(grid, x, y + 1, steps));
    }
}