package com.study.algorithm.algorithms.algorithmsKT

//给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。
//
//请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
//
//要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
//
// 
//
//示例 1：
//
//输入：croakOfFrogs = "croakcroak"
//输出：1
//解释：一只青蛙 “呱呱” 两次
//示例 2：
//
//输入：croakOfFrogs = "crcoakroak"
//输出：2
//解释：最少需要两只青蛙，“呱呱” 声用黑体标注
//第一只青蛙 "crcoakroak"
//第二只青蛙 "crcoakroak"
//示例 3：
//
//输入：croakOfFrogs = "croakcrook"
//输出：-1
//解释：给出的字符串不是 "croak" 的有效组合。
// 
//
//提示：
//
//1 <= croakOfFrogs.length <= 105
//字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-number-of-frogs-croaking
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 数青蛙 {

    //模拟,计数
    //用一个数组croak,统计每一个字符声音的次数
    //croak[0]表示当前有多少只青蛙正在发声'c'
    //croak[1]表示当前有多少只青蛙正在发声'r'
    //croak[2]表示当前有多少只青蛙正在发声'o'
    //croak[3]表示当前有多少只青蛙正在发声'a'
    //没有'k'的发声,是因为发出'k'声时,某一只青蛙已经蛙鸣结束,故而不用进行统计
    //遍历蛙鸣字符串时,先判断字符属于第几声,再判断上一个字符声是否大于0,否则说明是无效蛙鸣
    //比如发声'r',如果没有青蛙正在发声'c',那么croak[0]=0,说明这是个无效发声
    //再用一个字段count统计正在发声的青蛙的数目:
    //当又青蛙发声'c'时,count++,表示有一只青蛙开始发声了;
    //当又青蛙发声'k'时,count--,表示有一只青蛙开始发声结束了,因为要统计的是最少的青蛙数量,所以这里要进行-1操作;
    //这里我们只需要维护count的最大值,即可得到发出蛙鸣声的青蛙的最少数量
    val croak = IntArray(4)
    var count = 0
    var res = 0
    fun minNumberOfFrogs(croakOfFrogs: String): Int {
        for (c in croakOfFrogs) {
            if (!croak(c)) {
                //无效蛙鸣
                return res
            }
        }
        //如果count>0说明还有青蛙没发声完毕,那么就不是一个有效的蛙鸣;否则返回最少数量
        return if (count == 0) res else -1
    }

    fun croak(v: Char): Boolean {
        when (v) {
            'c' -> {
                croak[0]++
                count++
            }

            'r' -> if (croak[0] > 0) {
                croak[0]--
                croak[1]++
            } else {
                res = -1
                return false
            }

            'o' -> if (croak[1] > 0) {
                croak[1]--
                croak[2]++
            } else {
                res = -1
                return false
            }

            'a' -> if (croak[2] > 0) {
                croak[2]--
                croak[3]++
            } else {
                res = -1
                return false
            }

            'k' -> if (croak[3] > 0) {
                croak[3]--
                //这里维护青蛙的最少数量
                res = Math.max(count, res)
                count--
            } else {
                res = -1
                return false
            }

            else -> {
                res = -1
                return false
            }

        }
        return true
    }
}