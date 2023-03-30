package com.study.algorithm.algorithms;

import com.study.algorithm.algorithms.structure.TreeNode.Node;
import org.junit.Test;

//给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
//
//给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
//
//如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
//
//如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
//
// 
//
//示例 1：
//
//
// 
//
//输入：head = [3,4,1], insertVal = 2
//输出：[3,4,1,2]
//解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
//
//
//示例 2：
//
//输入：head = [], insertVal = 1
//输出：[1]
//解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
//示例 3：
//
//输入：head = [1], insertVal = 0
//输出：[1,0]
// 
//
//提示：
//
//0 <= Number of Nodes <= 5 * 10^4
//-10^6 <= Node.val <= 10^6
//-10^6 <= insertVal <= 10^6
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/4ueAj6
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 排序的循环链表 {
    @Test
    public void 排序的循环链表() {
        Node result = new Node(3);
        result.next = new Node(5);
        result.next.next = new Node(1);
        result.next.next.next = result;
        System.out.println("排序的循环链表：" + insert(result, 6));
    }

    public Node insert(Node head, int insertVal) {
        //单链表遍历
        //用一个dummy节点指向head，遍历过程中next节点是否为dummy.next，如果相等，说明链表已遍历完成
        //遍历时，注意要插入的节点有多种情况：
        //1.小于等于next节点，并且大于pre节点（在链表中部）
        //2.小于等于next节点，小于pre节点（说明此节点小于链表中所有节点）
        //3.大于等于pre节点，并且大于next节点（说明此节点大于等于链表所有节点）
        //找到合适的位置插入节点到链表中，并重新组成环

        if (head == null) {
            Node result = new Node(insertVal);
            result.next = result;
            return result;
        }
        //构造dummy节点，next指向head，最后返回dummy.next
        Node dummy = new Node();
        dummy.next = head;

        Node pre = null;
        //插入节点大于当前节点值
        while (head.val < insertVal) {
            pre = head;
            if (head.val > head.next.val) {
                //说明已经遍历到链表的最大值，退出循环
                head = head.next;
                break;
            }
            head = head.next;
            if (head == dummy.next) {
                //链表一趟遍历结束
                break;
            }
        }

        //说明目标节点的值小于最初给定的head值
        if (pre == null) {
            //找到链表最大节点
            while (head.next != dummy.next && head.val <= head.next.val) {
                head = head.next;
            }
            //找到目标节点的插入位置（目标节点小于next节点）
            while (head.next != dummy.next && head.next.val < insertVal) {
                head = head.next;
            }
            pre = head;
            head = head.next;
        }

        //插入节点
        pre.next = new Node(insertVal);
        pre.next.next = head;

        return dummy.next;
    }
}
