package com.study.algorithm.algorithms.algorithmsKT

//给你一个整数数组 arr ，数组中的每个整数 互不相同 。另有一个由整数数组构成的数组 pieces，其中的整数也 互不相同 。请你以 任意顺序 连接 pieces 中的数组以形成 arr 。但是，不允许 对每个数组 pieces[i] 中的整数重新排序。
//
//如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
//
// 
//
//示例 1：
//
//输入：arr = [15,88], pieces = [[88],[15]]
//输出：true
//解释：依次连接 [15] 和 [88]
//示例 2：
//
//输入：arr = [49,18,16], pieces = [[16,18,49]]
//输出：false
//解释：即便数字相符，也不能重新排列 pieces[0]
//示例 3：
//
//输入：arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
//输出：true
//解释：依次连接 [91]、[4,64] 和 [78]
// 
//
//提示：
//
//1 <= pieces.length <= arr.length <= 100
//sum(pieces[i].length) == arr.length
//1 <= pieces[i].length <= arr.length
//1 <= arr[i], pieces[i][j] <= 100
//arr 中的整数 互不相同
//pieces 中的整数 互不相同（也就是说，如果将 pieces 扁平化成一维数组，数组中的所有整数互不相同）
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/check-array-formation-through-concatenation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 能否连接形成数组 {
    fun canFormArray(arr: IntArray, pieces: Array<IntArray>): Boolean {
        //哈希表
        //1.用哈希表map记录pieces里每个子数组的首元素以及当前子数组对应的下标
        //2.遍历arr,根据当前遍历到的元素,查找其是否在map中:
        //若不存在,说明无法按序连接成原数组(第一个匹配的元素也必须在pieces中子数组的首元素位置);
        //否则,根据取出的下标找到其在pieces中的子数组list.
        //3.双指针遍历list,依次按序判断list中的元素和arr中的元素是否相同,若不相同那么返回false
        //4.list遍历结束后,继续返回第二步,直到arr遍历结束
        val map = mutableMapOf<Int, Int>()
        pieces.forEachIndexed { index, ints ->
            map[ints[0]] = index
        }

        var index = 0

        while (index < arr.size) {
            val list = map[arr[index]]?.let {
                pieces[it]
            } ?: return false

            for (num in list) {
                if (/*index == arr.size || */arr[index++] != num) {
                    return false
                }
            }
        }

        return true
    }
}