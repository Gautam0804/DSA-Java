import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

            int time = arrival[i];

            // Release completed servers
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            // Find next available server
            if (available.isEmpty()) {
                continue;
            }

            int serverId = i % k;

            Integer server = available.ceiling(serverId);

            if (server == null) {
                server = available.first();
            }

            // Assign request
            available.remove(server);

            count[server]++;

            busy.offer(new int[]{
                time + load[i],
                server
            });
        }

        // Find maximum requests
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}