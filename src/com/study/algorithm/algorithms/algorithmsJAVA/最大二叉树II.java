package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

//最大树 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。
//
//给你最大树的根节点 root 和一个整数 val 。
//
//就像 之前的问题 那样，给定的树是利用 Construct(a) 例程从列表 a（root = Construct(a)）递归地构建的：
//
//如果 a 为空，返回 null 。
//否则，令 a[i] 作为 a 的最大元素。创建一个值为 a[i] 的根节点 root 。
//root 的左子树将被构建为 Construct([a[0], a[1], ..., a[i - 1]]) 。
//root 的右子树将被构建为 Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]) 。
//返回 root 。
//请注意，题目没有直接给出 a ，只是给出一个根节点 root = Construct(a) 。
//
//假设 b 是 a 的副本，并在末尾附加值 val。题目数据保证 b 中的值互不相同。
//
//返回 Construct(b) 。
//
// 
//
//示例 1：
//
//
//
//输入：root = [4,1,3,null,null,2], val = 5
//输出：[5,4,null,1,3,null,null,2]
//解释：a = [1,4,2,3], b = [1,4,2,3,5]
//示例 2：
//
//
//输入：root = [5,2,4,null,1], val = 3
//输出：[5,2,4,null,1,null,3]
//解释：a = [2,1,5,4], b = [2,1,5,4,3]
//示例 3：
//
//
//输入：root = [5,2,3,null,1], val = 4
//输出：[5,2,4,null,1,3]
//解释：a = [2,1,5,3], b = [2,1,5,3,4]
// 
//
//提示：
//
//树中节点数目在范围 [1, 100] 内
//1 <= Node.val <= 100
//树中的所有值 互不相同
//1 <= val <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-binary-tree-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大二叉树II {
    public TreeNode insertIntoMaxTreeByStack(TreeNode root, int val) {
        //栈
        //使用栈存储所有右子节点
        //再把val根栈顶的节点node一个个作对比
        //如果node.val < val,那么继续出栈,判断下一个栈顶节点
        //否则,这个栈顶节点将是新节点的父节点,node.right将是新节点的左子节点,新节点是node的右子节点
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode dummy = new TreeNode(101);
        dummy.right = root;
        deque.push(dummy);

        while (root != null) {
            deque.push(root);
            root = root.right;
        }

        while (true) {
            root = deque.pop();
            if (root.val > val) {
                TreeNode r = new TreeNode(val);
                r.left = root.right;
                root.right = r;
                break;
            }
        }

        return dummy.right;
    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        //直接遍历右子节点
        //用一个指针指向父节点,一个指针指向父节点的右子节点
        //判断右子节点的值是否大于val
        //如果右子节点为空,或者右子节点的值小于val,那么,这个新节点将挂在此父节点的右子节点下,并且原先父节点的右子节点将在新节点的左子节点下
        TreeNode dummy = new TreeNode(101);
        dummy.right = root;

        TreeNode parent = dummy;

        while (root != null && root.val > val) {
            parent = root;
            root = root.right;
        }
        TreeNode r = new TreeNode(val);
        r.left = parent.right;
        parent.right = r;

        return dummy.right;
    }
}
