package com.study.algorithm.algorithmsKT

//设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
//
//在链表类中实现这些功能：
//
//get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
//addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
//addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
//addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
//deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
// 
//
//示例：
//
//MyLinkedList linkedList = new MyLinkedList();
//linkedList.addAtHead(1);
//linkedList.addAtTail(3);
//linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
//linkedList.get(1);            //返回2
//linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//linkedList.get(1);            //返回3
// 
//
//提示：
//
//所有val值都在 [1, 1000] 之内。
//操作次数将在  [1, 1000] 之内。
//请不要使用内置的 LinkedList 库。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/design-linked-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 设计链表 {
    class MyLinkedList() {
        //双向链表

        var head: IntNode? = null
        var tail: IntNode? = null
        var size = 0

        fun getNode(index: Int): IntNode? {
            if (index < 0 || index >= size) {
                return null
            }
            var node: IntNode
            if (index > (size / 2)) {
                val count = size - index - 1
                node = tail!!
                for (i in 0 until count) {
                    node = node.preNode!!
                }
            } else {
                node = head!!
                for (i in 0 until index) {
                    node = node.nextNode!!
                }
            }
            return node
        }

        fun get(index: Int): Int {
            return getNode(index)?.num ?: -1
        }

        fun addAtHead(`val`: Int) {
            val node = IntNode(`val`)
            if (size++ == 0) {
                tail = node
            }
            head?.preNode = node
            node.nextNode = head
            head = node
        }

        fun addAtTail(`val`: Int) {
            val node = IntNode(`val`)
            if (size++ == 0) {
                head = node
            }
            tail?.nextNode = node
            node.preNode = tail
            tail = node
        }

        fun addAtIndex(index: Int, `val`: Int) {
            when {
                index <= 0 -> {
                    addAtHead(`val`)
                }

                index == size -> {
                    addAtTail(`val`)
                }

                index < size -> {
                    val node = getNode(index)!!
                    val newNode = IntNode(`val`)
                    newNode.nextNode = node
                    node.preNode?.nextNode = newNode
                    newNode.preNode = node.preNode
                    node.preNode = newNode
                    size++
                }
            }
        }

        fun deleteAtIndex(index: Int) {
            if (index in 0 until size) {
                val deleteNode = getNode(index)!!
                if (deleteNode == head) {
                    head = deleteNode.nextNode
                } else if (deleteNode == tail) {
                    tail = deleteNode.preNode
                }
                deleteNode.preNode?.nextNode = deleteNode.nextNode
                deleteNode.nextNode?.preNode = deleteNode.preNode
                deleteNode.preNode = null
                deleteNode.nextNode = null
                size--

            }
        }

    }

    class IntNode(var num: Int) {
        var preNode: IntNode? = null
        var nextNode: IntNode? = null
    }
}