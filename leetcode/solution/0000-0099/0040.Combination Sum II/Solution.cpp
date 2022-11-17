class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (s > target) return;
            if (s == target) {
                ans.emplace_back(t);
                return;
            }
            for (int j = i; j < candidates.size(); ++j) {
                if (j > i && candidates[j] == candidates[j - 1]) continue;
                t.emplace_back(candidates[j]);
                dfs(j + 1, s + candidates[j]);
                t.pop_back();
            }
        };
        dfs(0, 0);
        return ans;
    }
};