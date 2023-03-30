package com.study.algorithm.algorithms;

import org.junit.Test;

//累加数 是一个字符串，组成它的数字可以形成累加序列。
//
//一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
//
//给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
//
//说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
//
// 
//
//示例 1：
//
//输入："112358"
//输出：true
//解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
//示例 2：
//
//输入："199100199"
//输出：true
//解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
// 
//
//提示：
//
//1 <= num.length <= 35
//num 仅由数字（0 - 9）组成
// 
//
//进阶：你计划如何处理由过大的整数输入导致的溢出?
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/additive-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 累加数 {
    @Test
    public void 累加数() {
        System.out.println("累加数：" + isAdditiveNumber("121474836472147483648"));
    }

    public boolean isAdditiveNumber(String num) {
        //回溯
        //从后往前遍历：
        //首先枚举末尾数当作最后得到的sum
        //其次枚举倒数第二个数secondNum
        //最后根据sum-secondNum，即可得到firstNum，若firstNum存在于字符串中，那么继续查找firstNum，此时sum=secondNum，secondNum=sum - secondNum。
        //若firstNum不存在于字符串中，回溯secondNum和sum
        int len = num.length() - 1;
        if (len < 2) {
            return false;
        }

        //判断字符串是否都是0
        if (num.charAt(len) == '0' && num.charAt(len - 1) == '0') {
            if (backTrack(len - 2, 0, 0, 0, num)) {
                return true;
            }
        }

        long sum = 0;
        long secondNum;
        for (int f = len; f >= 0; f--) {
            if (num.charAt(f) == '0') {
                //sum不为0，那么不能有前驱‘0’值
                continue;
            }
            sum = (long) (sum + (num.charAt(f) - '0') * Math.pow(10, len - f));
            secondNum = 0;
            int fi = f - 1;
            if (fi <= 0) {
                return false;
            }
            if (num.charAt(fi) == '0') {
                //判断当secondNum = 0时是否符合条件
                if (backTrack(fi - 1, sum, sum, secondNum, num)) {
                    return true;
                }
            }
            for (int s = fi; s >= 0; s--) {
                if (num.charAt(s) == '0') {
                    //secondNum不为0，那么不能有前驱‘0’值
                    continue;
                }
                secondNum = (long) (secondNum + (num.charAt(s) - '0') * Math.pow(10, fi - s));
                long firstNum = sum - secondNum;
                if (backTrack(s - 1, firstNum, sum, secondNum, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean backTrack(int index, long diffNum, long sum, long secondNum, String num) {
        if (index < 0) {
            //若当前下标小于0，说明还未找到符合条件的字符
            return false;
        }
        //若当前字符是差值末尾的值
        if (num.charAt(index) - '0' == diffNum % 10) {
            diffNum = diffNum / 10;
            //如果差值为0
            if (diffNum == 0) {
                if (index == 0) {
                    //如果index=0，说明已经遍历完字符串，此字符串符合累加数
                    return true;
                } else {
                    //否则继续下一轮判断
                    //继续查找firstNum，此时sum=secondNum，secondNum=sum - secondNum。
                    long secNum = sum - secondNum;
                    return backTrack(index - 1, secondNum - secNum, secondNum, secNum, num);
                }
            }
            //差值还未找完，继续查找当前的差值diffNum
            return backTrack(index - 1, diffNum, sum, secondNum, num);
        }
        return false;
    }
}
