class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        int turn = 0;

        while (n >= remove) {
            n -= remove;
            remove--;
            turn++;
        }

        return turn % 2 == 1;
    }
}