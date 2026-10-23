class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int ans = -1;

        for(int i=0;i<n;i++){
            if(nums[i] != i){
                ans &= nums[i];
            }
            }
            if(ans == -1){
                return 0;
        }
        return ans;
        
    }
}