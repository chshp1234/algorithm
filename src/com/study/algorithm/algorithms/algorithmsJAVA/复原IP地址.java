package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 复原IP地址 {
    @Test
    public void 复原IP地址() {

        System.out.println("复原IP地址：" + restoreIpAddresses("000256"));
    }

    // 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
    //
    // 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
    //
    //
    //
    // 示例:
    //
    // 输入: "25525511135"
    // 输出: ["255.255.11.135", "255.255.111.35"]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/restore-ip-addresses
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();

        // 经典回溯
        // 1.数字每段数字只能在0-255之间，并且不能以0开头
        // 2.字符串必须用完
        // 3.必须返回4段的IP形式
        restoreIpAddresses(result, s, 4, 0, new StringBuilder());
        return result;
    }

    // 回溯方法
    public void restoreIpAddresses(
            // 结果列表，当前字符串，当前第几段，当前遍历的位置，当前保存（已生成）的字符串
            List<String> result, String s, int count, int index, StringBuilder curr) {

        // 剩余未遍历的字符串
        int left = (s.length() - index);

        // 如果剩余字符串不足，或者剩余的字符串过多，则当前组合不能生成一个有效的IP段
        // 关于剩余字符串过多：ip地址有4段，每段中最多3个数字，所以，最多只允许12个长度的字符串，同理，第一段判断结束后，
        // 第二段后还剩3段，则最多只允许9个长度的字符串...
        if (left <= 0 || left > count * 3) {
            return;
        }

        // 获取到当前位置时，该段字符串应有几位
        int len = getCount(s.charAt(index));

        // 结束条件：
        // 1.当前已到ip最后一段
        // 2.剩余的字符数量小于等于该段字符串应有的位数
        if (count == 1 && left <= len) {
            String substring = s.substring(index);

            // 判断该段字符是否超出范围
            if (checkRange(substring)) {
                curr.append(".");
                curr.append(substring);
                result.add(curr.deleteCharAt(0).toString());
            }
            return;
        }

        // 加入分隔符“.”，由于是事先加入，所以在结尾加入结果时需删除首尾
        curr.append(".");

        // 当前位可以有的长度，为剩余字符串长度和应该有的长度的最小值（防止数组越界）
        len = Math.min(left, len);

        // 当前段的字符
        StringBuilder stringBuilder = new StringBuilder();

        for (int j = 0; j < len; j++) {

            // 依次加入下一位字符
            stringBuilder.append(s.charAt(index + j));

            // 判断该段字符是否超出范围
            if (checkRange(stringBuilder.toString())) {

                // 寻找下一段地址
                restoreIpAddresses(
                        result,
                        s,
                        count - 1,
                        index + j + 1,
                        new StringBuilder(curr).append(stringBuilder));
            }
        }
    }

    private int getCount(char c) {
        // 如果当前字符是‘0’，则最多只能有一位（就是0）
        if (c == '0') {
            return 1;
        }
        // 如果当前字符是‘1’或‘2’，则最多可有3位
        else if (c == '1' || c == '2') {
            return 3;
        }
        // 如果当前字符大于2，则最多只能有2位
        else {
            return 2;
        }
    }

    // 判断该数字字符是否超出ip范围
    private boolean checkRange(String s) {
        int i = Integer.parseInt(s);
        if (i >= 0 && i <= 255) {
            return true;
        }

        return false;
    }
}
