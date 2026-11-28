class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] cnt = new int[n + 1];
        long m = Long.MAX_VALUE;
        for (int[] it : items) {
            cnt[it[0]]++;
            m = Math.min(m, it[1]);
        }

        long[] mult = new long[n + 1];
        for (int a = 1; a <= n; a++) {
            for (int b = a; b <= n; b += a) {
                mult[a] += cnt[b];
            }
        }

        List<long[]> offers = new ArrayList<>();
        for (int[] it : items) {
            long g = mult[it[0]] - 1;
            long price = it[1];
            if (g > 0 && price < 2 * m) {
                offers.add(new long[]{price, g});
            }
        }
        offers.sort((x, y) -> Long.compare(x[0], y[0]));

        long remaining = budget;
        long special = 0;
        for (long[] o : offers) {
            long price = o[0], count = o[1];
            long take = Math.min(count, remaining / price);
            special += take;
            remaining -= take * price;
            if (take < count) break; 
        }

        long ans = 2 * special + remaining / m;
        return (int) ans;
    }
}