package com.study.algorithm.algorithms;

import java.util.HashSet;
import java.util.Set;

//每个 有效电子邮件地址 都由一个 本地名 和一个 域名 组成，以 '@' 符号分隔。除小写字母之外，电子邮件地址还可以含有一个或多个 '.' 或 '+' 。
//
//例如，在 alice@leetcode.com中， alice 是 本地名 ，而 leetcode.com 是 域名 。
//如果在电子邮件地址的 本地名 部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名中没有点的同一地址。请注意，此规则 不适用于域名 。
//
//例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。
//如果在 本地名 中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件。同样，此规则 不适用于域名 。
//
//例如 m.y+name@email.com 将转发到 my@email.com。
//可以同时使用这两个规则。
//
//给你一个字符串数组 emails，我们会向每个 emails[i] 发送一封电子邮件。返回实际收到邮件的不同地址数目。
//
// 
//
//示例 1：
//
//输入：emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
//输出：2
//解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
//示例 2：
//
//输入：emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
//输出：3
//
//提示：
//
//1 <= emails.length <= 100
//1 <= emails[i].length <= 100
//emails[i] 由小写英文字母、'+'、'.' 和 '@' 组成
//每个 emails[i] 都包含有且仅有一个 '@' 字符
//所有本地名和域名都不为空
//本地名不会以 '+' 字符作为开头
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/unique-email-addresses
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 独特的电子邮件地址 {
    public int numUniqueEmails(String[] emails) {
        //哈希表
        //首先按“@”分割每个email
        //按规则取出本地地址，最后拼接上“@”和主机名，就是实际转发的邮箱地址，将此地址加入哈希表中，最后返回哈希表的长度
        Set<String> set = new HashSet<>();
        String[] temp;
        StringBuilder sb;
        for (String email : emails) {
            //分割邮箱，判断本地地址
            temp = email.split("@");
            sb = new StringBuilder();
            for (int i = 0, l = temp[0].length(); i < l; i++) {
                char c = temp[0].charAt(i);
                //如果遇到'+'，忽略后续所有内容，则跳出循环
                if (c == '+') {
                    break;
                } else if (c != '.') {
                    //忽略'.'
                    sb.append(c);
                }
            }
            //拼接'@'和域名
            sb.append("@");
            sb.append(temp[1]);
            set.add(sb.toString());
        }

        return set.size();
    }
}
