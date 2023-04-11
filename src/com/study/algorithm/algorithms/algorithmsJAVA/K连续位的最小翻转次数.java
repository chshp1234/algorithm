package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
//
//返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
//
// 
//
//示例 1：
//
//输入：A = [0,1,0], K = 1
//输出：2
//解释：先翻转 A[0]，然后翻转 A[2]。
//示例 2：
//
//输入：A = [1,1,0], K = 2
//输出：-1
//解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
//示例 3：
//
//输入：A = [0,0,0,1,0,1,1,0], K = 3
//输出：3
//解释：
//翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
//翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
//翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
// 
//
//提示：
//
//1 <= A.length <= 30000
//1 <= K <= A.length
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class K连续位的最小翻转次数 {
    @Test
    public void K连续位的最小翻转次数() {
        int[] ints = {0, 0, 0, 1, 0, 1, 1, 0};
        System.out.println("K连续位的最小翻转次数:" + minKBitFlips(ints, 3));
    }

    //由于对同一个子数组执行两次翻转操作不会改变该子数组，所以对每个长度为 K 的子数组，应至多执行一次翻转操作。
    //对于若干个 K 位翻转操作，改变先后顺序并不影响最终翻转的结果。
    //所以我们从左到右遍历数组，遇到A[i]==0，则进行翻转K个数，若i+K>len，则说明无法完全翻转，返回-1。
    /*public int minKBitFlips(int[] A, int K) {
        //暴力解法，根据上述规则，遇0就翻转。
        int len = A.length;

        int count = 0;
        for (int i = 0; i < len; i++) {
            if (A[i] == 0) {
                count++;
                for (int j = i + 1, l = K + i; j < l; j++) {

                    if (j == len) {
                        return -1;
                    }

                    // 超时
                    *//*if (A[j] == 0) {
                        A[j] = 1;
                    } else {
                        A[j] = 0;
                    }*//*
//                    A[j] = ~A[j] + 2;
                    A[j]^=1;
                }
            }
        }

        return count;
    }*/

    /*public int minKBitFlips(int[] A, int K) {
        //对于暴力解法中，每次都进行了K次翻转，有无意义的重复操作
        //可以使用队列，记录当前翻转后的子数组区间A[i]~A[i+k]，我们可以将i+k加入队列，说明此区间的子数组进行了翻转。
        //继续遍历时，若A[j]位于翻转的区间，则需要判断A[j]==1，同样将j+K加入队列中，
        //注意若j小于之前翻转的结束坐标i+k，则说明j~i+k区间的子数组又进行了一次翻转，回到了原来的数，则继续判断此区间数是否为0，
        //而i+k~j+k区间是进行了真正的翻转后的数。
        //若j==i+k，则说明到了一个翻转界限，若之前的子区间是翻转的，则接下来的子区间没有翻转；若之前的子区间没有翻转，则接下来的子区间进行了翻转。
        //注：每次翻转时，需要判断i+k>len，大于数组长度则说明无法进行翻转，返回-1。
        int len = A.length;

        int count = 0;

        Deque<Integer> deque = new LinkedList<>();
        boolean turn = false;
        int lastBarry = len;

        for (int i = 0; i < len; i++) {

            if (i == lastBarry) {
                //若当前下标到了翻转的界限，则更新“翻转”状态
                //（若之前的子区间是翻转的，则接下来的子区间没有翻转；若之前的子区间没有翻转，则接下来的子区间进行了翻转）
                turn = !turn;
                //下一个界限从队列中取
                lastBarry = deque.isEmpty() ? len : deque.poll();
            }

            //若此区间进行了翻转，则应判断A[i] == 1
            if (turn) {
                if (A[i] == 1) {
                    //翻转次数+1
                    count++;
                    int next = i + K;
                    if (next > len) {
                        //若翻转后的子区间界限大于数组长度，返回-1
                        return -1;
                    }

                    //若队列为空，则翻转后的子区间界限为i+k
                    if (lastBarry == len) {
                        lastBarry = next;
                    } else {
                        deque.offer(next);
                    }

                    //根据当前翻转的状态取反
                    turn = !turn;
                }
            } else {
                //若此区间没有进行翻转，则应判断A[i] == 0
                //后续判断步骤同上
                if (A[i] == 0) {
                    count++;
                    int next = i + K;
                    if (next > len) {
                        return -1;
                    }

                    if (lastBarry == len) {
                        lastBarry = next;
                    } else {
                        deque.offer(next);
                    }
                    turn = !turn;
                }

            }
        }

        return count;
    }*/

    //差分数组
    //考虑不去翻转数字，而是统计每个数字需要翻转的次数。对于一次翻转操作，相当于把子数组中所有数字的翻转次数加 1。
    //
    //这启发我们用差分数组的思想来计算当前数字需要翻转的次数。
    //我们可以维护一个差分数组 diff，其中 diff[i] 表示两个相邻元素 A[i−1] 和 A[i] 的翻转次数的差，
    //对于区间 [l,r]，将其元素全部加 1，只会影响到 l 和 r+1 处的差分值，故 diff[l] 增加 1，diff[r+1] 减少 1。
    //
    //通过累加差分数组可以得到当前位置需要翻转的次数，我们用变量 revCnt 来表示这一累加值。
    //
    //遍历到 A[i] 时，若A[i]+revCnt 是偶数，则说明当前元素的实际值为 0，需要翻转区间 [i,i+K−1]，我们可以直接将 revCnt 增加 1，diff[i+K] 减少 1。
    //
    //注意到若 i+K>n 则无法执行翻转操作，此时应返回 −1。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/solution/k-lian-xu-wei-de-zui-xiao-fan-zhuan-ci-s-bikk/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            revCnt += diff[i];
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                --diff[i + K];
            }
        }
        return ans;
    }

    //滑动窗口
    //能否将空间复杂度优化至 O(1) 呢？
    //
    //回顾方法一的代码，当遍历到位置 i 时，若能知道位置 i−K 上发生了翻转操作，便可以直接修改 revCnt，从而去掉 diff 数组。
    //
    //注意到 0≤A[i]≤1，我们可以用 A[i] 范围之外的数来表达「是否翻转过」的含义。
    //
    //具体来说，若要翻转从位置 i 开始的子数组，可以将 A[i] 加 2，这样当遍历到位置 i'时，若有 A[i'-K]>1，
    //则说明在位置 i'-K 上发生了翻转操作。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/solution/k-lian-xu-wei-de-zui-xiao-fan-zhuan-ci-s-bikk/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int minKBitFlipsBySlidingWindow(int[] A, int K) {
        int n = A.length;
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            //判断A[i-k]处是否翻转
            if (i >= K && A[i - K] > 1) {
                //反转“翻转标志”
                revCnt ^= 1;
                A[i - K] -= 2; // 复原数组元素，若允许修改数组 A，则可以省略
            }
            //若翻转标志为奇数，则A[i]=1需要翻转
            //若翻转标志为偶数，则A[i]=0需要翻转
            if (A[i] == revCnt) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                revCnt ^= 1;

                //设置A[i]翻转
                A[i] += 2;
            }
        }
        return ans;
    }
}
