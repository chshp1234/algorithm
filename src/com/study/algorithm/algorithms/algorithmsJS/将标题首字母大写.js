/**
给你一个字符串 title ，它由单个空格连接一个或多个单词组成，每个单词都只包含英文字母。请你按以下规则将每个单词的首字母 大写 ：

如果单词的长度为 1 或者 2 ，所有字母变成小写。
否则，将单词首字母大写，剩余字母变成小写。
请你返回 大写后 的 title 。



示例 1：

输入：title = "capiTalIze tHe titLe"
输出："Capitalize The Title"
解释：
由于所有单词的长度都至少为 3 ，将每个单词首字母大写，剩余字母变为小写。
示例 2：

输入：title = "First leTTeR of EACH Word"
输出："First Letter of Each Word"
解释：
单词 "of" 长度为 2 ，所以它保持完全小写。
其他单词长度都至少为 3 ，所以其他单词首字母大写，剩余字母小写。
示例 3：

输入：title = "i lOve leetcode"
输出："i Love Leetcode"
解释：
单词 "i" 长度为 1 ，所以它保留小写。
其他单词长度都至少为 3 ，所以其他单词首字母大写，剩余字母小写。


提示：

1 <= title.length <= 100
title 由单个空格隔开的单词组成，且不含有任何前导或后缀空格。
每个单词由大写和小写英文字母组成，且都是 非空 的。
 * @param {string} title
 * @return {string}
 */
var capitalizeTitle = function(title) {
    //模拟
    //按题意，先将字符串按空格" "进行分割，提取出单词数组
    //并将每个单词都转换成小写字母
    //再判断每个单词的长度是否大于2，如果大于2，则将单词首字母大写并拼接上后续的小写字母
    //将处理完成的单词重新拼接到结果字符串中，再加入一个空格" "
    //最后返回去除尾空格的结果字符串
    let array = title.split(" ")
    let res = ""
    for (let i = 0; i < array.length; i++) {
        let word = array[i].toLowerCase()
        if (word.length > 2) {
            //首字母大写+后续小写字符串
            word = word[0].toUpperCase() + word.substring(1)
            // word = word[0].toUpperCase() + word.slice(1)
            // word = word[0].toUpperCase() + word.substr(1)
        }
        res += word + " "
    }
    return res.trim()
};