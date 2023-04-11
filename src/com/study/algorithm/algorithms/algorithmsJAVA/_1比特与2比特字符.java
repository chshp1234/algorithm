package com.study.algorithm.algorithms.algorithmsJAVA;

//有两种特殊字符：
//
//第一种字符可以用一个比特 0 来表示
//第二种字符可以用两个比特(10 或 11)来表示、
//给定一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一位字符，则返回 true 。
//
// 
//
//示例 1:
//
//输入: bits = [1, 0, 0]
//输出: true
//解释: 唯一的编码方式是一个两比特字符和一个一比特字符。
//所以最后一个字符是一比特字符。
//示例 2:
//
//输入: bits = [1, 1, 1, 0]
//输出: false
//解释: 唯一的编码方式是两比特字符和两比特字符。
//所以最后一个字符不是一比特字符。
// 
//
//提示:
//
//1 <= bits.length <= 1000
//bits[i] == 0 or 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class _1比特与2比特字符 {
    public boolean isOneBitCharacter(int[] bits) {
        //一趟遍历
        //遍历时碰到‘1’，则是两比特字符，下标移动2位;若遇到‘0’，则是一比特字符，下标移动一；
        int len = bits.length;
        int index = 0;

        int result = 0;
        while (index < len) {
            if (bits[index] == 1) {
                result = 2;
                index += 2;
            } else {
                result = 1;
                index += 1;
            }
        }
        return result == 1;
    }
}
