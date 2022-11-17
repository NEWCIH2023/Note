# [1012. 至少有 1 位重复的数字](https://leetcode.cn/problems/numbers-with-repeated-digits)

[English Version](/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定正整数&nbsp;<code>n</code>，返回在<em>&nbsp;</em><code>[1, n]</code><em>&nbsp;</em>范围内具有 <strong>至少 1 位</strong> 重复数字的正整数的个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 20
<strong>输出：</strong>1
<strong>解释：</strong>具有至少 1 位重复数字的正数（&lt;= 20）只有 11 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 100
<strong>输出：</strong>10
<strong>解释：</strong>具有至少 1 位重复数字的正数（&lt;= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 1000
<strong>输出：</strong>262
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数位 DP**

题目求解 $[1,n]$ 范围内至少有 $1$ 位重复数字的正整数个数，我们可以转换为求解无重复数字的正整数个数 $cnt$，那么 $n-cnt$ 就是答案。

接下来我们就来求解 $[1,n]$ 范围内无重复数字的正整数个数。

定义 $m$ 表示数字 $n$ 的位数。我们可以将数字分成两类：(1) 数字位数小于 $m$；(2) 数字位数等于 $m$。

对于第一类，我们可以枚举数字的位数 $i$，其中 $i∈[1,m)$，第一位的数字不为 $0$，有 $[1,9]$ 可选，共 $9$ 种可能。剩余需要选择 $i-1$ 位数字，可选数字为 $[0,9]$ 的数字中除去第一位，共 $9$ 种可能。因此，第一类的数字共有：

$$
\sum \limits_{i=1}^{m-1} 9\times A_{9}^{i-1}
$$

对于第二类，数字的位数等于 $m$，我们从 $n$ 的高位（即 $i=m-1$）开始处理。不妨设 $n$ 当前位的数字为 $v$。

如果当前是 $n$ 的最高一位，那么数字不能为 $0$，可选数字为 $[1,v)$，否则可选数字为 $[0,v)$。若当前可选数字 $j$，那么剩余低位可选的数字总共有 $A_{10-(m-i)}^{i}$，累加到答案中。

以上我们算的是可选数字小于 $v$ 的情况，若等于 $v$，则需要继续外层循环，继续处理下一位。如果数字 $n$ 所有位均不重复，则 $n$ 本身也是一个特殊整数，需要累加到答案中。

时间复杂度 $O(m^2)$，其中 $m$ 是数字 $n$ 的位数，这里我们假定 $A_{m}^{n}$ 可以 $O(1)$ 时间算出。

相似题目：

-   [233. 数字 1 的个数](/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
-   [357. 统计各位数字都不同的数字个数](/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
-   [600. 不含连续 1 的非负整数](/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
-   [788. 旋转数字](/solution/0700-0799/0788.Rotated%20Digits/README.md)
-   [902. 最大为 N 的数字组合](/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
-   [2376. 统计特殊整数](/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numDupDigitsAtMostN(self, n: int) -> int:
        return n - self.f(n)

    def f(self, n):
        def A(m, n):
            return 1 if n == 0 else A(m, n - 1) * (m - n + 1)

        vis = [False] * 10
        ans = 0
        digits = [int(c) for c in str(n)[::-1]]
        m = len(digits)
        for i in range(1, m):
            ans += 9 * A(9, i - 1)
        for i in range(m - 1, -1, -1):
            v = digits[i]
            j = 1 if i == m - 1 else 0
            while j < v:
                if not vis[j]:
                    ans += A(10 - (m - i), i)
                j += 1
            if vis[v]:
                break
            vis[v] = True
            if i == 0:
                ans += 1
        return ans
```

```python
class Solution:
    def numDupDigitsAtMostN(self, n: int) -> int:
        return n - self.f(n)

    def f(self, n):
        @cache
        def dfs(pos, mask, lead, limit):
            if pos <= 0:
                return lead ^ 1
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if (mask >> i) & 1:
                    continue
                if i == 0 and lead:
                    ans += dfs(pos - 1, mask, lead, limit and i == up)
                else:
                    ans += dfs(pos - 1, mask | 1 << i, False, limit and i == up)
            return ans

        a = [0] * 11
        l = 0
        while n:
            l += 1
            a[l] = n % 10
            n //= 10
        return dfs(l, 0, True, True)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numDupDigitsAtMostN(int n) {
        return n - f(n);
    }

    public int f(int n) {
        List<Integer> digits = new ArrayList<>();
        while (n != 0) {
            digits.add(n % 10);
            n /= 10;
        }
        int m = digits.size();
        int ans = 0;
        for (int i = 1; i < m; ++i) {
            ans += 9 * A(9, i - 1);
        }
        boolean[] vis = new boolean[10];
        for (int i = m - 1; i >= 0; --i) {
            int v = digits.get(i);
            for (int j = i == m - 1 ? 1 : 0; j < v; ++j) {
                if (vis[j]) {
                    continue;
                }
                ans += A(10 - (m - i), i);
            }
            if (vis[v]) {
                break;
            }
            vis[v] = true;
            if (i == 0) {
                ++ans;
            }
        }
        return ans;
    }

    private int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }
}
```

```java
class Solution {
    private int[] a = new int[11];
    private int[][] dp = new int[11][1 << 11];

    public int numDupDigitsAtMostN(int n) {
        return n - f(n);
    }

    private int f(int n) {
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        int len = 0;
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true, true);
    }

    private int dfs(int pos, int mask, boolean lead, boolean limit) {
        if (pos <= 0) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (((mask >> i) & 1) == 1) {
                continue;
            }
            if (i == 0 && lead) {
                ans += dfs(pos - 1, mask, lead, limit && i == up);
            } else {
                ans += dfs(pos - 1, mask | 1 << i, false, limit && i == up);
            }
        }
        if (!lead && !limit) {
            dp[pos][mask] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numDupDigitsAtMostN(int n) {
        return n - f(n);
    }

    int f(int n) {
        int ans = 0;
        vector<int> digits;
        while (n) {
            digits.push_back(n % 10);
            n /= 10;
        }
        int m = digits.size();
        vector<bool> vis(10);
        for (int i = 1; i < m; ++i) {
            ans += 9 * A(9, i - 1);
        }
        for (int i = m - 1; ~i; --i) {
            int v = digits[i];
            for (int j = i == m - 1 ? 1 : 0; j < v; ++j) {
                if (!vis[j]) {
                    ans += A(10 - (m - i), i);
                }
            }
            if (vis[v]) {
                break;
            }
            vis[v] = true;
            if (i == 0) {
                ++ans;
            }
        }
        return ans;
    }

    int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }
};
```

```cpp
class Solution {
public:
    int a[11];
    int dp[11][1 << 11];

    int numDupDigitsAtMostN(int n) {
        return n - f(n);
    }

    int f(int n) {
        memset(dp, -1, sizeof dp);
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true, true);
    }

    int dfs(int pos, int mask, bool lead, bool limit) {
        if (pos <= 0) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if ((mask >> i) & 1) continue;
            if (i == 0 && lead) {
                ans += dfs(pos - 1, mask, lead, limit && i == up);
            } else {
                ans += dfs(pos - 1, mask | 1 << i, false, limit && i == up);
            }
        }
        if (!lead && !limit) {
            dp[pos][mask] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func numDupDigitsAtMostN(n int) int {
	return n - f(n)
}

func f(n int) int {
	digits := []int{}
	for n != 0 {
		digits = append(digits, n%10)
		n /= 10
	}
	m := len(digits)
	vis := make([]bool, 10)
	ans := 0
	for i := 1; i < m; i++ {
		ans += 9 * A(9, i-1)
	}
	for i := m - 1; i >= 0; i-- {
		v := digits[i]
		j := 0
		if i == m-1 {
			j = 1
		}
		for ; j < v; j++ {
			if !vis[j] {
				ans += A(10-(m-i), i)
			}
		}
		if vis[v] {
			break
		}
		vis[v] = true
		if i == 0 {
			ans++
		}
	}
	return ans
}

func A(m, n int) int {
	if n == 0 {
		return 1
	}
	return A(m, n-1) * (m - n + 1)
}
```

```go
func numDupDigitsAtMostN(n int) int {
	return n - f(n)
}

func f(n int) int {
	a := make([]int, 11)
	dp := make([][]int, 11)
	for i := range dp {
		dp[i] = make([]int, 1<<11)
		for j := range dp[i] {
			dp[i][j] = -1
		}
	}
	l := 0
	for n > 0 {
		l++
		a[l] = n % 10
		n /= 10
	}
	var dfs func(int, int, bool, bool) int
	dfs = func(pos, mask int, lead, limit bool) int {
		if pos <= 0 {
			if lead {
				return 0
			}
			return 1
		}
		if !lead && !limit && dp[pos][mask] != -1 {
			return dp[pos][mask]
		}
		ans := 0
		up := 9
		if limit {
			up = a[pos]
		}
		for i := 0; i <= up; i++ {
			if ((mask >> i) & 1) == 1 {
				continue
			}
			if i == 0 && lead {
				ans += dfs(pos-1, mask, lead, limit && i == up)
			} else {
				ans += dfs(pos-1, mask|1<<i, false, limit && i == up)
			}
		}
		if !lead && !limit {
			dp[pos][mask] = ans
		}
		return ans
	}

	return dfs(l, 0, true, true)
}
```

### **...**

```

```

<!-- tabs:end -->
