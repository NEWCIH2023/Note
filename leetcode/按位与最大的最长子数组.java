public class 按位与最大的最长子数组 {
    public static void main(String[] args) {
        按位与最大的最长子数组 s = new 按位与最大的最长子数组();
        System.out.println(s.longestSubarray(new int[] { 3, 532, 5, 25 }));
    }

    public int longestSubarray(int[] nums) {
        int result = 1;
        int temp = 1;
        int maxIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
                temp = 1;
                result = 1;
            } else if (nums[i] == nums[maxIndex]) {
                if (i - maxIndex == 1) {
                    temp++;
                } else {
                    temp = 1;
                }

                maxIndex = i;
                result = Math.max(result, temp);
            }
        }

        return result;
    }
}