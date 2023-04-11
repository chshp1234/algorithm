package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 钥匙和房间 {

    @Test
    public void 重新排列数组() {

        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> keys = new ArrayList<>();
        keys.add(1);
        keys.add(3);
        rooms.add(keys);

        keys = new ArrayList<>();
        keys.add(1);
        keys.add(3);
        keys.add(0);
        rooms.add(keys);

        keys = new ArrayList<>();
        keys.add(2);
        rooms.add(keys);

        keys = new ArrayList<>();
        keys.add(1);
        rooms.add(keys);

        System.out.println("重新排列数组:" + canVisitAllRooms(rooms));
    }

    // 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
    //
    // 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N =
    // rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
    //
    // 最初，除 0 号房间外的其余所有房间都被锁住。
    //
    // 你可以自由地在房间之间来回走动。
    //
    // 如果能进入每个房间返回 true，否则返回 false。
    //
    // 示例 1：
    //
    // 输入: [[1],[2],[3],[]]
    // 输出: true
    // 解释:
    // 我们从 0 号房间开始，拿到钥匙 1。
    // 之后我们去 1 号房间，拿到钥匙 2。
    // 然后我们去 2 号房间，拿到钥匙 3。
    // 最后我们去了 3 号房间。
    // 由于我们能够进入每个房间，我们返回 true。
    // 示例 2：
    //
    // 输入：[[1,3],[3,0,1],[2],[0]]
    // 输出：false
    // 解释：我们不能进入 2 号房间。
    // 提示：
    //
    // 1 <= rooms.length <= 1000
    // 0 <= rooms[i].length <= 1000
    // 所有房间中的钥匙数量总计不超过 3000。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/keys-and-rooms
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        // 有向图遍历，判断当前有向图是否是连通图

        // 哈希表，记录已访问过的节点
        Set<Integer> visit = new HashSet<>();

        dfs(rooms, visit, 0);

        return visit.size() == rooms.size();
    }

    // 深度优先搜索（当然，广度也行）
    private void dfs(List<List<Integer>> rooms, Set<Integer> visit, int index) {
        // 如果当前边已经访问过，则跳过
        if (visit.add(index)) {
            for (int i = 0, l = rooms.get(index).size(); i < l; i++) {
                // 搜索当前边指向的其他边
                dfs(rooms, visit, rooms.get(index).get(i));
            }
        }
    }
}
