package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test;

//在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
//
// 
//
//示例 :
//
//输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
//输出: True
//解释:
//我们可以通过以下几步将start转换成end:
//RXXLRXRXL ->
//XRXLRXRXL ->
//XRLXRXRXL ->
//XRLXXRRXL ->
//XRLXXRRLX
// 
//
//提示：
//
//1 <= len(start) = len(end) <= 10000。
//start和end中的字符串仅限于'L', 'R'和'X'。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/swap-adjacent-in-lr-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 在LR字符串中交换相邻字符 {

    @Test
    fun 在LR字符串中交换相邻字符() {
        println("在LR字符串中交换相邻字符:${canTransform("XLXRRXXRXX", "LXXXXXXRRR")}")
    }

    fun canTransform(start: String, end: String): Boolean {
        //双指针模拟
        //因为R只能向右移动,L只能向左移动
        //使用两个指针分别遍历start和end
        //遍历end时,如果字符不是'X',记录当前指针indexEnd
        //遍历start时,如果字符不是'X',记录当前指针indexStart
        //再判断两个指针:
        //1.如果(indexEnd == end.length && indexStart == start.length),说明两个指针都遍历到了字符串结尾,返回true
        //2.如果((indexEnd == end.length && indexStart != start.length) || (indexEnd != end.length && indexStart == start.length)),
        //说明其中一个遍历到了字符串结尾,而另一个指针没遍历到结尾(遇到'L'或'R'),那么肯定不能通过移动进行转换,返回false
        //3.如果(end[indexEnd] == 'L'),end字符串此时遍历到一个'L'字符,那么这个'L'肯定是indexEnd或者右侧的'L'向左移动来的,
        //也就是如果(indexStart < indexEnd || start[indexStart] != 'L'),说明start处的'L'在indexEnd左边,肯定移动不到indexEnd的位置,甚至不是字符'L',那么返回false
        //4.如果(end[indexEnd] == 'R'),end字符串此时遍历到一个'R'字符,那么这个'R'肯定是indexEnd或者左侧的'R'向右移动来的,
        //也就是如果(indexStart > indexEnd || start[indexStart] != 'R'),说明start处的'R'在indexEnd右边,肯定移动不到indexEnd的位置,甚至不是字符'R',那么返回false

        var indexStart = 0
        var indexEnd = 0

        while (true) {
            //寻找end中下一个不是'X'的字符
            while (indexEnd < end.length) {
                if (end[indexEnd] != 'X') {
                    break
                }
                indexEnd++
            }
            //寻找start中下一个不是'X'的字符
            while (indexStart < start.length) {
                if (start[indexStart] != 'X') {
                    break
                }
                indexStart++
            }
            //1.
            if (indexEnd == end.length && indexStart == start.length) {
                return true
            }
            //2.
            if ((indexEnd == end.length && indexStart != start.length) || (indexEnd != end.length && indexStart == start.length)) {
                return false
            }
            if (end[indexEnd] == 'L') {
                //3.
                if (indexStart < indexEnd || start[indexStart] != 'L') {
                    return false
                }
            } else if (end[indexEnd] == 'R') {
                //4.
                if (indexStart > indexEnd || start[indexStart] != 'R') {
                    return false
                }
            }
            indexStart++
            indexEnd++
        }
    }
}