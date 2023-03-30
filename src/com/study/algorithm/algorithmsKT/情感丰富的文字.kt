package com.study.algorithm.algorithmsKT

//有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" -> "hiii"。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。
//
//对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母 c ），然后往其中添加相同的字母 c 使其长度达到 3 或以上。
//
//例如，以 "hello" 为例，我们可以对字母组 "o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于 3。此外，我们可以进行另一种扩张 "ll" -> "lllll" 以获得 "helllllooo"。如果 s = "helllllooo"，那么查询词 "hello" 是可扩张的，因为可以对它执行这两种扩张操作使得 query = "hello" -> "hellooo" -> "helllllooo" = s。
//
//输入一组查询单词，输出其中可扩张的单词数量。
//
// 
//
//示例：
//
//输入：
//s = "heeellooo"
//words = ["hello", "hi", "helo"]
//输出：1
//解释：
//我们能通过扩张 "hello" 的 "e" 和 "o" 来得到 "heeellooo"。
//我们不能通过扩张 "helo" 来得到 "heeellooo" 因为 "ll" 的长度小于 3 。
// 
//
//提示：
//
//1 <= s.length, words.length <= 100
//1 <= words[i].length <= 100
//s 和所有在 words 中的单词都只由小写字母组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/expressive-words
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 情感丰富的文字 {
    fun expressiveWords(s: String, words: Array<String>): Int {
        //双指针,计数
        //首先用counter记录s字符串中,每个字符及其连续出现的次数
        //遍历words中的每个字符串word时,先判断和counter中第一个字符是否相等
        //再从word的下标1开始遍历,用index记录counter的下标,用count记录word中每个字符连续出现的次数
        //如果当前字符和上一个字符相同,count+1,这时候判断count是否大于counter中记录的次数,若大于,则不可能扩充得到,返回false
        //如果当前字符和上一个字符不相同:
        //1.count等于counter中记录的次数
        //2.如果count小于counter中记录的次数,则counter中记录的次数必须大于等于3,才能通过扩充获得
        //3.index+1后,判断s中的下一个字符是否和当前字符相同
        //4.count重置为1
        val counter = ArrayList<IntArray>()
        var count = 1
        //统计每个字符及其连续出现的次数
        for (i in 1 until s.length) {
            if (s[i] == s[i - 1]) {
                count++
            } else {
                counter.add(intArrayOf(s[i - 1].toInt(), count))
                count = 1
            }
        }

        counter.add(intArrayOf(s[s.length - 1].toInt(), count))

        count = 0

        for (word in words) {
            if (isExpressiveWord(counter, word)) {
                count++
            }
        }

        return count
    }

    fun isExpressiveWord(counter: ArrayList<IntArray>, word: String): Boolean {
        //先判断第一个字符,方便后续判断
        if (word[0].toInt() != counter[0][0]) {
            return false
        }
        var count = 1
        var index = 0
        for (i in 1 until word.length) {
            //如果字符连续
            if (word[i] == word[i - 1]) {
                count++
                //判断连续的次数count是否大于s中当前字符的连续的次数
                if (count > counter[index][1]) {
                    return false
                }
            }
            //如果连续的次数不相同,则s中当前字符连续的次数必须大于等于3(前面已经判断了count不可能大于当前s中的字符的次数)
            else if (count != counter[index][1] && counter[index][1] < 3) {
                return false
            }
            //index+1后,判断当前字符和s中下一个字符是否相同(counter首先不能遍历结束)
            else if (++index == counter.size || word[i].toInt() != counter[index][0]) {
                return false
            } else {
                count = 1
            }
        }
        //最后再判断,结尾处的字符,连续出现的次数是否匹配要求
        return index == counter.size - 1 && (count == counter[index][1] || counter[index][1] >= 3)
    }
}