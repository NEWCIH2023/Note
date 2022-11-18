public class 所有蚂蚁掉下来前的最后一刻 {
    public static void main(String[] args) {
        所有蚂蚁掉下来前的最后一刻 s = new 所有蚂蚁掉下来前的最后一刻();
        System.out.println(s.getLastMoment(23, new int[] { 325, 235 }, new int[] { 234, 235, 235, 32 }));
    }

    public int getLastMoment(int n, int[] left, int[] right) {
        int result = 0;
        for (int i : right) {
            result = Math.max(result, n - i);
        }
        for (int i : left) {
            result = Math.max(result, i);
        }

        return result;
    }
}