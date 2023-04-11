package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

//如果一个十进制数字不含任何前导零，且每一位上的数字不是 0 就是 1 ，那么该数字就是一个 十-二进制数 。例如，101 和 1100 都是 十-二进制数，而 112 和 3001 不是。
//
//给你一个表示十进制整数的字符串 n ，返回和为 n 的 十-二进制数 的最少数目。
//
// 
//
//示例 1：
//
//输入：n = "32"
//输出：3
//解释：10 + 11 + 11 = 32
//示例 2：
//
//输入：n = "82734"
//输出：8
//示例 3：
//
//输入：n = "27346209830709182346"
//输出：9
// 
//
//提示：
//
//1 <= n.length <= 105
//n 仅由数字组成
//n 不含任何前导零并总是表示正整数
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 十_二进制数的最少数目 {

    @Test
    public void 区间和的个数() {
        //找最大值
        //根据几个例子就可以看出，只要找出字符串中最大的那个字符即可。
        //因为分解后的十进制数为满足条件，每一位数非‘1’即‘0’；
        //所以至少需要满足累加到原数中最大数字最大的那一位；而其他位中，加到当前位的值后，其余分解的数在该位置0即可。
        //注：不可能进位，因为要进位最少得加到10，而一位中最大也为9，所以肯定不符合条件“最少”。
        System.out.println("区间和的个数:" + minPartitionsByMatch("1515642354640216"));
    }

    public int minPartitionsBySort(String n) {
        //排序
        //对原字符串的字符数组进行排序，直接拿出最大值（最后一位）即可
        //优化：既然只需要知道最大值，那么可以不用对整体进行排序，可使用大顶堆取出最大值（优先队列）
        char[] chars = n.toCharArray();
        Arrays.sort(chars);
        return chars[chars.length - 1] - '0';
    }

    public int minPartitionsByMatch(String n) {
        //匹配
        //使用字符串自带的indexOf方法可判断某个字符是否存在字符串中
        //我们从‘9’往下进行匹配查找，第一个匹配到的即为该字符串中最大值的字符
        for (int i = '9'; i > '1'; i--) {
            if (n.indexOf(i) >= 0) {
                return i - '0';
            }
        }
        return 1;
    }

    public int minPartitions(String n) {
        //遍历
        //最简单，一趟遍历，每次更新最大值
        //这里如果查找到‘9’可以直接返回，因为不会比9还大的值
        char[] chars = n.toCharArray();
        int result = '1';
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] > result) {
                result = chars[i];
                if (result == '9') {
                    return 9;
                }
            }
        }
        return result - '0';
    }
}
