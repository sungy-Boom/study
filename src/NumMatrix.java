/**
 * @author guiyong
 * @date 2022/3/27 09:26
 * https://leetcode-cn.com/problems/O4NDxx/
 */
public class NumMatrix {

    int[][] sum;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            sum = null;
            return;
        }

        sum = new int[matrix.length][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= matrix[0].length; j++) {
                if (j == 0) {
                    sum[i][j] = 0;
                } else {
                    sum[i][j] = sum[i][j - 1] + matrix[i][j - 1];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (sum == null) {
            return 0;
        }
        int total = 0;
        for (int i = row1; i <= row2; i++) {
            total += sum[i][col2 + 1] - sum[i][col1];
        }
        return total;
    }
}
