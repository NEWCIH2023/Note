/**
 * Author: ������
 * Date: 2015-08-21
 * Time: 19:36
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * ԭ��
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * For example,
     * Consider the following matrix:����Given target = 3, return true.
     *
     * [
     *  [1,   3,  5,  7],
     *  [10, 11, 16, 20],
     *  [23, 30, 34, 50]
     * ]
     *
     * ��Ŀ����
     * ����һ����ά����ʵ��һ���㷨�ھ�����ʵ�ֿ���������������k���ھ���������k
     * ��������������ʣ�ÿһ��ÿһ�ж����ź���ģ�ÿһ�еĵ�һ����������һ�е����һ������
     *
     * ����˼·
     * �ⷨһ�����ö���鿴���㷨�ҵ��������ڵ��У����ö�������㷨���������ڵ��С��ҵ��ͷ���true�����򷵻�false
     * </pre>
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int column = matrix[0].length;
        int low = 0;
        int high = row * column - 1;
        int mid;

        // �ҽ�����ڵ���
        while (low <= high) {
            mid = low + (high - low) / 2;
            int value = matrix[mid / column][mid % column];
            if (value == target) {
                return true;
            } else if (value < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }
}
