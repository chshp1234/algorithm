package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.Node4;
import org.junit.Test;

//给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。
//
//你需要返回能表示矩阵的 四叉树 的根结点。
//
//注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
//
//四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
//
//val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
//isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
//class Node {
//    public boolean val;
//    public boolean isLeaf;
//    public Node topLeft;
//    public Node topRight;
//    public Node bottomLeft;
//    public Node bottomRight;
//}
//我们可以按以下步骤为二维区域构建四叉树：
//
//如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
//如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
//使用适当的子网格递归每个子节点。
//
//
//如果你想了解更多关于四叉树的内容，可以参考 wiki 。
//
//四叉树格式：
//
//输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
//
//它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
//
//如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
//
// 
//
//示例 1：
//
//
//
//输入：grid = [[0,1],[1,0]]
//输出：[[0,1],[1,0],[1,1],[1,1],[1,0]]
//解释：此示例的解释如下：
//请注意，在下面四叉树的图示中，0 表示 false，1 表示 True 。
//
//示例 2：
//
//
//
//输入：grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
//输出：[[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
//解释：网格中的所有值都不相同。我们将网格划分为四个子网格。
//topLeft，bottomLeft 和 bottomRight 均具有相同的值。
//topRight 具有不同的值，因此我们将其再分为 4 个子网格，这样每个子网格都具有相同的值。
//解释如下图所示：
//
//示例 3：
//
//输入：grid = [[1,1],[1,1]]
//输出：[[1,1]]
//示例 4：
//
//输入：grid = [[0]]
//输出：[[1,0]]
//示例 5：
//
//输入：grid = [[1,1,0,0],[1,1,0,0],[0,0,1,1],[0,0,1,1]]
//输出：[[0,1],[1,1],[1,0],[1,0],[1,1]]
// 
//
//提示：
//
//n == grid.length == grid[i].length
//n == 2^x 其中 0 <= x <= 6
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/construct-quad-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 建立四叉树 {
    @Test
    public void 建立四叉树() {
        int[][] richer = {
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}
        };

        System.out.println("建立四叉树：" + construct(richer));
    }

    public Node4 construct(int[][] grid) {
        //递归+分治
        //分治构建4叉树的4个节点
        //1.若当前节点只有一个格子，则遍历到子节点，返回该子节点
        //2.若4个子节点均是叶子节点并且值都相同，则该节点也是叶子节点
        //3.若4个子节点均是叶子节点，但值不相同，或者其中有个子节点不是叶子节点，则该节点不是叶子节点
        return constructNode4(grid, 0, grid.length - 1, 0, grid.length - 1);
    }

    public Node4 constructNode4(int[][] grid, int leftR, int rightR, int leftC, int rightC) {
        if (leftR == rightR) {
            return new Node4(grid[leftC][leftR] == 1, true);
        }

        int halfR = leftR + ((rightR - leftR) >> 1);
        int halfC = leftC + ((rightC - leftC) >> 1);
        Node4 node4_1 = constructNode4(grid, leftR, halfR, leftC, halfC);
        Node4 node4_2 = constructNode4(grid, halfR + 1, rightR, leftC, halfC);
        Node4 node4_3 = constructNode4(grid, leftR, halfR, halfC + 1, rightC);
        Node4 node4_4 = constructNode4(grid, halfR + 1, rightR, halfC + 1, rightC);

        if (node4_1.isLeaf && node4_2.isLeaf && node4_3.isLeaf && node4_4.isLeaf) {
            if (node4_1.val == node4_2.val && node4_1.val == node4_3.val && node4_1.val == node4_4.val) {
                return new Node4(node4_1.val, true);
            }
        }
        Node4 result = new Node4(true, false);
        result.topLeft = node4_1;
        result.topRight = node4_2;
        result.bottomLeft = node4_3;
        result.bottomRight = node4_4;

        return result;
    }
}
