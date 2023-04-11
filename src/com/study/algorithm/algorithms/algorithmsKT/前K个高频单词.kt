package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test
import java.util.*

//给一非空的单词列表，返回前 k 个出现次数最多的单词。
//
//返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
//
//示例 1：
//
//输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//输出: ["i", "love"]
//解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
//    注意，按字母顺序 "i" 在 "love" 之前。
// 
//
//示例 2：
//
//输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
//输出: ["the", "is", "sunny", "day"]
//解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
//    出现次数依次为 4, 3, 2 和 1 次。
// 
//
//注意：
//
//假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
//输入的单词均由小写字母组成。
// 
//
//扩展练习：
//
//尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/top-k-frequent-words
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 前K个高频单词 {
    @Test
    fun 前K个高频单词() {
        System.out.println("前K个高频单词:${topKFrequent(arrayOf("i", "love", "leetcode", "i", "love", "coding"), 2)}")
    }

    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        //哈希表+优先队列
        //哈希表记录每个单词出现的次数，优先队列统计排序单词
        //因为我们只需要知道频次为前K的单词，所以并不需要对整体进行排序，使用大顶堆即可即可很好的得到结果
        val map = HashMap<String, Int>()
        val len = words.size
        val compare = Comparator<Map.Entry<String, Int>> { o1, o2 ->

            if (o1.value == o2.value) {
                o1.key.compareTo(o2.key)
            } else {
                o2.value - o1.value
            }
        }
        val queue = PriorityQueue(compare)
        for (i in 0 until len) {
            map[words[i]] = map[words[i]]?.let {
                it + 1
            } ?: 1
//            ts.add(words[i])
        }
        for (i in map) {
            queue.offer(i)
        }
        val result = ArrayList<String>(k)
        for (i in 0 until k) {
            result.add(queue.poll().key)
        }
        return result
    }
}