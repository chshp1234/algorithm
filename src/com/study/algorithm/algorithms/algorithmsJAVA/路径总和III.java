package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;
import org.junit.Test;

//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
//
//路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
//
// 
//
//示例 1：
//
//
//
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
//示例 2：
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
//提示:
//
//二叉树的节点个数的范围是 [0,1000]
//-109 <= Node.val <= 109 
//-1000 <= targetSum <= 1000 
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/path-sum-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 路径总和III {
    @Test
    public void 路径总和III() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.right = new TreeNode(3);
        treeNode.right.right.right = new TreeNode(4);
        treeNode.right.right.right.right = new TreeNode(5);
        System.out.println("路径总和III：" + pathSum(treeNode, 3));
        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

    }

    int srcSum;

    public int pathSum(TreeNode root, int targetSum) {
        //穷举+深度优先遍历
        //因为路径必须连续，所以递归时，若不选取当前节点，那么就要以左右子节点为根节点进行查找
        //若选取当前节点，那么遍历左右子节点时，也必须连续选中
        srcSum = targetSum;
        return pathNewSum(root);
    }

    public int pathNewSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return pathNewSum(root.left) +
                pathNewSum(root.right) +
                pathChildSum(root.left, srcSum - root.val) +
                pathChildSum(root.right, srcSum - root.val) +
                (root.val - srcSum == 0 ? 1 : 0);
    }

    public int pathChildSum(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        return pathChildSum(root.left, target - root.val) +
                pathChildSum(root.right, target - root.val) +
                (root.val - target == 0 ? 1 : 0);
    }

    //方法二: 前缀和
    //思路与算法
    //
    //我们仔细思考一下，解法一中应该存在许多重复计算。
    // 我们定义节点的前缀和为：由根结点到当前结点的路径上所有节点的和。
    // 我们利用先序遍历二叉树，记录下根节点 root 到当前节点 p 的路径上除当前节点以外所有节点的前缀和，
    // 在已保存的路径前缀和中查找是否存在前缀和刚好等于当前节点到根节点的前缀和 curr 减去 targetSum。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/path-sum-iii/solution/lu-jing-zong-he-iii-by-leetcode-solution-z9td/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    //public int pathSum(TreeNode root, int targetSum) {
    //        HashMap<Long, Integer> prefix = new HashMap<>();
    //        prefix.put(0L, 1);
    //        return dfs(root, prefix, 0, targetSum);
    //    }
    //
    //    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
    //        if (root == null) {
    //            return 0;
    //        }
    //
    //        int ret = 0;
    //        curr += root.val;
    //
    //        ret = prefix.getOrDefault(curr - targetSum, 0);
    //        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
    //        ret += dfs(root.left, prefix, curr, targetSum);
    //        ret += dfs(root.right, prefix, curr, targetSum);
    //        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
    //
    //        return ret;
    //    }
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/path-sum-iii/solution/lu-jing-zong-he-iii-by-leetcode-solution-z9td/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}
