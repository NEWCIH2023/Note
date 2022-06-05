## 基本

```shell script

// 显示各提交关系
git log --graph

// 查看最近几次提交日志
git log -n 3
git log -3

// 一行输出
git log --oneline

// 查看统计
git log --stat

```

## 暂存

```shell script

// 将文件移出暂存区
git reset HEAD blah.txt

// 显示当前版本库中HEAD提交与暂存区之间的不同之处
git diff --staged

// 不带任何选项，diff显示工作区中尚未被注册的本地修改。即暂存区与工作区之间的不同
git diff

// 忽略一个已经被版本化的文件 (使.gitignore生效)
git update-index --assume-unchanged


```

## 储存

```shell script

// 保存修改
git stash

// 恢复位于栈顶的被储存修改
git stash pop

// 列出储存
git stash list

// 恢复更早之前的储存
git stash pop stash@{1}

```

## 提交

```shell script

// 两次提交之间
git diff 77d231f HEAD

// 与上一次提交进行比较
git diff 77d231f^!

// 限制文件范围
git diff 77d231f 05bcfd1 -- book/bisection/

// 统计修改情况
git diff --stat 77d231f 05bcfd1

```

## 解决冲突

```shell script

// 启动合并工具
git mergetool --tool=vimdiff3

// 取消合并操作
git reset --merge

```

## 分支

```shell script

// 拉取远程分支并切换
git checkout -b 本地分支名 origin/远程分支名

```


