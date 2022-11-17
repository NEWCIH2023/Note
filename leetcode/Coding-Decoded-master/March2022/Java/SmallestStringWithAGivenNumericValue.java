

// @saorav21994
// TC : O(n)
// SC : O(n)

class Solution {
    public String getSmallestString(int n, int k) {
        char [] res = new char[n];
        Arrays.fill(res, 'a');
        k -= n;
        while (k > 0) {
            res[--n] += Math.min(25, k);
            k -= Math.min(25, k);
        }
        return String.valueOf(res);
    }
}
class Solution {
	public String getSmallestString(int n, int k) {
		char[] ans = new char[n];
		int i = 0 ;
		while(i<n){
			ans[i] = 'a';
			i++;
		}
		k = k - n;
		int j = n-1;
		while(k>0){
			if(k<25){
				ans[j] = (char)('a' + k);
				k = 0;
			} else {
				ans[j] = 'z';
				k = k - 25;
			}
			j--;
		}
		return new String(ans);


	}
}

// Author: @romitdutta10
// TC: O(N)
// SC: O(N)
// Problem : https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/


class Solution {
    public String getSmallestString(int n, int k) {
        k = k - n;
        
        int zcount = k / 25;
        int value = k % 25;
        
        int count = n-1;
        
        char c[] = new char[n];
        Arrays.fill(c, 'a');
        
        while(zcount-- > 0) {
            c[count--] = 'z';
        }
        
        if(value > 0)
            c[count--] = (char)('a' + value);
        
        return new String(c);
    }
}
