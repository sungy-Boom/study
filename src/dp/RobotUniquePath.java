package dp;

/**
 * @author guiyong
 * @date 2022/5/17 21:46
 * https://leetcode.cn/problems/minimum-path-sum/
 */
public class RobotUniquePath {
    public static void main(String[] args) {
        System.out.println(new RobotUniquePath().uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
        System.out.println(new RobotUniquePath().uniquePathsWithObstacles(new int[][]{{0, 1}, {1, 0}}));
        System.out.println(new RobotUniquePath().uniquePathsWithObstacles(new int[][]{{0, 0}, {0, 1}}));
        System.out.println(new RobotUniquePath().uniquePathsWithObstacles(new int[][]{{0, 0}, {1, 1}, {0, 0}}));

    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int row = obstacleGrid.length, column = obstacleGrid[0].length;
        int[] columnArr = new int[column];
        columnArr[0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for (int[] anObstacleGrid : obstacleGrid) {
            for (int j = 0; j < column; j++) {
                if (anObstacleGrid[j] == 1) {
                    columnArr[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && anObstacleGrid[j - 1] == 0) {
                    columnArr[j] = columnArr[j - 1] + columnArr[j];
                }
            }
        }
        return columnArr[column - 1];
    }


    //boolean[][] visited = new boolean[obstacleGrid.length][obstacleGrid[0].length];
    //return dfs(obstacleGrid, 0, 0, visited);
    //超时
    public int dfs(int[][] arr, int row, int column, boolean[][] visited) {
        if (row > arr.length - 1 || column > arr[0].length - 1
                || arr[row][column] == 1 || visited[row][column]) {
            return 0;
        }
        if (row == arr.length - 1 && column == arr[0].length - 1) {
            return 1;
        }
        visited[row][column] = true;
        int res = dfs(arr, row + 1, column, visited) + dfs(arr, row, column + 1, visited);
        visited[row][column] = false;
        return res;
    }
}
