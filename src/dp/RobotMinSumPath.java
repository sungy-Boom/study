package dp;

/**
 * @author guiyong
 * @date 2022/5/17 22:35
 * https://leetcode.cn/problems/minimum-path-sum/
 */
public class RobotMinSumPath {

    public static void main(String[] args) {
        System.out.println(new RobotMinSumPath().minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length, column = grid[0].length;
        int[][] sumArr = new int[row][column];
        sumArr[0][0] = grid[0][0];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    sumArr[i][j] = sumArr[i][j - 1];
                } else if (j == 0) {
                    sumArr[i][j] = sumArr[i - 1][j];
                } else {
                    sumArr[i][j] = Math.min(sumArr[i][j - 1], sumArr[i - 1][j]);
                }
                sumArr[i][j] += grid[i][j];
            }
        }
        return sumArr[row - 1][column - 1];
    }
}
