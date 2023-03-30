package com.study.algorithm.algorithms;

import org.junit.Test;

//给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
//
//（当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
//
//示例 1:
//
//输入: N = 10
//输出: 9
//示例 2:
//
//输入: N = 1234
//输出: 1234
//示例 3:
//
//输入: N = 332
//输出: 299
//说明: N 是在 [0, 10^9] 范围内的一个整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/monotone-increasing-digits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 单调递增的数字 {

    @Test
    public void 单调递增的数字() {
        System.out.println("单调递增的数字:" + monotoneIncreasingDigits(1234));
    }

    public int monotoneIncreasingDigits(int N) {

//         贪心
//         思路：从题目我们可以看出这里有3个条件
//         1.结果数字要小于等于给定的数字
//         2.结果数字中每一位从左到右单调递增（可以相等）
//         3.结果数字满足以上两个条件下要尽量大
//         其实总结出这三个条件时，就可以发现（再加上例子）：
//         1.源数字N满足就满足条件，N即为结果
//         2.源数字不满足条件，则结果数字中，末尾几位必定是9（请再次看以上3个条件）
//         所以我们只要找出从第几位开始，末尾填充9
//
//         解：我们从末尾（个位数）开始，依次和高位进行比较：
//         若低位大于等于高位数字，则继续下一轮比较（满足条件2）
//         若低位比高位小于高位数字，则记录此低位位置，因为此位以及之后的数字将成为999...（满足条件3）。并且此高位需要-1（满足条件1）
//         此时高位数字的值将成为下一次遍历时的低位数字值
//         继续遍历，直到高位数为0（遍历到头）为止
//         最后计算结果
//         若果要改变的位置为0，说明源数字符合条件；
//         否则，结果为，要改变的位数之后的数字全为9，且高位-1.

        // 低位数字
        int pre = N % 10;
        // 高位数字
        int next;

        // 当前遍历的数字
        int current = N / 10;

        // 需要改变的位置
        int changeIndex = 0;

        for (int i = 1; current > 0; i++) {
            // 取余操作来获取个位上的数字
            next = current % 10;

            // 判断高低位数字大小
            if (next > pre) {
                next -= 1;
                changeIndex = i;
            }
            pre = next;
            current = current / 10;
        }

        if (changeIndex == 0) {
            return N;
        }

        changeIndex = (int) Math.pow(10, changeIndex);

        // 需要改变源数字
        return ((N / changeIndex) - 1) * changeIndex + changeIndex - 1;
    }
}
