"""
给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。



示例1：



输入：l1 = [7,2,4,3], l2 = [5,6,4]
输出：[7,8,0,7]
示例2：

输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[8,0,7]
示例3：

输入：l1 = [0], l2 = [0]
输出：[0]


提示：

链表的长度范围为 [1, 100]
0 <= node.val <= 9
输入数据保证链表代表的数字无前导 0


进阶：如果输入链表不能翻转该如何解决？
"""
from typing import Optional

from com.study.algorithm.algorithms.structure.ListNode import ListNode


class Solution:

    """
    栈
    先将链表加入数组中,然后模拟出栈反向遍历数组
    注意进位,并且最后进位数大于0时,还需加入一个头节点
    """
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        li1 = self.getList(l1)
        li2 = self.getList(l2)
        if len(li1) < len(li2):
            li1, li2 = li2, li1
        res = None
        step = 0
        for i in range(-1, -len(li2) - 1, -1):
            sum = li1[i] + li2[i] + step
            res = ListNode(sum % 10, res)
            step = sum // 10
        for i in range(-len(li2) - 1, -len(li1) - 1, -1):
            sum = li1[i] + step
            res = ListNode(sum % 10, res)
            step = sum // 10
        if step > 0:
            res = ListNode(step, res)
        return res

    def getList(self, l: ListNode):
        li = []
        while l:
            li.append(l.val)
            l = l.next
        return li


print(
    Solution().addTwoNumbers(ListNode(7, ListNode(2, ListNode(4, ListNode(3)))), ListNode(5, ListNode(6, ListNode(4)))))
