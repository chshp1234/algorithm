package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
//
//k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
//
//0 <= i, j < nums.length
//i != j
//nums[i] - nums[j] == k
//注意，|val| 表示 val 的绝对值。
//
// 
//
//示例 1：
//
//输入：nums = [3, 1, 4, 1, 5], k = 2
//输出：2
//解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
//尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
//示例 2：
//
//输入：nums = [1, 2, 3, 4, 5], k = 1
//输出：4
//解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5) 。
//示例 3：
//
//输入：nums = [1, 3, 1, 5, 4], k = 0
//输出：1
//解释：数组中只有一个 0-diff 数对，(1, 1) 。
// 
//
//提示：
//
//1 <= nums.length <= 104
//-107 <= nums[i] <= 107
//0 <= k <= 107
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/k-diff-pairs-in-an-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数组中的k_diff数对 {
    @Test
    public void 数组中的k_diff数对() {
        System.out.println("数组中的k_diff数对：" + findPairs(new int[]{
                1, 3, 1, 5, 4
        }, 0));
    }

    //哈希表
    //用哈希表记录每个数字，及其的状态
    //0->代表该数字在哈希表中，但为进行匹配
    //1->代表该数字在哈希表中，并且已经匹配小于该数的数
    //2->代表该数字在哈希表中，并且已经匹配过大于该数的数
    //3->该数字已经全部匹配
    //那么在遍历的过程中，如果该数不再哈希表中，或者值为0，说明该数需要匹配n-k和n+k；同时我们还要判断n-k和n+k是否已经匹配过该数
    //同理，如果已经匹配过小于该数的数，那么接下来应该匹配大于该数的数，即n+k;
    //如果已经匹配过大于该数的数，那么接下来该匹配小于该数的数，即n-k
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        Integer temp;

        if (k == 0) {
            for (int n : nums) {
                if ((temp = map.get(n)) == null) {
                    map.put(n, 0);
                } else if (temp == 0) {
                    result++;
                    map.put(n, 3);
                }
            }
        } else {
            for (int n : nums) {
                if ((temp = map.get(n)) == null || temp == 0) {
                    int low = map.getOrDefault(n - k, 4);
                    int top = map.getOrDefault(n + k, 4);

                    if ((low == 1 || low == 0) && (top == 2 || top == 0)) {
                        result += 2;
                        map.put(n, 3);
                        if (low == 0) {
                            map.put(n - k, 2);
                        } else {
                            map.put(n - k, 3);
                        }

                        if (top == 0) {
                            map.put(n + k, 1);
                        } else {
                            map.put(n + k, 3);
                        }

                    } else if (low == 1 || low == 0) {
                        result += 1;
                        map.put(n, 1);
                        if (low == 0) {
                            map.put(n - k, 2);
                        } else {
                            map.put(n - k, 3);
                        }
                    } else if (top == 2 || top == 0) {
                        result += 1;
                        map.put(n, 2);
                        if (top == 0) {
                            map.put(n + k, 1);
                        } else {
                            map.put(n + k, 3);
                        }
                    } else if (low == 4 && top == 4) {
                        map.put(n, 0);
                    }
                } else if (temp == 1) {
                    int top = map.getOrDefault(n + k, 4);
                    if (top == 2 || top == 0) {
                        result += 1;
                        map.put(n, 3);
                        if (top == 0) {
                            map.put(n + k, 1);
                        } else {
                            map.put(n + k, 3);
                        }
                    }
                } else if (temp == 2) {
                    int low = map.getOrDefault(n - k, 4);
                    if (low == 1 || low == 0) {
                        result += 1;
                        map.put(n, 3);
                        if (low == 0) {
                            map.put(n - k, 2);
                        } else {
                            map.put(n - k, 3);

                        }
                    }
                }
            }
        }
        return result;
    }
}