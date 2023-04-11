package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
//
//另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
//
//返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
//
// 
//
//注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
//
// 
//
//示例 1：
//
//输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
//解释：
//条件：a / b = 2.0, b / c = 3.0
//问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
//示例 2：
//
//输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//输出：[3.75000,0.40000,5.00000,0.20000]
//示例 3：
//
//输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//输出：[0.50000,2.00000,-1.00000,-1.00000]
// 
//
//提示：
//
//1 <= equations.length <= 20
//equations[i].length == 2
//1 <= Ai.length, Bi.length <= 5
//values.length == equations.length
//0.0 < values[i] <= 20.0
//1 <= queries.length <= 20
//queries[i].length == 2
//1 <= Cj.length, Dj.length <= 5
//Ai, Bi, Cj, Dj 由小写英文字母与数字组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/evaluate-division
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 除法求值 {
    @Test
    public void 除法求值() {

        List<List<String>> equations = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add("a");
        temp.add("b");
        equations.add(temp);
        temp = new ArrayList<>();
        temp.add("c");
        temp.add("b");
        equations.add(temp);
        temp = new ArrayList<>();
        temp.add("bc");
        temp.add("cd");
        equations.add(temp);
        double[] values = new double[3];
        values[0] = 1;
        values[1] = 2;
        values[2] = 3;

        List<List<String>> queries = new ArrayList<>();
        temp = new ArrayList<>();
        temp.add("a");
        temp.add("c");
        queries.add(temp);
        temp = new ArrayList<>();
        temp.add("b");
        temp.add("a");
        queries.add(temp);
        temp = new ArrayList<>();
        temp.add("a");
        temp.add("e");
        queries.add(temp);
        temp = new ArrayList<>();
        temp.add("a");
        temp.add("a");
        queries.add(temp);
        temp = new ArrayList<>();
        temp.add("x");
        temp.add("x");
        queries.add(temp);
        System.out.println("除法求值:" + Arrays.toString(calcEquation(equations, values, queries)));
    }


    public double[] calcEquation(
            List<List<String>> equations, double[] values, List<List<String>> queries) {

        //图，并查集，深度优先搜索
        //此题很明显，可以把除数和被除数关系看成有向带权图（且是双向的）
        //我们就可以根据equations构造一个有向带权图
        //在求解问题时，使用深度优先搜索（广度优先搜索也行）根据图中两点间带权的距离，即可求得解
        //在搜索过程中，可继续维护两个间接相连的点，使之直接相连，并更新权值，方便下次查询时更快速求得值
        //当两个点不连通时，会造成不必要的求值计算，此时可以使用并查集，方便判断两个点是否属于同一个集合
        int eLen = equations.size();
        int qLen = queries.size();
        //有向带权图
        Map<String, Map<String, Double>> nodes = new HashMap<>(eLen);
        MyUnionFind unionFind = new MyUnionFind(eLen);
        Map<String, Double> temp;
        for (int i = 0; i < eLen; i++) {
            //构造有向带权图，双向
            String dividend = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);
            temp = nodes.get(dividend);
            if (temp == null) {
                temp = new HashMap<>();
                nodes.put(dividend, temp);
            }
            temp.put(divisor, values[i]);

            temp = nodes.get(divisor);
            if (temp == null) {
                temp = new HashMap<>();
                nodes.put(divisor, temp);
            }
            temp.put(dividend, 1 / values[i]);

            unionFind.merge(divisor, dividend);
        }


        double[] result = new double[qLen];

        for (int i = 0; i < qLen; i++) {
            String dividend = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);
            String uDividend = unionFind.find(dividend);
            //如果两个点不在同一个集合
            if (uDividend == null || !uDividend.equals(unionFind.find(divisor))) {
                result[i] = -1.0;
                continue;
            }
            if (dividend.equals(divisor)) {
                result[i] = 1.0;
                continue;
            }
            Set<String> visit = new HashSet<>();
            //计算权值
            result[i] = dfs(dividend, divisor, visit, nodes);
        }

        return result;
    }

    // 深度优先搜索（当然，广度也行）
    private double dfs(String start, String end, Set<String> visit,
                       Map<String, Map<String, Double>> nodes) {
        // 如果当前边已经访问过，则跳过
        if (visit.add(start)) {
            //遍历每一条边
            for (Map.Entry<String, Double> next : nodes.get(start).entrySet()) {
                //找到目标顶点
                if (next.getKey().equals(end)) {
                    return next.getValue();
                }
                double dfs = dfs(next.getKey(), end, visit, nodes);
                //如果可以到达目标顶点，连接此顶点到目标顶点，并计算权值
                if (dfs != -1) {
                    double result = dfs * next.getValue();
                    nodes.get(start).put(end, result);
                    return result;
                }
            }
        }
        return -1.0;
    }

    // 并查集
    public class MyUnionFind {
        Map<String, String> members;

        public MyUnionFind(int count) {
            members = new HashMap<>(count);
        }

        public String find(String i) {
            String s = members.get(i);
            if (s == null) {
                return null;
            }
            if (i.equals(s)) {
                return i;
            }
            return find(s);
        }

        public void merge(String index1, String index2) {
            if (members.get(index1) == null) {
                members.put(index1, index1);
            }
            if (members.get(index2) == null) {
                members.put(index2, index2);
            }
            String i1 = find(index1);
            String i2 = find(index2);
            members.put(i2, i1);
        }
    }

    //上一个方法没有完全发挥出并查集的作用，比如维护并查集的时候，其实已经间接的确认了各个顶点的关系
    //在此可以使用带权并查集，更高效的处理问题
    //
    public double[] calcEquationByLeetCode(List<List<String>> equations, double[] values, List<List<String>> queries) {

        //我们计算了两个结果，不难知道：
        //可以将题目给出的 equation 中的两个变量所在的集合进行「合并」，同在一个集合中的两个变量就可以通过某种方式计算出它们的比值。
        //具体来说，可以把 不同的变量的比值转换成为相同的变量的比值，这样在做除法的时候就可以消去相同的变量，然后再计算转换成相同变量以后的系数的比值，就是题目要求的结果。
        //统一了比较的标准，可以以 O(1) 的时间复杂度完成计算。
        //
        //如果两个变量不在同一个集合中， 返回 −1.0。
        //并且根据题目的意思，如果两个变量中 至少有一个 变量没有出现在所有 equations 出现的字符集合中，也返回 −1.0。
        //
        //作者：LeetCode
        //链接：https://leetcode-cn.com/problems/evaluate-division/solution/399-chu-fa-qiu-zhi-nan-du-zhong-deng-286-w45d/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        int equationsSize = equations.size();

        UnionFind unionFind = new UnionFind(2 * equationsSize);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    //带权并查集
    private class UnionFind {

        private int[] parent;

        /**
         * 指向的父结点的权值
         */
        private double[] weight;


        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            //关系式的推导请见「参考代码」下方的示意图
            //说明：代码 weight[rootX] = weight[y] * value / weight[x]; 的推导过程，
            //主要需要明白各个变量的含义，由两条路径有向边的权值乘积相等得到相等关系，然后做等价变换即可。
            //
            //作者：LeetCode
            //链接：https://leetcode-cn.com/problems/evaluate-division/solution/399-chu-fa-qiu-zhi-nan-du-zhong-deng-286-w45d/
            //来源：力扣（LeetCode）
            //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         *
         * @param x
         * @return 根结点的 id
         */
        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        //是否相连
        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}
