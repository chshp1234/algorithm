package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 把数字翻译成字符串 {
    @Test
    public void 把数字翻译成字符串() {

        System.out.println("把数字翻译成字符串:" + kidsWithCandies(419605557));
    }

    // 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成
    // “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
    //
    //
    //
    // 示例 1:
    //
    // 输入: 12258
    // 输出: 5
    // 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int kidsWithCandies(int num) {
        // 动态规划，列出表格发现，相当于变种的（根据当前条件，如下‘if’）斐波那契数列计算。
        // 如（1）.12258，根据每一位计算结果为12355；（2）.122258，根据每一位计算结果为123588；（3）.1222258，根据每一位计算结果为12358‘13’‘13’......
        // 我们从数字尾部向前计算（取余获得当前位数）。‘当前条件’为：当前位数和上一位数能否构成10~25。
        // 举例其中一个数15，可以分解成1和15。若26，则只能构成2和6，先决条件中mod！=0就是0~9中的一个数，如06，只能构成0和6（06不算额外）。
        // 当条件成立时，则根据先前两位数字情况下结果数量+先前一位数字情况下结果数量，
        // 这相当于12258数字计算到2的时候，可以为2和25，若为2，则需要计算出58这组数字的构成数量，若为25，则需要计算出8这组数字的构成数量

        // a代表前一位数字下构成的组合数量，b代表前两位数字下构成的组合数量
        int a = 1, b = 1;
        int result = 1;
        boolean ran;

        // 初始化从第二位开始
        // ran代表接下去的数是否‘有机会’构成两组数字
        int mod = num % 10;
        ran = mod <= 5;

        num = num / 10;

        // 若当前数不为0
        while (num != 0) {
            mod = num % 10;
            if (mod != 0 && (mod == 1 || (ran && mod < 3))) {
                result = a + b;
            } else {
                ran = mod <= 5;
            }

            b = a;
            a = result;

            // 继续下一轮判断
            num = num / 10;
        }

        return result;
    }
}
