class Solution {
    List<Integer>[] graph;
    List<Integer>[] ancestors;
    int[] newParent;
    int[] ans;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        graph = new ArrayList[n];
        ancestors = new ArrayList[26];

        newParent = parent.clone();
        ans = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < 26; i++) {
            ancestors[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            graph[parent[i]].add(i);
        }

        // Find new parents using original tree
        dfsUpdate(0, parent, s);

        // Build modified tree
        List<Integer>[] newGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newGraph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newGraph[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        dfsSize(0, newGraph);

        return ans;
    }

    private void dfsUpdate(int node, int[] parent, String s) {
        int ch = s.charAt(node) - 'a';

        // Find nearest matching ancestor
        if (!ancestors[ch].isEmpty()) {
            newParent[node] =
                ancestors[ch].get(ancestors[ch].size() - 1);
        }

        // Add current node to ancestor path
        ancestors[ch].add(node);

        // Visit children in original tree
        for (int child : graph[node]) {
            dfsUpdate(child, parent, s);
        }

        // Backtrack
        ancestors[ch].remove(ancestors[ch].size() - 1);
    }

    private int dfsSize(int node, List<Integer>[] tree) {
        int size = 1;

        for (int child : tree[node]) {
            size += dfsSize(child, tree);
        }

        ans[node] = size;
        return size;
    }
}
