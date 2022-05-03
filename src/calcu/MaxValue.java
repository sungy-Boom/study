package calcu;

/**
 * @author sunguiyong
 * @date 2022/4/27 8:35 下午
 * https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/
 */
public class MaxValue {
    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;
        int[][] sum = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int maxj, maxi;
                if (j == 0) {
                    maxj = 0;
                } else {
                    maxj = sum[i][j - 1];
                }
                if (i == 0) {
                    maxi = 0;
                } else {
                    maxi = sum[i - 1][j];
                }
                sum[i][j] = Math.max(maxi, maxj) + grid[i][j];
            }
        }
        return sum[row - 1][column - 1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new MaxValue().maxValue(grid));
    }
}
