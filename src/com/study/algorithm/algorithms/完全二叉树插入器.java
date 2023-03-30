package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
//
//设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。
//
//实现 CBTInserter 类:
//
//CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
//CBTInserter.insert(int v)  向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态，并返回插入节点 TreeNode 的父节点的值；
//CBTInserter.get_root() 将返回树的头节点。
// 
//
//示例 1：
//
//
//
//输入
//["CBTInserter", "insert", "insert", "get_root"]
//[[[1, 2]], [3], [4], []]
//输出
//[null, 1, 2, [1, 2, 3, 4]]
//
//解释
//CBTInserter cBTInserter = new CBTInserter([1, 2]);
//cBTInserter.insert(3);  // 返回 1
//cBTInserter.insert(4);  // 返回 2
//cBTInserter.get_root(); // 返回 [1, 2, 3, 4]
// 
//
//提示：
//
//树中节点数量范围为 [1, 1000] 
//0 <= Node.val <= 5000
//root 是完全二叉树
//0 <= val <= 5000 
//每个测试用例最多调用 insert 和 get_root 操作 104 次
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/complete-binary-tree-inserter
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 完全二叉树插入器 {
    //队列
    //根据完全二叉树的性质，一个节点左子树为空，那么其右子树肯定为空，并且同一层的右边的节点的子节点都为空
    //一个节点的右子树不为空，那么起左子树不为空，并且同一层的左边的节点的子树都不为空
    //那么我们可以使用一个队列，用广度优先搜索的方式遍历二叉树
    //并且当一个节点的左子树为空，或者右子树为空时，说明遍历到了尾节点（最后一层的最后一个节点），不再遍历后续节点，后续节点将在队列中，以供插入时使用
    //插入时，取出队列头部节点，若其左子树为空，则将当前节点插入其左子树，并且不弹出该节点，否则插入到右子树中，并且弹出该节点，该节点也为待插入的节点的父节点

    class CBTInserter {

        private TreeNode root;
        private Queue<TreeNode> queue;

        public CBTInserter(TreeNode root) {
            this.root = root;
            queue = new LinkedList<>();
            queue.offer(root);
            TreeNode temp;
            while (true) {
                temp = queue.peek();
                if (temp.left == null || temp.right == null) {
                    if (temp.left != null) {
                        queue.offer(temp.left);
                    }
                    break;
                }
                queue.poll();
                queue.offer(temp.left);
                queue.offer(temp.right);
            }
        }

        public int insert(int val) {
            TreeNode top = queue.peek();
            if (top.left == null) {
                top.left = new TreeNode(val);
                queue.offer(top.left);
            } else {
                top.right = new TreeNode(val);
                queue.poll();
                queue.offer(top.right);
            }
            return top.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }
}
