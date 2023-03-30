package com.study.algorithm.algorithmsKT

//我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
//
//原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
//
//最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
//
// 
//
//示例 1:
//输入: "(123)"
//输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
//示例 2:
//输入: "(00011)"
//输出:  ["(0.001, 1)", "(0, 0.011)"]
//解释:
//0.0, 00, 0001 或 00.01 是不被允许的。
//示例 3:
//输入: "(0123)"
//输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
//示例 4:
//输入: "(100)"
//输出: [(10, 0)]
//解释:
//1.0 是不被允许的。
// 
//
//提示:
//
//4 <= S.length <= 12.
//S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/ambiguous-coordinates
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 模糊坐标 {
    fun ambiguousCoordinates(s: String): List<String> {
        //枚举,字符串分割
        //对字符串进行分割
        //1.先将字符串分割成左右两部分,分别代表坐标的x和y
        //2.对分割的字符串进行再进行分割,找出小数点出现的位置
        //3.将分割后的左右字符串列表(x坐标列表,y坐标列表)进行合并,即可得到一个有效的单个坐标,并加入结果列表中

        val end = s.length - 2
        val result = arrayListOf<String>()

        //分割线
        var split = 1
        while (split < end) {
            split++
            //左边有效数字字符串列表
            val left = validateNums(s, 1, split - 1) ?: continue
            //右边有效数字字符串列表
            val right = validateNums(s, split, end) ?: continue
            for (l in left) {
                for (r in right) {
                    //得到一个有效坐标
                    result.add("($l, $r)")
                }
            }
        }

        return result
    }

    fun validateNums(s: String, start: Int, end: Int): List<String>? {
        //如果起止相同,则返回这个下标的字符
        if (start == end) {
            return listOf(s[start].toString())
        }
        //如果起始坐标大于结束坐标,或者,起止坐标处的字符都是'0',则不可能凑成一个有效数字
        if (start > end || s[start] == '0' && s[end] == '0') {
            return null
        }
        //如果起始字符是'0',则只可能是数字"0.xxx"
        if (s[start] == '0') {
            return listOf("0.${s.substring(start + 1, end + 1)}")
        }

        val result = arrayListOf<String>()
        result.add(s.substring(start, end + 1))
        //如果结束处的字符是'0',则该数字不可能有小数部分,只能是个整数
        if (s[end] == '0') {
            return result
        }
        //根据小数点的位置再分割
        var split = start
        while (split < end) {
            split++
            result.add("${s.substring(start, split)}.${s.substring(split, end + 1)}")
        }
        return result
    }
}