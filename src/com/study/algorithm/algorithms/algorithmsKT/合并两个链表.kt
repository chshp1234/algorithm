package com.study.algorithm.algorithms.algorithmsKT

import com.study.algorithm.algorithms.structure.ListNode

//给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
//
//请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。
//
//下图中蓝色边和节点展示了操作后的结果：
//
//
//请你返回结果链表的头指针。
//
// 
//
//示例 1：
//
//
//
//输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
//输出：[0,1,2,1000000,1000001,1000002,5]
//解释：我们删除 list1 中下标为 3 和 4 的两个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
//示例 2：
//
//
//输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
//输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
//解释：上图中蓝色的边和节点为答案链表。
// 
//
//提示：
//
//3 <= list1.length <= 104
//1 <= a <= b < list1.length - 1
//1 <= list2.length <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/merge-in-between-linked-lists
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 合并两个链表 {
    fun mergeInBetween(list1: ListNode, a: Int, b: Int, list2: ListNode): ListNode {
        //链表，双指针
        //使用两个指针指向要删除的子链表的头节点的前驱结点start，以及要删除的子链表的尾节点的后置节点end
        //做删除操作就是将start.next=list2，并找到list2的尾节点tail，再将tail.next=end，即可删除子节点，并将list2插入其中
        var start = list1
        for (i in 1 until a) {
            start = start.next!!
        }
        var end = start
        for (i in a..b) {
            end = end.next!!
        }
        end = end.next!!
        start.next = list2
        do {
            start = start.next!!
        } while (start.next != null)
        start.next = end
        return list1
    }
}