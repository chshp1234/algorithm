package com.study.algorithm.algorithms.algorithmsJAVA;

//给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
//
//有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
//
//替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。
//
// 
//
//示例 1：
//
//输入：time = "2?:?0"
//输出："23:50"
//解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
//示例 2：
//
//输入：time = "0?:3?"
//输出："09:39"
//示例 3：
//
//输入：time = "1?:22"
//输出："19:22"
// 
//
//提示：
//
//time 的格式为 hh:mm
//题目数据保证你可以由输入的字符串生成有效的时间
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/latest-time-by-replacing-hidden-digits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 替换隐藏数字得到的最晚时间 {
    public String maximumTime(String time) {
        //贪心
        //总是选取当前可选的最大值
        //注意hh中，最大为23
        char[] chars = time.toCharArray();

        if (chars[0] == '?') {
            if (chars[1] > '9' || chars[1] <= '3') {
                chars[0] = '2';
            } else {
                chars[0] = '1';
            }
        }
        if (chars[1] == '?') {
            if (chars[0] == '2') {
                chars[1] = '3';
            } else {
                chars[1] = '9';
            }
        }

        if (chars[3] == '?') {
            chars[3] = '5';
        }
        if (chars[4] == '?') {
            chars[4] = '9';
        }

        return new String(chars);
    }
}
