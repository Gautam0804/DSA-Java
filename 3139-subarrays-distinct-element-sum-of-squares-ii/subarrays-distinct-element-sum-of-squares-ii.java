class Solution {
    static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        int size = 4 * n;

        long[] sum = new long[size];
        long[] sumSq = new long[size];
        long[] lazy = new long[size];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            int left = last[x] + 1;
            update(1, 0, n - 1, left, i, sum, sumSq, lazy);

            ans = (ans + sumSq[1]) % MOD;
            last[x] = i;
        }

        return (int) ans;
    }

    private void update(int node, int start, int end,
                        int l, int r,
                        long[] sum, long[] sumSq, long[] lazy) {
        if (l > end || r < start) {
            return;
        }

        if (l <= start && end <= r) {
            long len = end - start + 1L;
            sumSq[node] = (sumSq[node] + 2L * sum[node] + len) % MOD;
            sum[node] = (sum[node] + len) % MOD;
            lazy[node]++;
            return;
        }

        push(node, start, end, sum, sumSq, lazy);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, l, r, sum, sumSq, lazy);
        update(node * 2 + 1, mid + 1, end, l, r,
               sum, sumSq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }

    private void push(int node, int start, int end,
                      long[] sum, long[] sumSq, long[] lazy) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        long value = lazy[node];

        apply(node * 2, start, mid, value, sum, sumSq, lazy);
        apply(node * 2 + 1, mid + 1, end, value, sum, sumSq, lazy);

        lazy[node] = 0;
    }

    private void apply(int node, int start, int end, long value,
                       long[] sum, long[] sumSq, long[] lazy) {
        long len = end - start + 1L;

        sumSq[node] = (sumSq[node] + 2L * value * sum[node]
                       + len * value % MOD * value) % MOD;

        sum[node] = (sum[node] + len * value) % MOD;
        lazy[node] += value;
    }
}