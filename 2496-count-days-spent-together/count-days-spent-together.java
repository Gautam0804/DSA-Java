class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                String arriveBob, String leaveBob) {

        int[] days = {31, 28, 31, 30, 31, 30,
                      31, 31, 30, 31, 30, 31};

        int aStart = getDay(arriveAlice, days);
        int aEnd = getDay(leaveAlice, days);
        int bStart = getDay(arriveBob, days);
        int bEnd = getDay(leaveBob, days);

        int start = Math.max(aStart, bStart);
        int end = Math.min(aEnd, bEnd);

        if (start > end) {
            return 0;
        }

        return end - start + 1;
    }

    private int getDay(String date, int[] days) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int total = day;

        for (int i = 0; i < month - 1; i++) {
            total += days[i];
        }

        return total;
    }
}