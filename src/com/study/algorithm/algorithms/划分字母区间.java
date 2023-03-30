package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 划分字母区间 {
    @Test
    public void 划分字母区间() {
        int[] graph = {1, 2, 7, 6};
        //        System.out.println("划分字母区间:" + partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println("划分字母区间:" + partitionLabels("caedbdedda"));
    }

    // 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
    //
    //
    //
    // 示例 1：
    //
    // 输入：S = "ababcbacadefegdehijhklij"
    // 输出：[9,7,8]
    // 解释：
    // 划分结果为 "ababcbaca", "defegde", "hijhklij"。
    // 每个字母最多出现在一个片段中。
    // 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
    //
    //
    // 提示：
    //
    // S的长度在[1, 500]之间。
    // S只包含小写字母 'a' 到 'z' 。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/partition-labels
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<Integer> partitionLabelsByStack(String S) {

        // 用栈，模拟每次加入分割的起止点
        // 用一个数组，记录当前字符的最早出现的位置，如果遍历中再次遇到这个字符，则这个字符之间的分割点从栈中取出，并将当前字符位置加入栈中。
        // 遇到未记录的字符时，先加入两个分割点，表示此字符单独分割，但注意后续再次遇到此字符时，需去除重复的分割点位。
        // 最后两个分割点为一个分割区间，计算区间长度并加入结果列表
        LinkedList<Integer> stack = new LinkedList<>();

        int[] first = new int[26];

        Arrays.fill(first, -1);

        for (int i = 0, len = S.length(); i < len; i++) {
            int currChar = S.charAt(i) - 'a';
            if (first[currChar] == -1) {
                stack.offerFirst(i);
                stack.offerFirst(i);
                first[currChar] = i;
            } else {
                while (!stack.isEmpty()) {

                    // 清楚此字符区间的所有分割点位
                    int lastSplit = stack.peek();
                    if (first[currChar] < lastSplit) {
                        stack.poll();
                    } else {
                        if (stack.size() > 1) {
                            // 去除重复的分割点位
                            lastSplit = stack.poll();
                            if (stack.peek() != lastSplit) {
                                stack.offerFirst(lastSplit);
                            }
                        }
                        stack.offerFirst(i);

                        break;
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        // 两个分割点为一个分割区间，计算区间长度并加入结果列表
        while (!stack.isEmpty()) {

            int pre = stack.pollLast();
            int next = stack.pollLast();
            result.add(next - pre + 1);
        }
        return result;
    }

    public List<Integer> partitionLabels(String S) {
        // 由于同一个字母只能出现在同一个片段，显然同一个字母的第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段。
        // 因此需要遍历字符串，得到每个字母最后一次出现的下标位置。
        //
        // 在得到每个字母最后一次出现的下标位置之后，可以使用贪心算法和双指针的方法将字符串划分为尽可能多的片段，具体做法如下。
        //
        // 从左到右遍历字符串，遍历的同时维护当前片段的开始下标 start 和结束下标 end，初始时start=end=0。
        //
        // 对于每个访问到的字母 c，得到当前字母的最后一次出现的下标位置 endc，则当前片段的结束下标一定不会小于endc，
        // 因此令end=max(end,endc)。
        //
        // 当访问到下标end 时，当前片段访问结束，当前片段的下标范围是 [start,end]，长度为end−start+1，
        // 将当前片段的长度添加到返回值，然后令start=end+1，继续寻找下一个片段。
        //
        // 重复上述过程，直到遍历完字符串。
        //
        // 上述做法使用贪心的思想寻找每个片段可能的最小结束下标，因此可以保证每个片段的长度一定是符合要求的最短长度，
        // 如果取更短的片段，则一定会出现同一个字母出现在多个片段中的情况。由于每次取的片段都是符合要求的最短的片段，
        // 因此得到的片段数也是最多的。
        //
        // 由于每个片段访问结束的标志是访问到下标end，因此对于每个片段，可以保证当前片段中的每个字母都一定在当前片段中
        // ，不可能出现在其他片段，可以保证同一个字母只会出现在同一个片段。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/partition-labels/solution/hua-fen-zi-mu-qu-jian-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int[] last = new int[26];
        int length = S.length();
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }
}
