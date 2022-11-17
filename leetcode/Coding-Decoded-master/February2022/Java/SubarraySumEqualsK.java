
// Author : Shobhit Behl (LC : shobhitbruh)
class Solution {
    public int subarraySum(int[] nums, int k) {
       HashMap<Integer,Integer> hs=new HashMap<>();
        hs.put(0,1);
        int c=0;
        int[] ts=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            ts[i]=nums[i];
            if(i>0){
                ts[i]+=ts[i-1];
            }
            
            if(hs.containsKey(ts[i]-k)){
                c+=hs.get(ts[i]-k);
            }
            
            hs.put(ts[i],hs.getOrDefault(ts[i],0)+1);
        }
        
        return c;
    }
}

// @saorav21994
// TC : O(n)
// SC : O(n)

/*
  basic concept -> if cumulative_sum(num[i]) - cumulative_sum(num[j]) == k then the sum of numbers between i and j is k. so c_sum(num[i])-k = c_sum(num[j]) -> which means
	frequency of curSum-k will give the number of subarray(s) till this index with sum=k.
*/


class Solution {
    public int subarraySum(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int curSum = 0;
		int res = 0;
        map.put(0, 1);
		for (int n : nums) {
			curSum += n;
			res += map.getOrDefault(curSum-k, 0);
			map.put(curSum, map.getOrDefault(curSum, 0) + 1);
		}
		return res;
    }

  
class Solution {
	public int subarraySum(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0,1);
		int ans = 0;
		int sum = 0;
		for(int i =0;i<nums.length;i++){

			sum += nums[i];
			if(map.containsKey(sum- k)){
				ans += map.get(sum-k);
			}

			map.put(sum, map.getOrDefault(sum,0)+1);
		}
		return ans;
	}
}
