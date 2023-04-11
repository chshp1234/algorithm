package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.ArrayList;
import java.util.List;

//有 n 个 (id, value) 对，其中 id 是 1 到 n 之间的一个整数，value 是一个字符串。不存在 id 相同的两个 (id, value) 对。
//
//设计一个流，以 任意 顺序获取 n 个 (id, value) 对，并在多次调用时 按 id 递增的顺序 返回一些值。
//
//实现 OrderedStream 类：
//
//OrderedStream(int n) 构造一个能接收 n 个值的流，并将当前指针 ptr 设为 1 。
//String[] insert(int id, String value) 向流中存储新的 (id, value) 对。存储后：
//如果流存储有 id = ptr 的 (id, value) 对，则找出从 id = ptr 开始的 最长 id 连续递增序列 ，并 按顺序 返回与这些 id 关联的值的列表。然后，将 ptr 更新为最后那个  id + 1 。
//否则，返回一个空列表。
//
// 
//
//示例：
//
//
//
//输入
//["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
//[[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
//输出
//[null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]
//
//解释
//OrderedStream os= new OrderedStream(5);
//os.insert(3, "ccccc"); // 插入 (3, "ccccc")，返回 []
//os.insert(1, "aaaaa"); // 插入 (1, "aaaaa")，返回 ["aaaaa"]
//os.insert(2, "bbbbb"); // 插入 (2, "bbbbb")，返回 ["bbbbb", "ccccc"]
//os.insert(5, "eeeee"); // 插入 (5, "eeeee")，返回 []
//os.insert(4, "ddddd"); // 插入 (4, "ddddd")，返回 ["ddddd", "eeeee"]
// 
//
//提示：
//
//1 <= n <= 1000
//1 <= id <= n
//value.length == 5
//value 仅由小写字母组成
//每次调用 insert 都会使用一个唯一的 id
//恰好调用 n 次 insert
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/design-an-ordered-stream
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 设计有序流 {

    //哈希表
    //因为大小确定，我们用一个数组存储每个下标下的字符串
    //每次加入时，判断当前下标ptr的数组中是否存在字符串，若存在，则加入列表中，并继续遍历下一个下标，直到遍历到数组结尾或者不存在字符串的下标为止
    //若当前下标下没有字符串，则将此字符串加入数组对应的下标中
    class OrderedStream {

        int ptr = 1;
        int size;
        String[] map;

        public OrderedStream(int n) {
            size = n;
            map = new String[n + 1];
        }

        public List<String> insert(int idKey, String value) {
            List<String> result = new ArrayList<>();

            if (ptr == idKey) {
                result.add(value);
                ptr++;
            } else {
                map[idKey] = value;
                return result;
            }

            while (ptr <= size) {
                String temp = map[ptr];
                if (temp != null) {
                    result.add(temp);
                    ptr++;
                } else {
                    break;
                }
            }

            return result;
        }
    }
}
