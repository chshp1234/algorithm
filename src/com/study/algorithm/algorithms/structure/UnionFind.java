package com.study.algorithm.algorithms.structure;

// 并查集
// 算法：
// 如果我们熟悉并查集（DSU）数据结构，我们可以直接使用它来解决这个问题：
// 我们只需找到已经连接的图中出现的第一条边。本解释的其余部分将重点介绍实现 DSU 的细节。
//
// 一个 DSU 数据结构可以用来维护图形连接组件的数据，并快速查询它们。有两种操作：
//
// dsu.find(node x)，找到元素 x 所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合。
// dsu.union(node x, node y)，把元素 x 和元素 y 所在的集合合并，要求 x和 y 所在的集合不相交，如果相交则不合并。
// 为了实现这一点，我们跟踪父结点，它会记录同一连接节点中较小结点的所在的集合。如果结点是它自己的父结点，我们将其称为连接结点的领导者。
//
// DSU 结构的简单实现如下所示：
// 伪代码 ：
// 我们使用两种技术来提高运行时的复杂性：路径压缩和按秩合并。
//
// 路径压缩涉及将 find 函数中的 x=parent[x] 更改为parent[x]=find(parent[x])。
// 按秩合并涉及到将发现的工作量平均分配给领导者。每当 dsu.union(x, y) 时，我们都有两个领导者 xr，yr，并且我们要选择是要 parent[x]=yr 还是
// parent[y]=xr。我们选择有更多子节点的领导者作为领导者。
// 具体地说，rank 的含义是 x 的跟随者少于 2 ^ rank[x]，这个策略可以作为 dsu.find 中的递归循环可中的界限。
//
// 作者：LeetCode
// 链接：https://leetcode-cn.com/problems/redundant-connection/solution/rong-yu-lian-jie-by-leetcode/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//
// 并查集可方便计算一个图中有多少个连通分量。
// 连通分量：
// 设集合 V 为无向图中节点的一个子集，集合 E 为无向图中所有两个端点都在 V 中的边。
// 设图 S=(V, E)，那么 S 就称为无向图的一个「诱导子图」（或者叫「导出子图」）。
// 「连通分量」是极大的「诱导子图」，这里的「极大」表现在：
// V 中的任意两个节点仅通过 E 就可以相互到达；
// 不存在一个不属于 V 的节点 x，使得 x 与 V 中的某个节点直接相连。
// 我们可以通过节点集合 V 唯一地描述一个连通分量：
// 例如在题目给出的样例 1 中，有两个连通分量 {0,1,2} 和 {3}；样例 2 中，有三个连通分量 {0,1,2,3}，{4} 和 {5}。
//
// 作者：LeetCode-Solution
// 链接：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected/solution/lian-tong-wang-luo-de-cao-zuo-ci-shu-by-leetcode-s/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
public class UnionFind {
    int[] members;

    //某节点的深度
    int[] size;

    int len;

    public UnionFind(int count) {
        members = new int[count];
        len = count;
        for (int i = 0; i < count; i++) {
            members[i] = i;
        }
    }

    //查找根节点，并进行路径压缩（使得该节点直接指向根节点）
    public int find(int i) {
        if (members[i] == i) {
            return i;
        }
        int parent = find(members[i]);
        members[i] = parent;
        return parent;
    }

    //合并两个节点
    public void merge(int index1, int index2) {
        members[find(index1)] = find(index2);
    }

    //获取该图的连通分量数
    public int getCount() {
        int count = 0;
        for (int i = 0, len = members.length; i < len; i++) {
            if (i == members[i]) {
                count++;
            }
        }
        return count;
    }
}
