package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

//给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
//
// 
//
//示例 1：
//
//
//
//输入：root1 = [2,1,4], root2 = [1,0,3]
//输出：[0,1,1,2,3,4]
//示例 2：
//
//
//
//输入：root1 = [1,null,8], root2 = [8,1]
//输出：[1,1,8,8]
// 
//
//提示：
//
//每棵树的节点数在 [0, 5000] 范围内
//-105 <= Node.val <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 两棵二叉搜索树中的所有元素 {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        //二叉搜索树中序遍历
        //利用二叉搜索树树的性质，进行中序遍历后，可得到一个有序的递增的数组
        //将遍历完两个二叉搜索树后得到的数组进行合并（归并排序），即可到一个有序数组
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        dfs(list1, root1);
        dfs(list2, root2);

        int len1 = list1.size();
        int len2 = list2.size();

        int index1 = 0;
        int index2 = 0;

        List<Integer> result = new ArrayList<>();
        while (index1 < len1 && index2 < len2) {
            if (list1.get(index1) < list2.get(index2)) {
                result.add(list1.get(index1));
                index1++;
            } else {
                result.add(list2.get(index2));
                index2++;
            }
        }

        while (index1 < len1) {
            result.add(list1.get(index1));
            index1++;
        }

        while (index2 < len2) {
            result.add(list2.get(index2));
            index2++;
        }

        return result;

    }

    public void dfs(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(list, root.left);
        list.add(root.val);
        dfs(list, root.right);
    }
}
