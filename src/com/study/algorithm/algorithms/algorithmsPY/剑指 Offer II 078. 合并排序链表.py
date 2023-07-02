from typing import List

from com.study.algorithm.algorithms.structure.ListNode import ListNode


class Solution:
    # 分治,归并排序
    # 因为每个列表中的元素已经是有序的,那么使用归并对两两列表进行排序,整合成一个列表后返回
    # 对两个列表进行排序时,使用双指针,小的元素加入结果列表中,再使其重新只想next节点,直到有一个列表为空为止;再将另一个列表元素全部加入结果列表中,并返回
    # 当两个列表合并成一个时,再与另一个合并后的列表继续进行合并操作,直到所有元素都有序加入结果中
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        if len(lists) < 1:
            return lists[0]
        elif len(lists) == 0:
            return
        else:
            return self.mergeSort(lists, 0, len(lists))

    def mergeSort(self, lists, start, end) -> ListNode:
        count = end - start
        if count >= 2:
            left = self.mergeSort(lists, start, (start + end) // 2)
            right = self.mergeSort(lists, (start + end) // 2, end)
            return self.mergeList(left, right)
        else:
            return lists[start]

    def mergeList(self, left, right) -> ListNode:
        dummy = ListNode()
        temp = dummy
        while left is not None and right is not None:
            if left.val > right.val:
                temp.next = right
                right = right.next
            else:
                temp.next = left
                left = left.next

            temp = temp.next

        while left is not None:
            temp.next = left
            left = left.next
            temp = temp.next

        while right is not None:
            temp.next = right
            right = right.next
            temp = temp.next

        return dummy.next


node1 = ListNode(1, ListNode(4, ListNode(5)))
node2 = ListNode(1, ListNode(3, ListNode(4)))
node3 = ListNode(2, ListNode(6))
print(Solution().mergeKLists([node1, node2, node3]))
