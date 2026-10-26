
import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        backtrack(nums, 0, list, ans);

        return ans;
    }

    private void backtrack(int[] nums, int index,
                           List<Integer> list,
                           List<List<Integer>> ans) {

        ans.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);

            backtrack(nums, i + 1, list, ans);

            list.remove(list.size() - 1);
        }
    }
}
