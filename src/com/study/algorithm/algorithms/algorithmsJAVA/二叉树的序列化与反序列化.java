package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的序列化与反序列化 {
    @Test
    public void 二叉树的序列化与反序列化() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(2);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.left.left = new TreeNode(3);
        treeNode.right.left.right = new TreeNode(1);

        Codec codec = new Codec();
        codec.deserialize(codec.serialize(treeNode));

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        //        System.out.println("二叉树的序列化与反序列化:" + treeNode);
    }

    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
     * right; TreeNode(int x) { val = x; } }
     */

    // 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
    //
    // 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
    //
    // 示例:
    //
    // 你可以将以下二叉树：
    //
    //    1
    //   / \
    //  2   3
    //     / \
    //    4   5
    //
    // 序列化为 "[1,2,3,null,null,4,5]"
    // 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
    //
    // 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public class Codec {

        // 二叉树的序列化本质上是对其值进行编码，更重要的是对其结构进行编码。可以遍历树来完成上述任务。众所周知，我们一般有两个策略：BFS / DFS。
        //
        // BFS 可以按照层次的顺序从上到下遍历所有的节点
        // DFS 可以从一个根开始，一直延伸到某个叶，然后回到根，到达另一个分支。根据根节点、左节点和右节点之间的相对顺序，可以进一步将DFS策略区分为：
        // ·先序遍历
        // ·中序遍历
        // ·后序遍历
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/er-cha-shu-de-xu-lie-hua-yu-fan-xu-lie-hua-by-le-2/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }

            // 使用层次遍历（BFS）
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            StringBuilder stringBuilder = new StringBuilder();
            TreeNode treeNode;
            while (!queue.isEmpty()) {
                treeNode = queue.poll();
                if (treeNode == null) {
                    stringBuilder.append("null").append(",");
                } else {
                    stringBuilder.append(treeNode.val).append(",");
                    queue.offer(treeNode.left);
                    queue.offer(treeNode.right);
                }
            }
            /*while (stringBuilder.charAt(stringBuilder.length() - 1) < 48
                    || stringBuilder.charAt(stringBuilder.length() - 1) > 57) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }*/
            System.out.println("serialize:" + root);
            System.out.println("serialize:" + stringBuilder.toString());
            return stringBuilder.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] split = data.split(",");

            System.out.println("deserialize:" + data);
            System.out.println("deserialize:" + Arrays.toString(split));

            int index = 0;
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(split[index]));
            queue.offer(root);

            TreeNode temp;
            while (!queue.isEmpty()) {
                temp = queue.poll();
                if (temp != null) {
                    temp.left = analysis(split[++index]);
                    queue.offer(temp.left);
                    temp.right = analysis(split[++index]);
                    queue.offer(temp.right);
                }
            }

            //            levelTraversal(split, 0, root);
            System.out.println("deserialize:" + root);
            return root;
        }

        private TreeNode analysis(String node) {
            if ("null".equals(node)) {
                return null;
            }
            return new TreeNode(Integer.parseInt(node));
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));
}
