package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给你两个版本号 version1 和 version2 ，请你比较它们。
//
//版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
//
//比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
//
//返回规则如下：
//
//如果 version1 > version2 返回 1，
//如果 version1 < version2 返回 -1，
//除此之外返回 0。
// 
//
//示例 1：
//
//输入：version1 = "1.01", version2 = "1.001"
//输出：0
//解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
//示例 2：
//
//输入：version1 = "1.0", version2 = "1.0.0"
//输出：0
//解释：version1 没有指定下标为 2 的修订号，即视为 "0"
//示例 3：
//
//输入：version1 = "0.1", version2 = "1.1"
//输出：-1
//解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 < version2
//示例 4：
//
//输入：version1 = "1.0.1", version2 = "1"
//输出：1
//示例 5：
//
//输入：version1 = "7.5.2.4", version2 = "7.5.3"
//输出：-1
// 
//
//提示：
//
//1 <= version1.length, version2.length <= 500
//version1 和 version2 仅包含数字和 '.'
//version1 和 version2 都是 有效版本号
//version1 和 version2 的所有修订号都可以存储在 32 位整数 中
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/compare-version-numbers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 比较版本号 {
    @Test
    public void 比较版本号() {
        String S = "1.1.0";
        String T = "1.001";
        System.out.println("比较版本号:S=" + S + " T=" + T + " " + compareVersion(S, T));
    }

    public int compareVersion(String version1, String version2) {
        //方法一：字符串分割
        //分割字符串后，再判断每个分割的字符串转换成数字后的大小

        //方法二：双指针
        //两个指针指向两个字符串起始下标
        //分别构造每个字符串中“每一位”的版本（两个‘.’之间的数值），并比较
        char[] chars1 = version1.toCharArray();
        char[] chars2 = version2.toCharArray();
        return compareVersion(chars1, 0, chars2, 0);
    }

    public int compareVersion(char[] chars1, int index1, char[] chars2, int index2) {
        int v1, v2;
        int len1 = chars1.length;
        int len2 = chars2.length;
        while (index1 < len1 || index2 < len2) {
            v1 = 0;
            v2 = 0;

            //构造版本数值
            while (index1 < len1) {
                //跳过前驱‘0’
                if (chars1[index1] != '0') {
                    break;
                }
                index1++;
            }
            while (index1 < len1) {
                //遍历到‘.’时，数值构造完毕
                if (chars1[index1] == '.') {
                    index1++;
                    break;
                }

                //否则当前版本号的数值为 v*10+chars1[index1] - '0'
                v1 = v1 * 10 + chars1[index1] - '0';
                index1++;
            }

            //同上v1的构造方式
            while (index2 < len2) {
                if (chars2[index2] != '0') {
                    break;
                }
                index2++;
            }
            while (index2 < len2) {
                if (chars2[index2] == '.') {
                    index2++;
                    break;
                }
                v2 = v2 * 10 + chars2[index2] - '0';
                index2++;
            }

            //比较
            if (v1 < v2) {
                return -1;
            }
            if (v1 > v2) {
                return 1;
            }
        }

        return 0;
    }

}
