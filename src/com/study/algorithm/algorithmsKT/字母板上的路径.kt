package com.study.algorithm.algorithmsKT

//我们从一块字母板上的位置 (0, 0) 出发，该坐标对应的字符为 board[0][0]。
//
//在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]，如下所示。
//
//
//
//我们可以按下面的指令规则行动：
//
//如果方格存在，'U' 意味着将我们的位置上移一行；
//如果方格存在，'D' 意味着将我们的位置下移一行；
//如果方格存在，'L' 意味着将我们的位置左移一列；
//如果方格存在，'R' 意味着将我们的位置右移一列；
//'!' 会把在我们当前位置 (r, c) 的字符 board[r][c] 添加到答案中。
//（注意，字母板上只存在有字母的位置。）
//
//返回指令序列，用最小的行动次数让答案和目标 target 相同。你可以返回任何达成目标的路径。
//
// 
//
//示例 1：
//
//输入：target = "leet"
//输出："DDR!UURRR!!DDD!"
//示例 2：
//
//输入：target = "code"
//输出："RR!DDRR!UUL!R!"
// 
//
//提示：
//
//1 <= target.length <= 100
//target 仅含有小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/alphabet-board-path
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 字母板上的路径 {
    fun alphabetBoardPath(target: String): String {
        //直接模拟
        //首先用哈希表记录每个字母应在的位置坐标
        //然后初始化一个起始点位source=map['a']，开始遍历字符串时，从哈希表中找到目标点target=map[char]
        //如果source!=target,说明需要移动：
        //计算两个点的坐标的x距离和y的距离，因为要考虑‘z’这个特殊位置的存在（至少得先到位置'u'），其他点到‘z’时只能先向左移动，再向下移动，‘z’点到其他点时只能先向上移动，在向右移动
        //所以，可以先计算负值的距离，再计算正值的距离，然后按照距离计算需要移动几步
        //最后再加入"!"指令
        val map = HashMap<Char, Pair<Int, Int>>()
        for (c in 'a'..'z') {
            val i = c - 'a'
            map[c] = Pair(i % 5, i / 5)
        }
        val res = StringBuilder()
        var s = map['a']!!
        for (c in target) {
            val t = map[c]!!
            if (s != t) {
                val xd = t.first - s.first
                val yd = t.second - s.second
                if (xd < 0) {
                    for (j in 0 until -xd) {
                        res.append("L")
                    }
                }
                if (yd < 0) {
                    for (j in 0 until -yd) {
                        res.append("U")
                    }
                }
                if (xd > 0) {
                    for (j in 0 until xd) {
                        res.append("R")
                    }
                }
                if (yd > 0) {
                    for (j in 0 until yd) {
                        res.append("D")
                    }
                }
            }
            res.append("!")
            s = t
        }
        return res.toString()
    }
}