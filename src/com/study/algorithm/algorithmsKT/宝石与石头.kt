package com.study.algorithm.algorithmsKT

//给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
//
//字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
//
// 
//
//示例 1：
//
//输入：jewels = "aA", stones = "aAAbbbb"
//输出：3
//示例 2：
//
//输入：jewels = "z", stones = "ZZ"
//输出：0
// 
//
//提示：
//
//1 <= jewels.length, stones.length <= 50
//jewels 和 stones 仅由英文字母组成
//jewels 中的所有字符都是 唯一的
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/jewels-and-stones
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 宝石与石头 {
    fun numJewelsInStones(jewels: String, stones: String): Int {
        //哈希表
        //将宝石加入哈希表中
        //遍历石头,判断每个石头是否存在于哈希表中,如果存在,说明这块石头是个宝石,否则不是
        val jew = HashSet<Char>()
        for (c in jewels) {
            jew.add(c)
        }

        var res=0

        for (s in stones) {
            if (jew.contains(s)) {
                res++
            }
        }

        return res
    }
}