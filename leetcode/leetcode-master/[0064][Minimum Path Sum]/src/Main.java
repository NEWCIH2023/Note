/**
 * Author: ������
 * Date: 2015-06-22
 * Time: 16:29
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid = {{5, 0, 1, 1, 2, 1, 0, 1, 3, 6, 3, 0, 7, 3, 3, 3, 1}, {1, 4, 1, 8, 5, 5, 5, 6, 8, 7, 0, 4, 3, 9, 9, 6, 0}, {2, 8, 3, 3, 1, 6, 1, 4, 9, 0, 9, 2, 3, 3, 3, 8, 4}, {3, 5, 1, 9, 3, 0, 8, 3, 4, 3, 4, 6, 9, 6, 8, 9, 9}, {3, 0, 7, 4, 6, 6, 4, 6, 8, 8, 9, 3, 8, 3, 9, 3, 4}, {8, 8, 6, 8, 3, 3, 1, 7, 9, 3, 3, 9, 2, 4, 3, 5, 1}, {7, 1, 0, 4, 7, 8, 4, 6, 4, 2, 1, 3, 7, 8, 3, 5, 4}, {3, 0, 9, 6, 7, 8, 9, 2, 0, 4, 6, 3, 9, 7, 2, 0, 7}, {8, 0, 8, 2, 6, 4, 4, 0, 9, 3, 8, 4, 0, 4, 7, 0, 4}, {3, 7, 4, 5, 9, 4, 9, 7, 9, 8, 7, 4, 0, 4, 2, 0, 4}, {5, 9, 0, 1, 9, 1, 5, 9, 5, 5, 3, 4, 6, 9, 8, 5, 6}, {5, 7, 2, 4, 4, 4, 2, 1, 8, 4, 8, 0, 5, 4, 7, 4, 7}, {9, 5, 8, 6, 4, 4, 3, 9, 8, 1, 1, 8, 7, 7, 3, 6, 9}, {7, 2, 3, 1, 6, 3, 6, 6, 6, 3, 2, 3, 9, 9, 4, 4, 8}};

//        System.out.println(solution.minPathSum(grid));

        int[][] grid2 = {{4, 1, 7, 8, 4, 9, 2, 9, 9, 0, 2, 9, 4, 0, 2}, {2, 2, 7, 7, 3, 9, 0, 8, 4, 7, 8, 2, 5, 5, 5}, {2, 9, 8, 1, 7, 2, 0, 2, 1, 9, 4, 5, 0, 1, 5}, {4, 3, 0, 1, 1, 5, 2, 3, 5, 8, 3, 3, 0, 0, 8}, {7, 2, 9, 5, 3, 7, 7, 6, 1, 1, 7, 5, 6, 8, 6}, {4, 4, 2, 6, 6, 3, 1, 0, 8, 7, 9, 1, 0, 1, 3}, {1, 9, 5, 0, 4, 1, 9, 4, 7, 0, 5, 6, 8, 3, 6}, {4, 7, 1, 6, 3, 7, 1, 7, 9, 0, 6, 8, 3, 8, 0}, {7, 9, 1, 4, 2, 5, 7, 1, 1, 6, 4, 6, 3, 2, 2}, {9, 8, 9, 0, 7, 5, 9, 8, 4, 9, 0, 0, 9, 4, 8}, {1, 3, 0, 2, 7, 2, 0, 5, 5, 1, 1, 9, 0, 4, 3}, {2, 4, 4, 3, 6, 1, 0, 6, 1, 4, 5, 2, 4, 4, 8}, {5, 8, 1, 5, 0, 0, 9, 0, 5, 4, 4, 9, 6, 4, 5}};

//        System.out.println(solution.minPathSum(grid2));


        int[][] grid3 = {{0, 7, 7, 8, 1, 2, 4, 3, 0, 0, 5, 9, 8, 3, 6, 5, 1, 0}, {2, 1, 1, 0, 8, 1, 3, 3, 9, 9, 5, 8, 7, 5, 7, 5, 5, 8}, {9, 2, 3, 1, 2, 8, 1, 2, 3, 7, 9, 7, 9, 3, 0, 0, 3, 8}, {3, 9, 3, 4, 8, 1, 2, 6, 8, 9, 3, 4, 9, 4, 8, 3, 6, 2}, {3, 7, 4, 7, 6, 5, 6, 5, 8, 6, 7, 3, 6, 2, 2, 9, 9, 3}, {2, 3, 1, 1, 5, 4, 7, 4, 0, 7, 7, 6, 9, 1, 5, 5, 0, 3}, {0, 8, 8, 8, 4, 7, 1, 0, 2, 6, 1, 1, 1, 6, 4, 2, 7, 9}, {8, 6, 6, 8, 3, 3, 5, 4, 6, 2, 9, 8, 6, 9, 6, 6, 9, 2}, {6, 2, 2, 8, 0, 6, 1, 1, 4, 5, 3, 1, 7, 3, 9, 3, 2, 2}, {8, 9, 8, 5, 3, 7, 5, 9, 8, 2, 8, 7, 4, 4, 1, 9, 2, 2}, {7, 3, 3, 1, 0, 9, 4, 7, 2, 3, 2, 6, 7, 1, 7, 7, 8, 1}, {4, 3, 2, 2, 7, 0, 1, 4, 4, 4, 3, 8, 6, 2, 1, 2, 5, 4}, {1, 9, 3, 5, 4, 6, 4, 3, 7, 1, 0, 7, 2, 4, 0, 7, 8, 0}, {7, 1, 4, 2, 5, 9, 0, 4, 1, 4, 6, 6, 8, 9, 7, 1, 4, 3}, {9, 8, 6, 8, 2, 6, 5, 6, 2, 8, 3, 2, 8, 1, 5, 4, 5, 2}, {3, 7, 8, 6, 3, 4, 2, 3, 5, 1, 7, 2, 4, 6, 0, 2, 5, 4}, {8, 2, 1, 2, 2, 6, 6, 0, 7, 3, 6, 4, 5, 9, 4, 4, 5, 7}};
        System.out.println(solution.minPathSum(grid3));

    }
}
