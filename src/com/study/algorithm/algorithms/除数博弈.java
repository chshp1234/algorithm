package com.study.algorithm.algorithms;

import org.junit.Test;

public class 除数博弈 {
    @Test
    public void 除数博弈() {
        System.out.println("除数博弈:" + divisorGame(15));
    }

    // 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
    //
    // 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
    //
    // 选出任一 x，满足 0 < x < N 且 N % x == 0 。
    // 用 N - x 替换黑板上的数字 N 。
    // 如果玩家无法执行这些操作，就会输掉游戏。
    //
    // 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
    //
    //
    //
    // 示例 1：
    //
    // 输入：2
    // 输出：true
    // 解释：爱丽丝选择 1，鲍勃无法进行操作。
    // 示例 2：
    //
    // 输入：3
    // 输出：false
    // 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
    //
    //
    // 提示：
    //
    // 1 <= N <= 1000
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/divisor-game
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean divisorGame(int N) {
        // 方法一：动态规划：f(i)代表当前数字i的情况下爱丽丝(先手)能否取胜
        boolean[] dp = new boolean[N + 1];

        for (int i = 2; i <= N; i++) {

            // i的约数最大为i的一半
            int mi = i / 2;
            for (int j = 1; j <= mi; j++) {
                // 从1开始循环到mi，若当前数字j为i的约数并且dp[i-j]为false（代表在数字i-j下，爱丽丝先手则为false，反之若爱丽丝后手，则为true），
                if (i % j == 0 && !dp[i - j]) {
                    // 此时爱丽丝后手，所以设dp[i]=true;
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[N];

        // 方法二：规律
        // 根据官方提示：If the current number is even, we can always subtract a 1 to make it odd. If the
        // current number is odd, we must subtract an odd number to make it even.
        // 如果当前数是偶数，我们总是可以减去1使其为奇数。如果当前数是奇数，我们必须（只能）减去一个奇数使其为偶数。
        // 我们可以从1开始找找规律：
        // 1:F
        // 2:T
        // 3:F
        // ...
        // 13:F
        // 14:T
        // 15:F
        // ...
        // 所以根据规律可以看出，当N为偶数时，爱丽丝总能获胜，当N为奇数时，爱丽丝总是失败。
        // 因为0<x<N,所有最后剩余肯定为1，而先使数字到1的选手获胜，所有爱丽丝只要保证拿走一个数字时，剩余的数字为奇数，方可取得胜利。
        // return N % 2 == 0;
    }
}
