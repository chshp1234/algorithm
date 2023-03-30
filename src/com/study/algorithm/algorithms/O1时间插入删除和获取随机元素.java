package com.study.algorithm.algorithms;

import java.util.*;

//实现RandomizedSet 类：
//
//RandomizedSet() 初始化 RandomizedSet 对象
//bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
//bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
//int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
//你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
//
// 
//
//示例：
//
//输入
//["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
//[[], [1], [2], [2], [], [1], [2], []]
//输出
//[null, true, false, true, 2, true, false, 2]
//
//解释
//RandomizedSet randomizedSet = new RandomizedSet();
//randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
//randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
//randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
//randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
//randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
//randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
//randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
// 
//
//提示：
//
//-231 <= val <= 231 - 1
//最多调用 insert、remove 和 getRandom 函数 2 * 105 次
//在调用 getRandom 方法时，数据结构中 至少存在一个 元素。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class O1时间插入删除和获取随机元素 {
    List<Integer> list = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();

    public O1时间插入删除和获取随机元素() {
        //哈希表+动态数组
        //哈希表用于存储元素以及元素在数组中的下标
        //数组用于获取元素时可根据下标O1的获取到某个元素
        //删除元素时，先判断原生是否存在（用哈希表），再将数组末尾元素移至待删除的元素下标处，这样，其余元素及其对应的下标将不会改变
        //注意删除时：如果待删除的元素正好在末尾，不可重新移动元素。
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        Integer index = map.remove(val);
        if (index == null) {
            return false;
        }
        int last = list.remove((int) list.size() - 1);
        if (last == val) {
            return true;
        }
        list.set((int) index, last);
        map.put(last, index);

        return true;
    }

    public int getRandom() {
        return list.get(new Random().nextInt(list.size()));
    }
}
