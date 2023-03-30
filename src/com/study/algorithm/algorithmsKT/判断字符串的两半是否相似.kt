package com.study.algorithm.algorithmsKT

//给你一个偶数长度的字符串 s 。将其拆分成长度相同的两半，前一半为 a ，后一半为 b 。
//
//两个字符串 相似 的前提是它们都含有相同数目的元音（'a'，'e'，'i'，'o'，'u'，'A'，'E'，'I'，'O'，'U'）。注意，s 可能同时含有大写和小写字母。
//
//如果 a 和 b 相似，返回 true ；否则，返回 false 。
//
// 
//
//示例 1：
//
//输入：s = "book"
//输出：true
//解释：a = "bo" 且 b = "ok" 。a 中有 1 个元音，b 也有 1 个元音。所以，a 和 b 相似。
//示例 2：
//
//输入：s = "textbook"
//输出：false
//解释：a = "text" 且 b = "book" 。a 中有 1 个元音，b 中有 2 个元音。因此，a 和 b 不相似。
//注意，元音 o 在 b 中出现两次，记为 2 个。
// 
//
//提示：
//
//2 <= s.length <= 1000
//s.length 是偶数
//s 由 大写和小写 字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/determine-if-string-halves-are-alike
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 判断字符串的两半是否相似 {
    //哈希表,双指针
    //用一个哈希表存储所有元音字母
    //使用双指针遍历前后两段字符串,并计数元音出现的次数
    //如果前后两段字符的元音字母数量相同,数量差肯定为0
    //那么前一段字符串如果出现元音字母,则记录数量加一,后一段字符串如果出现元音字母,则记录数量减一
    //最后只要判断记录的元音数量是否为0即可
    private val vowel = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    fun halvesAreAlike(s: String): Boolean {
        var left = 0
        var right = s.length - 1

        var count = 0

        while (left < right) {
            if (vowel.contains(s[left++])) {
                count++
            }
            if (vowel.contains(s[right--])) {
                count--
            }
        }

        return count == 0
    }
}