package com.study.algorithm.algorithms;

//给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。
//
// 
//
//示例 1：
//
//输入：a = 1, b = 2
//输出：3
//示例 2：
//
//输入：a = 2, b = 3
//输出：5
// 
//
//提示：
//
//-1000 <= a, b <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sum-of-two-integers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 两整数之和 {
    public int getSum(int a, int b) {

        if (b==0){
            return a;
        }
        return getSum(a ^ b, (a & b) << 1);

        //while (b != 0) {
        //            int carry = (a & b) << 1;
        //            a = a ^ b;
        //            b = carry;
        //        }
        //        return a;
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/sum-of-two-integers/solution/liang-zheng-shu-zhi-he-by-leetcode-solut-c1s3/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }
}
