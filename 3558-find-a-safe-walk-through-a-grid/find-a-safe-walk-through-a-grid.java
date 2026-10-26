
import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );

        dist[0][0] = grid.get(0).get(0);
        pq.offer(new int[]{0, 0, dist[0][0]});

        int[] dirs = {-1, 0, 1, 0, -1};

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int cost = cur[2];

            if (cost != dist[r][c]) {
                continue;
            }

            if (r == m - 1 && c == n - 1) {
                return cost < health;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dirs[d];
                int nc = c + dirs[d + 1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                int newCost = cost + grid.get(nr).get(nc);

                if (newCost < dist[nr][nc]) {
                    dist[nr][nc] = newCost;
                    pq.offer(new int[]{nr, nc, newCost});
                }
            }
        }

        return false;
    }
}
