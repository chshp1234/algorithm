package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 全O1的数据结构 {

    //哈希表+双向链表
    //双向链表中，节点按序存储每种出现次数以及所包含的字符串
    //哈希表用于存储每个字符串所对应的链表的节点
    //添加时，可根据哈希表快速找到当前字符串所在的节点，并将该字符串从改节点的字符串列表中删除，该字符串所出现的次数为节点对应的次数值+1
    //那么判断下一个次数的节点时，若存在，则直接将字符串加入节点的字符串列表中
    //若不存在，则新建当前次数对应的节点，插入前后节点当中
    //删除时，同添加，只不过该字符串出现的次数将为节点对应的次数-1，并且该与上一个节点的次数相比较
    //节点中的字符串列表，可以用Set进行存储，查询删除的时间复杂度为O1,因为是链表结构，所以链表节点的插入删除的时间复杂度也为O1
    //查找最大最小值时，只要找到链表头部和尾部的节点即可。固我们可以维护两个哨兵节点head和tail代表头尾节点，那么查找最大最小值的时间复杂度也为O1

    private Map<String, Table> tableMap;
    private Table head;
    private Table tail;

    public 全O1的数据结构() {
        head = new Table();
        tail = new Table();
        tableMap = new HashMap<>();

        head.next = tail;
        tail.pre = head;

        tail.count = Integer.MAX_VALUE;
    }

    public void inc(String key) {
        Table kt = tableMap.get(key);

        if (kt == null) {
            if (head.next.count > 1) {
                kt = new Table();
                kt.count = 1;
                kt.set.add(key);

                kt.next = head.next;
                kt.pre = head;
                head.next.pre = kt;
                head.next = kt;

                tableMap.put(key, kt);
            } else {
                head.next.set.add(key);

                tableMap.put(key, head.next);
            }
        } else {
            kt.set.remove(key);
            int count = kt.count + 1;

            Table next = kt.next;
            if (kt.set.size() == 0) {
                Table pre = kt.pre;
                pre.next = kt.next;
                kt.next.pre = pre;
                kt.pre = null;
                kt.next = null;
            }
            if (next.count > count) {
                kt = new Table();
                kt.count = count;
                kt.set.add(key);

                kt.next = next;
                kt.pre = next.pre;
                kt.pre.next = kt;
                kt.next.pre = kt;

                tableMap.put(key, kt);
            } else {
                next.set.add(key);

                tableMap.put(key, next);
            }
        }
    }

    public void dec(String key) {
        Table kt = tableMap.get(key);
        kt.set.remove(key);
        int count = kt.count - 1;

        Table pre = kt.pre;
        if (kt.set.size() == 0) {
            Table temp = kt.pre;
            temp.next = kt.next;
            kt.next.pre = temp;
            kt.pre = null;
            kt.next = null;
        }

        if (count == 0) {
            tableMap.remove(key);
            return;
        }

        if (pre.count < count) {
            kt = new Table();
            kt.count = count;
            kt.set.add(key);

            kt.next = pre.next;
            kt.pre = pre;
            kt.pre.next = kt;
            kt.next.pre = kt;

            tableMap.put(key, kt);

        } else {
            pre.set.add(key);
            tableMap.put(key, pre);
        }
    }

    public String getMaxKey() {
        return tail.pre.set.size() == 0 ? "" : tail.pre.set.iterator().next();
    }

    public String getMinKey() {
        return head.next.set.size() == 0 ? "" : head.next.set.iterator().next();
    }

    public class Table {
        int count = 0;
        Set<String> set = new HashSet<>();

        Table pre;
        Table next;

    }
}
