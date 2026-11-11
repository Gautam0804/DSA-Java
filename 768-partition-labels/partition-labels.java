
import java.util.*;

class Solution {
    public List<Integer> partitionLabels(String s) {

        int[] lastIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();

        int start = 0;
        int end = 0;


        for (int i = 0; i < s.length(); i++) {

            end = Math.max(end,
                    lastIndex[s.charAt(i) - 'a']);

            if (i == end) {
                result.add(i - start + 1);
                start = i + 1;
            }
        }

        return result;
    }
}
