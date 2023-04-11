package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

import org.junit.Test;

public class 环形链表 {

    @Test
    public void 环形链表() {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(3);
        listNode1.next.next.next = new ListNode(4);
        listNode1.next.next.next.next = listNode1.next;
        System.out.println("链表是否有环:" + hasCycle(listNode1));
    }

    // 给定一个链表，判断链表中是否有环。
    //
    // 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果
    // pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
    //
    // 如果链表中存在环，则返回 true 。 否则，返回 false 。
    //
    //
    //
    // 进阶：
    //
    // 你能用 O(1)（即，常量）内存解决此问题吗？
    //
    //
    //
    // 示例 1：
    //
    //
    //
    // 输入：head = [3,2,0,-4], pos = 1
    // 输出：true
    // 解释：链表中有一个环，其尾部连接到第二个节点。
    // 示例 2：
    //
    //
    //
    // 输入：head = [1,2], pos = 0
    // 输出：true
    // 解释：链表中有一个环，其尾部连接到第一个节点。
    // 示例 3：
    //
    //
    //
    // 输入：head = [1], pos = -1
    // 输出：false
    // 解释：链表中没有环。
    //
    //
    // 提示：
    //
    // 链表中节点的数目范围是 [0, 104]
    // -105 <= Node.val <= 105
    // pos 为 -1 或者链表中的一个 有效索引 。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/linked-list-cycle
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;

            fast = fast.next.next;
        }

        return true;
    }

    @Test
    public void 环形链表II() {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(3);
        listNode1.next.next.next = new ListNode(4);
        listNode1.next.next.next.next = listNode1.next;

        ListNode result = detectCycle(listNode1);
        System.out.println("链表入环口:" + (result == null ? "null" : result.toStringSingle()));
    }

    // 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    //
    // 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
    //
    // 说明：不允许修改给定的链表。
    //
    //
    //
    // 示例 1：
    //
    // 输入：head = [3,2,0,-4], pos = 1
    // 输出：tail connects to node index 1
    // 解释：链表中有一个环，其尾部连接到第二个节点。
    //
    //
    // 示例 2：
    //
    // 输入：head = [1,2], pos = 0
    // 输出：tail connects to node index 0
    // 解释：链表中有一个环，其尾部连接到第一个节点。
    //
    //
    // 示例 3：
    //
    // 输入：head = [1], pos = -1
    // 输出：no cycle
    // 解释：链表中没有环。
    //
    //
    //
    //
    // 进阶：
    // 你是否可以不用额外空间解决此题？
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }

            slow = slow.next;

            fast = fast.next.next;
        }

        slow = head;

        while (slow != fast) {

            slow = slow.next;

            fast = fast.next;
        }

        return slow;
    }
}
