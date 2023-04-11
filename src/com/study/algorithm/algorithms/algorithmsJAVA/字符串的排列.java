package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.*;

//输入一个字符串，打印出该字符串中字符的所有排列。
//
// 
//
//你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
//
// 
//
//示例:
//
//输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
//限制：
//
//1 <= s 的长度 <= 8
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 字符串的排列 {

    Set<String> result = new HashSet<>();
    char[] chars;
    StringBuilder stringBuilder = new StringBuilder();
    int lenNow = 0;
    boolean[] count;
    int len;

    public String[] permutation(String s) {
        //回溯算法
        //注意不能有重复字符串
        chars = s.toCharArray();
        len = s.length();
        count = new boolean[len];
        backTrack();

        return result.toArray(new String[0]);

    }

    public void backTrack() {
        for (int i = 0; i < len; i++) {
            if (!count[i]) {
                count[i] = true;
                stringBuilder.append(chars[i]);
                lenNow++;
                String s = stringBuilder.toString();
                if (lenNow == len && !result.contains(s)) {
                    result.add(s);
                } else {
                    backTrack();
                }
                lenNow--;
                count[i] = false;
                stringBuilder.deleteCharAt(lenNow);
            }
        }
    }

    //方法二：下一个排列
    //思路及解法
    //
    //我们可以这样思考：当我们已知了当前的一个排列，我们能不能快速得到字典序中下一个更大的排列呢？
    //
    //答案是肯定的，参见「31. 下一个排列的官方题解」，当我们已知了当前的一个排列，我们可以在 O(n) 的时间内计算出字典序下一个中更大的排列。
    //这与 C++ 中的 next_permutation 函数功能相同。
    //
    //具体地，我们首先对给定的字符串中的字符进行排序，即可得到当前字符串的第一个排列，然后我们不断地计算当前字符串的字典序中下一个更大的排列，直到不存在更大的排列为止即可。
    //
    //这个方案的优秀之处在于，我们得到的所有排列都不可能重复，这样我们就无需进行去重的操作。同时因为无需使用回溯法，没有栈的开销，算法时间复杂度的常数较小。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/zi-fu-chuan-de-pai-lie-by-leetcode-solut-hhvs/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public String[] permutationByLeetCode(String s) {
        List<String> ret = new ArrayList<String>();
        char[] arr = s.toCharArray();
        //排序，第一个全排列为最小序列
        Arrays.sort(arr);
        do {
            ret.add(new String(arr));
        } while (nextPermutation(arr));
        int size = ret.size();
        String[] retArr = new String[size];
        for (int i = 0; i < size; i++) {
            retArr[i] = ret.get(i);
        }
        return retArr;
    }

    //下一个更大的全排列
    public boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = arr.length - 1;
        while (j >= 0 && arr[i] >= arr[j]) {
            j--;
        }
        //找到下一个更大的全排列的起止位置i、j，交换位置
        swap(arr, i, j);
        //翻转，使得i+1位置之后的全排列数字最小
        reverse(arr, i + 1);
        return true;
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void reverse(char[] arr, int start) {
        int left = start, right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }
}
