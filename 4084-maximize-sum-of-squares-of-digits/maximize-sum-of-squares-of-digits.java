class Solution {
    public String maxSumOfSquares(int num, int sum) {

        StringBuilder result = new StringBuilder();
        int remainingSum = sum;

        if(sum > num*9){
            return "";
        }

        for(int i=0;i<num;i++){
            int digit = Math.min(9, remainingSum);

            result.append(digit);
            remainingSum -= digit; 
        }
        return result.toString();

        
    }
}