import java.util.*;

class Solution {

    private int digitSum(int n) {
        int sum = 0;

        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;

        // Store original indices along with values
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        // Sort by digit sum, then by number
        Arrays.sort(arr, (a, b) -> {
            int sumA = digitSum(a[0]);
            int sumB = digitSum(b[0]);

            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }

            return Integer.compare(a[0], b[0]);
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {

            // Already visited or already in correct position
            if (visited[i] || arr[i][1] == i) {
                continue;
            }

            int cycleSize = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;

                // Move to the original index of this element
                j = arr[j][1];

                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += cycleSize - 1;
            }
        }

        return swaps;
    }
}