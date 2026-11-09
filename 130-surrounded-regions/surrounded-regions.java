class Solution {

    private void dfs(char[][] board, int r, int c) {

        if (r < 0 || r >= board.length ||
            c < 0 || c >= board[0].length ||
            board[r][c] != 'O') {
            return;
        }

        // Mark safe
        board[r][c] = '#';

        // Explore 4 directions
        dfs(board, r - 1, c);
        dfs(board, r + 1, c);
        dfs(board, r, c - 1);
        dfs(board, r, c + 1);
    }

    public void solve(char[][] board) {

        int n = board.length;
        int m = board[0].length;

        // First and last column
        for (int i = 0; i < n; i++) {

            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }

            if (board[i][m - 1] == 'O') {
                dfs(board, i, m - 1);
            }
        }

        // First and last row
        for (int j = 0; j < m; j++) {

            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }

            if (board[n - 1][j] == 'O') {
                dfs(board, n - 1, j);
            }
        }

        // Flip surrounded O and restore safe cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}