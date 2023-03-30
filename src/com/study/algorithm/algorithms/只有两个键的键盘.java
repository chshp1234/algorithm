package com.study.algorithm.algorithms;

//最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
//
//Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
//Paste（粘贴）：粘贴 上一次 复制的字符。
//给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
//
// 
//
//示例 1：
//
//输入：3
//输出：3
//解释：
//最初, 只有一个字符 'A'。
//第 1 步, 使用 Copy All 操作。
//第 2 步, 使用 Paste 操作来获得 'AA'。
//第 3 步, 使用 Paste 操作来获得 'AAA'。
//示例 2：
//
//输入：n = 1
//输出：0
// 
//
//提示：
//
//1 <= n <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/2-keys-keyboard
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 只有两个键的键盘 {
    public int minSteps(int n) {

        //分解质因数
        //递归解，每次找到n的第一个大于1的质因数i，然后再找i的第一个质因数j...直到参数小于等于5时，小于等于5的数k答案都为k
        //其中，两数之积，只需要最大遍历到sqrt(n)处即可，另，如果没找到，说明此数就为一个质数，无法进行分解，那么答案就为当前数
        if (n <= 1) {
            return 0;
        }
        if (n <= 5) {
            return n;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return minSteps(n / i) + i;
            }
        }

        return n;
    }

    public int minStepsByLeetCode(int n) {

        //分解质因数，递推式
        int ans = 0;
        for (int i = 2; i * i <= n; ++i) {
            while (n % i == 0) {
                n /= i;
                ans += i;
            }
        }
        if (n > 1) {
            ans += n;
        }
        return ans;
    }

//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/2-keys-keyboard/solution/zhi-you-liang-ge-jian-de-jian-pan-by-lee-ussa/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
