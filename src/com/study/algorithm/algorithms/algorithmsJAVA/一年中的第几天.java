package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
//
//通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
//
// 
//
//示例 1：
//
//输入：date = "2019-01-09"
//输出：9
//示例 2：
//
//输入：date = "2019-02-10"
//输出：41
//示例 3：
//
//输入：date = "2003-03-01"
//输出：60
//示例 4：
//
//输入：date = "2004-03-01"
//输出：61
// 
//
//提示：
//
//date.length == 10
//date[4] == date[7] == '-'，其他的 date[i] 都是数字
//date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/day-of-the-year
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 一年中的第几天 {
    @Test
    public void 一年中的第几天() {
        System.out.print("数字%一年中的第几天：" + dayOfYear("2003-03-01"));

    }

    int[] months = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int dayOfYear(String date) {
        //初始化月份数组，每个月有几天
        //根据字符串得出月份mon，算出mon-1个月中共有几天
        //在加上当前第几天day
        //最后判断是否是闰年并且月份大于2，需要再加1天
        int mon = (date.charAt(5) - '0') * 10 + date.charAt(6) - '0';
        int year = (date.charAt(0) - '0') * 1000 + (date.charAt(1) - '0') * 100 + (date.charAt(2) - '0') * 10 + date.charAt(3) - '0';
        int day = (date.charAt(8) - '0') * 10 + date.charAt(9) - '0';

        int result = 0;
        for (int i = 0; i < mon; i++) {
            result += months[i];
        }
        if (mon >= 3 && isLeapYear(year)) {
            result++;
        }
        result += day;
        return result;
    }

    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0 && year % 3200 != 0) || year % 172800 == 0;
    }
}
