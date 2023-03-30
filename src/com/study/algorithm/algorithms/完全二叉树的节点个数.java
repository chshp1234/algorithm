package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

// 给出一个完全二叉树，求出该树的节点个数。
//
// 说明：
//
// 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h
//  个节点。
//
// 示例:
//
// 输入:
//    1
//   / \
//  2   3
// / \  /
// 4  5 6
//
// 输出: 6
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 完全二叉树的节点个数 {

    @Test
    public void 完全二叉树的节点个数() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(2);
        //        treeNode.right.left = new TreeNode(2);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("完全二叉树的节点个数:" + countNode(treeNode));
    }

    int count = 0;

    public int countNodes(TreeNode root) {
        // 直接遍历整棵树，统计数量

        traversalNodes(root);
        return count;
    }

    public void traversalNodes(TreeNode root) {
        if (root == null) {
            return;
        }
        count++;
        traversalNodes(root.left);
        traversalNodes(root.right);
    }

    public int countNodesByBinarySearch(TreeNode root) {
        // 二分法
        // 对于最大层数为 h 的完全二叉树，节点个数一定在 [2^h,2^{h+1}-1] 的范围内，可以在该范围内通过二分查找的方式得到完全二叉树的节点个数。
        //
        // 具体做法是，根据节点个数范围的上下界得到当前需要判断的节点个数 k，
        // 如果第 k 个节点存在，则节点个数一定大于或等于 k，如果第 k 个节点不存在，则节点个数一定小于k，由此可以将查找的范围缩小一半，直到得到节点个数。
        //
        // 如何判断第 k 个节点是否存在呢？
        // 如果第 k 个节点位于第 h 层，则 k 的二进制表示包含 h+1 位，其中最高位是 1，其余各位从高到低表示从根节点到第 k个节点的路径，
        // 0 表示移动到左子节点，1 表示移动到右子节点。
        // 通过位运算得到第 k 个节点对应的路径，判断该路径对应的节点是否存在，即可判断第 k 个节点是否存在。
        // 比如节点12（1100），表示当前节点在第4层，除去最高位的‘1’，二进制为‘100’，所以节点12在‘右子树-左子树-左子树’的位置上。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/wan-quan-er-cha-shu-de-jie-dian-ge-shu-by-leetco-2/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    public int countNode(TreeNode root) {
        // “二分”法
        // 不同于上一种二分法，这里我们不用上下界来判断。而且只需要计算其中一边的子树数量。
        //
        // 首先，我们来对 root 节点的左右子树进行高度统计，分别记为 left 和 right，有以下两种结果：
        // left == right。这说明，左子树一定是满二叉树，因为最后一层中节点已经填充到右子树了，左子树必定已经填满了。
        // 所以左子树的节点总数我们可以直接得到，是 2^left -1，加上当前这个 root 节点，则正好是 2^left。再对右子树进行递归统计。
        // left != right。说明右子树一定是满二叉树，因为左子树在最后一层中还没填充，可以直接得到右子树的节点个数。
        // 同理，右子树节点 +root 节点（+1），总数为2^right。再对左子树进行递归查找。
        //
        // 作者：xiao-ping-zi-5
        // 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/chang-gui-jie-fa-he-ji-bai-100de-javajie-fa-by-xia/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        if (root == null) {
            return 0;
        }
        int leftLevel = countLevel(root.left);
        int rightLevel = countLevel(root.right);
        if (leftLevel == rightLevel) {
            return (1 << leftLevel) + countNode(root.right);
        } else {
            return (1 << rightLevel) + countNode(root.left);
        }
    }

    public int countLevel(TreeNode root) {

        int count = 0;
        while (root != null) {
            count++;
            root = root.left;
        }
        return count;
    }
}
