class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int prev = 0;
            boolean hasPrev = false;

            for (int start = 0; start < n && valid; start += k) {
                int descents = 0;
                int rotation = start;

                for (int j = start; j < start + k; j++) {
                    int next = (j + 1 == start + k) ? start : j + 1;

                    if (nums[j] > nums[next]) {
                        descents++;
                        rotation = next;
                    }
                }

                if (descents > 1) {
                    valid = false;
                    break;
                }

                int first = nums[rotation];
                int last = nums[(rotation - start + k - 1) % k + start];

                if (hasPrev && prev > first) {
                    valid = false;
                    break;
                }

                prev = last;
                hasPrev = true;
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}