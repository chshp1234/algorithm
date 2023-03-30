package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class 从中序与后序遍历序列构造二叉树 {

    @Test
    public void 从中序与后序遍历序列构造二叉树() {

        int[] inorder = {-4, -10, 3, -1, 7, 11, -8, 2};
        int[] postorder = {-4, -1, 3, -10, 11, -8, 2, 7};

        System.out.println(
                "从中序与后序遍历序列构造二叉树:" + TreeNode.toArrayString(buildTrees(inorder, postorder)));
    }

    Map<Integer, Integer> mapInorder = new HashMap<>();

    // 思路一：递归
    // 由后序遍历中，根节点是最后遍历的，所以，整个后续遍历数组中最后一个元素，为整棵树的根节点
    // 找到根节点元素在中序遍历中的位置，其中根节点左侧的元素为根节点的左子树，右侧的节点为根节点右子树
    // 其中左右子树也同样的规律（但需要注意，这颗子树的起止位置，在起止位置中间的才为此节点的子树节点）
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }

        int len = inorder.length;

        // 哈希表记录中序遍历中，各个元素的位置
        for (int i = 0; i < len; i++) {
            mapInorder.put(inorder[i], i);
        }

        TreeNode treeNode = buildTree(postorder, 0, len - 1, 0, len - 1);

        return treeNode;
    }

    public TreeNode buildTree(
            int[] postorder,
            // 中序遍历起始位置
            int inorderStart,
            // 中序遍历结束位置
            int inorderEnd,
            // 后续遍历起始位置
            int postorderStart,
            // 后续遍历结束为止
            int postorderEnd) {

        // 后序遍历中，区间的末尾节点为根节点
        TreeNode root = new TreeNode(postorder[postorderEnd]);

        if (postorderStart == postorderEnd) {
            return root;
        }

        // 获取此根节点在中序遍历中的位置
        int rootPosInorder = mapInorder.get(postorder[postorderEnd]);
        int lEnd = rootPosInorder - 1;
        int rStart = rootPosInorder + 1;

        // 如果根节点在区间左侧还有值，则这段元素为此节点的左子树
        if (lEnd >= inorderStart && lEnd <= inorderEnd) {
            root.left =
                    buildTree(
                            postorder,
                            inorderStart,
                            lEnd,
                            postorderStart,
                            // 后续遍历的结束位置，为起始位置+元素个数
                            postorderStart + lEnd - inorderStart);
        }

        // 如果根节点在区间右侧还有值，则这段元素为此节点的右子树
        if (rStart <= inorderEnd && rStart >= inorderStart) {

            root.right =
                    buildTree(
                            postorder,
                            rootPosInorder + 1,
                            inorderEnd,
                            // 后续遍历的开始位置，为结束位置-元素个数
                            postorderEnd - 1 - inorderEnd + rStart,
                            postorderEnd - 1);
        }

        return root;
    }

    // 思路二：从后续遍历依次填入根节点
    // 由后序遍历中，根节点是最后遍历的，所以，整个后续遍历数组中最后一个元素，为整棵树的根节点
    // ·先从最后一个元素填入根节点，并获取根节点值在中序遍历中的位置
    // ·从后序遍历的数组从后往前遍历，获取元素在中序遍历中的位置，如果值在根节点位置的右侧，则此节点存在于根节点的右子树当中，此时如果根节点右子树有值，则继续判断，
    // 如果根节点右子树没有值，则此元素就为根节点的右子树
    // ·同理可得左子树
    //
    // 官方题解很巧妙，也是从后序遍历的数组从后往前遍历，但构造时先构造右子树，在构造左子树，这样不用每次都从根节点判断当前元素是左子树的值还是右子树的值
    // 因为在后续遍历中，是遍历左、右、根的顺序，所以从后往前的顺序就为根右左，假如中序遍历中，根节点右侧有值（在范围内的有效值），则反向遍历时肯定是从右子树开始的
    //
    public TreeNode buildTrees(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }

        int len = inorder.length;

        TreeNode treeNode = new TreeNode(postorder[len - 1]);

        for (int i = 0; i < len; i++) {
            mapInorder.put(inorder[i], i);
        }

        for (int i = len - 2; i >= 0; i--) {
            buildTree(treeNode, postorder[i], mapInorder.get(postorder[i]));
        }

        return treeNode;
    }

    public void buildTree(TreeNode root, int val, int index) {
        // 如果次节点在中序遍历中根节点的右侧，则此节点存在于根节点的右子树当中
        if (index > mapInorder.get(root.val)) {
            // 如果右子树为空，则此元素为根节点的右子树
            if (root.right == null) {
                root.right = new TreeNode(val);
            }
            // 否则继续递归判断此元素和根节点右子树的关系
            else {
                buildTree(root.right, val, index);
            }
        }
        // 同理可得左子树
        else {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                buildTree(root.left, val, index);
            }
        }
    }
    // 为了高效查找根节点元素在中序遍历数组中的下标，我们选择创建哈希表来存储中序序列，即建立一个（元素，下标）键值对的哈希表。
    //
    // 定义递归函数 helper(in_left, in_right) 表示当前递归到中序序列中当前子树的左右边界，递归入口为helper(0, n - 1) ：
    //
    // ·如果 in_left > in_right，说明子树为空，返回空节点。
    //
    // ·选择后序遍历的最后一个节点作为根节点。
    //
    // ·利用哈希表 O(1) 查询当根节点在中序遍历中下标为 index。从 in_left 到 index - 1 属于左子树，从 index + 1 到 in_right属于右子树。
    //
    // ·根据后序遍历逻辑，递归创建右子树 helper(index + 1, in_right) 和左子树 helper(in_left, index -1)。
    // 注意这里有需要先创建右子树，再创建左子树的依赖关系。
    // 可以理解为在后序遍历的数组中整个数组是先存储左子树的节点，再存储右子树的节点，最后存储根节点，
    // 如果按每次选择「后序遍历的最后一个节点」为根节点，则后续遍历中先被构造出来的应该为右子树。
    //
    // ·返回根节点 root。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/cong-zhong-xu-yu-hou-xu-bian-li-xu-lie-gou-zao-14/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    // int post_idx;
    // int[] postorder;
    // int[] inorder;
    // Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();
    //
    // public TreeNode helper(int in_left, int in_right) {
    //     // 如果这里没有节点构造二叉树了，就结束
    //     if (in_left > in_right) {
    //         return null;
    //     }
    //
    //     // 选择 post_idx 位置的元素作为当前子树根节点
    //     int root_val = postorder[post_idx];
    //     TreeNode root = new TreeNode(root_val);
    //
    //     // 根据 root 所在位置分成左右两棵子树
    //     int index = idx_map.get(root_val);
    //
    //     // 下标减一
    //     post_idx--;
    //     // 构造右子树
    //     root.right = helper(index + 1, in_right);
    //     // 构造左子树
    //     root.left = helper(in_left, index - 1);
    //     return root;
    // }
    //
    // public TreeNode buildTree(int[] inorder, int[] postorder) {
    //     this.postorder = postorder;
    //     this.inorder = inorder;
    //     // 从后序遍历的最后一个元素开始
    //     post_idx = postorder.length - 1;
    //
    //     // 建立（元素，下标）键值对的哈希表
    //     int idx = 0;
    //     for (Integer val : inorder) {
    //         idx_map.put(val, idx++);
    //     }
    //
    //     return helper(0, inorder.length - 1);
    // }
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/cong-zhong-xu-yu-hou-xu-bian-li-xu-lie-gou-zao-14/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
