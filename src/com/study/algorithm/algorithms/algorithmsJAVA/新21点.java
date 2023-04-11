package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class 新21点 {

    @Test
    public void 新21点() {

        int N = 6, K = 3, W = 6;
        long start;
        long end;

        start = System.nanoTime();
        System.out.println("新21点:" + new21Game(N, K, W));
        end = System.nanoTime();
        System.out.println("用时:" + (end - start));

        start = System.nanoTime();
        System.out.println("新21点:" + new21GameByOneArray(N, K, W));
        end = System.nanoTime();
        System.out.println("用时:" + (end - start));

        start = System.nanoTime();
        System.out.println("新21点:" + new21GameOptimize(N, K, W));
        end = System.nanoTime();
        System.out.println("用时:" + (end - start));
    }

    // 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
    //
    // 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。
    // 每次抽取都是独立的，其结果具有相同的概率。
    //
    // 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
    //
    // 示例 1：
    //
    // 输入：N = 10, K = 1, W = 10
    // 输出：1.00000
    // 说明：爱丽丝得到一张卡，然后停止。
    // 示例 2：
    //
    // 输入：N = 6, K = 1, W = 10
    // 输出：0.60000
    // 说明：爱丽丝得到一张卡，然后停止。
    // 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
    // 示例 3：
    //
    // 输入：N = 21, K = 17, W = 10
    // 输出：0.73278
    // 提示：
    //
    // 0 <= K <= N <= 10000
    // 1 <= W <= 10000
    // 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
    // 此问题的判断限制时间已经减少。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/new-21-game
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /*根据状态N和K记录概率（可改为二维数组）*/
    Map<Integer, Map<Integer, Double>> map = new HashMap<>();

    // 递归，超时
    // 思路，备忘录map记录（N，K，W）状态下的概率
    public double new21Game(int N, int K, int W) {

        // 退出条件
        if (K <= 0 && N >= 0) {
            return 1;
        }
        if (K > N || N < 0) {
            return 0;
        }

        Double result;
        Map<Integer, Double> mapK;
        if ((mapK = map.get(N)) != null) {
            result = mapK.get(K);
            if (result != null) {
                return result;
            }
        }

        result = 0d;
        for (int i = 1; i <= W; i++) {
            // 每次从1开始到W
            // 计算（N - i, K - i, W）状态下的概率
            double resultInner = new21Game(N - i, K - i, W);
            if (resultInner == 0) {
                // 剪枝，若结果为0，则后续都为0不用进行后续计算
                break;
            }
            // 当前抽中数字的概率均为(1d / W)
            result += (1d / W) * resultInner;
        }

        if (mapK == null) {
            mapK = new HashMap<>();
        }
        mapK.put(K, result);
        map.put(N, mapK);

        return result;
    }

    // 一维数组迭代计算,依旧超时：（
    // 根据图表规律可得，当前状态等于上一阶段状态*rate（1 / W）
    // 若当前状态下（N,K,W）,抽中数字x（几率为1/W），则状态转移到（N-1,K-1,W），结果再*rate，累加...循环Math.min(N,W)次（表示，抽中1，抽中2，抽中3...一直到抽中Math.min(N,W)）
    public double new21GameByOneArray(int N, int K, int W) {

        double[] temps = new double[W];

        double rate = (1d / W);
        int difference = N - K;

        // 初始化当前状态都为1
        for (int i = 0; i < W; i++) {
            temps[i] = 1;
        }

        // 从 difference + 1开始（此时表格开始斜向上计算）
        for (int i = difference + 1; i <= N; i++) {
            double newValue = 0;
            // 若当前状态下（N,K,W）,抽中数字x（几率为1/W），则状态转移到（N-1,K-1,W），结果再*rate，累加...循环Math.min(N,
            // W)次（表示，抽中1，抽中2，抽中3...一直到抽中Math.min(N,W)）
            double current;
            double last = temps[W - 1];
            for (int j = 0, l = Math.min(i, W); j < l; j++) {
                current = temps[W - j - 1];
                newValue += current * rate;
                // 数组值前移一位，并使得最新值放在数组最后一位
                temps[W - j - 1] = last;
                last = current;
            }
            // 数组值前移一位，并使得最新值放在数组最后一位
            //            System.arraycopy(temps, 1, temps, 0, W - 1);
            temps[W - 1] = newValue;
        }

        // 数组最后一位即为结果
        return temps[W - 1];
    }

    // 爱丽丝获胜的概率只和下一轮开始前的得分有关，因此根据得分计算概率。
    //
    // 令 dp[x]dp[x] 表示从得分为 xx 的情况开始游戏并且获胜的概率，目标是求 dp[0]dp[0] 的值。
    //
    // 根据规则，当分数达到或超过 KK 时游戏结束，游戏结束时，如果分数不超过 NN 则获胜，如果分数超过 NN 则失败。因此当 K\leq x\leq \min(N,
    // K+W-1)K≤x≤min(N,K+W−1) 时有 dp[x]=1dp[x]=1，当 x>\min(N, K+W-1)x>min(N,K+W−1) 时有 dp[x]=0dp[x]=0。
    //
    // 为什么分界线是 \min(N, K+W-1)min(N,K+W−1)？首先，只有在分数不超过 NN 时才算获胜；其次，可以达到的最大分数为
    // K+W-1K+W−1，即在最后一次抽取数字之前的分数为 K-1K−1，并且抽到了 WW。
    //
    // 当 0\leq x < K0≤x<K 时，如何计算 dp[x]dp[x] 的值？注意到每次在范围 [1, W][1,W]
    // 内随机抽取一个整数，且每个整数被抽取到的概率相等，因此可以得到如下状态转移方程：
    //
    // dp[x]=\frac{dp[x+1]+dp[x+2]+\cdots+dp[x+W]}{W}
    // dp[x]=
    // W
    // dp[x+1]+dp[x+2]+⋯+dp[x+W]
    // ​
    //
    // 考虑对 dpdp 的相邻项计算差分，有如下结果：
    //
    // dp[x] - dp[x+1]=\frac{dp[x+1] - dp[x+W+1]}{W}
    // dp[x]−dp[x+1]=
    // W
    // dp[x+1]−dp[x+W+1]
    // ​
    //
    //
    // 其中 0\leq x<K-10≤x<K−1。
    //
    // 因此可以得到新的状态转移方程：
    //
    // dp[x]=dp[x+1]-\frac{dp[x+W+1]-dp[x+1]}{W}
    // dp[x]=dp[x+1]−
    // W
    // dp[x+W+1]−dp[x+1]
    // ​
    //
    //
    // 其中 0\leq x<K-10≤x<K−1。
    //
    // 注意到上述状态转移方程中 xx 的取值范围，当 x=K-1x=K−1 时不适用。因此对于 dp[K-1]dp[K−1] 的值，需要通过
    //
    // dp[K-1]=\frac{dp[K]+dp[K+1]+\cdots+dp[K+W-1]}{W}
    // dp[K−1]=
    // W
    // dp[K]+dp[K+1]+⋯+dp[K+W−1]
    // ​
    //
    //
    // 计算得到。注意到只有当 K\leq x\leq \min(N, K+W-1)K≤x≤min(N,K+W−1) 时才有 dp[x]=1dp[x]=1，因此
    //
    // dp[K-1]=\frac{\min(N, K+W-1) - K + 1}{W} = \frac{\min(N-K+1,W)}{W}
    // dp[K−1]=
    // W
    // min(N,K+W−1)−K+1
    // ​
    // =
    // W
    // min(N−K+1,W)
    // ​
    //
    //
    // 可在 O(1)O(1) 时间内计算得到 dp[K-1]dp[K−1] 的值。
    //
    // 对于 dp[K-2]dp[K−2] 到 dp[0]dp[0] 的值，则可通过新的状态转移方程得到。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/new-21-game/solution/xin-21dian-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public double new21GameOptimize(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }

        // 相比上述方法，以空间换时间
        double[] dp = new double[K + W];
        for (int i = K; i <= N && i < K + W; i++) {
            dp[i] = 1.0;
        }
        dp[K - 1] = 1.0 * Math.min(N - K + 1, W) / W;
        for (int i = K - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + W + 1] - dp[i + 1]) / W;
        }
        return dp[0];
    }
}
