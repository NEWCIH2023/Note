class Solution {

  // TC : O(N)
  // SC: O(1)
  public void nextPermutation(int[] nums) {
    int first = nums.length-1;


    while(first>=1 && nums[first-1] >=nums[first]){
      first--;
    }

    if(first==0){
      Arrays.sort(nums);
      return;
    }

    first--;

    int second = nums.length-1;

    while(first<second && nums[first] >=nums[second]){
      second--;
    }


    swap(nums, first, second);

    int start = first+1;
    int end = nums.length -1;
    while(start < end){
      int temp = nums[start];
      nums[start] = nums[end] ;
      nums[end] = temp;
      end--;
      start++;
    }
  }

  private void swap(int[] nums, int first, int second){
    if(first!=second){
      int temp = nums[second];
      nums[second] = nums[first];
      nums[first] = temp;
    }
  }
}


// Author: Shobhit Behl (LC: shobhitbruh)
class Solution {
    public void nextPermutation(int[] nums) {
        ArrayList<Integer> x=new ArrayList<>();
        int p=0;
        int q=0;
       for(int i=nums.length-2;i>=0;i--){
           if(nums[i]<nums[i+1]){
              p=i;
              q++;
               break;
           }
       }
        if(q==0){
            Arrays.sort(nums);
            return;
        }
       int z=Integer.MAX_VALUE;
        int idx=-1;
       for(int i=nums.length-1;i>p;i--){
           if(nums[i]<z&&nums[p]<nums[i]){
               z=nums[i];
               idx=i;
           }
       }
       if(idx!=-1){
           int temp=nums[p];
           nums[p]=nums[idx];
           nums[idx]=temp;
           for(int i=nums.length-1;i>p;i--){
          x.add(nums[i]);
           }
           Collections.sort(x);
           int j=0;
           for(int i=p+1;i<nums.length;i++){
               nums[i]=x.get(j);
               j++;
           }
        }
        
        
    }
}

// Author: @romitdutta10
// TC : O(N)
// SC: O(1)
// Problem : https://leetcode.com/problems/next-permutation/


class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length - 1;
        
        int first = n;
        
        while(first > 0 && nums[first-1] >= nums[first] ) {
            first--;
        }
        
        
        
        if(first == 0) {
            reverse(nums, 0, n);
            return;
        }
        
        first--;
        
        int second = n;
        
        while(first < second && nums[second] <= nums[first]) {
            second--;
        }
        
        swap(nums, first, second);
        reverse(nums, first + 1, n);
        
    }
    
    private void swap(int[] nums, int first, int second) {
        if(first != second) {
            int temp = nums[first];
            nums[first] = nums[second];
            nums[second] = temp;
        }
    }
    
    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}


// @saorav21994

class Solution {
    public void nextPermutation(int[] nums) {
        int l = nums.length;
        int flag = -1;
        
        for (int i = l-2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                flag = i;
                break;
            }
        }
        if (flag != -1) {
            int max = Integer.MAX_VALUE;
            int idx = -1;
            for (int i = flag+1; i < l; i++) {
                if (nums[i] > nums[flag] && nums[i] < max) {
                    max = nums[i];
                    idx = i;
                }
            }
            
            int tmp = nums[flag];
            nums[flag] = nums[idx];
            nums[idx] = tmp;
        }
        Arrays.sort(nums, flag+1, l);
    }
}
