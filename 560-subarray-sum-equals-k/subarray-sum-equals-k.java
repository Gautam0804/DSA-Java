import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int sum = 0;
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            int remaining = sum - k;

            if (map.containsKey(remaining)) {
                ans += map.get(remaining);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
}