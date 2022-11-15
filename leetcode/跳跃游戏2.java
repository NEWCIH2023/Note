public class 跳跃游戏2 {
    public static void main(String[] args) {
        跳跃游戏2 s = new 跳跃游戏2();
        System.out.println(
                s.jump(new int[] { 23, 3, 5, 23, 5, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 4, 0, 0, 0, 1 }));
    }

    public int jump(int[] nums) {
        int position = nums.length - 1, step = 0;
        while (position > 0) {
            for (int i = 0; i < nums.length; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    step++;
                    break;
                }
            }
        }

        return step;
    }
}