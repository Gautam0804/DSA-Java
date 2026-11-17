
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        List<Integer>[] divisors = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            divisors[i] = new ArrayList<>();
        }

        for (int d = 1; d < n; d++) {
            for (int len = d * 2; len <= n; len += d) {
                divisors[len].add(d);
            }
        }


        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int len = j - i + 1;
                int minCost = n;

                for (int d : divisors[len]) {
                    int changes = 0;

                    for (int offset = 0; offset < d; offset++) {
                        int left = i + offset;
                        int right = j - d + 1 + offset;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[i][j] = minCost;
            }
        }

        int INF = 1000000;
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int parts = 1; parts <= k; parts++) {

                for (int prev = 0; prev < i; prev++) {

                    if (i - prev < 2) {
                        continue;
                    }

                    if (dp[prev][parts - 1] == INF) {
                        continue;
                    }

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        dp[prev][parts - 1] + cost[prev][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
