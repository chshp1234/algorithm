package com.study.algorithm.algorithms.algorithmsJAVA;

//为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。
//
//给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，判断 ransomNote 能不能由 magazines 里面的字符构成。
//
//如果可以构成，返回 true ；否则返回 false 。
//
//magazine 中的每个字符只能在 ransomNote 中使用一次。
//
// 
//
//示例 1：
//
//输入：ransomNote = "a", magazine = "b"
//输出：false
//示例 2：
//
//输入：ransomNote = "aa", magazine = "ab"
//输出：false
//示例 3：
//
//输入：ransomNote = "aa", magazine = "aab"
//输出：true
// 
//
//提示：
//
//1 <= ransomNote.length, magazine.length <= 105
//ransomNote 和 magazine 由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/ransom-note
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 赎金信 {
    public boolean canConstruct(String ransomNote, String magazine) {
        //哈希表
        //哈希表记录已有的字符，遍历过程中若已有字符，则匹配，否则不匹配
        int lenNote = ransomNote.length();
        int lenMagazine = magazine.length();
        if (lenNote > lenMagazine) {
            return false;
        }
        int[] counterNote = new int[26];
        int[] counterMagazine = new int[26];

        int matchCount = 0;
        int index = 0;
        while (index < lenNote) {
            int maga = magazine.charAt(index) - 'a';
            if (counterNote[maga] > 0) {
                matchCount++;
                counterNote[maga]--;
            } else {
                counterMagazine[maga]++;
            }
            int note = ransomNote.charAt(index) - 'a';
            if (counterMagazine[note] > 0) {
                matchCount++;
                counterMagazine[note]--;
            } else {
                counterNote[note]++;
            }
            index++;
        }

        while (index < lenMagazine) {
            int maga = magazine.charAt(index) - 'a';
            if (counterNote[maga] > 0) {
                matchCount++;
                counterNote[maga]--;
            }
            index++;
        }

        return matchCount == lenNote;

//        if (ransomNote.length() > magazine.length()) {
//            return false;
//        }
//        int[] cnt = new int[26];
//        for (char c : magazine.toCharArray()) {
//            cnt[c - 'a']++;
//        }
//        for (char c : ransomNote.toCharArray()) {
//            cnt[c - 'a']--;
//            if(cnt[c - 'a'] < 0) {
//                return false;
//            }
//        }
//        return true;
//
//        作者：LeetCode-Solution
//        链接：https://leetcode-cn.com/problems/ransom-note/solution/shu-jin-xin-by-leetcode-solution-ji8a/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }
}
