package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
//
//有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
//只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
//返回 图中最大连通组件的大小 。
//
// 
//
//示例 1：
//
//
//
//输入：nums = [4,6,15,35]
//输出：4
//示例 2：
//
//
//
//输入：nums = [20,50,9,63]
//输出：2
//示例 3：
//
//
//
//输入：nums = [2,3,6,7,4,12,21,39]
//输出：8
// 
//
//提示：
//
//1 <= nums.length <= 2 * 104
//1 <= nums[i] <= 105
//nums 中所有值都 不同
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-component-size-by-common-factor
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 按公因数计算最大组件大小 {
    @Test
    public void 按公因数计算最大组件大小() {
        System.out.println("按公因数计算最大组件大小:" + largestComponentSizeByLeetCode(
                new int[]{99,39,11}));
    }

    public int largestComponentSizeByLeetCode(int[] nums) {
        //并查集
        //利用并查集统计每个数
        //计算每个数的大于1的因数，并将其和原数合并到并查集中
        //再遍历一遍数组，将根节点相同的数记为同一个连通分量中，那么数量将+1
        //最后返回所有连通分量中，元素最多的一个
        if (nums.length == 1) {
            return 1;
        }
        UnionFundByLeetCode unionFund = new UnionFundByLeetCode(nums);
        return unionFund.maxCount();
    }

    static class UnionFundByLeetCode {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> rank = new HashMap<>();

        Map<Integer, Integer> counter = new HashMap<>();

        public UnionFundByLeetCode(int[] member) {
            for (int i : member) {
                if (!map.containsKey(i)) {
                    map.put(i, i);
                    rank.put(i, 1);
                }

                for (int j = 2, border = (int) Math.sqrt(i); j <= border; j++) {
                    if (i % j == 0) {
                        if (!map.containsKey(j)) {
                            map.put(j, j);
                            rank.put(j, 1);
                        }
                        int divisor = i / j;
                        if (!map.containsKey(divisor)) {
                            map.put(divisor, divisor);
                            rank.put(divisor, 1);
                        }
                        merge(i, j);
                        merge(i, divisor);
                    }
                }
            }

            for (int n : member) {
                int root = find(n);
                counter.put(root, counter.getOrDefault(root, 0) + 1);
            }

        }

        public int find(int c) {
            int p = map.get(c);
            if (map.get(c) == c) {
                return p;
            }
            return find(p);
        }

        public void merge(int i1, int i2) {
            int root1 = find(i1);
            int root2 = find(i2);
            if (root1 == root2) {
                return;
            }
            int tier1 = rank.get(root1);
            int tier2 = rank.get(root2);
            if (tier1 > tier2) {
                map.put(root2, root1);
            } else if (tier2 > tier1) {
                map.put(root1, root2);
            } else {
                map.put(root2, root1);
                rank.put(root1, tier1 + 1);
            }
        }

        public int maxCount() {
            int max = 0;
            for (int count : counter.values()) {
                max = Math.max(max, count);
            }
            return max;
        }
    }

    public int largestComponentSize(int[] nums) {
        //并查集
        //利用并查集统计每个数
        //当两个数的最大公约数大于1时，合并两个数。合并时，同时将子树的数量加入到父树中。
        //最后返回并查集中，子树数量最大的数
        if (nums.length == 1) {
            return 1;
        }
        UnionFund unionFund = new UnionFund(nums);
        for (int i = 1, len = nums.length; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (gcd(nums[i], nums[j]) > 1) {
                    unionFund.merge(nums[i], nums[j]);
                }
            }
        }
        return unionFund.maxCount();
    }

    public int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        int c = a % b;
        while (c > 0) {
            a = b;
            b = c;
            c = a % b;
        }
        return b;
    }

    static class UnionFund {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> counter = new HashMap<>();

        public UnionFund(int[] member) {
            for (int i : member) {
                map.put(i, i);
                counter.put(i, 1);
            }
        }

        public int find(int c) {
            int p = map.get(c);
            if (map.get(c) == c) {
                return p;
            }
            return find(p);
        }

        public void merge(int i1, int i2) {
            int parent = find(i1);
            int child = find(i2);
            if (parent == child) {
                return;
            }

            map.put(child, parent);

            counter.put(parent, counter.get(parent) + counter.get(child));
        }

        public int maxCount() {
            int max = 0;
            for (int count : counter.values()) {
                max = Math.max(max, count);
            }
            return max;
        }
    }
}
