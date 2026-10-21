class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // 0: already sorted
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) > s.charAt(i)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;

        // n == 2 and unsorted: only single-character substrings are allowed
        if (n == 2) return -1;

        char mn = 'z', mx = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < mn) mn = c;
            if (c > mx) mx = c;
        }

        // 1: first char is the min (sort s[1:]) or last char is the max (sort s[:n-1])
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) return 1;

        // 2: min appears in s[:n-1] (sort s[:n-1], then s[1:])
        //    or max appears in s[1:] (sort s[1:], then s[:n-1])
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == mn) return 2;
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == mx) return 2;
        }

        // 3: min only at the last index and max only at index 0
        return 3;
    }
}