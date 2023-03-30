package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

//实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
//BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
//boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
//int next()将指针向右移动，然后返回指针处的数字。
//注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
//
//你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
//
// 
//
//示例：
//
//
//输入
//["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
//[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
//输出
//[null, 3, 7, true, 9, true, 15, true, 20, false]
//
//解释
//BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
//bSTIterator.next();    // 返回 3
//bSTIterator.next();    // 返回 7
//bSTIterator.hasNext(); // 返回 True
//bSTIterator.next();    // 返回 9
//bSTIterator.hasNext(); // 返回 True
//bSTIterator.next();    // 返回 15
//bSTIterator.hasNext(); // 返回 True
//bSTIterator.next();    // 返回 20
//bSTIterator.hasNext(); // 返回 False
// 
//
//提示：
//
//树中节点的数目在范围 [1, 105] 内
//0 <= Node.val <= 106
//最多调用 105 次 hasNext 和 next 操作
// 
//
//进阶：
//
//你可以设计一个满足下述条件的解决方案吗？
//next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存。其中 h 是树的高度。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-search-tree-iterator
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉搜索树迭代器 {
    //栈
    //平衡二叉树的中序遍历结果，就是一组升序的元素
    //我们并不需要刚开始就遍历整颗二叉树，因为最小的数肯定在左子树中，所以只要维护栈中的元素都是“左子树”即可
    //这个“左子树”并不是说整颗左子树，而是千真万确的左子树（所有tree.left,包括root）
    //当然还是要管右子树，根据中序遍历，我们遍历完“根节点”后，就需要遍历右节点，所以调用一次next()后就需要重新维护这个栈
    //将右子树的所有“左子树”加入栈中
    //由于栈的后进先出，所以后加入的最小值也将最先出栈
    Deque<TreeNode> deque = new LinkedList<>();

    public 二叉搜索树迭代器(TreeNode root) {
        push(root);
    }

    public int next() {
        //弹出栈顶元素
        TreeNode next = deque.pop();
        //维护右子树
        push(next.right);
        return next.val;
    }

    public boolean hasNext() {
        return !deque.isEmpty();
    }

    //维护栈中的“左子树”
    public void push(TreeNode root) {
        if (root == null) {
            return;
        }
        deque.push(root);
        //只加入左子树到栈中
        push(root.left);
    }
}
