package com.study.algorithm.algorithms;

import org.junit.Test;

public class 不同的二叉搜索树 {

    @Test
    public void 不同的二叉搜索树() {
        System.out.println("不同的二叉搜索树:" + numTrees(3));
    }

    // 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
    //
    // 示例:
    //
    // 输入: 3
    // 输出: 5
    // 解释:
    // 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
    //
    //   1         3     3      2      1
    //    \       /     /      / \      \
    //     3     2     1      1   3      2
    //    /     /       \                 \
    //   2     1         2                 3
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int numTrees(int n) {

        int[] DP = new int[n + 1];

        // 边界条件，当序列长度为1或者0时，都只有一中情况
        DP[1] = 1;
        DP[0] = 1;

        // 递推计算数列长度为i时，可创建的二叉搜索树的数量，最后答案即为DP[n]
        for (int i = 2; i <= n; i++) {

            // 在数列1~i中，依次设定以1~i为根节点时，计算二叉搜索树数量
            for (int j = 1; j <= i; j++) {
                // 其中DP[i]（序列长度为1时的二叉搜索树的数量）为左子树可创建的数量*右子树可创建的数量
                // 其中左子树为数列1~j-1，右子树为1~i-j（虽然右子树起始值为j+1,但是右子树中可创建的树的数量和数列内容无关，只和序列的长度有关）
                DP[i] += DP[j - 1] * DP[i - j];
            }
        }

        // 递归求解
        // numTree(DP, n);

        return DP[n];
    }

    private int numTree(int[] dp, int n) {
        // 当已有记录的结果时，直接返回值
        if (dp[n] != 0) {
            return dp[n];
        }

        // 在数列1~n中，依次设定以1~n为根节点时，计算二叉搜索树数量
        for (int i = 1; i <= n; i++) {
            // 以i为根节点，可创建的二叉搜索树数量，并记录累加到dp[n]中
            // 递归计算其左子树可创建的数量和右子树可创建的数量，结果相乘（笛卡尔积）
            // 其中左子树为数列1~i-1，右子树为1~n-i（虽然右子树起始值为i+1,但是右子树中可创建的树的数量和数列内容无关，只和序列的长度有关）
            dp[n] += numTree(dp, i - 1) * numTree(dp, n - i);
        }

        return dp[n];
    }

    public int numTreesByCatalanNumber(int n) {
        // 事实上我们在方法一中推导出的 G(n)函数的值在数学上被称为卡塔兰数
        // C(0)=1,C(n+1)=2*(2*n+1)*C(n)/(n+2)
        //
        // 作者：=LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        // 提示：我们在这里需要用 long 类型防止计算过程中的溢出
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }
}
