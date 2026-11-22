class Solution {
    public boolean checkString(String s) {
        int n = s.length();
         boolean seenB = false;
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);

            if(ch == 'b'){
                seenB = true;
            }
            if(ch == 'a' && seenB){
                return false; 
            }
        }
        return true;
    }
}