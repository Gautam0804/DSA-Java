import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = queries.length;

        // Step 1: Sort logs by timestamp
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Step 2: Store queries with their original indices
        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        // Step 3: Sort queries by time
        Arrays.sort(q, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 4: HashMap to track server request frequencies
        HashMap<Integer, Integer> map = new HashMap<>();

        int[] ans = new int[m];

        int left = 0;
        int right = 0;

        // Step 5: Process queries in sorted order
        for (int i = 0; i < m; i++) {

            int time = q[i][0];
            int index = q[i][1];

            int start = time - x;

            // Add logs inside the current window
            while (right < logs.length && logs[right][1] <= time) {

                int server = logs[right][0];

                map.put(server, map.getOrDefault(server, 0) + 1);

                right++;
            }

            // Remove expired logs
            while (left < right && logs[left][1] < start) {

                int server = logs[left][0];

                map.put(server, map.get(server) - 1);

                if (map.get(server) == 0) {
                    map.remove(server);
                }

                left++;
            }

            // Servers with zero requests
            ans[index] = n - map.size();
        }

        return ans;
    }
}