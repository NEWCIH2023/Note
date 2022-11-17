# [410. Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum)

[中文文档](/solution/0400-0499/0410.Split%20Array%20Largest%20Sum/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, split <code>nums</code> into <code>k</code> non-empty subarrays such that the largest sum of any subarray is <strong>minimized</strong>.</p>

<p>Return <em>the minimized largest sum of the split</em>.</p>

<p>A <strong>subarray</strong> is a contiguous part of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,2,5,10,8], k = 2
<strong>Output:</strong> 18
<strong>Explanation:</strong> There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], k = 2
<strong>Output:</strong> 9
<strong>Explanation:</strong> There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(50, nums.length)</code></li>
</ul>

## Solutions

Binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def splitArray(self, nums: List[int], m: int) -> int:
        def check(x):
            s, cnt = 0, 1
            for num in nums:
                if s + num > x:
                    cnt += 1
                    s = num
                else:
                    s += num
            return cnt <= m

        left, right = max(nums), sum(nums)
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

```java
class Solution {
    public int splitArray(int[] nums, int m) {
        int mx = -1;
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        int left = mx, right = (int) 1e9;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(nums, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int m, int x) {
        int s = 0, cnt = 1;
        for (int num : nums) {
            if (s + num > x) {
                ++cnt;
                s = num;
            } else {
                s += num;
            }
        }
        return cnt <= m;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int splitArray(vector<int>& nums, int m) {
        int left = *max_element(nums.begin(), nums.end()), right = (int)1e9;
        while (left < right) {
            int mid = left + right >> 1;
            if (check(nums, m, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    bool check(vector<int>& nums, int m, int x) {
        int s = 0, cnt = 1;
        for (int num : nums) {
            if (s + num > x) {
                ++cnt;
                s = num;
            } else {
                s += num;
            }
        }
        return cnt <= m;
    }
};
```

### **Go**

```go
func splitArray(nums []int, m int) int {
	mx := -1
	for _, num := range nums {
		mx = max(mx, num)
	}
	left, right := mx, int(1e9)
	for left < right {
		mid := (left + right) >> 1
		if check(nums, m, mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func check(nums []int, m, x int) bool {
	s, cnt := 0, 1
	for _, num := range nums {
		if s+num > x {
			cnt++
			s = num
		} else {
			s += num
		}
	}
	return cnt <= m
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
