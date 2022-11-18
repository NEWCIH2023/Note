public class 所有数对的异或和 {
    public static void main(String[] args) {
        所有数对的异或和 s = new 所有数对的异或和();
        System.out.println(s.xorAllNums(new int[] { 3, 2, 432, 5 }, new int[] { 35, 223, 523, 5 }));
    }

    public int xorAllNums(int[] nums1, int[] nums2) {
        int result = 0;

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] != nums2[j]) {
                    result ^= (nums1[i] ^ nums2[j]);
                }
            }
        }

        return result;
    }

}