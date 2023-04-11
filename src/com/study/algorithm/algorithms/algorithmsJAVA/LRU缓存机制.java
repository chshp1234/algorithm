package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU缓存机制 {

    @Test
    public void LRU缓存机制() {
        LRUCache obj = new LRUCache(3);
        int param_1 = obj.get(2);
        obj.put(1, 1);
        System.out.println("LRU缓存机制:");
    }

    // 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
    //
    // 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
    // 写入数据 put(key, value) -
    // 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
    //
    //
    //
    // 进阶:
    //
    // 你是否可以在 O(1) 时间复杂度内完成这两种操作？
    //
    //
    //
    // 示例:
    //
    // LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
    //
    // cache.put(1, 1);
    // cache.put(2, 2);
    // cache.get(1);       // 返回  1
    // cache.put(3, 3);    // 该操作会使得密钥 2 作废
    // cache.get(2);       // 返回 -1 (未找到)
    // cache.put(4, 4);    // 该操作会使得密钥 1 作废
    // cache.get(1);       // 返回 -1 (未找到)
    // cache.get(3);       // 返回  3
    // cache.get(4);       // 返回  4
    //\
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/lru-cache
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class LRUCache extends LinkedHashMap<Integer, Integer> {
        int maxCapacity;

        // 实现本题的两种操作，需要用到一个哈希表和一个双向链表。在面试中，面试官一般会期望读者能够自己实现一个简单的双向链表，而不是使用语言自带的、封装好的数据结构。在 Python
        // 语言中，有一种结合了哈希表与双向链表的数据结构 OrderedDict，只需要短短的几行代码就可以完成本题。在 Java 语言中，同样有类似的数据结构
        // LinkedHashMap。这些做法都不会符合面试官的要求，因此下面只给出使用封装好的数据结构实现的代码，而不多做任何阐述。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            maxCapacity = capacity;
        }

        public int get(int key) {
            return getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            if (size() > maxCapacity) {
                return true;
            }
            return false;
        }

    }
}
