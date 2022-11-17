class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] delta = new int[n];
        for (int[] booking : bookings) {
            int first = booking[0], last = booking[1], seats = booking[2];
            delta[first - 1] += seats;
            if (last < n) {
                delta[last] -= seats;
            }
        }
        for (int i = 0; i < n - 1; ++i) {
            delta[i + 1] += delta[i];
        }
        return delta;
    }
}