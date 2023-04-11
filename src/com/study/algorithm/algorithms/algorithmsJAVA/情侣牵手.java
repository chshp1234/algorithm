package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
//
//人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
//
//这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
//
//示例 1:
//
//输入: row = [0, 2, 1, 3]
//输出: 1
//解释: 我们只需要交换row[1]和row[2]的位置即可。
//示例 2:
//
//输入: row = [3, 2, 0, 1]
//输出: 0
//解释: 无需交换座位，所有的情侣都已经可以手牵手了。
//说明:
//
//len(row) 是偶数且数值在 [4, 60]范围内。
//可以保证row 是序列 0...len(row)-1 的一个全排列。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/couples-holding-hands
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 情侣牵手 {

    @Test
    public void 情侣牵手() {
        int[] ints = {0, 2, 3, 4, 1, 5};
        System.out.println("情侣牵手:" + minSwapsCouples(ints));
    }

    public int minSwapsCouples(int[] row) {
        //并查集
        //首先根据数组，构图，要交换的数量为：图中点的个数-连通分量数
        //首先得确定图的顶点，对于数组中，根据条件可知：
        //根据情侣分队确定顶点，数组元素num/2可代表此为第几对情侣，元素1、2就为第一对（0）和第二队（1）情侣。
        //根据座位确定顶点，由2个人做一个座位计算，数组下标index/2作为第几个座位，下标1、2就为第一个（0）和第二个（1）座位
        //我们可以根据不同的顶点来确定图（都是一样的）。
        //如果根据情侣分队构图，那么一个顶点代表第几对情侣，则座位是边，在每个座位（数组中两两成对）上的两个人代表两队情侣，即可连接起来。
        //若这两人就是情侣，那么相当于自己连自己，即单独成为一个连通分量。
        //如果根据座位构图，那么情侣是边，就需要构造出一对情侣分别属于哪两个座位（或在一个座位上），那么这两个座位作为的点即可连接起来。

        //两种构图的顶点，最后结果都是一样的，因为座位和情侣都是确定的（可以保证row 是序列 0...len(row)-1 的一个全排列）。
        //每个连通分量中必然是只有一条路径的环，当我们交换连通分量中点的个数n-1次时，此连通分量中所有情侣都已坐到一起。
        //所以总的结论即为：图中点的个数-连通分量数。
        int len = row.length / 2;

        int[] members = new int[len];
        for (int i = 0; i < len; i++) {
            members[i] = i;
        }

        //根据座位构图，couple[i][0]和couple[i][1]代表第i对情侣中，两个人分别坐在哪个座位上
        /*int[][] couple = new int[len][2];
        for (int i = 0; i < len; i++) {
            int num = 2 * i;
            couple[row[num] / 2][row[num] % 2] = i;
            couple[row[num + 1] / 2][row[num + 1] % 2] = i;
        }*/

        //根据情侣构图，一个座位上分别坐了哪两对情侣
        for (int i = 0; i < len; i++) {
            merge(members, row[2 * i] / 2, row[2 * i + 1] / 2);
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (members[i] == i) {
                count++;
            }
        }

        //同样，这个len即为多少个座位或多少对情侣
        return len - count;
    }


    public int find(int[] members, int x) {
        if (members[x] == x) {
            return x;
        }
        int parent = find(members, members[x]);
        members[x] = parent;
        return parent;
    }

    public void merge(int[] members, int x, int y) {
        int xp = find(members, x);
        int yp = find(members, y);
        members[xp] = yp;
    }
}
