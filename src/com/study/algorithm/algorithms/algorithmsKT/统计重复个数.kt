package com.study.algorithm.algorithms.algorithmsKT

//定义 str = [s, n] 表示 str 由 n 个字符串 s 连接构成。
//
//例如，str == ["abc", 3] =="abcabcabc" 。
//如果可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。
//
//例如，根据定义，s1 = "abc" 可以从 s2 = "abdbec" 获得，仅需要删除加粗且用斜体标识的字符。
//现在给你两个字符串 s1 和 s2 和两个整数 n1 和 n2 。由此构造得到两个字符串，其中 str1 = [s1, n1]、str2 = [s2, n2] 。
//
//请你找出一个最大整数 m ，以满足 str = [str2, m] 可以从 str1 获得。
//
//
//
//示例 1：
//
//输入：s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
//输出：2
//示例 2：
//
//输入：s1 = "acb", n1 = 1, s2 = "acb", n2 = 1
//输出：1
//
//
//提示：
//
//1 <= s1.length, s2.length <= 100
//s1 和 s2 由小写英文字母组成
//1 <= n1, n2 <= 106
class 统计重复个数 {

    //双指针
    //使用两个指针index1和index2，分别指向s1和s2
    //每次遍历从，s2中选出一个字符c，遍历s1，找出第一个相匹配的的字符c，期间略过的字符将是要删去的字符。
    //当index2==s2.length * n2时，说明已有一个完整的str2已经遍历完成，那么令index2=0，res++（结果数）；
    //直到index1==s1.length * n1时，s1遍历结束，退出循环，返回res。
    //进阶：当s2遍历到下一轮时，理应已得到所有s1和s2相匹配的字符对应的下标位置，那么这里就可以有个优化点。
    fun getMaxRepetitions(s1: String, n1: Int, s2: String, n2: Int): Int {
        val size1 = s1.length * n1
        val size2 = s2.length * n2

        var index1 = 0
        var index2 = 0

        var res = 0
        while (index1 < size1) {
            //取出s2中的字符
            val c = s2[index2 % s2.length]
            do {
                if (s1[index1++ % s1.length] == c) {
                    //如果匹配，index2和index1都+1，否则index1+1
                    index2++
                    break
                }
            } while (index1 < size1)


            if (index2 == size2) {
                //遍历完一个完整的str2，继续下一轮
                index2 = 0
                res++
            }
        }
        return res
    }
}