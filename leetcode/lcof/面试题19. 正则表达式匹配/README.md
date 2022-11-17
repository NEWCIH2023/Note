# [面试题 19. 正则表达式匹配](https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/)

## 题目描述

<p>请实现一个函数用来匹配包含<code>&#39;. &#39;</code>和<code>&#39;*&#39;</code>的正则表达式。模式中的字符<code>&#39;.&#39;</code>表示任意一个字符，而<code>&#39;*&#39;</code>表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串<code>&quot;aaa&quot;</code>与模式<code>&quot;a.a&quot;</code>和<code>&quot;ab*ac*a&quot;</code>匹配，但与<code>&quot;aa.a&quot;</code>和<code>&quot;ab*a&quot;</code>均不匹配。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong>
s = &quot;aa&quot;
p = &quot;a&quot;
<strong>输出:</strong> false
<strong>解释:</strong> &quot;a&quot; 无法匹配 &quot;aa&quot; 整个字符串。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong>
s = &quot;aa&quot;
p = &quot;a*&quot;
<strong>输出:</strong> true
<strong>解释:</strong>&nbsp;因为 &#39;*&#39; 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 &#39;a&#39;。因此，字符串 &quot;aa&quot; 可被视为 &#39;a&#39; 重复了一次。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre><strong>输入:</strong>
s = &quot;ab&quot;
p = &quot;.*&quot;
<strong>输出:</strong> true
<strong>解释:</strong>&nbsp;&quot;.*&quot; 表示可匹配零个或多个（&#39;*&#39;）任意字符（&#39;.&#39;）。
</pre>

<p><strong>示例 4:</strong></p>

<pre><strong>输入:</strong>
s = &quot;aab&quot;
p = &quot;c*a*b&quot;
<strong>输出:</strong> true
<strong>解释:</strong>&nbsp;因为 &#39;*&#39; 表示零个或多个，这里 &#39;c&#39; 为 0 个, &#39;a&#39; 被重复一次。因此可以匹配字符串 &quot;aab&quot;。
</pre>

<p><strong>示例 5:</strong></p>

<pre><strong>输入:</strong>
s = &quot;mississippi&quot;
p = &quot;mis*is*p*.&quot;
<strong>输出:</strong> false</pre>

<ul>
	<li><code>s</code>&nbsp;可能为空，且只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母。</li>
	<li><code>p</code>&nbsp;可能为空，且只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母以及字符&nbsp;<code>.</code>&nbsp;和&nbsp;<code>*</code>，无连续的 <code>&#39;*&#39;</code>。</li>
</ul>

<p>注意：本题与主站 10&nbsp;题相同：<a href="https://leetcode.cn/problems/regular-expression-matching/">https://leetcode.cn/problems/regular-expression-matching/</a></p>

## 解法

动态规划法，`dp[i][j]` 表示 s 的前 i 项和 p 的前 j 项是否匹配。

现在如果已知了 `dp[i-1][j-1]` 的状态，我们该如何确定 `dp[i][j]` 的状态呢？我们可以分三种情况讨论，其中，前两种情况考虑了所有能匹配的情况，剩下的就是不能匹配的情况了：

1. `s[i] == p[j]` or `p[j] == '.'`：比如 ab**b** 和 ab**b**，或者 ab**b** 和 ab. ，很容易得到 `dp[i][j]` = `dp[i-1][j-1]` = True。因为 ab 和 ab 是匹配的，如果后面分别加一个 b，或者 s 加一个 b 而 p 加一个 `.` ，仍然是匹配的。
2. `p[j] == '*'`：当 `p[j] == '*'` 时，由于 `*` 与前面的字符相关，因此我们比较 `*` 前面的字符 `p[j-1]` 和 `s[i]` 的关系。根据 `*` 前面的字符与 s[i] 是否相等，又可分为以下两种情况：
    - `p[j-1] != s[i]`：如果 `*` 前一个字符匹配不上，`*` 匹配了 0 次，应忽略这两个字符，看 `p[j-2]` 和 `s[i]` 是否匹配。 这时 `dp[i][j] = dp[i][j-2]`。
    - `p[j-1] == s[i]` or `p[j-1] == '.'`：`*` 前面的字符可以与 s[i] 匹配，这种情况下，`*` 可能匹配了前面的字符的 0 个，也可能匹配了前面字符的多个，当匹配 0 个时，如 `ab` 和 `abb*`，或者 `ab` 和 `ab.*` ，这时我们需要去掉 p 中的 `b*` 或 `.*` 后进行比较，即 `dp[i][j] = dp[i][j-2]`；当匹配多个时，如 `abbb` 和 `ab*`，或者 `abbb` 和 `a.*`，我们需要将 s[i] 前面的与 p 重新比较，即 `dp[i][j] = dp[i-1][j]`。
