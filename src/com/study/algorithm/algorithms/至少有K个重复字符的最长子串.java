package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.PriorityQueue;

//给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
//
// 
//
//示例 1：
//
//输入：s = "aaabb", k = 3
//输出：3
//解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
//示例 2：
//
//输入：s = "ababbc", k = 2
//输出：5
//解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
// 
//
//提示：
//
//1 <= s.length <= 104
//s 仅由小写英文字母组成
//1 <= k <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 至少有K个重复字符的最长子串 {
    @Test
    public void 至少有K个重复字符的最长子串() {
        System.out.print("至少有K个重复字符的最长子串:" + longestSubstringBySlidingWindow("aababcc", 3));
    }

    int maxLen = 0;

    public int longestSubstring(String s, int k) {
        //分治（按区间分割）
        //首先，只有小写字母，我们用counter数组计数每个字母出现的频次，以及这个字母的首尾位置
        //对counter数组，按首下标位置进行排序
        //·若区间中只有一个字母，且数量大于k，那么和此区间大小作对比，更新maxLen的大小
        //·我们一次性取出两个区间：
        //1.若两个区间，字母频次都小于k，或者字母频次都大于等于k，则合并区间；
        //2.若一个区间的频次小于k，一个区间的频次大于等于k，则此时需要进一步对子区间进行查找；
        //3.根据步骤2，我们需要对频次大于等于k的区间进行调整，去除频次小于k的区间，不能和小于k的区间有交集，之后对调整后的区间进行查找；
        //4.到最后合并调整区间结束，若最后的区间频次依旧大于等于k，则还需对此区间进行查找。
        //对于区间合并，调整，可以使用优先队列数据结构。

        //By力扣：（按字符串分割）
        //对于字符串 s，如果存在某个字符 ch，它的出现次数大于 0 且小于 k，则任何包含 ch 的子串都不可能满足要求。
        //也就是说，我们将字符串按照 ch 切分成若干段，则满足要求的最长子串一定出现在某个被切分的段内，而不能跨越一个或多个段。
        //因此，可以考虑分治的方式求解本题。

        int len = s.length();
        if (k == 1) {
            return len;
        }
        find(s, 0, len, k);
        return maxLen;
    }

    //找到字符串某个区间中的最长子串
    //（过多重复计算，可以保存上一轮遍历中已得到的区间结果）
    public void find(String s, int start, int end, int k) {
        //若当前区间长度已经小于maxLen或者小于k，则肯定不用计算了
        if (end - start < maxLen || end - start + 1 < k) {
            return;
        }

        //优先队列，以区间左下标进行排序
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (ints, t1) -> Integer.compare(ints[1], t1[1]));

        //计数器，代表字母出现的频次，最左端下标，最右端下标
        int[][] counter = new int[26][3];
        char[]  chars   = s.toCharArray();
        for (int i = start; i < end; i++) {
            int index = chars[i] - 'a';
            counter[index][0]++;
            if (counter[index][1] == 0) {
                counter[index][1] = i;
            }
            counter[index][2] = i;
        }
        if (start == 0) {
            counter[chars[0] - 'a'][1] = 0;
        }
        for (int i = 0; i < 26; i++) {
            if (counter[i][0] != 0) {
                //频次不为0的元素加入优先队列中
                queue.offer(counter[i]);
            }
        }
        //上一个区间
        int[] last = queue.poll();
        //当前区间
        int[] temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();

            //两个区间对比
            if (temp[0] < k && last[0] < k) {
                last[2] = Math.max(last[2], temp[2]);
            } else if (temp[0] < k) {
                //若上一个区间中的频次大于等于k，查找子区间
                find(s, last[1], temp[1], k);

                //更新合并区间
                if (last[2] < temp[2]) {
                    last = temp;
                } else {
                    last[1] = temp[2] + 1;
                }
            } else if (last[0] < k) {
                //若当前区间频次大于等于k
                if (last[2] < temp[2]) {
                    //更新合并区间
                    last[1] = last[2] + 1;
                    last[2] = temp[2];
                    last[0] = temp[0];
                } else {
                    //若当前区间包含在上一个区间中（不相交），查找此区间，当前左坐标到上一个的右左边
                    find(s, temp[1], last[2], k);
                }
            } else {
                last[2] = Math.max(last[2], temp[2]);
            }
        }

        //若遍历到结尾处，子区间还是符合条件大于等于k
        if (last[0] >= k && last[2] == end - 1) {
            if (last[1] == start) {
                //若子区间左右坐标等于查找的范围左右左边，则说明此子区间字母全部重复
                maxLen = Math.max(maxLen, last[2] - last[1] + 1);
            } else {
                find(s, last[1], end, k);
            }
        }
    }

    //对于分治，不管按什么进行分割，都会有重复计算，重复统计数组中字符长度的问题，求最长子字符串/区间的这类题一般可以用滑动窗口来做。
    //https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/solution/zhi-shao-you-kge-zhong-fu-zi-fu-de-zui-c-o6ww/
    public int longestSubstringBySlidingWindow(String s, int k) {
        int ret = 0;
        int n   = s.length();

        //枚举数组中允许的字符种类数量t
        for (int t = 1, tl = Math.min(26, n / k); t <= tl; t++) {
            int l = 0, r = 0;

            //滑动窗口内部每个字符出现的次数 cnt
            int[] cnt = new int[26];

            //滑动窗口内的字符种类数目 total
            int tot = 0;

            //当前出现次数小于 k 的字符的数量
            int less = 0;

            //遍历字符串
            while (r < n) {
                cnt[s.charAt(r) - 'a']++;
                if (cnt[s.charAt(r) - 'a'] == 1) {
                    //若第一次遇到此字符
                    tot++;
                    less++;
                }
                if (cnt[s.charAt(r) - 'a'] == k) {
                    //若此字符数量加到了k，则less减1
                    less--;
                }

                //若当前字符种类数量大于给定的数量，左指针右移
                while (tot > t) {
                    cnt[s.charAt(l) - 'a']--;
                    if (cnt[s.charAt(l) - 'a'] == k - 1) {
                        //若左指针右移时，字符数量等于k-1，说明小于 k 的字符的数量多了一个
                        less++;
                    }
                    if (cnt[s.charAt(l) - 'a'] == 0) {
                        //如果字符减为0
                        tot--;
                        less--;
                    }
                    l++;
                }

                //只有当不符合条件的字符数量为0，说明此区间符合条件，更新结果
                if (less == 0) {
                    ret = Math.max(ret, r - l + 1);
                }
                r++;
            }
        }
        return ret;
    }

}
