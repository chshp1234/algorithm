package com.study.algorithm.algorithms;

import org.junit.Test;

public class 字符串最长公共前缀 {
    @Test
    public void 最长公共前缀() {
        System.out.println("最长公共前缀:" + longestCommonPrefix(new String[] {"he", "hello"}));
    }
    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * <p>如果不存在公共前缀，返回空字符串 ""。
     *
     * <p>示例 1:
     *
     * <p>输入: ["flower","flow","flight"] 输出: "fl" 示例 2:
     *
     * <p>输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。 说明:
     *
     * <p>所有输入只包含小写字母 a-z 。
     */

    // 方法一：纵向扫描
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int i = 1, l = strs.length, j = 0;

        // 纵向扫描
        while (true) {
            i = 1;
            // 循环判断每一位字符在字符串数组中的每一个字符串中是否相同
            for (; i < l; i++) {
                // 如果遍历到其中一个字符串尾部，则返回
                if (j == strs[0].length() || j == strs[i].length()) {
                    return strs[0].substring(0, j);
                    //                    return stringBuilder.toString();
                }
                // 如果遍历到其中一个字符串的字符与其他字符串不相同，则返回
                else if (strs[i].charAt(j) != strs[0].charAt(j)) {
                    return strs[0].substring(0, j);
                    //                    return stringBuilder.toString();
                }
            }
            //            stringBuilder.append(strs[0].charAt(j));
            j++;
        }
    }

    // 方法二：横向扫描
    // 用 LCP(S1…Sn) 表示字符串S1​…Sn​的最长公共前缀。
    //
    // 可以得到以下结论：
    // LCP(S1…Sn)=LCP(LCP(LCP(S1,S2),S3),…Sn)
    //
    // 基于该结论，可以得到一种查找字符串数组中的最长公共前缀的简单方法。
    // 依次遍历字符串数组中的每个字符串，对于每个遍历到的字符串，更新最长公共前缀，当遍历完所有的字符串以后，即可得到字符串数组中的最长公共前缀。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    /*public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }*/

    // 方法三：分治法
    // 基于上述结论，可以使用分治法得到字符串数组中的最长公共前缀。对于问题 LCP(Si⋯Sj)，
    // 可以分解成两个子问题 LCP(Si…Smid) 与 LCP(Smid+1…Sj)，其中 mid=（i+j）/2。
    // 对两个子问题分别求解，然后对两个子问题的解计算最长公共前缀，即为原问题的解。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    /*public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }
    }

    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }*/

    // 方法四：二分查找
    // 显然，最长公共前缀的长度不会超过字符串数组中的最短字符串的长度。用 minLength 表示字符串数组中的最短字符串的长度，则可以在
    // [0,minLength] 的范围内通过二分查找得到最长公共前缀的长度。每次取查找范围的中间值mid，判断每个字符串的长度为 mid
    // 的前缀是否相同，如果相同则最长公共前缀的长度一定大于或等于
    // mid，如果不相同则最长公共前缀的长度一定小于 mid，通过上述方式将查找范围缩小一半，直到得到最长公共前缀的长度。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    /*public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }*/
}
