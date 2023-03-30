package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


//有一个密钥字符串 S ，只包含字母，数字以及 '-'（破折号）。其中， N 个 '-' 将字符串分成了 N+1 组。
//
//给你一个数字 K，请你重新格式化字符串，使每个分组恰好包含 K 个字符。特别地，第一个分组包含的字符个数必须小于等于 K，但至少要包含 1 个字符。两个分组之间需要用 '-'（破折号）隔开，并且将所有的小写字母转换为大写字母。
//
//给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。
//
// 
//
//示例 1：
//
//输入：S = "5F3Z-2e-9-w", K = 4
//输出："5F3Z-2E9W"
//解释：字符串 S 被分成了两个部分，每部分 4 个字符；
//     注意，两个额外的破折号需要删掉。
//示例 2：
//
//输入：S = "2-5g-3-J", K = 2
//输出："2-5G-3J"
//解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
// 
//
//提示:
//
//S 的长度可能很长，请按需分配大小。K 为正整数。
//S 只包含字母数字（a-z，A-Z，0-9）以及破折号'-'
//S 非空
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/license-key-formatting
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 密钥格式化 {

    @Test
    public void 密钥格式化() {
        System.out.println("密钥格式化:" + licenseKeyFormatting("5F3Z-2e-9-w", 4));
    }

    public String licenseKeyFormatting(String s, int k) {
        //模拟
        //先遍历一遍字符串s，去除破折号‘-’后，将剩余字符加入一个列表中list，并将小写字母转换成大写字母
        //按每k个字符为一组，再对list进行分组，因为第一组中字符数小于等于k个，所以list对k进行取余后，就是剩下不够k个的字符数的分组，放入第一组
        //最后剩余字符便都可按每k个字符进行分组，注分组之间需加入破折号‘-’。并且字符串最后没有破折号
        List<Character> list = new ArrayList<>();
        for (int i = 0, l = s.length(); i < l; i++) {
            char c = s.charAt(i);
            if (c != '-') {
                if (c >= 97 && c <= 122) {
                    list.add((char) (c - 32));
                } else {
                    list.add(c);
                }
            }
        }
        int len = list.size();

        if (len == 0) {
            return "";
        }

        int left = len % k;

        StringBuilder stringBuilder = new StringBuilder();

        if (left > 0) {
            for (int i = 0; i < left; i++) {
                stringBuilder.append(list.get(i));
            }
            stringBuilder.append('-');
        }

        for (int i = left; i < len; i = i + k) {
            for (int j = 0; j < k; j++) {
                stringBuilder.append(list.get(i + j));
            }
            stringBuilder.append('-');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
