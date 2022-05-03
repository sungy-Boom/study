package calcu;

/**
 * @author sunguiyong
 * @date 2022/4/28 8:14 下午
 * https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 */
public class RobotMove {
    int[][] direct = new int[][]{
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count = dfs(i, j, m, n, k, 0);
            }
        }
        return count;
    }

    private int dfs(int i, int j, int m, int n, int k, int count) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        int sum = sumIndex(i, j);
        if (sum > k) {
            return 0;
        }

        count++;
        for (int[] ints : direct) {
            count += dfs(i + ints[0], j + ints[1], m, n, k, count);
        }
        return count;
    }

    private int sumIndex(int i, int j) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i = i / 10;
        }
        while (j != 0) {
            sum += j % 10;
            j = j / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new RobotMove().movingCount(2, 3, 1));
        System.out.println(new RobotMove().movingCount(3, 1, 0));
    }
}
