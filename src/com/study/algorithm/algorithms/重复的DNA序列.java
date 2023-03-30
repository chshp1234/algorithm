package com.study.algorithm.algorithms;

import java.util.*;

//所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
//
//编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
//
// 
//
//示例 1：
//
//输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//输出：["AAAAACCCCC","CCCCCAAAAA"]
//示例 2：
//
//输入：s = "AAAAAAAAAAAAA"
//输出：["AAAAAAAAAA"]
// 
//
//提示：
//
//0 <= s.length <= 105
//s[i] 为 'A'、'C'、'G' 或 'T'
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/repeated-dna-sequences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 重复的DNA序列 {
    public List<String> findRepeatedDnaSequences(String s) {
        //滑动窗口，哈希表
        //用滑动窗口匹配每10个字符串；
        //用哈希表记录已匹配过的字符串；
        //若当前字符串已出现过，但未被统计，则加入结果列表中，若已统计则略过，若字符串没出现过，则将该字符串加入哈希表中，并标记为未统计；
        if (s == null || s.length() < 10) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        Map<String, Boolean> set = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(s.charAt(i));
        }
        set.put(stringBuilder.toString(), false);
        for (int i = 10, l = s.length(); i < l; i++) {
            stringBuilder.deleteCharAt(0).append(s.charAt(i));
            Boolean v;
            String temp = stringBuilder.toString();
            if ((v = set.get(temp)) != null) {
                if (!v) {
                    result.add(temp);
                    set.put(temp, true);
                }
            } else {
                set.put(temp, false);
            }
        }
        return result;


    }

    //方法二：哈希表 + 滑动窗口 + 位运算
    //由于 s 中只含有 4 种字符，我们可以将每个字符用 2 个比特表示，即：
    //
    //A 表示为二进制 00；
    //C 表示为二进制 01；
    //G 表示为二进制 10；
    //T 表示为二进制 11。
    //如此一来，一个长为 10 的字符串就可以用 20 个比特表示，而一个int 整数有 32 个比特，足够容纳该字符串，
    //因此我们可以将 s 的每个长为 10 的子串用一个 int 整数表示（只用低 20 位）。
    //
    //注意到上述字符串到整数的映射是一一映射，每个整数都对应着一个唯一的字符串，
    //因此我们可以将方法一中的哈希表改为存储每个长为 10 的子串的整数表示。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/repeated-dna-sequences/solution/zhong-fu-de-dnaxu-lie-by-leetcode-soluti-z8zn/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    /*static final int L = 10;
    Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<String>();
        int n = s.length();
        if (n <= L) {
            return ans;
        }
        int x = 0;
        for (int i = 0; i < L - 1; ++i) {
            x = (x << 2) | bin.get(s.charAt(i));
        }
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n - L; ++i) {
            x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.get(x) == 2) {
                ans.add(s.substring(i, i + L));
            }
        }
        return ans;*/
//    }

}
