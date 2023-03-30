package com.study.algorithm.algorithmsKT

import org.junit.Test

//给你一个长度为 n 的字符串数组 names 。你将会在文件系统中创建 n 个文件夹：在第 i 分钟，新建名为 names[i] 的文件夹。
//
//由于两个文件 不能 共享相同的文件名，因此如果新建文件夹使用的文件名已经被占用，系统会以 (k) 的形式为新文件夹的文件名添加后缀，其中 k 是能保证文件名唯一的 最小正整数 。
//
//返回长度为 n 的字符串数组，其中 ans[i] 是创建第 i 个文件夹时系统分配给该文件夹的实际名称。
//
// 
//
//示例 1：
//
//输入：names = ["pes","fifa","gta","pes(2019)"]
//输出：["pes","fifa","gta","pes(2019)"]
//解释：文件系统将会这样创建文件名：
//"pes" --> 之前未分配，仍为 "pes"
//"fifa" --> 之前未分配，仍为 "fifa"
//"gta" --> 之前未分配，仍为 "gta"
//"pes(2019)" --> 之前未分配，仍为 "pes(2019)"
//示例 2：
//
//输入：names = ["gta","gta(1)","gta","avalon"]
//输出：["gta","gta(1)","gta(2)","avalon"]
//解释：文件系统将会这样创建文件名：
//"gta" --> 之前未分配，仍为 "gta"
//"gta(1)" --> 之前未分配，仍为 "gta(1)"
//"gta" --> 文件名被占用，系统为该名称添加后缀 (k)，由于 "gta(1)" 也被占用，所以 k = 2 。实际创建的文件名为 "gta(2)" 。
//"avalon" --> 之前未分配，仍为 "avalon"
//示例 3：
//
//输入：names = ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]
//输出：["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece(4)"]
//解释：当创建最后一个文件夹时，最小的正有效 k 为 4 ，文件名变为 "onepiece(4)"。
//示例 4：
//
//输入：names = ["wano","wano","wano","wano"]
//输出：["wano","wano(1)","wano(2)","wano(3)"]
//解释：每次创建文件夹 "wano" 时，只需增加后缀中 k 的值即可。
//示例 5：
//
//输入：names = ["kaido","kaido(1)","kaido","kaido(1)"]
//输出：["kaido","kaido(1)","kaido(2)","kaido(1)(1)"]
//解释：注意，如果含后缀文件名被占用，那么系统也会按规则在名称后添加新的后缀 (k) 。
// 
//
//提示：
//
//1 <= names.length <= 5 * 10^4
//1 <= names[i].length <= 20
//names[i] 由小写英文字母、数字和/或圆括号组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/making-file-names-unique
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 保证文件名唯一 {
    @Test
    fun 保证文件名唯一() {
        println(
            "保证文件名唯一:${
                getFolderNames(
                    arrayOf("kaido","kaido(1)","kaido","kaido(1)","kaido(2)")
                )
            }"
        )
    }

    fun getFolderNames(names: Array<String>): Array<String> {
        //哈希表
        //用哈希表记录当前字符串已在第几位(以当前字符串为文件记录有多少个重复字符串)
        //首先判断当前字符串是否在哈希表中,如果不存在,则当前记录为0
        //如果存在,取出当前字符串的下标index,那么下一个字符串下标index++,且字符串更新为"name(index)",一直判断下一个字符串是否在哈希表中,如果存在,则index++
        //直到找到不在哈希表中的字符串的下标,那么当前字符串的下标更新为index
        //同时将"name(index)"加入结果中,并注意更新哈希表中"name(index)"字符串的下标为0(因为已经加入结果中)
        val map = HashMap<String, Int>()
        val res = Array(names.size) {
            names[it]
        }
        for (n in names.indices) {
            val minIndex = map[names[n]]?.let {
                var index = it + 1
                while (map.contains("${names[n]}($index)")) {
                    index++
                }
                index
            } ?: 0
            map[res[n]] = minIndex
            if (minIndex > 0) {
                res[n] = "${names[n]}($minIndex)"
                map[res[n]] = 0
            }
        }
        return res
    }
}