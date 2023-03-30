package com.study.algorithm.algorithms;

import org.junit.Test;

//给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
//
//我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
//
//返回数组能分成的最多块数量。
//
// 
//
//示例 1:
//
//输入: arr = [4,3,2,1,0]
//输出: 1
//解释:
//将数组分成2块或者更多块，都无法得到所需的结果。
//例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
//示例 2:
//
//输入: arr = [1,0,2,3,4]
//输出: 4
//解释:
//我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
//然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
// 
//
//提示:
//
//n == arr.length
//1 <= n <= 10
//0 <= arr[i] < n
//arr 中每个元素都 不同
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/max-chunks-to-make-sorted
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最多能完成排序的块 {
    @Test
    public void 最多能完成排序的块() {

        System.out.println("最多能完成排序的块:" + maxChunksToSorted(new int[]{1, 0, 2, 3, 4}));
    }

    public int maxChunksToSorted(int[] arr) {
        //因为元素在[0...n-1]中，并且不含重复元素
        //当区间[i...j]中的最大值为j，最小值为i时，那么这个区间的元素都在[i...j]中，此区间可分为一个块
        //遍历，记录区间起始位置start，最大值max，最小值min，每次更新最大值最小值，并且start == min && index == max时，说明此区间满足上述条件，可分为一个块

        int index = 0;
        int len = arr.length;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int start = 0;

        int count = 0;
        while (index < len) {
            //更新最大值最小值
            min = Math.min(min, arr[index]);
            max = Math.max(max, arr[index]);

            if (start == min && index == max) {
                //满足条件
                count++;
                start = index + 1;

                //更新最大值最小值
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
            }

            index++;
        }

        return count;
    }

    //首先找到从左块开始最小块的大小。
    //如果前 k 个元素为 [0, 1, ..., k-1]，可以直接把他们分为一个块。
    //当我们需要检查 [0, 1, ..., n-1] 中前 k+1 个元素是不是 [0, 1, ..., k] 的时候，只需要检查其中最大的数是不是 k 就可以了。
    //
    //作者：LeetCode
    //链接：https://leetcode.cn/problems/max-chunks-to-make-sorted/solution/zui-duo-neng-wan-cheng-pai-xu-de-kuai-i-by-leetcod/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int maxChunksToSortedByLeetCode(int[] arr) {
        int ans = 0, max = 0;
        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(max, arr[i]);
            if (max == i) ans++;
        }
        return ans;
    }

}
