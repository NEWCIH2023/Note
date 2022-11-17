# [1189. “气球” 的最大数量](https://leetcode.cn/problems/maximum-number-of-balloons)

[English Version](/solution/1100-1199/1189.Maximum%20Number%20of%20Balloons/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>text</code>，你需要使用 <code>text</code> 中的字母来拼凑尽可能多的单词&nbsp;<strong>&quot;balloon&quot;（气球）</strong>。</p>

<p>字符串&nbsp;<code>text</code> 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词&nbsp;<strong>&quot;balloon&quot;</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1189.Maximum%20Number%20of%20Balloons/images/1536_ex1_upd.jpeg" style="height: 35px; width: 154px;"></strong></p>

<pre><strong>输入：</strong>text = &quot;nlaebolko&quot;
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1189.Maximum%20Number%20of%20Balloons/images/1536_ex2_upd.jpeg" style="height: 35px; width: 233px;"></strong></p>

<pre><strong>输入：</strong>text = &quot;loonbalxballpoon&quot;
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>text = &quot;leetcode&quot;
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 10^4</code></li>
	<li><code>text</code>&nbsp;全部由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

简单计数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxNumberOfBalloons(self, text: str) -> int:
        counter = Counter(text)
        counter['l'] >>= 1
        counter['o'] >>= 1
        return min(counter[c] for c in 'balon')
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] counter = new int[26];
        for (char c : text.toCharArray()) {
            ++counter[c - 'a'];
        }
        counter['l' - 'a'] >>= 1;
        counter['o' - 'a'] >>= 1;
        int ans = 10000;
        for (char c : "balon".toCharArray()) {
            ans = Math.min(ans, counter[c - 'a']);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function maxNumberOfBalloons(text: string): number {
    let targets: Set<string> = new Set('balloon'.split(''));
    let cnt = new Array(126).fill(0);
    for (let char of text) {
        if (targets.has(char)) {
            cnt[char.charCodeAt(0)]++;
        }
    }
    cnt['l'.charCodeAt(0)] >>= 1;
    cnt['o'.charCodeAt(0)] >>= 1;
    let ans = Number.MAX_SAFE_INTEGER;
    for (let char of targets) {
        ans = Math.min(cnt[char.charCodeAt(0)], ans);
    }
    return ans;
}
```

```ts
function maxNumberOfBalloons(text: string): number {
    const map = new Map([
        ['b', 0],
        ['a', 0],
        ['l', 0],
        ['o', 0],
        ['n', 0],
    ]);
    for (const c of text) {
        if (map.has(c)) {
            map.set(c, map.get(c) + 1);
        }
    }
    map.set('l', Math.floor(map.get('l') / 2));
    map.set('o', Math.floor(map.get('o') / 2));
    let res = Infinity;
    for (const value of map.values()) {
        res = Math.min(res, value);
    }
    return res;
}
```

### **C++**

```cpp
class Solution {
public:
    int maxNumberOfBalloons(string text) {
        vector<int> counter(26);
        for (char& c : text) ++counter[c - 'a'];
        counter['l' - 'a'] >>= 1;
        counter['o' - 'a'] >>= 1;
        int ans = 10000;
        string t = "balon";
        for (char& c : t) ans = min(ans, counter[c - 'a']);
        return ans;
    }
};
```

### **Go**

```go
func maxNumberOfBalloons(text string) int {
	counter := make([]int, 26)
	for i := range text {
		counter[text[i]-'a']++
	}
	counter['l'-'a'] >>= 1
	counter['o'-'a'] >>= 1
	ans := 10000
	t := "balon"
	for i := range t {
		ans = min(ans, counter[t[i]-'a'])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_number_of_balloons(text: String) -> i32 {
        let mut arr = [0; 5];
        for c in text.chars() {
            match c {
                'b' => arr[0] += 1,
                'a' => arr[1] += 1,
                'l' => arr[2] += 1,
                'o' => arr[3] += 1,
                'n' => arr[4] += 1,
                _ => {}
            }
        }
        arr[2] /= 2;
        arr[3] /= 2;
        let mut res = i32::MAX;
        for num in arr {
            res = res.min(num);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
