class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        // Case 1: Buy everything separately
        long separate = (long) need1 * cost1 + (long) need2 * cost2;

        // Case 2: Buy only combined items
        long both = (long) Math.max(need1, need2) * costBoth;

        // Case 3: Buy combined items for common requirements
        long mixed = (long) Math.min(need1, need2) * costBoth;

        mixed += (long) (need1 - Math.min(need1, need2)) * cost1;
        mixed += (long) (need2 - Math.min(need1, need2)) * cost2;

        return Math.min(separate, Math.min(both, mixed));
    }
}