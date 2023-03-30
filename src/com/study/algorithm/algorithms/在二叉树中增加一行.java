package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。
//
//注意，根节点 root 位于深度 1 。
//
//加法规则如下:
//
//给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
//cur 原来的左子树应该是新的左子树根的左子树。
//cur 原来的右子树应该是新的右子树根的右子树。
//如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
// 
//
//示例 1:
//
//
//
//输入: root = [4,2,6,3,1,5], val = 1, depth = 2
//输出: [4,1,1,2,null,null,6,3,1,5]
//示例 2:
//
//
//
//输入: root = [4,2,null,3,1], val = 1, depth = 3
//输出:  [4,2,null,1,1,3,null,null,1]
// 
//
//提示:
//
//节点数在 [1, 104] 范围内
//树的深度在 [1, 104]范围内
//-100 <= Node.val <= 100
//-105 <= val <= 105
//1 <= depth <= the depth of tree + 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/add-one-row-to-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 在二叉树中增加一行 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        //广度优先遍历
        //首先如果depth为1，那么构造新的根节点，并将原根节点挂在此新根节点left下
        //否则：用层次遍历的方式，将depth-1层的节点存入队列中
        //再遍历此队列，将队列中的节点的左右子树设为新的值为val的节点，并将原左子节点挂在新左子节点的左子树下，将原右子节点挂在新右子节点的右子树下
        //返回root根节点
        if (depth == 1) {
            TreeNode result = new TreeNode(val);
            result.left = root;
            return result;

        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 1;
        while (!queue.isEmpty()) {
            if (++count == depth) {
                break;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode child = node.left;
            node.left = new TreeNode(val);
            node.left.left = child;

            child = node.right;
            node.right = new TreeNode(val);
            node.right.right = child;
        }

        return root;
    }
}
