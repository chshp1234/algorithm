package com.study.algorithm.algorithms;

//不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
//
//实现 MyHashSet 类：
//
//void add(key) 向哈希集合中插入值 key 。
//bool contains(key) 返回哈希集合中是否存在这个值 key 。
//void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
// 
//示例：
//
//输入：
//["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
//[[], [1], [2], [1], [3], [2], [2], [2], [2]]
//输出：
//[null, null, null, true, false, null, true, null, false]
//
//解释：
//MyHashSet myHashSet = new MyHashSet();
//myHashSet.add(1);      // set = [1]
//myHashSet.add(2);      // set = [1, 2]
//myHashSet.contains(1); // 返回 True
//myHashSet.contains(3); // 返回 False ，（未找到）
//myHashSet.add(2);      // set = [1, 2]
//myHashSet.contains(2); // 返回 True
//myHashSet.remove(2);   // set = [1]
//myHashSet.contains(2); // 返回 False ，（已移除）
// 
//
//提示：
//
//0 <= key <= 106
//最多调用 104 次 add、remove 和 contains 。
// 
//
//进阶：你可以不使用内建的哈希集合库解决此问题吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/design-hashset
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 设计哈希集合 {
    //为了实现哈希集合这一数据结构，有以下几个关键问题需要解决：
    //
    //1.哈希函数：能够将集合中任意可能的元素映射到一个固定范围的整数值，并将该元素存储到整数值对应的地址上。
    //2.冲突处理：由于不同元素可能映射到相同的整数值，因此需要在整数值出现「冲突」时，需要进行冲突处理。总的来说，有以下几种策略解决冲突：
    //·链地址法：为每个哈希值维护一个链表，并将具有相同哈希值的元素都放入这一链表当中。
    //·开放地址法：当发现哈希值 h 处产生冲突时，根据某种策略，从 h 出发找到下一个不冲突的位置。
    //例如，一种最简单的策略是，不断地检查 h+1,h+2,h+3,… 这些整数对应的位置。
    //·再哈希法：当发现哈希冲突后，使用另一个哈希函数产生一个新的地址。
    //3.扩容：当哈希表元素过多时，冲突的概率将越来越大，而在哈希表中查询一个元素的效率也会越来越低。
    //因此，需要开辟一块更大的空间，来缓解哈希表中发生的冲突。
    //
    //设哈希表的大小为 base，则可以设计一个简单的哈希函数：hash(x)=x mod base。
    //我们开辟一个大小为 base 的数组，数组的每个位置是一个链表。当计算出哈希值之后，就插入到对应位置的链表当中。
    //由于我们使用整数除法作为哈希函数，为了尽可能避免冲突，应当将 base 取为一个质数。
    //查了下质数取模，其实是利用了同余的感念：
    //当元素是个有规律的等差数列时，并且和基数（数组大小）最大公约数不为1时，就会造成哈希映射时冲突变高（数组某些位置永远不会有值）。
    //比如数列0，6，12，18，24...，
    //·base为10，取模放入哈希表中位置将只能在0,2,4,6,8这几个数组位置上
    //·但我们如果把base取7（数组大小甚至比10小），同样数列可以分布在哈希表中的0,1,2,3,4,5,6
    //所以用质数可以使得哈希表中每个位置都“有用武之地”。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/design-hashset/solution/she-ji-ha-xi-ji-he-by-leetcode-solution-xp4t/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/design-hashset/solution/she-ji-ha-xi-ji-he-by-leetcode-solution-xp4t/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    int[] set = new int[1000001];

    /** Initialize your data structure here. */
    public 设计哈希集合() {

    }

    public void add(int key) {
        set[key] = 1;
    }

    public void remove(int key) {
        set[key] = 0;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return set[key] == 1;
    }
}
