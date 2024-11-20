package com.study.algorithm.algorithms.algorithmsKT

//给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。
//
//如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。
//
//
//
//示例 1：
//
//输入：word = "b"
//输出：2
//解释：在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "abc" 。
//示例 2：
//
//输入：word = "aaa"
//输出：6
//解释：在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "abcabcabc" 。
//示例 3：
//
//输入：word = "abc"
//输出：0
//解释：word 已经是有效字符串，不需要进行修改。
//
//
//提示：
//
//1 <= word.length <= 50
//word 仅由字母 "a"、"b" 和 "c" 组成。
class 构造有效字符串的最少插入数 {
    //贪心，状态机
    //定义3种状态：start，a，ab。分别表示起始状态，匹配到字符a状态，匹配到字符ab状态。
    //那么根据这3种状态，以及将要输入的字符，就可以得到一个有限状态机
    //根据不同状态以及不同的输入字符，计算需要拼接的字符数量，以及生成下一个状态
    //遍历字符串结束后，还需要再根据状态，判断最后是否补充字符
    fun addMinimum(word: String): Int {
        var state = "start"
        var res = 0
        for (c in word) {
            //匹配状态，加上需要拼接的字符数量
            res += when (state) {
                "start" -> {
                    when (c) {
//                        start+a=a
                        'a' -> {
                            state = "a"
                            0
                        }

//                        start+b=ab，补充1个a
                        'b' -> {
                            state = "ab"
                            1
                        }

//                        start+c=start,补充1个a和1个b
                        else -> {
                            2
                        }
                    }
                }

                "a" -> {
                    when (c) {
//                        a+a=a，补充1个b和1个c
                        'a' -> {
                            2
                        }

//                        a+b=ab
                        'b' -> {
                            state = "ab"
                            0
                        }

//                        a+c=start，补充1个b
                        else -> {
                            state = "start"
                            1
                        }
                    }
                }

                "ab" -> {
                    when (c) {
//                        ab+a=a，补充1个c
                        'a' -> {
                            state = "a"
                            1
                        }

//                        ab+b=ab，补充1个c和1个a
                        'b' -> {
                            2
                        }

//                        ab+c=start
                        else -> {
                            state = "start"
                            0
                        }
                    }
                }

                else -> 0
            }
        }

        res += when (state) {
            "a" -> 2
            "ab" -> 1
            else -> 0
        }
        return res
    }

    //动态规划
    //相比单纯的状态机，此思路维护一个整体状态,其中
    //dp[0]：表示当输入字符'a'时，需要添加的字符数量
    //dp[1]：表示当输入字符'b'时，需要添加的字符数量
    //dp[2]：表示当输入字符'c'时，需要添加的字符数量
    //此时，我们默认输入的字符可以完成连续匹配，连续匹配表示已匹配的字符是a，ab，abc
    //输入a，将匹配a；输入b将匹配ab；输入c将匹配abc。
    //比如输入b，说明此时连续字符以b结尾，那么前面已经匹配了ab，接下来只要输入c即可完成一个连续的abc，然后更新状态：
    //dp[0]=1，表示，接下来如果输入a，那么ab后面需要拼接1个c
    //dp[1]=2，表示，接下来如果输入b，那么ab后面需要拼接1个c和1个a
    //dp[2]=0，表示，接下来如果输入c，可以直接匹配成abc
    //输入a和c，同理
    //遍历完成后还需要加上dp[0]，表示接下来如果拼接a，前面已匹配的字符还需要拼接几个字符，此时可不用再更新状态。
    fun addMinimumByDp(word: String): Int {
        val dp = intArrayOf(0, 1, 2)
        var res = 0
        for (c in word) {
            //输入的字符还需拼接几个字符才可连续
            res += dp[c - 'a']
            //根据输入字符，更新状态
            when (c) {
                //以a结尾时，接下来的状态
                'a' -> {
                    //接下来如果输入a，那么a后面需要拼接1个b和1个c
                    dp[0] = 2
                    //接下来如果输入b，那么可以匹配成ab
                    dp[1] = 0
                    //接下来如果输入c，需要拼接1个b
                    dp[2] = 1
                }
                //以b结尾时，接下来的状态
                'b' -> {
                    //接下来如果输入a，那么ab后面需要拼接1个c
                    dp[0] = 1
                    //接下来如果输入b，那么ab后面需要拼接1个c和1个a
                    dp[1] = 2
                    //接下来如果输入c，可以直接匹配成abc
                    dp[2] = 0
                }
                //以c结尾时，接下来的状态
                'c' -> {
                    //接下来如果输入a，可以直接匹配成a
                    dp[0] = 0
                    //接下来如果输入b，需要拼接1个a
                    dp[1] = 1
                    //接下来如果输入c，需要拼接1个a和1个b
                    dp[2] = 2
                }
            }
        }
        //最后再判断一次
        res += dp[0]
        return res
    }

    //思路也很简单，也是贪心思路，我们把每一组abc都当作一组来匹配，遍历结束时，只需要知道共有几（n）组abc，就可以得出需要添加的字符数量=n*3-s.length。
    //
    // 过程
    //1. 当遇到的字符s[i]<=s[i-1]时，说明需要开启新一轮字符匹配，n+1；
    //2. 当遇到的字符s[i]>s[i-1]时，说明这个字符可以和上一个字符凑成一组，不处理；
    //3. 返回结果n*3-s.length。
    fun addMinimumByMath(word: String): Int {
        //因为word字符数量>=1，所以初始1组，并且从下标1开始遍历。
        var n = 1
        for (i in 1 until word.length) {
            if (word[i] <= word[i - 1]) {
                n++
            }
        }
        return n * 3 - word.length
    }
}