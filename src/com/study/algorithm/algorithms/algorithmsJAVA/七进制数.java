package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
//
// 
//
//示例 1:
//
//输入: num = 100
//输出: "202"
//示例 2:
//
//输入: num = -7
//输出: "-10"
// 
//
//提示：
//
//-107 <= num <= 107
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/base-7
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 七进制数 {
    @Test
    public void 七进制数() {
        System.out.println("七进制数:" + convertToBase7(-10));
    }

    public String convertToBase7(int num) {
        //取余
        //对num依次对7求余，求余结果依次加入字符串尾部
        //注意0和负数
        if (num == 0) {
            return "0";
        }

        int n = Math.abs(num);

        StringBuilder result = new StringBuilder();

        while (n != 0) {
            result.insert(0, n % 7);
            n = n / 7;
        }

        if (num < 0) {
            result.insert(0, "-");
        }

        return result.toString();
    }
}
