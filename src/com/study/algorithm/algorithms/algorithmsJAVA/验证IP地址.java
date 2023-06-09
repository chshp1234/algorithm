package com.study.algorithm.algorithms.algorithmsJAVA;

//给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
//
//有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
//
//一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
//
//1 <= xi.length <= 4
//xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
//在 xi 中允许前导零。
//例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
//
// 
//
//示例 1：
//
//输入：queryIP = "172.16.254.1"
//输出："IPv4"
//解释：有效的 IPv4 地址，返回 "IPv4"
//示例 2：
//
//输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
//输出："IPv6"
//解释：有效的 IPv6 地址，返回 "IPv6"
//示例 3：
//
//输入：queryIP = "256.256.256.256"
//输出："Neither"
//解释：既不是 IPv4 地址，又不是 IPv6 地址
// 
//
//提示：
//
//queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/validate-ip-address
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 验证IP地址 {

    //模拟，遍历
    //按ip规则，一趟遍历，判断字符串
    public String validIPAddress(String queryIP) {
        if (queryIP.length() == 0) {
            return "Neither";
        }
        char last = queryIP.charAt(queryIP.length() - 1);
        if (last == '.' || last == ':') {
            return "Neither";
        }
        if (queryIP.indexOf('.') > 0) {
            return checkIpv4(queryIP.split("\\."));
        } else {
            return checkIpv6(queryIP.split(":"));
        }
    }

    public String checkIpv4(String[] ips) {
        if (ips.length != 4) {
            return "Neither";
        }
        for (String ip : ips) {
            int len = ip.length();
            if (len == 1) {
                if (!Character.isDigit(ip.charAt(0))) {
                    return "Neither";
                }
            } else if (len == 2) {
                if (!Character.isDigit(ip.charAt(0)) || ip.charAt(0) == '0') {
                    return "Neither";
                }
                if (!Character.isDigit(ip.charAt(1))) {
                    return "Neither";
                }
            } else if (len == 3) {
                if (ip.charAt(0) == '1') {
                    if (!Character.isDigit(ip.charAt(1)) || !Character.isDigit(ip.charAt(2))) {
                        return "Neither";
                    }
                } else if (ip.charAt(0) == '2') {
                    if (!Character.isDigit(ip.charAt(1)) || ip.charAt(1) > '5') {
                        return "Neither";
                    }
                    if (!Character.isDigit(ip.charAt(2)) || (ip.charAt(1) == '5' && ip.charAt(2) > '5')) {
                        return "Neither";
                    }
                } else {
                    return "Neither";
                }
            } else {
                return "Neither";
            }

        }
        return "IPv4";
    }

    public String checkIpv6(String[] ips) {
        if (ips.length != 8) {
            return "Neither";
        }

        char temp;
        for (String ip : ips) {
            if (ip.length() == 0 || ip.length() > 4) {
                return "Neither";
            }

            for (int i = 0; i < ip.length(); i++) {
                temp = ip.charAt(i);
                if (!(temp >= '0' && temp <= '9') && !(temp >= 'a' && temp <= 'f') && !(temp >= 'A' && temp <= 'F')) {
                    return "Neither";
                }
            }
        }

        return "IPv6";
    }
}
