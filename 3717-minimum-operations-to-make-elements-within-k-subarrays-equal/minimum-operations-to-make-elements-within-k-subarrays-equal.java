
import java.util.*;

class Solution {

    class DualSet {
        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();

        int lowerSize = 0;
        int upperSize = 0;

        long lowerSum = 0;
        long upperSum = 0;

        void add(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        void remove(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);

            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }

        void insert(int val) {
            if (lower.isEmpty() || val <= lower.lastKey()) {
                add(lower, val);
                lowerSize++;
                lowerSum += val;
            } else {
                add(upper, val);
                upperSize++;
                upperSum += val;
            }

            balance();
        }

        void erase(int val) {
            if (lower.containsKey(val)) {
                remove(lower, val);
                lowerSize--;
                lowerSum -= val;
            } else {
                remove(upper, val);
                upperSize--;
                upperSum -= val;
            }

            balance();
        }

        void balance() {
            while (lowerSize > upperSize + 1) {
                int val = lower.lastKey();

                remove(lower, val);
                lowerSize--;
                lowerSum -= val;

                add(upper, val);
                upperSize++;
                upperSum += val;
            }

            while (lowerSize < upperSize) {
                int val = upper.firstKey();

                remove(upper, val);
                upperSize--;
                upperSum -= val;

                add(lower, val);
                lowerSize++;
                lowerSum += val;
            }
        }

        long getCost() {
            long median = lower.lastKey();

            long leftCost = median * lowerSize - lowerSum;
            long rightCost = upperSum - median * upperSize;

            return leftCost + rightCost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;


        long[] cost = new long[n - x + 1];

        DualSet ds = new DualSet();

        for (int i = 0; i < x; i++) {
            ds.insert(nums[i]);
        }

        cost[0] = ds.getCost();

        for (int i = x; i < n; i++) {


            ds.erase(nums[i - x]);

            ds.insert(nums[i]);

            cost[i - x + 1] = ds.getCost();
        }


        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                dp[i][j] = dp[i - 1][j];

                if (i >= x && dp[i - x][j - 1] != INF) {

                    long pick = dp[i - x][j - 1]
                                + cost[i - x];

                    dp[i][j] = Math.min(dp[i][j], pick);
                }
            }
        }

        return dp[n][k];
    }
}
