package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
//
//请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
//
//说明:
//不允许旋转信封。
//
//示例:
//
//输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出: 3
//解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/russian-doll-envelopes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 俄罗斯套娃信封问题 {
    @Test
    public void 俄罗斯套娃信封问题() {
        int[][] matrix = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};

        System.out.println("俄罗斯套娃信封问题：" + maxEnvelopes(matrix));
    }

    public int maxEnvelopes(int[][] envelopes) {
        //方法一：动态规划
        //首先，要想能把一封信套进另一封中，必须长宽都小于另一封信
        //我们可以先对数组envelopes进行排序：
        //按envelopes[i][0]升序排序；
        //如果envelopes[i][0]==envelopes[i-1][0]，则按envelopes[i][1]降序排序。
        //此时，由于envelopes[i][0]是升序的（即使前后相等，envelopes[i][1]也是降序的，不影响结果），
        //那么我们就可以把问题转换一个思路，在数组envelopes中，查找按envelopes[i][1]排列的最长上升子序列，
        //这样转换后，就相当于子序列按envelopes[i][0]升序，并且按envelopes[i][1]升序，那么此子序列长度也是此问题的答案。
        //对于dp[i]，我们规定，envelopes[i][1]为结尾的最长子序列（注意，要以envelopes[i][1]结尾），
        //那么dp[i]=Max(dp[j]+1,dp[i]),其中0<=j<i，并且envelopes[j][1]<envelopes[i][1],
        //因为envelopes[j][1]<envelopes[i][1]，所以dp[i]就可以在dp[j]的基础上+1，
        //所以在遍历到dp[i]的时候，只要找到从0~i中，最大的符合条件的dp[j]即可，也就是上述的状态转移方程。

        int len = envelopes.length;
        if (len < 2) {
            return len;
        }

        Arrays.sort(envelopes, (ints, t1) -> {
            if (ints[0] == t1[0]) {
                //若前后envelopes[i][0]相等，则按envelopes[i][1]降序排序，
                //这样可保证在计算dp值的时候，不会对后续的值（envelopes[i][0]相等情况下）产生影响
                return Integer.compare(t1[1], ints[1]);
            }
            //按envelopes[i][0]升序排序
            return Integer.compare(ints[0], t1[0]);
        });
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxCount = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    //j<i，并且envelopes[i][1] > envelopes[j][1]，则可推出状态转移方程
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            //更新最大值
            maxCount = Math.max(maxCount, dp[i]);
        }
        return maxCount;
    }

    //方法二：基于二分查找的动态规划
    //思路与算法
    //
    //设 f[j] 表示 h 的前 i 个元素可以组成的长度为 j 的最长严格递增子序列的末尾元素的最小值，
    //如果不存在长度为 j 的最长严格递增子序列，对应的 f 值无定义。
    //在定义范围内，可以看出 f 值是严格单调递增的，因为越长的子序列的末尾元素显然越大。
    //
    //在进行状态转移时，我们考虑当前的元素 hi：
    //·如果 hi大于 f 中的最大值，那么 hi就可以接在 f 中的最大值之后，形成一个长度更长的严格递增子序列；
    //·否则我们找出 f 中比 hi严格小的最大的元素 f[j0]，即 f[j0]<hi≤f[j0+1]，那么 hi可以接在 f[j0] 之后，
    //形成一个长度为 j0+1 的严格递增子序列，因此需要对 f[j0+1] 进行更新：f[j0+1]=hi
    //​
    //我们可以在 f 上进行二分查找，找出满足要求的 j0。
    //在遍历所有的 hi之后，f 中最后一个有定义的元素的下标增加 1（下标从 0 开始）即为最长严格递增子序列的长度。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/russian-doll-envelopes/solution/e-luo-si-tao-wa-xin-feng-wen-ti-by-leetc-wj68/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int maxEnvelopesByBinarySearch(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });

        List<Integer> f = new ArrayList<Integer>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; ++i) {
            int num = envelopes[i][1];
            if (num > f.get(f.size() - 1)) {
                f.add(num);
            } else {
                int index = binarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }

    public int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
