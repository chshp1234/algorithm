package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class N叉树的前序遍历 {
    public List<Integer> preorder(NodeN root) {
        //dfs前序遍历，先遍历根节点，在遍历子节点
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            dfs(result, root);
        }
        return result;
    }

    public void dfs(List<Integer> result, NodeN root) {
        result.add(root.val);

        if (root.children != null) {
            for (NodeN child : root.children) {
                dfs(result, child);
            }
        }
    }

    //方法三：迭代优化
    //思路
    //
    //在前序遍历中，我们会先遍历节点本身，然后从左向右依次先序遍历该每个以子节点为根的子树，
    //此时利用栈先进后出的原理，依次从右向左将子节点入栈，这样出栈的时候即可保证从左向右依次遍历每个子树。
    //参考方法二的原理，可以提前将后续需要访问的节点压入栈中，这样就可以避免记录每个节点的子节点访问数量。
    //
    //首先把根节点入栈，因为根节点是前序遍历中的第一个节点。
    //随后每次我们从栈顶取出一个节点 uu，它是我们当前遍历到的节点，并把 u 的所有子节点从右向左逆序压入栈中，这样出栈的节点则是顺序从左向右的。
    //例如 uu 的子节点从左到右为 v_1, v_2, v_3v，那么入栈的顺序应当为 v_3, v_2, v_1v，
    //这样就保证了下一个遍历到的节点（即 u 的左侧第一个孩子节点 v_1）出现在栈顶的位置。
    //此时，访问第一个子节点 v_1时，仍然按照此方法则会先访问 v_1的左侧第一个孩子节点。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/solution/n-cha-shu-de-qian-xu-bian-li-by-leetcode-bg99/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public List<Integer> preorderByLeetcode(NodeN root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<NodeN> stack = new ArrayDeque<NodeN>();
        stack.push(root);
        while (!stack.isEmpty()) {
            NodeN NodeN = stack.pop();
            res.add(NodeN.val);
            for (int i = NodeN.children.size() - 1; i >= 0; --i) {
                stack.push(NodeN.children.get(i));
            }
        }
        return res;
    }

}

class NodeN {
    public int val;
    public List<NodeN> children;

    public NodeN() {
    }

    public NodeN(int _val) {
        val = _val;
    }

    public NodeN(int _val, List<NodeN> _children) {
        val = _val;
        children = _children;
    }
}
