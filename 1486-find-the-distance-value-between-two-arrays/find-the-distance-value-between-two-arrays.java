import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

        Arrays.sort(arr2);

        int ans = 0;

        for (int x : arr1) {

            int low = x - d;
            int high = x + d;

            int left = 0;
            int right = arr2.length - 1;
            int index = arr2.length;

            // Find first element >= low
            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (arr2[mid] >= low) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // Check whether an element exists in [low, high]
            if (index == arr2.length || arr2[index] > high) {
                ans++;
            }
        }

        return ans;
    }
}