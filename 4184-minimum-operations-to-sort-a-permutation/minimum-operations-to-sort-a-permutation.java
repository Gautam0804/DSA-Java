class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int z = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) { z = i; break; }
        }

        boolean asc = true, desc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(z + i) % n] != i) asc = false;
            if (nums[((z - i) % n + n) % n] != i) desc = false;
        }

        int ans = Integer.MAX_VALUE;
        if (asc) {
            ans = Math.min(ans, z);                       // rotate left z times
            if (z > 0) ans = Math.min(ans, n - z + 2);    // reverse, left (n-z), reverse
        }
        if (desc) {
            ans = Math.min(ans, 1 + (n - 1 - z));         // reverse, then rotate left
            ans = Math.min(ans, (z + 1) % n + 1);         // rotate left, then reverse
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}