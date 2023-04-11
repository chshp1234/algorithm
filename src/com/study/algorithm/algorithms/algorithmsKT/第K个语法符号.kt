package com.study.algorithm.algorithms.algorithmsKT

//我们构建了一个包含 n 行( 索引从 1  开始 )的表。首先在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
//
//例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
//给定行数 n 和序数 k，返回第 n 行中第 k 个字符。（ k 从索引 1 开始）
//
//
//示例 1:
//
//输入: n = 1, k = 1
//输出: 0
//解释: 第一行：0
//示例 2:
//
//输入: n = 2, k = 1
//输出: 0
//解释:
//第一行: 0
//第二行: 01
//示例 3:
//
//输入: n = 2, k = 2
//输出: 1
//解释:
//第一行: 0
//第二行: 01
// 
//
//提示:
//
//1 <= n <= 30
//1 <= k <= 2n - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/k-th-symbol-in-grammar
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 第K个语法符号 {
    fun kthGrammar(n: Int, k: Int): Int {
        //根据第n层的第k个数的下标k,求出其对应的第n-1层的下标pos
        //1.根据规律可以看出,每一层相对上一层,数字数量翻倍,且前一半的数字和上一层数字一样,那么如果k<=count/2,pos=k
        //2.每一层后一半的数字,是上一层中的,后一半数字拼接前一半数字:
        //- 那么如果(k-count/4)>(count /2),说明其在上一层的前一半数字中,pos=(k-count/4) -count/2
        //- 否则,其在上一层的后一半数字中,pos=(k-count/4)
        //3.递归遍历kthGrammar(n-1,pos)
        //4.因为前面的数都是不变的,那么递归终止条件是,k=1时,结果为0;k=2时,结果为1
        if (k == 1) {
            return 0
        }
        if (k == 2) {
            return 1
        }
        val count = Math.pow(2.0, (n - 1).toDouble()).toInt() / 2
        val pos = if (k <= count) k else {
            (count / 2).let { half ->
                (k - half).let {
                    if (it > count) it - count else it
                }
            }
        }

        return kthGrammar(n - 1, pos)
    }
}