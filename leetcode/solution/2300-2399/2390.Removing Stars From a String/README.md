# [2390. 从字符串中移除星号](https://leetcode.cn/problems/removing-stars-from-a-string)

[English Version](/solution/2300-2399/2390.Removing%20Stars%20From%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个包含若干星号 <code>*</code> 的字符串 <code>s</code> 。</p>

<p>在一步操作中，你可以：</p>

<ul>
	<li>选中 <code>s</code> 中的一个星号。</li>
	<li>移除星号 <strong>左侧</strong> 最近的那个 <strong>非星号</strong> 字符，并移除该星号自身。</li>
</ul>

<p>返回移除 <strong>所有</strong> 星号之后的字符串<strong>。</strong></p>

<p><strong>注意：</strong></p>

<ul>
	<li>生成的输入保证总是可以执行题面中描述的操作。</li>
	<li>可以证明结果字符串是唯一的。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "leet**cod*e"
<strong>输出：</strong>"lecoe"
<strong>解释：</strong>从左到右执行移除操作：
- 距离第 1 个星号最近的字符是 "lee<em><strong>t</strong></em>**cod*e" 中的 't' ，s 变为 "lee*cod*e" 。
- 距离第 2 个星号最近的字符是 "le<em><strong>e</strong></em>*cod*e" 中的 'e' ，s 变为 "lecod*e" 。
- 距离第 3 个星号最近的字符是 "leco<em><strong>d</strong></em>*e" 中的 'd' ，s 变为 "lecoe" 。
不存在其他星号，返回 "lecoe" 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "erase*****"
<strong>输出：</strong>""
<strong>解释：</strong>整个字符串都会被移除，所以返回空字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母和星号 <code>*</code> 组成</li>
	<li><code>s</code> 可以执行上述操作</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈模拟**

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeStars(self, s: str) -> str:
        stk = []
        for c in s:
            if c == '*':
                stk.pop()
            else:
                stk.append(c)
        return ''.join(stk)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '*') {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string removeStars(string s) {
        string ans;
        for (char c : s) {
            if (c == '*') ans.pop_back();
            else ans.push_back(c);
        }
        return ans;
    }
};
```

### **Go**

```go
func removeStars(s string) string {
	ans := []rune{}
	for _, c := range s {
		if c == '*' {
			ans = ans[:len(ans)-1]
		} else {
			ans = append(ans, c)
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function removeStars(s: string): string {
    const stack = [];
    for (const c of s) {
        if (c === '*') {
            stack.pop();
        } else {
            stack.push(c);
        }
    }
    return stack.join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn remove_stars(s: String) -> String {
        let mut res = String::new();
        for &c in s.as_bytes().iter() {
            if c == b'*' {
                res.pop();
            } else {
                res.push(char::from(c));
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
