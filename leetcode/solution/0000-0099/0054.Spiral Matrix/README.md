# [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix)

[English Version](/solution/0000-0099/0054.Spiral%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m</code> 行 <code>n</code> 列的矩阵 <code>matrix</code> ，请按照 <strong>顺时针螺旋顺序</strong> ，返回矩阵中的所有元素。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0054.Spiral%20Matrix/images/spiral1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0054.Spiral%20Matrix/images/spiral.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
<strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 10</code></li>
	<li><code>-100 <= matrix[i][j] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

从外往里一圈一圈遍历并存储矩阵元素即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        m, n = len(matrix), len(matrix[0])
        ans = []
        top, bottom, left, right = 0, m - 1, 0, n - 1
        while left <= right and top <= bottom:
            ans.extend([matrix[top][j] for j in range(left, right + 1)])
            ans.extend([matrix[i][right] for i in range(top + 1, bottom + 1)])
            if left < right and top < bottom:
                ans.extend([matrix[bottom][j] for j in range(right - 1, left - 1, -1)])
                ans.extend([matrix[i][left] for i in range(bottom - 1, top, -1)])
            top, bottom, left, right = top + 1, bottom - 1, left + 1, right - 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        List<Integer> ans = new ArrayList<>();
        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; ++j) {
                ans.add(matrix[top][j]);
            }
            for (int i = top + 1; i <= bottom; ++i) {
                ans.add(matrix[i][right]);
            }
            if (left < right && top < bottom) {
                for (int j = right - 1; j >= left; --j) {
                    ans.add(matrix[bottom][j]);
                }
                for (int i = bottom - 1; i > top; --i) {
                    ans.add(matrix[i][left]);
                }
            }
            ++top;
            --bottom;
            ++left;
            --right;
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    let [top, bottom, left, right] = [0, m - 1, 0, n - 1];
    let ans = [];
    while (top <= bottom && left <= right) {
        for (let j = left; j <= right; ++j) {
            ans.push(matrix[top][j]);
        }
        for (let i = top + 1; i <= bottom; ++i) {
            ans.push(matrix[i][right]);
        }
        if (left < right && top < bottom) {
            for (let j = right - 1; j >= left; --j) {
                ans.push(matrix[bottom][j]);
            }
            for (let i = bottom - 1; i > top; --i) {
                ans.push(matrix[i][left]);
            }
        }
        [top, bottom, left, right] = [top + 1, bottom - 1, left + 1, right - 1];
    }
    return ans;
};
```

### **Go**

```go
func spiralOrder(matrix [][]int) []int {
	m, n := len(matrix), len(matrix[0])
	ans := make([]int, 0, m*n)

	top, bottom, left, right := 0, m-1, 0, n-1
	for left <= right && top <= bottom {
		for i := left; i <= right; i++ {
			ans = append(ans, matrix[top][i])
		}
		for i := top + 1; i <= bottom; i++ {
			ans = append(ans, matrix[i][right])
		}
		if left < right && top < bottom {
			for i := right - 1; i >= left; i-- {
				ans = append(ans, matrix[bottom][i])
			}
			for i := bottom - 1; i > top; i-- {
				ans = append(ans, matrix[i][left])
			}
		}
		top++
		bottom--
		left++
		right--
	}

	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        vector<int> ans;
        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; ++j) ans.push_back(matrix[top][j]);
            for (int i = top + 1; i <= bottom; ++i) ans.push_back(matrix[i][right]);
            if (left < right && top < bottom) {
                for (int j = right - 1; j >= left; --j) ans.push_back(matrix[bottom][j]);
                for (int i = bottom - 1; i > top; --i) ans.push_back(matrix[i][left]);
            }
            ++top;
            --bottom;
            ++left;
            --right;
        }
        return ans;
    }
};
```

### **C#**

```cs
public class Solution {
    public IList<int> SpiralOrder(int[][] matrix) {
        int m = matrix.Length;
        int n = matrix[0].Length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        var ans = new List<int>(m * n);
        while (top <= bottom && left <= right)
        {
            for (int j = left; j <= right; ++j)
            {
                ans.Add(matrix[top][j]);
            }
            for (int i = top + 1; i <= bottom; ++i)
            {
                ans.Add(matrix[i][right]);
            }
            if (left < right && top < bottom)
            {
                for (int j = right - 1; j >= left; --j)
                {
                    ans.Add(matrix[bottom][j]);
                }
                for (int i = bottom - 1; i > top; --i)
                {
                    ans.Add(matrix[i][left]);
                }
            }
            ++top;
            --bottom;
            ++left;
            --right;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function spiralOrder(matrix: number[][]): number[] {
    const m = matrix.length;
    const n = matrix[0].length;
    const res = [];
    for (let i = 0; i <= m >> 1; i++) {
        for (let j = i; j < n - i - 1; j++) {
            res.push(matrix[i][j]);
        }
        for (let j = i; j < m - i - 1; j++) {
            res.push(matrix[j][n - i - 1]);
        }
        for (let j = i; j < n - i - 1; j++) {
            res.push(matrix[m - i - 1][n - j - 1]);
        }
        for (let j = i; j < m - i - 1; j++) {
            res.push(matrix[m - j - 1][i]);
        }
    }
    if (m & 1) {
        res.push(matrix[m >> 1][n >> 1]);
    }
    return res.slice(0, m * n);
}
```

### **...**

```

```

<!-- tabs:end -->
