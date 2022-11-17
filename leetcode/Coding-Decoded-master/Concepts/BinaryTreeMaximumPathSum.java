public class Solution {

    private int maxValue = Integer.MIN_VALUE;
    // Current Node acting as the junction, Max Path Sum
     private Map<TreeNode, Integer> map = new HashMap<>();

    public int maxPathSum(TreeNode root) {

        dfs(root);
        return maxValue;
    }


    private int dfs(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int leftMaxPath = 0;
        int rightMaxPath = 0;

        if(map.containsKey(node.left)){
            leftMaxPath = map.get(node.left);
        } else {
            leftMaxPath = Math.max(0, dfs(node.left));
        }

         if(map.containsKey(node.right)){
            rightMaxPath = map.get(node.right);
        } else {
            rightMaxPath = Math.max(0, dfs(node.right));
         }

        maxValue = Math.max(maxValue, leftMaxPath + rightMaxPath + node.val);

        map.put(node, maxValue);

        return Math.max(leftMaxPath, rightMaxPath) + node.val;
    }
}