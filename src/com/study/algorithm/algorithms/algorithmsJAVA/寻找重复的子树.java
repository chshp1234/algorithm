package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;
import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

//给定一棵二叉树 root，返回所有重复的子树。
//
//对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
//
//如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
//
// 
//
//示例 1：
//
//
//
//输入：root = [1,2,3,4,null,2,4,null,null,4]
//输出：[[2,4],[4]]
//示例 2：
//
//
//
//输入：root = [2,1,1]
//输出：[[1]]
//示例 3：
//
//
//
//输入：root = [2,2,2,3,null,3,null]
//输出：[[2,3],[3]]
// 
//
//提示：
//
//树中的结点数在[1,10^4]范围内。
//-200 <= Node.val <= 200
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-duplicate-subtrees
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 寻找重复的子树 {
    @Test
    public void 寻找重复的子树() {
        TreeNode treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(0);
        treeNode.left.left = new TreeNode(0);
        treeNode.left.right = new TreeNode(0);

        /*List<Integer> list = new ArrayList<>();
        list.add(1, 1);*/

        System.out.println("寻找重复的子树:" + findDuplicateSubtreesByLeetCode(treeNode));
    }

    Set<String> treeNodes = new HashSet<>();
    Set<String> nodeHash = new HashSet<>();

    List<TreeNode> result = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        //哈希表
        //将二叉树序列化为一个字符串，再将字符串存入哈希表中
        //遍历的每个子树都按上述操作，当匹配到相同的字符串时，说明已存在重复子树，加入结果中
        hashWithDfs(root);
        return result;
    }

    public String hashWithDfs(TreeNode root) {
        if (root == null) {
            return "null";
        }
        String left = hashWithDfs(root.left);
        String right = hashWithDfs(root.right);

        String hash = root.val + "/" + left + "\\" + right;
        if (!nodeHash.add(hash)) {
            if (treeNodes.add(hash)) {
                result.add(root);
            }
        }
        return hash;
    }

    Map<String, Pair<TreeNode, Integer>> seen = new HashMap<>();
    Set<TreeNode> repeat = new HashSet<TreeNode>();
    int idx = 0;

    public List<TreeNode> findDuplicateSubtreesByLeetCode(TreeNode root) {
        //通过方法一中的递归序列化技巧，我们可以进一步进行优化。
        //
        //我们可以用一个三元组直接表示一棵子树，即(x,l,r)，它们分别表示：
        //
        //根节点的值为 x；
        //左子树的序号为 l；
        //右子树的序号为 r。
        //
        //这里的「序号」指的是：每当我们发现一棵新的子树，就给这棵子树一个序号，用来表示其结构。
        //那么两棵树是重复的，当且仅当它们对应的三元组完全相同。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode.cn/problems/find-duplicate-subtrees/solution/xun-zhao-zhong-fu-de-zi-shu-by-leetcode-zoncw/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        dfs(root);
        return new ArrayList<>(repeat);
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int[] tri = {node.val, dfs(node.left), dfs(node.right)};
        String hash = Arrays.toString(tri);
        if (seen.containsKey(hash)) {
            Pair<TreeNode, Integer> pair = seen.get(hash);
            repeat.add(pair.getKey());
            return pair.getValue();
        } else {
            seen.put(hash, new Pair<>(node, ++idx));
            return idx;
        }
    }
}
