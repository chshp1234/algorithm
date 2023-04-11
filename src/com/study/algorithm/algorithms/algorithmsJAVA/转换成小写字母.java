package com.study.algorithm.algorithms.algorithmsJAVA;

//给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
//
// 
//
//示例 1：
//
//输入：s = "Hello"
//输出："hello"
//示例 2：
//
//输入：s = "here"
//输出："here"
//示例 3：
//
//输入：s = "LOVELY"
//输出："lovely"
// 
//
//提示：
//
//1 <= s.length <= 100
//s 由 ASCII 字符集中的可打印字符组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/to-lower-case
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 转换成小写字母 {
    public String toLowerCase(String s) {
        //如果我们发现 ch 的 ASCII 码在 [65, 96]的范围内，那么我们将它的 ASCII 码增加 32，即可得到对应的小写字母
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/to-lower-case/solution/zhuan-huan-cheng-xiao-xie-zi-mu-by-leetc-5e29/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, l = s.length(); i < l; i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                stringBuilder.append((char) (s.charAt(i) + 32));
                //近而我们可以发现，由于 [65, 96]对应的二进制表示为 [(01000001), (01011010)]，
                // 32 对应的二进制表示为 (00100000)，而对于 [(01000001), (01011010)] 内的所有数，
                // 表示 32 的那个二进制位都是 0，因此可以对 ASCII 码与 32 做按位或运算，替代与 32 的加法运算。
                //
                //作者：LeetCode-Solution
                //链接：https://leetcode-cn.com/problems/to-lower-case/solution/zhuan-huan-cheng-xiao-xie-zi-mu-by-leetc-5e29/
                //来源：力扣（LeetCode）
                //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//                stringBuilder.append((char) (s.charAt(i) | 32));
            } else {
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}
