package com.study.algorithm.algorithms;

import java.util.*;

public class 基于时间的键值存储 {

    //哈希表+二分查找
    //既然是键值进行存取，那么肯定用数据结构哈希表
    //set操作中，严格按照时间戳递增进行，所以此存储的列表肯定是有序的
    //那么我们进行查找时可以使用二分法进行查找

    Map<String, List<ValueWithTimestamp>> table = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public 基于时间的键值存储() {

    }

    public void set(String key, String value, int timestamp) {
        List<ValueWithTimestamp> list = table.get(key);
        if (list == null) {
            list = new ArrayList<>();
            table.put(key, list);
        }
        list.add(new ValueWithTimestamp(value, timestamp));
    }

    public String get(String key, int timestamp) {
        List<ValueWithTimestamp> list = table.get(key);
        if (list == null) {
            return "";
        }
        return binarySearch(list, timestamp);
    }

    public String binarySearch(List<ValueWithTimestamp> list, int timestamp) {
        if (list.get(0).timestamp > timestamp) {
            return "";
        }
        int len = list.size();
        if (list.get(len - 1).timestamp <= timestamp) {
            return list.get(len - 1).value;
        }
        int l = 1, r = len - 2;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int timemid = list.get(mid).timestamp;
            if (timemid < timestamp) {
                l = mid + 1;
            } else if (timemid > timestamp) {
                r = mid - 1;
            } else {
                return list.get(mid).value;
            }
        }
        return list.get(r).value;
    }

    public static class ValueWithTimestamp {
        public String value;
        public int timestamp;

        public ValueWithTimestamp(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
}
