
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        // dpRight: ways entering a cell from left
        // dpDown: ways entering a cell from top
        long[][] dpRight = new long[m][n];
        long[][] dpDown = new long[m][n];

        // Starting position
        dpRight[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                long right = dpRight[i][j];
                long down = dpDown[i][j];

                if (grid[i][j] == 0) {

                    // Empty cell: move right
                    if (j + 1 < n) {
                        dpRight[i][j + 1] =
                            (dpRight[i][j + 1] + right + down) % MOD;
                    }

                    // Empty cell: move down
                    if (i + 1 < m) {
                        dpDown[i + 1][j] =
                            (dpDown[i + 1][j] + right + down) % MOD;
                    }

                } else {

                    // Mirror: right becomes down
                    if (i + 1 < m) {
                        dpDown[i + 1][j] =
                            (dpDown[i + 1][j] + right) % MOD;
                    }

                    // Mirror: down becomes right
                    if (j + 1 < n) {
                        dpRight[i][j + 1] =
                            (dpRight[i][j + 1] + down) % MOD;
                    }
                }
            }
        }

        return (int)((dpRight[m - 1][n - 1]
                    + dpDown[m - 1][n - 1]) % MOD);
    }
}
