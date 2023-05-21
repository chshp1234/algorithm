package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。
//
//注意：本题中，每个活字字模只能使用一次。
//
// 
//
//示例 1：
//
//输入："AAB"
//输出：8
//解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
//示例 2：
//
//输入："AAABBC"
//输出：188
//示例 3：
//
//输入："V"
//输出：1
// 
//
//提示：
//
//1 <= tiles.length <= 7
//tiles 由大写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/letter-tile-possibilities
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 活字印刷 {
    @Test
    fun 活字印刷() {
        println(
            "活字印刷:${
                numTilePossibilities2(
                    "AAABBC"
                )
            }"
        )
    }

    fun numTilePossibilities(tiles: String): Int {
        val map = HashMap<Char, Int>()
        for (t in tiles) {
            map[t] = (map[t] ?: 0) + 1
        }
        val list = map.toList()
        return numTilePossibilities(HashMap(), list, 0, 0, 0) - 1
    }

    fun numTilePossibilities2(tiles: String): Int {
        val set = HashSet<String>()
        backTrack(tiles, set, HashSet(), StringBuilder())
        return set.size - 1
    }

    fun backTrack(
        tiles: String,
        res: MutableSet<String>,
        memory: MutableSet<Int>,
        last: StringBuilder
    ) {
        //回溯
        //记录每个字符选中或不选中,并记录当前字符的下标
        //如果当前下标已遍历过则跳过
        //当全部字符都遍历过时,将当前字符串加入Set中
        //最后返回set.size-1(因为有空字符串)
        if (memory.size == tiles.length) {
            res.add(last.toString())
            return
        }
        for (i in tiles.indices) {
            if (!memory.contains(i)) {
                memory.add(i)
                backTrack(tiles, res, memory, last)
                last.append(tiles[i])
                backTrack(tiles, res, memory, last)
                last.deleteCharAt(last.length - 1)
                memory.remove(i)
            }
        }
    }

    //哈希表,回溯
    //先用哈希表将每个数字进行统计其数量
    //再将统计后的数字进行组合
    // TODO: 没解出来
    fun numTilePossibilities(
        memory: MutableMap<MyPair, Int>,
        list: List<Pair<Char, Int>>,
        index: Int,
        lastCount: Int,
        lastNum: Int
    ): Int {
        if (index == list.size) {
            return if (lastCount == 0) 0 else lastNum
        }

        var res = 0
        val count = list[index].second
        for (i in 0..count) {
            val c = if (lastCount == 0) {
                1
            } else {
                lastNum * getCount(memory, MyPair(lastCount, i))
            }
            res += numTilePossibilities(memory, list, index + 1, lastCount + i, c)
        }

        return res
    }

    fun getCount(memory: MutableMap<MyPair, Int>, pair: MyPair): Int {
        return memory[pair] ?: let {
            if (pair.left == 0 || pair.right == 0) {
                return 1
            }
            if (pair.left == 1) {
                return pair.right + 1
            }
            var res = 0
            for (i in 0 until pair.left) {
                res += getCount(memory, MyPair(i, pair.right))
            }
            memory[pair] = res
            return res
        }
    }

    class MyPair(val left: Int, val right: Int) {
        override fun equals(other: Any?): Boolean {
            if (other is MyPair) {
                return (left == other.left && right == other.right) || (left == other.right && right == other.left)
            }
            return false
        }
    }

}