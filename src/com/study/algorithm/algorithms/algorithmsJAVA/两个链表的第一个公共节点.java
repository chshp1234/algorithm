package com.study.algorithm.algorithms.algorithmsJAVA;

import com.study.algorithm.algorithms.structure.ListNode;

public class 两个链表的第一个公共节点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //1.哈希表记录每个链表的每个节点，返回第一个相同的节点
        //2.分别记录两个链表的长度（快慢双指针），再从较长的链表中去除多余的过长的节点，最后依次判断两个节点是否相同。
        if (headA == null || headB == null) {
            return null;
        }

        ListNode slow  = headA;
        ListNode quick = headA.next;
        int      ASize = 1;
        while (true) {
            if (quick == null) {
                ASize = ASize * 2 - 1;
                break;
            }
            if (quick.next == null) {
                ASize = ASize * 2;
                break;
            }
            ASize++;
            slow = slow.next;
            quick = quick.next.next;
        }

        int BSize = 1;
        slow = headB;
        quick = headB.next;
        while (true) {
            if (quick == null) {
                BSize = BSize * 2 - 1;
                break;
            }
            if (quick.next == null) {
                BSize = BSize * 2;
                break;
            }
            BSize++;
            slow = slow.next;
            quick = quick.next.next;
        }

        if (ASize > BSize) {
            for (int i = 0, l = ASize - BSize; i < l; i++) {
                headA = headA.next;
            }
        } else if (BSize > ASize) {
            for (int i = 0, l = BSize - ASize; i < l; i++) {
                headB = headB.next;
            }
        }

        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;

        //让两个链表一起走，
        //先到结尾的链表必然比另一个链表短，
        //那么重新指向另一个链表，当较长的链表走到尾时，较短的链表也也将走过长度的差值个节点
        //此时开始链表剩余长度将相等，即可进行判断
        /*ListNode a = headA;
        ListNode b = headB;
        while(a != b) {
            a = a != null ? a.next:headB;
            b = b != null ? b.next:headA;
        }
        return a;

        作者：wzy-id
        链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/2fen-zhong-ti-jie-jian-zhi-offer-52-lian-tfoa/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
