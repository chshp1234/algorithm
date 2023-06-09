package com.study.algorithm.algorithms.algorithmsKT

class `Excel 表中某个范围内的单元格` {
    fun cellsInRange(s: String): List<String> {
        //模拟
        //字符串分割
        //按列,按行进行字符串拼接
        val res = mutableListOf<String>()
        for (c in s[0]..s[3]) {
            for (r in s[1]..s[4]) {
                res.add("$c$r")
            }
        }
        return res
    }
}