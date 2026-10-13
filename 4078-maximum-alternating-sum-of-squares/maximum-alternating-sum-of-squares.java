class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        
        for(int i = 0; i < n; ++i) 
            nums[i] = Math.abs(nums[i]);

        Arrays.sort(nums,0,n);
        long ans = 0;
        int mid = n/2;
        for(int i=0;i<n;i++){
            long square = (long)nums[i]*nums[i];

            if(i < mid){
                ans -= (square);
            }else{
                ans += square;
            }
        }
        return ans;
        
    }
}