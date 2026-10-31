class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {

        // 1. Create adjacency list
        ArrayList<Integer>[] graph = new ArrayList[n];

        // 2. Initialize graph
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 3. Add edges (Undirected Graph)
        for (int i = 0; i < edges.length; i++) {

            int u = edges[i][0];
            int v = edges[i][1];

            graph[u].add(v);
            graph[v].add(u);
        }

        // 4. Create visited array and Queue
        boolean[] vis = new boolean[n];

        Queue<Integer> q = new LinkedList<>();

        // 5. Start BFS from source
        q.add(source);
        vis[source] = true;

        while (!q.isEmpty()) {

            int curr = q.remove();

            // Destination found
            if (curr == destination) {
                return true;
            }

            // Visit neighbours
            for (int i = 0; i < graph[curr].size(); i++) {

                int neighbour = graph[curr].get(i);

                if (!vis[neighbour]) {

                    vis[neighbour] = true;
                    q.add(neighbour);
                }
            }
        }

        return false;
    }
}