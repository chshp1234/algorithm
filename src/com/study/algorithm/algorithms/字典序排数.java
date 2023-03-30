package com.study.algorithm.algorithms;

import java.util.ArrayList;
import java.util.List;

//给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
//
//你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
//
// 
//
//示例 1：
//
//输入：n = 13
//输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
//示例 2：
//
//输入：n = 2
//输出：[1,2]
// 
//
//提示：
//
//1 <= n <= 5 * 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/lexicographical-numbers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 字典序排数 {
    public List<Integer> lexicalOrder(int n) {
        //dfs
        //第一位只能是1-9，所以第一位单独一层循环
        //后面几层，都从0开始，遍历到9，每次递归将当前层数*10继续遍历下一层数字0-9
        //若当前数字大于n，则退出当前层的循环

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            if (i > n) {
                break;
            }
            result.add(i);

            dfs(result, i * 10, n);
        }

        return result;
    }

    public void dfs(List<Integer> list, int n, int limit) {
        for (int i = 0; i < 10; i++) {
            if (n + i > limit) {
                break;
            }
            list.add(n + i);
            dfs(list, (n + i) * 10, limit);
        }
    }

    public List<Integer> lexicalOrderByAC_OIER(int n) {
        //递归具有额外的空间开销，为了实现严格的  O(1) 空间，我们需要使用「迭代」来实现 DFS。
        //
        //共有 n个数需要被处理，假设当前处理到的数为 j，根据字典序规则，在满足条件的前提下，我们优先在 j 的后面添加 0（即 j∗10<n 满足），否则我们考虑将上一位回退并进行加一操作。
        //
        //作者：AC_OIer
        //链接：https://leetcode-cn.com/problems/lexicographical-numbers/solution/by-ac_oier-ktn7/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 1; i < n; i++) {
            ans.add(j);
            if (j * 10 <= n) {
                //将j*10，遍历下一层数字
                j *= 10;
            } else {
                //如果j % 10 == 9（遍历到末尾），或者j + 1 > n（j==n）
                //j=j/10，返回上一层数字
                while (j % 10 == 9 || j + 1 > n) j /= 10;
                j++;
            }
        }
        return ans;
    }
}
