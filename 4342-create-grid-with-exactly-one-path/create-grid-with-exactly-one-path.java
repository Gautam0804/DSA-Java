import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {

        // Step 1: Create grid
        char[][] grid = new char[m][n];

        // Step 2: Fill every cell with '#'
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        // Step 3: Open the first row
        Arrays.fill(grid[0], '.');

        // Step 4: Open the last column
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        // Step 5: Convert char[][] to String[]
        String[] ans = new String[m];

        for (int i = 0; i < m; i++) {
            ans[i] = new String(grid[i]);
        }

        return ans;
    }
}