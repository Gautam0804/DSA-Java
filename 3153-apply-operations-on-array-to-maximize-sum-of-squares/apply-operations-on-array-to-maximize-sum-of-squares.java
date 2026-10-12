
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1_000_000_007;
        int[] bits = new int[32];

        for (int num : nums) {
            for (int b = 0; b < 32; b++) {
                if ((num & (1 << b)) != 0) {
                    bits[b]++;
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int b = 0; b < 32; b++) {
                if (bits[b] > 0) {
                    value |= (1L << b);
                    bits[b]--;
                }
            }

            ans = (ans + value * value) % MOD;
        }

        return (int) ans;
    }
}
