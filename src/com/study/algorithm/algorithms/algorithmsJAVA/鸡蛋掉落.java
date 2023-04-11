package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

@Deprecated
public class 鸡蛋掉落 {

    @Test
    public void 鸡蛋掉落() {
        System.out.println("鸡蛋掉落:" + superEggDrop(2, 3));
    }
    /**
     * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
     *
     * <p>每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
     *
     * <p>你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
     *
     * <p>每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
     *
     * <p>你的目标是确切地知道 F 的值是多少。
     *
     * <p>无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
     *
     * <p>
     *
     * <p>示例 1：
     *
     * <p>输入：K = 1, N = 2 输出：2 解释： 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
     * 如果它没碎，那么我们肯定知道 F = 2 。 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。 示例 2：
     *
     * <p>输入：K = 2, N = 6 输出：3 示例 3：
     *
     * <p>输入：K = 3, N = 14 输出：4
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/super-egg-drop
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    Integer[][] memo = null;
    /*todo 未完成*/
    public int superEggDrop(int K, int N) {
        memo = new Integer[K + 1][N + 1];
        return dp(K, N);
    }

    public int dp(int k, int n) {
        // 添加一个备忘录 消除之前算过的重叠子问题
        //        if (memo[k][n] != null) return memo[k][n];

        if (n == 0) {
            memo[k][n] = 0;
            //            System.out.printf("当前楼层数:%d\t当前鸡蛋数:%d\t当前结果:%d\n", n, k, 0);
            return 0;
        }
        if (k == 1) {
            memo[k][n] = n;
            System.out.printf("当前楼层数:%d\t当前鸡蛋数:%d\t当前结果:%d\n", n, k, n);
            return n;
        }

        int result = Integer.MAX_VALUE;

        for (int i = 1; i < n + 1; i++) {
            // 当选择在第i层楼扔了鸡蛋之后 可能出现鸡蛋碎了和鸡蛋没碎两种情况：
            // 当鸡蛋碎了 问题状态转嫁为求k-1个鸡蛋 搜索的楼层区间变为1~i-1共i-1层楼时求解
            // 当鸡蛋没碎 问题状态转嫁为鸡蛋的个数K不变 搜索楼层区间变为i+1~N共N-i层楼时求解
            // 最终以i层为切入点求解的答案 为两种状态的最坏情况 并加上i层时操作1 并更新最小值
            result = Math.min(result, Math.max(dp(k - 1, i - 1), dp(k, n - i)) + 1);
            System.out.printf("当前楼层数:%d\t当前鸡蛋数:%d\t当前结果:%d\t当前第%d层\n", n, k, result, i);
        }

        // 添加到备忘录里
        memo[k][n] = result;
        // 返回当前k个鸡蛋n层楼时求解的子问题的结果
        return result;
    }
}
