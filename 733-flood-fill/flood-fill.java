class Solution {

    public void dfs(int[][] image, int row, int col,
                    int originalColor, int newColor) {

        int n = image.length;
        int m = image[0].length;

        image[row][col] = newColor;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {

            int nr = row + dr[i];
            int nc = col + dc[i];

            if (nr >= 0 && nr < n &&
                nc >= 0 && nc < m &&
                image[nr][nc] == originalColor) {

                dfs(image, nr, nc, originalColor, newColor);
            }
        }
    }

    public int[][] floodFill(int[][] image,
                             int sr, int sc, int color) {

        int originalColor = image[sr][sc];

        if (originalColor == color) {
            return image;
        }

        dfs(image, sr, sc, originalColor, color);

        return image;
    }
}