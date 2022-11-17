

class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] summedRow = new int[n];
        int ans = 0;
        for(int i = 0; i < m; i++) { //i is the starting row
            Arrays.fill(summedRow, 0);
            for(int j = i; j < m; j++) { //j is the ending row
                for(int k = 0; k < n; k++) { // k is for column
                    summedRow[k] += matrix[j][k];
                }

                ans += subarraySum(summedRow, target);
            }
        }
        return ans;
    }

    private int subarraySum(int[] nums, int k) {
        int sum = 0;
        int ans = 0;

        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                ans += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
}

// @saorav21994
// TC : O(m*n*n)
// SC : O(1)

class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int res = 0;
        
        // row-wise cumulative sum for all rows
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] += matrix[i][j-1];
            }
        }
        
        // column-wise cumulative sum for all columns 
        for (int i = 0; i < n; i++) {       
            for (int j = i; j < n; j++) {       // for each column
                
                // First add the columns and update sum in map.
                // Then, subtract previous column and update sum in map.
                
                int curSum = 0;
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);         // for 0 entry, we will miss count for first occurance if default is not there
                
                for (int k = 0; k < m; k++) {       // for each row
                    
                    int previousSum = 0;        // Default : No preceding column to subtract sum
                    if (i != 0) {
                        previousSum += matrix[k][i-1];
                    }
                    curSum += matrix[k][j] - previousSum;
                    
                    // sub-array sum logic
                    
                    res += map.getOrDefault(curSum-target, 0);
                    map.put(curSum, map.getOrDefault(curSum, 0) + 1);
                    
                }
                
                
            }
        }
        
        return res;
        
    }
}
