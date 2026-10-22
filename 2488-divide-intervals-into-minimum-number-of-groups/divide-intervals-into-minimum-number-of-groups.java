class Solution {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals,(a,b)-> Integer.compare(a[0],b[0]));

        PriorityQueue<Integer> min = new PriorityQueue<>();

        for(int[] interval:intervals){
            int start = interval[0];
            int end = interval[1];

            if(!min.isEmpty() && min.peek() < start){
                min.poll();
            }
            min.offer(end);
        }
        return min.size();
    }
}