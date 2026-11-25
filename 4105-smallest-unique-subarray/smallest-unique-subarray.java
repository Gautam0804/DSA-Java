import java.util.*;

class Solution {
    private static final long M1 = 1_000_000_007L;
    private static final long M2 = 998_244_353L;

    private int n;
    private long[] h1, h2, p1, p2;

    public int smallestUniqueSubarray(int[] nums) {
        n = nums.length;
        Random rnd = new Random();
        long b1 = 100_003 + rnd.nextInt(900_000_000);
        long b2 = 100_003 + rnd.nextInt(900_000_000);

        h1 = new long[n + 1];
        h2 = new long[n + 1];
        p1 = new long[n + 1];
        p2 = new long[n + 1];
        p1[0] = 1;
        p2[0] = 1;
        for (int i = 0; i < n; i++) {
            h1[i + 1] = (h1[i] * b1 + nums[i]) % M1;
            h2[i + 1] = (h2[i] * b2 + nums[i]) % M2;
            p1[i + 1] = p1[i] * b1 % M1;
            p2[i + 1] = p2[i] * b2 % M2;
        }

        // Uniqueness is monotonic in length, so binary search.
        int lo = 1, hi = n;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (hasUnique(mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private boolean hasUnique(int len) {
        int m = n - len + 1;
        long[] keys = new long[m];
        for (int i = 0; i < m; i++) {
            long a = ((h1[i + len] - h1[i] * p1[len]) % M1 + M1) % M1;
            long b = ((h2[i + len] - h2[i] * p2[len]) % M2 + M2) % M2;
            keys[i] = a * M2 + b; // combined 2-modulus hash, fits in a long
        }
        Arrays.sort(keys);
        for (int i = 0; i < m; i++) {
            boolean diffPrev = (i == 0) || keys[i] != keys[i - 1];
            boolean diffNext = (i == m - 1) || keys[i] != keys[i + 1];
            if (diffPrev && diffNext) return true;
        }
        return false;
    }
}