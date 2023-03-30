package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;
import org.junit.Test;

//你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
//
//空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
//
//示例 1:
//
//输入: 二叉树: [1,2,3,4]
//       1
//     /   \
//    2     3
//   /
//  4
//
//输出: "1(2(4))(3)"
//
//解释: 原本将是“1(2(4)())(3())”，
//在你省略所有不必要的空括号对之后，
//它将是“1(2(4))(3)”。
//示例 2:
//
//输入: 二叉树: [1,2,3,null,4]
//       1
//     /   \
//    2     3
//     \
//      4
//
//输出: "1(2()(4))(3)"
//
//解释: 和第一个示例相似，
//除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/construct-string-from-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 根据二叉树创建字符串 {

    @Test
    public void 根据二叉树创建字符串() {
        //前序遍历二叉树
        //用DFS前序遍历二叉树：
        //遍历子节点前，字符串拼接"("，遍历子节点后,字符串拼接")"
        //1.如果当前节点为空，直接返回;否则加入当前节点值
        //2.如果没有子节点，直接返回
        //3.如果没有右子节点，递归遍历左子节点
        //4.递归遍历左右子节点
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.right.right = new TreeNode(4);
//        treeNode.left.left = new TreeNode(0);
        //        int[] b = {5, 2, 6, 2, 3, 2};
        System.out.println("根据二叉树创建字符串：" + tree2str(treeNode));
    }
    public String tree2str(TreeNode root) {
        StringBuilder result = new StringBuilder();
        tree2str(root, result);

        return result.toString();
    }

    public void tree2str(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        sb.append(root.val);

        if (root.left == null && root.right == null) {
            return;
        }

        sb.append("(");
        tree2str(root.left, sb);
        sb.append(")");

        if (root.right == null) {
            return;
        }

        sb.append("(");
        tree2str(root.right, sb);
        sb.append(")");
    }
}
