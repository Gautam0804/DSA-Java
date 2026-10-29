
import java.util.*;

class Solution {
    private int[][] up;
    private int[] depth, tin, tout;
    private int timer;
    private Fenwick bit;
    private int LOG;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];

        // Iterative DFS avoids stack overflow on a chain-shaped tree.
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] stack = new int[2 * n];
        int top = 0;
        stack[top++] = 0;
        parent[0] = 0;
        timer = 0;

        while (top > 0) {
            int event = stack[--top];

            if (event < 0) {
                int u = -event - 1;
                tout[u] = timer - 1;
                continue;
            }

            int u = event;
            tin[u] = timer++;

            stack[top++] = -(u + 1);

            for (int v : adj[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;

                for (int j = 1; j < LOG; j++) {
                    up[j][v] = up[j - 1][up[j - 1][v]];
                }

                stack[top++] = v;
            }
        }

        // Fenwick tree maintains root-to-node parity masks.
        bit = new Fenwick(n + 1);
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            rangeXor(tin[i], tout[i], mask);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] == c) {
                    continue;
                }

                int change =
                    (1 << (chars[u] - 'a')) ^
                    (1 << (c - 'a'));

                rangeXor(tin[u], tout[u], change);
                chars[u] = c;

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int mask = bit.query(tin[u])
                         ^ bit.query(tin[v])
                         ^ (1 << (chars[ancestor] - 'a'));

                // True when at most one bit is set.
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private void rangeXor(int left, int right, int mask) {
        bit.update(left, mask);
        bit.update(right + 1, mask);
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    private static class Fenwick {
        private final int[] tree;
        private final int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            for (int i = index + 1; i <= n; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int query(int index) {
            int result = 0;

            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }
    }
}
