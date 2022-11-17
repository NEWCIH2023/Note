/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:28
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     * For example,
     * Given n = 3,
     * You should return the following matrix:
     *
     * [
     *  [ 1, 2, 3 ],
     *  [ 8, 9, 4 ],
     *  [ 7, 6, 5 ]
     * ]
     *
     * ��Ŀ����
     * ����һ������n������һ��n*n�ľ�����1-n^2�����ֽ���������䡣
     *
     * ����˼·
     * ���ü������ɷ�����ÿһ��λ�ü����Ӧ������
     * </pre>
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int layer;
        int k;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                layer = layer(i, j, n); // ��ǰ�������м���
                // n * n - layer * layer��Χ��ʹ�õ����һ�����֣�Ҳ�����ģ�
                // �������ڵĵ�ǰ��ʹ�õĵ�һ������
                k = n * n - (n - 2 * layer) * (n - 2 * layer) + 1;
                result[i][j] = k;

                // (n - 2 * layer - 1)���ĸ�(n - 2 * layer - 1)���ǣ�x��y���������ڲ������Ԫ�ظ���
                if (i == layer) { // ���һ���������ϱ߽����
                    result[i][j] = k + j - layer;
                } else if (j == n - layer - 1) { // ��������������ұ߽����
                    result[i][j] = k + (n - 2 * layer - 1) + i - layer;
                } else if (i == n - layer - 1) { // ��������������±߽����
                    result[i][j] = k + 3 * (n - 2 * layer - 1) - (j - layer);
                } else { // ���������������߽����
                    result[i][j] = k + 4 * (n - 2 * layer - 1) - (i - layer);
                }
            }
        }

        return result;
    }

    /**
     * ��һ��n*n�ľ����У����㣨x,y���������ж��ٲ㣬�����0��ʼ����
     *
     * @param x ������
     * @param y ������
     * @param n �����С
     * @return ������Ĳ���
     */
    public int layer(int x, int y, int n) {
        x = x < n - 1 - x ? x : n - 1 - x; // ��������������±߽���������
        y = y < n - 1 - y ? y : n - 1 - y; // ���������������ұ߽���������

        return x < y ? x : y; // ��С��ֵΪ�������Χ����
    }
}
