package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，用以表示树的 格式化布局 。构造此格式化布局矩阵需要遵循以下规则：
//
//树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
//矩阵的列数 n 应该等于 2height+1 - 1 。
//根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
//对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2height-r-1] ，右子节点放置在 res[r+1][c+2height-r-1] 。
//继续这一过程，直到树中的所有节点都妥善放置。
//任意空单元格都应该包含空字符串 "" 。
//返回构造得到的矩阵 res 。
//
// 
//
// 
//
//示例 1：
//
//
//输入：root = [1,2]
//输出：
//[["","1",""],
// ["2","",""]]
//示例 2：
//
//
//输入：root = [1,2,3,null,4]
//输出：
//[["","","","1","","",""],
// ["","2","","","","3",""],
// ["","","4","","","",""]]
// 
//
//提示：
//
//树中节点数在范围 [1, 210] 内
//-99 <= Node.val <= 99
//树的深度在范围 [1, 10] 内
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/print-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 输出二叉树 {
    //广度优先遍历
    //使用广度优先遍历,构造一颗满二叉树(空节点用null表示)
    //在对这颗满二叉树进行处理,构造我们需要的字符串矩阵,矩阵的层数tier即为二叉树的最大高度
    //首先,每一层需要的元素个数为(Math.pow(2, tier) - 1)
    //其次,每一层i中,元素的起始位置start=(int) (Math.pow(2, tier - 1 - i) - 1);
    //最后就是每一层相邻元素的步进数step=(int) Math.pow(2, tier - i);
    //那么每一层中,元素下标index与其所在层的列表的具体位置为start + step * index,我们只需要把具体位置的元素替换成二叉树的值,空元数据替换成""即可.
    public List<List<String>> printTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<TreeNode>> nodes = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();

        queue.offer(root);

        while (true) {
            int size = queue.size();
            List<TreeNode> list = new ArrayList<>(size);
            boolean next = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node);
                if (node != null) {
                    next = true;
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    queue.offer(null);
                    queue.offer(null);
                }
            }
            if (next) {
                nodes.add(list);
            } else {
                break;
            }
        }

        int tier = nodes.size();
        int size = (int) (Math.pow(2, tier) - 1);

        List<String> temp = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            temp.add("");
        }

        for (int i = 0; i < tier; i++) {
            List<TreeNode> nodeList = nodes.get(i);
            List<String> stringList = new ArrayList<>(temp);

            int start = (int) (Math.pow(2, tier - 1 - i) - 1);
            int step = (int) Math.pow(2, tier - i);
            for (int j = 0; j < nodeList.size(); j++) {
                if (nodeList.get(j) != null) {
                    stringList.set(start + step * j, String.valueOf(nodeList.get(j).val));
                }
            }
            result.add(stringList);
        }

        return result;
    }
}
