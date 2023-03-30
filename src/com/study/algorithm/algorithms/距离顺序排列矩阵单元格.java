package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

// 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
//
// 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
//
// 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1
// - c2|。（你可以按任何满足此条件的顺序返回答案。）
//
//
//
// 示例 1：
//
// 输入：R = 1, C = 2, r0 = 0, c0 = 0
// 输出：[[0,0],[0,1]]
// 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
// 示例 2：
//
// 输入：R = 2, C = 2, r0 = 0, c0 = 1
// 输出：[[0,1],[0,0],[1,1],[1,0]]
// 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
// [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
// 示例 3：
//
// 输入：R = 2, C = 3, r0 = 1, c0 = 2
// 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
// 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
// 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
//
//
// 提示：
//
// 1 <= R <= 100
// 1 <= C <= 100
// 0 <= r0 < R
// 0 <= c0 < C
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 距离顺序排列矩阵单元格 {

    @Test
    public void 距离顺序排列矩阵单元格() {

        System.out.println("距离顺序排列矩阵单元格:" + Arrays.deepToString(allCellsDistOrder(2, 2, 0, 1)));
    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        // 排序

        int len = R * C;
        int[][] result = new int[len][2];

        for (int i = 0; i < len; i++) {
            result[i][0] = i / C;
            result[i][1] = i % C;
        }

        Arrays.sort(
                result,
                (o1, o2) ->
                        Math.abs(o1[0] - r0)
                                + Math.abs(o1[1] - c0)
                                - (Math.abs(o2[0] - r0) + Math.abs(o2[1] - c0)));

        return result;

        // 方法二：桶排序
        // 注意到方法一中排序的时间复杂度太高。实际在枚举所有点时，我们可以直接按照哈曼顿距离分桶。这样我们就可以实现线性的桶排序。

        // 方法三：几何
        // 我们也可以直接变换枚举矩阵的顺序，直接按照曼哈顿距离遍历该矩形即可。
        //
        // 注意到曼哈顿距离相同的位置恰好构成一个斜着的正方形边框，因此我们可以从小到大枚举曼哈顿距离，并使用循环来直接枚举该距离对应的边框。
        // 我们每次从该正方形边框的上顶点出发，依次经过右顶点、下顶点和左顶点，最后回到上顶点。这样即可完成当前层的遍历。
        // 注意正方形边框中的部分点不一定落在矩阵中，所以我们需要做好边界判断。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order/solution/ju-chi-shun-xu-pai-lie-ju-zhen-dan-yuan-ge-by-leet/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }
}
