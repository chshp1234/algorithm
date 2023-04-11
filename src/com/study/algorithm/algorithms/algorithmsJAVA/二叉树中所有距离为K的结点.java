package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
//
//返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
//
// 
//
//示例 1：
//
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//输出：[7,4,1]
//解释：
//所求结点为与目标结点（值为 5）距离为 2 的结点，
//值分别为 7，4，以及 1
//
//
//
//注意，输入的 "root" 和 "target" 实际上是树上的结点。
//上面的输入仅仅是对这些对象进行了序列化描述。
// 
//
//提示：
//
//给定的树是非空的。
//树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
//目标结点 target 是树上的结点。
//0 <= K <= 1000.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 二叉树中所有距离为K的结点 {
    //深度优先搜索
    //1.先找到目标节点target
    //2.从目标节点开始搜索其子节点，每向下一层，k-1，直到k=0时，将该层节点加入结果
    //3.返回其父节点，搜索父节点另一边子节点
    List<Integer> distanceKList = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findTarget(root, target, k);
        return distanceKList;
    }

    //返回-1，此层子节点搜索完毕
    public int findTarget(TreeNode node, TreeNode target, int k) {

        if (node == null) {
            return -1;
        }

        //找到目标节点
        if (node == target) {
            //距离目标节点距离0，就是目标节点自己
            if (k == 0) {
                distanceKList.add(node.val);
                return -1;
            } else {
                //否则搜索左右子节点
                findInChild(node.left, k - 1);
                findInChild(node.right, k - 1);

                //向上父节点返回距离（说明父节点应搜索其另一边k-1距离的子节点）
                return k - 1;
            }
        }

        //搜索左子节点，（k-leftK）表示其到目标节点的距离，那么还需向下或向上搜索leftK的距离的节点
        int leftK = findTarget(node.left, target, k);
        //leftK>=0,说明目标节点为此节点的左子节点
        //若值为0，则此节点距离目标节点距离为k
        if (leftK == 0) {
            distanceKList.add(node.val);
            return -1;
        }
        //若值大于0，则搜索其右子节点，搜索距离为leftK-1
        if (leftK > 0) {
            findInChild(node.right, leftK - 1);
            return leftK - 1;
        }

        //同上
        int rightK = findTarget(node.right, target, k);
        if (rightK == 0) {
            distanceKList.add(node.val);
            return -1;
        }
        if (rightK > 0) {
            findInChild(node.left, rightK - 1);
            return rightK - 1;
        }

        //未搜索到目标节点
        return -1;
    }

    //从node开始向下搜索子节点，距离node节点k的子节点
    public void findInChild(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        if (k == 0) {
            distanceKList.add(node.val);
        }
        if (k > 0) {
            findInChild(node.left, k - 1);
            findInChild(node.right, k - 1);
        }
    }
}
