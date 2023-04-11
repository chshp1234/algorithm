package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 最佳观光组合 {

    @Test
    public void 最佳观光组合() {

        int[] pillars = {2, 10, 9, 3, 2};

        System.out.println("最佳观光组合:" + largestRectangleArea(pillars));
    }

    // 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
    //
    // 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
    //
    // 返回一对观光景点能取得的最高分。
    //
    //
    //
    // 示例：
    //
    // 输入：[8,1,5,2,6]
    // 输出：11
    // 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/best-sightseeing-pair
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int largestRectangleArea(int[] A) {

        // 我们考虑从前往后枚举 j 来统计答案，对于每个观光景点 j 而言，我们需要遍历 [0,j−1] 的观光景点 i 来计算组成观光组合 (i,j)
        // 得分的最大值 cntj来作为第 j 个观光景点的值，那么最后的答案无疑就是所有观光景点值的最大值，即 maxj=0..n−1 {cnt j}。
        // 但是枚举 j 需要 O(n) 的时间复杂度，遍历[0,j−1] 的观光景点 i 也需要O(n) 的时间复杂度，因此该方法总复杂度为O(n^2)，
        // 不能通过所有测试用例，我们需要进一步优化时间复杂度。
        //
        // 我们回过头来看得分公式，我们可以将其拆分成 A[i]+i 和 A[j]−j 两部分，这样对于统计景点 j 答案的时候，由于 A[j]−j
        // 是固定不变的，因此最大化 A[i]+i+A[j]−j 的值其实就等价于求 [0,j−1] 中 A[i]+i 的最大值mx，景点 j 的答案即为 mx+A[j]−j 。
        // 而 mxmx 的值我们只要从前往后枚举 j 的时候同时维护即可，这样每次枚举景点 j的时候，寻找使得得分最大的 i 就能从 O(n) 降至O(1) 的时间复杂度，
        // 总时间复杂度就能从 O(n^2) 降至 O(n)。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/best-sightseeing-pair/solution/zui-jia-guan-guang-zu-he-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int result = 0, lastMaxi = A[0];

        // 我们可以将其拆分成 A[i]+i 和 A[j]−j 两部分
        for (int j = 1, l = A.length; j < l; j++) {
            result = Math.max(result, lastMaxi + A[j] - j);
            // 边遍历边维护A[i]+i的最大值
            lastMaxi = Math.max(lastMaxi, A[j] + j);
        }

        return result;
    }

}
