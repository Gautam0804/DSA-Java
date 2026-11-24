class Solution {

    public int maxLength(int[] nums) {

        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {

            long product = 1;
            long lcm = 1;
            int gcd = 0;

            for (int j = i; j < n; j++) {

                int x = nums[j];

                // Update product
                product *= x;

                // Update GCD
                gcd = gcd(gcd, x);

                // Update LCM
                lcm = (lcm / gcd((int) lcm, x)) * x;

                // Check condition
                if (product == (long) gcd * lcm) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}