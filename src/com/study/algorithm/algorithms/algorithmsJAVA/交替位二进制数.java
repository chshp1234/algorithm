package com.study.algorithm.algorithms.algorithmsJAVA;

//给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
//
// 
//
//示例 1：
//
//输入：n = 5
//输出：true
//解释：5 的二进制表示是：101
//示例 2：
//
//输入：n = 7
//输出：false
//解释：7 的二进制表示是：111.
//示例 3：
//
//输入：n = 11
//输出：false
//解释：11 的二进制表示是：1011.
// 
//
//提示：
//
//1 <= n <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-number-with-alternating-bits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 交替位二进制数 {
    public boolean hasAlternatingBits(int n) {
        //方法一：遍历n的二进制表示，判断是否交替显示0和1

        //方法二：位运算
        //对输入 n 的二进制表示右移一位后，得到的数字再与 n 按位异或得到 a。
        //当且仅当输入 n 为交替位二进制数时，a 的二进制表示全为 1（不包括前导 0）。
        //这里进行简单证明：当 a 的某一位为 1 时，当且仅当 n 的对应位和其前一位相异。
        //当 a 的每一位为 1 时，当且仅当 n 的所有相邻位相异，即 n 为交替位二进制数。
        //
        //将 a 与 a+1 按位与，当且仅当 a 的二进制表示全为 1 时，结果为 0。
        //这里进行简单证明：当且仅当 a 的二进制表示全为 1 时，a+1 可以进位，并将原最高位置为 0，按位与的结果为 0。
        //否则，不会产生进位，两个最高位都为 1，相与结果不为 0。
        //
        //结合上述两步，可以判断输入是否为交替位二进制数。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/binary-number-with-alternating-bits/solution/jiao-ti-wei-er-jin-zhi-shu-by-leetcode-s-bmxd/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int bit = (n >>> 1) ^ n;
        return ((bit + 1) & bit) == 0;
    }
}
