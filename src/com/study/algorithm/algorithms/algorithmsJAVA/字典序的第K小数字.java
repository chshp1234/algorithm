package com.study.algorithm.algorithms.algorithmsJAVA;

//给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
//
// 
//
//示例 1:
//
//输入: n = 13, k = 2
//输出: 10
//解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
//示例 2:
//
//输入: n = 1, k = 1
//输出: 1
// 
//
//提示:
//
//1 <= k <= n <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Deprecated
public class 字典序的第K小数字 {


    /*public void buildTree(int num, TrieNode parent, int len) {
        if (len == 0) {
            return;
        }
        for (int i = 0, l = (int) (num / Math.pow(10, len - 1)); i <= l; i++) {
            parent.child[i] = new TrieNode();
            buildTree(num, parent.child[i], len - 1);
            parent.child[i].childCount
        }
    }*/

    //字典树
    class TrieNode {
        TrieNode[] child;
        int childCount;

        public TrieNode() {
            child = new TrieNode[10];
        }
    }
}
