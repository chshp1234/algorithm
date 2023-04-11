package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

//给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
//
//两个节点之间的路径长度 由它们之间的边数表示。
//
// 
//
//示例 1:
//
//
//
//输入：root = [5,4,5,1,1,5]
//输出：2
//示例 2:
//
//
//
//输入：root = [1,4,5,4,4,5]
//输出：2
// 
//
//提示:
//
//树的节点数的范围是 [0, 104] 
//-1000 <= Node.val <= 1000
//树的深度将不超过 1000 
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/longest-univalue-path
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最长同值路径 {
    int maxCount = 0;

    public int longestUnivaluePath(TreeNode root) {
        //深度优先搜索
        //1.算出左子节点最长同值点数leftCount
        //2.算出左子节点最长同值点数rightCount
        //如果根节点和左右子节点都相同,那么更新最大节点数maxCount = Math.max(maxCount, leftCount + rightCount + 1),同时从左右子节点数选出最长的一条路径,+1后向父节点传递
        //如果根节点和左子节点相同, 那么同值节点数为leftCount + 1,再向父节点传递
        //如果根节点和右子节点相同, 那么同值节点数为rightCount + 1,再向父节点传递
        //否则,同值节点数只为当前节点,就是1
        if (root == null) {
            return 0;
        }
        dfs(root);
        return maxCount - 1;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int count = 1;

        int leftCount = dfs(root.left);
        int rightCount = dfs(root.right);
        if (leftCount > 0 && rightCount > 0) {
            if (root.val == root.left.val && root.val == root.right.val) {
                count = Math.max(leftCount, rightCount) + 1;
                maxCount = Math.max(maxCount, leftCount + rightCount + 1);
                return count;
            } else if (root.val == root.left.val) {
                count = leftCount + 1;
            } else if (root.val == root.right.val) {
                count = rightCount + 1;
            }
        } else if (leftCount > 0) {
            if (root.val == root.left.val) {
                count = leftCount + 1;
            }
        } else if (rightCount > 0) {
            if (root.val == root.right.val) {
                count = rightCount + 1;
            }
        }

        maxCount = Math.max(maxCount, count);
        return count;
    }
}
