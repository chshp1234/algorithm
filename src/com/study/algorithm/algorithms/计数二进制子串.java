package com.study.algorithm.algorithms;

import org.junit.Test;

public class 计数二进制子串 {

    @Test
    public void 计数二进制子串() {
        System.out.println("计数二进制子串:" + countBinarySubstringsByTwoPoints("10101"));

        TestClass testClass = new TestClass(5);

        System.out.println("计数二进制子串:" + testClass.val);
    }

    // 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
    //
    // 重复出现的子串要计算它们出现的次数。
    //
    // 示例 1 :
    //
    // 输入: "00110011"
    // 输出: 6
    // 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
    //
    // 请注意，一些重复出现的子串要计算它们出现的次数。
    //
    // 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
    // 示例 2 :
    //
    // 输入: "10101"
    // 输出: 4
    // 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
    // 注意：
    //
    // s.length 在1到50,000之间。
    // s 只包含“0”或“1”字符。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/count-binary-substrings
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int countBinarySubstrings(String s) {
        // 连续，子串，相同
        // 由于对称性，计算当前符合条件的子串最大数量，结果即为数量的一半
        // 比如符合条件的字符000111，其中当前串的子串0011和01也符合条件

        // 前一个数字，后一个数字，结果
        int countP = 0, countN = 0, result = 0;
        // 前一个字符
        char pre = s.charAt(0);
        // 后一个字符
        char next = 0;
        countP++;
        for (int i = 1, len = s.length(); i < len; i++) {
            // 当前字符
            char curr = s.charAt(i);
            // 1.如果当前字符等于前一个字符，并且后一个数字计数为0，则说明现在正在遍历前一个数字(这个可以当作前置单独些，因为后面的计算中countN始终不为0)
            if (curr == pre && countN == 0) {
                countP++;
            }
            // 2.如果当前字符不等于前一个字符，并且前一个字符计数不为0，说明正在遍历后一个字符
            else if (curr != pre && countP != 0) {
                next = curr;
                countN++;
            }
            // 3.如果此时字符等于前一个字符（并且后一个字符已经进行了计算），说明一轮已经计算结束，进行统计
            else if (curr == pre) {
                result += Math.min(countP, countN);
                // 此时前一个字符和计数都设为后一个字符
                countP = countN;
                pre = next;
                countN = 1;
                next = curr;
            }
        }
        result += Math.min(countP, countN);
        return result;
    }

    public int countBinarySubstringsByTwoPoints(String s) {

        // 双指针解法，低位在前一个数字开头，高位在后一个数字开头
        int result = 0, len = s.length();

        int low = 0, high = 0;

        // 代表前一个数字字符
        char pre = s.charAt(0);

        for (int i = 1; i < len; i++) {
            // 当遍历到不相等时，代表此时开始遍历到后一个数字字符
            if (s.charAt(i) != pre) {
                // 赋值高位
                high = i;

                // 此时前一个字符设为当前字符（由于上一个字符已经查找完，所以为了方便之后的比较）
                pre = s.charAt(i);
                break;
            }
        }

        for (int i = high + 1; i < len; i++) {

            // 同样，如果查找的字符不相等，说明查找到了下一个数字字符
            if (s.charAt(i) != pre) {
                result += Math.min(high - low, i - high);

                // 另低位到高位的位置
                low = high;

                // 高位则等于此时的位置i
                high = i;

                pre = s.charAt(i);
            }
        }

        result += Math.min(high - low, len - high);

        return result;
    }

    public static class TestClass {
        public int val = 1;

        {
            System.out.println("TestClass:第一个构造代码块");
            setVal(6);
        }

        public TestClass(int i) {
            System.out.println("TestClass:构造函数");
            val = i;
        }

        {
            System.out.println("TestClass:第二个构造代码块");
            setVal(6);
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
}
