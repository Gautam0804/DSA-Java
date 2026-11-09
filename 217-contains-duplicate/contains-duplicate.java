class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        int n = nums.length;

        for(int num:nums){
           // set.add(num);
            if(set.contains(num)){
            return true;
        }
        set.add(num);
        }
        return false;
    }
}