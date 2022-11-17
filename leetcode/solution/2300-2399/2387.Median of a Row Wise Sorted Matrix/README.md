# [2387. Median of a Row Wise Sorted Matrix](https://leetcode.cn/problems/median-of-a-row-wise-sorted-matrix)

[English Version](/solution/2300-2399/2387.Median%20of%20a%20Row%20Wise%20Sorted%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Given an <code>m x n</code> matrix <code>grid</code> containing an <strong>odd</strong> number of integers where each row is sorted in <strong>non-decreasing</strong> order, return <em>the <strong>median</strong> of the matrix</em>.</p>

<p>You must solve the problem in less than <code>O(m * n)</code> time complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,2],[2,3,3],[1,3,4]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The elements of the matrix in sorted order are 1,1,1,2,<u>2</u>,3,3,3,4. The median is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,3,3,4]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The elements of the matrix in sorted order are 1,1,<u>3</u>,3,4. The median is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>m</code> and <code>n</code> are both odd.</li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>grid[i]</code> is sorted in non-decreasing order.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：两次二分查找**

中位数实际上是排序后第 $target = \left \lceil \frac{m\times n}{2} \right \rceil $ 个数。

我们二分枚举矩阵的元素 $x$，统计网格中大于该元素的个数 $cnt$，如果 $cnt \ge target$，说明中位数在 $x$ 的左侧（包含 $x$），否则在右侧。

时间复杂度 $O(m\times \log n \times log M)$，其中 $M$ 为矩阵中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def matrixMedian(self, grid: List[List[int]]) -> int:
        def count(x):
            return sum(bisect_right(row, x) for row in grid)

        m, n = len(grid), len(grid[0])
        target = (m * n + 1) >> 1
        return bisect_left(range(10**6 + 1), target, key=count)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][] grid;

    public int matrixMedian(int[][] grid) {
        this.grid = grid;
        int m = grid.length, n = grid[0].length;
        int target = (m * n + 1) >> 1;
        int left = 0, right = 1000010;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (count(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int count(int x) {
        int cnt = 0;
        for (var row : grid) {
            int left = 0, right = row.length;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (row[mid] > x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            cnt += left;
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int matrixMedian(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int left = 0, right = 1e6 + 1;
        int target = (m * n + 1) >> 1;
        auto count = [&](int x) {
            int cnt = 0;
            for (auto& row : grid) {
                cnt += (upper_bound(row.begin(), row.end(), x) - row.begin());
            }
            return cnt;
        };
        while (left < right) {
            int mid = (left + right) >> 1;
            if (count(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func matrixMedian(grid [][]int) int {
	m, n := len(grid), len(grid[0])

	count := func(x int) int {
		cnt := 0
		for _, row := range grid {
			left, right := 0, n
			for left < right {
				mid := (left + right) >> 1
				if row[mid] > x {
					right = mid
				} else {
					left = mid + 1
				}
			}
			cnt += left
		}
		return cnt
	}
	left, right := 0, 1000010
	target := (m*n + 1) >> 1
	for left < right {
		mid := (left + right) >> 1
		if count(mid) >= target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
