class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[] ans = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<n;i++){
            int need = target-nums[i];

            if(map.containsKey(need)){
                return new int[]{map.get(need), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}