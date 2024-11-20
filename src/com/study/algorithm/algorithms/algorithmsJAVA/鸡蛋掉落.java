package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//

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
public class 鸡蛋掉落 {

    @Test
    public void 鸡蛋掉落() {
        System.out.println("鸡蛋掉落:" + superEggDrop(8, 7813));
    }

    public int superEggDrop(int K, int N) {

        //# 递推，动态规划
        //根据解题思路，设：dp[k][n]：k个鸡蛋n层楼最多需要操作几次。
        //
        //dp[k][n]=min(max(dp[k-1][0],dp[k][n-1]..max(dp[k-1][i],dp[k][n-i-1]..max(dp[k-1][n-1],dp[k][0])))
        //
        //## 优化
        //1. 其实不难得出dp[k][n]肯定小于等于dp[k-1][n]，因为毕竟在楼层一样的情况下，鸡蛋数多的，需要操作的数肯定只会少不会多。
        //那么在求解dp[k][n]的过程中，可以从max(dp[k-1][n/2],dp[k][n-1-n/2])开始遍历判断。因为虽然dp[k-1][0]...dp[k-1][n/2]的值会更小，但dp[k][n-1-n/2]...dp[k][0]的值会更大，那么对其max肯定得到一个更大的值，所以首先就从一半的楼层开始判断。
        //2. 根据优化1，可以在dp[k-1][i]<=dp[k][n-i-1]时结束判断。因为一开始判断时dp[k-1][n/2]>=dp[k][n-1-n/2]，那么在继续遍历的过程中，dp[k-1][i]（i<n/2）的值将会越来越小，而dp[k-1][n-1-i]的值将会越来越大，那么当dp[k-1][i]<=dp[k][n-i-1]时，就是在i+1层楼扔鸡蛋进行判断，所需要的最少的操作次数。赋值，退出循环。
        //3. 根据优化2，可以进一步使用二分来查找第一次出现dp[k-1][i]<=dp[k][n-i-1]的地方（大家可手动尝试(●ˇ∀ˇ●)）。
        //4. 由于dp[k][n]只和dp[k-1][n]有关，这里也可以使用滚动数组进行空间上的优化。
        int[][] dp = new int[K][N + 1];
        for (int i = 1; i <= N; i++) {
            dp[0][i] = i;
        }
        for (int k = 1; k < K; k++) {
            dp[k][1] = 1;
            for (int n = 2; n <= N; n++) {
//                int res = Math.max(dp[k - 1][n / 2], dp[k][n - 1 - n / 2]) + 1;
//                for (int i = n / 2; i >= 0; i--) {
//                    res = Math.min(res, Math.max(dp[k - 1][i], dp[k][n - i - 1]) + 1);
//                }

                //优化
                //根据二分的思路，从一半的位置开始查找
                //并且当dp[k - 1][mid] == dp[k][n - 1 - mid]时，退出循环。因为再继续遍历，dp[k][n - i - 1]只会越来越大或者相等，那么将不会有更小的值存在。
                int res = Integer.MAX_VALUE;
                for (int i = n / 2; i >= 0; i--) {
                    if (dp[k - 1][i] <= dp[k][n - 1 - i]) {
                        res = Math.min(res, Math.max(dp[k - 1][i], dp[k][n - i - 1]) + 1);
                        break;
                    }
                }
                dp[k][n] = res;
            }
        }
        return dp[K - 1][N];
    }


    int[][] mem;

    @Test
    public void 鸡蛋掉落2() {
        mem = new int[9][7814];
        System.out.println("鸡蛋掉落:" + memorySearch(8, 7813));
    }

    public int memorySearch(int K, int N) {
        //同递推思路
        //1. 因为每次都从一半的楼层开始扔，所以有些（大部分）楼层是不用计算的，那么使用递归的方式，可以只计算应该计算的楼层。
        //当然也同递推的思路，当low=high时，不用再进行计算，因为后续答案只可能相同或者越来越大。
        //2. 将结果加入到一个备忘录mem中，以便后续递归时可以直接得到答案。
        //    - 当楼层数<=1时，返回答案是楼层数；
        //    - 当K==1时，说明只有一个鸡蛋，也返回答案是楼层数；
        //    - 当备忘录中有答案时，直接返回答案；
        //3. low = superEggDropByDp(K - 1, i);high = superEggDropByDp(K, N - i - 1);（思路同递推式）
        //4. 更新答案，并且当low==high时，退出循环；
        //5. 更新备忘录，返回答案；
        if (N <= 1) {
            return N;
        }
        if (mem[K][N] != 0) {
            return mem[K][N];
        }
        if (K == 1) {
            return N;
        }

        int res = Integer.MAX_VALUE;
        for (int i = N / 2; i >= 0; i--) {
            int low = memorySearch(K - 1, i);
            int high = memorySearch(K, N - i - 1);
            if (low <= high) {
                res = Math.min(res, Math.max(low, high) + 1);
                break;
            }
        }
        mem[K][N] = res;
        return res;
    }
}
