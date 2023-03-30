package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
// 的深度尽可能大（一个节点也可以是它自己的祖先）。”
//
// 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
//
//
// 示例 1:
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
// 输出: 3
// 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
// 示例 2:
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
// 输出: 5
// 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
//
//
// 说明:
//
// 所有节点的值都是唯一的。
// p、q 为不同节点且均存在于给定的二叉树中。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Deprecated
public class 二叉树的最近公共祖先 {

    @Test
    public void 二叉树的最近公共祖先() {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(5);
        treeNode.right = new TreeNode(1);
        //        treeNode.left.left = new TreeNode(6);
        treeNode.left.left = new TreeNode(6);
        treeNode.left.right = new TreeNode(2);
        treeNode.left.right.left = new TreeNode(7);
        treeNode.left.right.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(0);
        treeNode.right.right = new TreeNode(8);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println(
                "二叉树的最近公共祖先:" + lowestCommonAncestorByDfs(treeNode, treeNode.left, treeNode.right).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //层次遍历
        //使用层次遍历构造一颗满二叉树，并且分别记录p节点和q节点的下标pi和qi
        //比较p和q的下标pi和qi的大小，若pi>qi,则pi=pi/2，若pi<qi，则qi=qi/2
        //重复比较，直到pi=qi，此值即为公共祖先在满二叉树中的坐标位置
        if (root == null) {
            return null;
        }

        //方便查找到根节点，需构造满二叉树
        //在满二叉树中，index下标的左子节点为index*2，右子节点为index*2+1
        //则当前节点下标index的根节点就为index/2，可很方便查找到根节点
        int pIndex = 0;
        int qIndex = 0;

        List<TreeNode> treeNodeList = new ArrayList<>();
        treeNodeList.add(null);
        treeNodeList.add(root);

        int index = 1;

        TreeNode temp;

        while (true) {
            temp = treeNodeList.get(index++);
            if (temp != null) {
                if (temp == p) {
                    pIndex = index - 1;
                } else if (temp == q) {
                    qIndex = index - 1;
                }

                if (pIndex > 0 && qIndex > 0) {
                    break;
                }

                treeNodeList.add(temp.left);
                treeNodeList.add(temp.right);
            } else {
                //构造满二叉树，空子节点也需要加入
                treeNodeList.add(null);
                treeNodeList.add(null);
            }
        }

        while (pIndex != qIndex) {
            if (pIndex > qIndex) {
                pIndex = pIndex >> 1;
            } else {
                qIndex = qIndex >> 1;
            }
        }

        return treeNodeList.get(pIndex);
    }

    public TreeNode lowestCommonAncestorByDfs(TreeNode root, TreeNode p, TreeNode q) {
        //深度优先搜索
        //深度优先搜索二叉树，跟踪记录根节点
        //当两个节点都没匹配到时，根节点为当前遍历的节点
        //若找到其中一个节点，则将当前的根节点传递下去
        //直到匹配到另一个节点为止，返回当前传递下来的根节点
        if (root == p || root == q) {
            return root;
        }
        return dfs(root, p, q, root, new boolean[]{false, false});
    }

    public TreeNode dfs(TreeNode root, TreeNode p, TreeNode q, TreeNode parent, boolean[] find) {
        if (root == null) {
            return null;
        }
        if (root == p) {
            find[0] = true;
            //若都匹配到
            if (find[0] && find[1]) {
                return parent;
            }
        } else if (root == q) {
            find[1] = true;
            //若都匹配到
            if (find[0] && find[1]) {
                return parent;
            }
        }
        TreeNode nodeRoot;
        //若当前匹配到p或q，则将根节点parent向下传递
        if (find[0] || find[1]) {
            nodeRoot = dfs(root.left, p, q, parent, find);
            if (nodeRoot == null) {
                nodeRoot = dfs(root.right, p, q, parent, find);
            }
            return nodeRoot;
        }
        //否则，传递的根节点为待搜索的节点本事
        nodeRoot = dfs(root.left, p, q, root.left, find);
        if (nodeRoot == null) {
            if (find[0] || find[1]) {
                nodeRoot = dfs(root.right, p, q, parent, find);
            } else {
                nodeRoot = dfs(root.right, p, q, root.right, find);
            }
        }
        return nodeRoot;
    }

    //哈希表存储父节点
    //我们可以用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，
    //并记录已经访问过的节点，再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，
    //那么这个节点就是我们要找的最近公共祖先。
    //
    //算法
    //
    //1.从根节点开始遍历整棵二叉树，用哈希表记录每个节点的父节点指针。
    //2.从 p 节点开始不断往它的祖先移动，并用数据结构记录已经访问过的祖先节点。
    //3.同样，我们再从 q 节点开始不断往它的祖先移动，如果有祖先已经被访问过，即意味着这是 p 和 q 的深度最深的公共祖先，即 LCA 节点。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-6fdt7/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestorByMap(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }
}
