package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
//
//（请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
//
//返回区域的数目。
//
// 
//
//示例 1：
//
//输入：
//[
//  " /",
//  "/ "
//]
//输出：2
//解释：2x2 网格如下：
//
//示例 2：
//
//输入：
//[
//  " /",
//  "  "
//]
//输出：1
//解释：2x2 网格如下：
//
//示例 3：
//
//输入：
//[
//  "\\/",
//  "/\\"
//]
//输出：4
//解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
//2x2 网格如下：
//
//示例 4：
//
//输入：
//[
//  "/\\",
//  "\\/"
//]
//输出：5
//解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
//2x2 网格如下：
//
//示例 5：
//
//输入：
//[
//  "//",
//  "/ "
//]
//输出：3
//解释：2x2 网格如下：
//
// 
//
//提示：
//
//1 <= grid.length == grid[0].length <= 30
//grid[i][j] 是 '/'、'\'、或 ' '。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/regions-cut-by-slashes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 由斜杠划分区域 {
    @Test
    public void 电话号码的字母组合() {
        System.out.println("电话号码的字母组合:" + regionsBySlashes(new String[]{"//", "/ "}));
    }

    public int regionsBySlashes(String[] grid) {
        //并查集
        //把一个空格用‘/’和‘\’分成上下左右4个三角形，所以，总共就有4*len*len个三角形：
        //当一个空格为‘/’时，上边三角形和左边三角形合并，右边三角形和下边三角形合并；
        //当一个空格为‘\’时，下边三角形和左边三角形合并，上边三角形和右边三角形合并；
        //当一个空格为空时，4个三角形区域全都合并；
        //最后不管一个空格是哪个字符，左边三角形都和左边空格的右边三角形合并；上边的三角形都和上一个空格的下边三角形合并。
        //
        //如果把每个三角形看成一张图上的一个节点，那么整个图形中，公共的区域就为一个连通分量。
        //因此，可以使用并查集，初始化4*len*len大小的数组，遍历过程中只需根据上面分析的规则进行节点合并，最后查找出有几个连通分量即可。

        int len = grid.length;
        int[] members = new int[4 * len * len];
        for (int i = 0, l = members.length; i < l; i++) {
            members[i] = i;
        }

        char[] temp;
        int l, t, r, b;
        int index = 0;
        for (int i = 0; i < len; i++) {
            temp = grid[i].toCharArray();
            for (int j = 0; j < len; j++) {
                l = 4 * index;
                t = 4 * index + 1;
                r = 4 * index + 2;
                b = 4 * index + 3;
                index++;
                if (temp[j] == '/') {
                    merge(members, l, t);
                    merge(members, r, b);
                } else if (temp[j] == '\\') {
                    merge(members, l, b);
                    merge(members, t, r);
                } else {
                    merge(members, l, t);
                    merge(members, l, r);
                    merge(members, l, b);
                }
                if (j > 0) {
                    merge(members, l - 2, l);
                }
                if (i > 0) {
                    merge(members, b - 4 * len, t);
                }
            }
        }

        int result = 0;
        for (int i = 0, il = members.length; i < il; i++) {
            if (i == members[i]) {
                result++;
            }
        }
        return result;
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
        members[find(members, y)] = find(members, x);
    }

}