3. 其他情况：以上两种情况把能匹配的都考虑全面了，所以其他情况为不匹配，即 `dp[i][j] = False`。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        if n == 0:
            return m == 0
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True
        for j in range(2, n + 1):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 2]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if s[i - 1] == p[j - 1] or p[j - 1] == '.':
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':
                    if p[j - 2] == '.' or p[j - 2] == s[i - 1]:
                        dp[i][j] = dp[i][j - 2] or dp[i - 1][j]
                    else:
                        dp[i][j] = dp[i][j - 2]
        return dp[-1][-1]
```

### **Java**

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        if (n == 0) {
            return m == 0;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j < n + 1; ++j) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size(), n = p.size();
        if (n == 0) return m == 0;
        vector<vector<bool>> dp(m + 1, vector<bool>(n + 1, false));
        dp[0][0] = true;
        for (int j = 1; j < n + 1; ++j) {
            if (p[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p[j - 1] == '*') {
                    if (s[i - 1] == p[j - 2] || p[j - 2] == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[m][n];
    }
};
```

### **Go**

```go
func isMatch(s string, p string) bool {
	m, n := len(s), len(p)
	if n == 0 {
		return m == 0
	}
	dp := make([][]bool, m+1)
	for i := 0; i < m+1; i++ {
		dp[i] = make([]bool, n+1)
	}
	dp[0][0] = true
	for j := 1; j < n+1; j++ {
		if p[j-1] == '*' {
			dp[0][j] = dp[0][j-2]
		}
	}
	for i := 1; i < m+1; i++ {
		for j := 1; j < n+1; j++ {
			if s[i-1] == p[j-1] || p[j-1] == '.' {
				dp[i][j] = dp[i-1][j-1]
			} else if p[j-1] == '*' {
				if s[i-1] == p[j-2] || p[j-2] == '.' {
					dp[i][j] = dp[i][j-2] || dp[i-1][j]
				} else {
					dp[i][j] = dp[i][j-2]
				}
			}
		}
	}
	return dp[m][n]
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */
var isMatch = function (s, p) {
    // 回溯大法好
    let memo = {};
    function recursive(i, j) {
        if (memo[[i, j]] !== undefined) return memo[[i, j]];
        if (j === p.length) return i === s.length;
        let tmp = i < s.length && (s[i] === p[j] || p[j] === '.');
        let ans = false;
        if (p[j + 1] === '*') {
            ans = recursive(i, j + 2) || (tmp && recursive(i + 1, j));
        } else {
            ans = tmp && recursive(i + 1, j + 1);
        }
        memo[[i, j]] = ans;
        return ans;
    }
    return recursive(0, 0);
};
```

### **C#**

```cs
public class Solution {
    public bool IsMatch(string s, string p) {
        int m = s.Length, n = p.Length;
        if (n == 0) {
            return m == 0;
        }
        bool[,] dp = new bool[m+1,n+1];
        dp[0,0] = true;
        for(int j = 1; j < n + 1; j++) {
            if (p[j-1] == '*') {
                dp[0,j] = dp[0,j-2];
            }
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s[i-1] == p[j-1] || p[j-1] == '.') {
                    dp[i,j] = dp[i-1,j-1];
                } else if (p[j-1] == '*') {
                    if (p[j-2] == '.' || p[j-2] == s[i-1]) {
                        dp[i,j] = dp[i,j-2] || dp[i-1,j];
                    } else {
                        dp[i,j] = dp[i,j-2];
                    }
                }
            }
        }
        return dp[m,n];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
