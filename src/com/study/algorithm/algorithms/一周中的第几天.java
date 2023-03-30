package com.study.algorithm.algorithms;

//给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
//
//输入为三个整数：day、month 和 year，分别表示日、月、年。
//
//您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
//
// 
//
//示例 1：
//
//输入：day = 31, month = 8, year = 2019
//输出："Saturday"
//示例 2：
//
//输入：day = 18, month = 7, year = 1999
//输出："Sunday"
//示例 3：
//
//输入：day = 15, month = 8, year = 1993
//输出："Sunday"
// 
//
//提示：
//
//给出的日期一定是在 1971 到 2100 年之间的有效日期。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/day-of-the-week
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 一周中的第几天 {
    public String dayOfTheWeek(int day, int month, int year) {
        //计算出1971.1.1距今有多少天，再与7的余数，即可得出是一周内第几天
        
        String[] weeks = new String[]{"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",};
        int days = 0;
        for (int i = 1971; i < year; i++) {
            if (isLeapYear(i)) {
                days += 366;
            } else {
                days += 365;
            }
        }
        int[] months = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 1; i < month; i++) {
            days += months[i];
        }
        if (month >= 3 && isLeapYear(year)) {
            days++;
        }
        days += day - 1;
        return weeks[days % 7];
    }

    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0 && year % 3200 != 0) || year % 172800 == 0;
    }
}
