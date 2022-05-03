package calcu;

/**
 * @author sunguiyong
 * @date 2022/4/28 5:24 下午
 * https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 */
public class MatrixExists {

    int[][] direct = new int[][]{
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public boolean exist(char[][] board, String word) {
        if (word == null || "".equals(word) || board == null || board.length == 0) {
            return false;
        }
        int row = board.length, column = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int i1 = 0; i1 < column; i1++) {
                boolean res = dfs(row, column, i, i1, board, word, 0);
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int row, int column, int indexI, int indexJ, char[][] board, String word, int wordIndex) {
        if (indexI > row - 1 || indexI < 0 || indexJ > column - 1 || indexJ < 0 || board[indexI][indexJ] != word.charAt(wordIndex)) {
            return false;
        }
        if (wordIndex == word.length() - 1) {
            return true;
        }

        board[indexI][indexJ] = '\0';
        for (int[] ints : direct) {
            if (dfs(row, column, indexI + ints[0], indexJ + ints[1], board, word, wordIndex + 1)) {
                return true;
            }
        }
        board[indexI][indexJ] = word.charAt(wordIndex);

        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        board = new char[][]{
                {'a', 'a'}
        };
        String word = "aaa";
//        word = "ABCCED";
        System.out.println(new MatrixExists().exist(board, word));
    }
}
