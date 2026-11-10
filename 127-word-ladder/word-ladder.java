import java.util.*;

class Solution {

    public int ladderLength(
        String beginWord,
        String endWord,
        List<String> wordList
    ) {

        // Step 1: Create HashSet
        Set<String> set = new HashSet<>(wordList);

        if (!set.contains(endWord)) {
            return 0;
        }

        // Step 2: Create Queue
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        set.remove(beginWord);

        int level = 1;

        // Step 3: BFS
        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String word = queue.poll();

                if (word.equals(endWord)) {
                    return level;
                }

                char[] arr = word.toCharArray();

                // Generate neighbours
                for (int j = 0; j < arr.length; j++) {

                    char original = arr[j];

                    for (char ch = 'a'; ch <= 'z'; ch++) {

                        arr[j] = ch;

                        String newWord = new String(arr);

                        if (set.contains(newWord)) {

                            queue.offer(newWord);

                            set.remove(newWord);
                        }
                    }

                    // Restore original character
                    arr[j] = original;
                }
            }

            level++;
        }

        return 0;
    }
}