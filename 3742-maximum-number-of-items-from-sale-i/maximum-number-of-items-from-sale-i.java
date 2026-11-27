class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        int[] freeCnt = new int[n];
        int cheapest = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            cheapest = Math.min(cheapest, items[i][1]);

            for (int j = 0; j < n; j++) {

                if (i != j && items[j][0] % items[i][0] == 0) {
                    freeCnt[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {

            int price = items[i][1];
            int profit = freeCnt[i] + 1;

            for (int j = budget; j >= price; j--) {

                dp[j] = Math.max(
                    dp[j],
                    dp[j - price] + profit
                );
            }
        }

        int ans = 0;

        for (int i = 0; i <= budget; i++) {

            int remaining = budget - i;

            int total = dp[i] + remaining / cheapest;

            ans = Math.max(ans, total);
        }

        return ans;
    }
}