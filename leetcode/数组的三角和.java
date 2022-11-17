public class 数组的三角和 {
    public static void main(String[] args) {
        数组的三角和 s = new 数组的三角和();
        System.out.println(s.triangularSum(new int[] { 1, 2, 3, 4, 5 }));
    }

    public int triangularSum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }

        for (int k = nums.length - 1; k > 0; k--) {
            for (int i = 0; i < k; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }

        return nums[0];
    }
}