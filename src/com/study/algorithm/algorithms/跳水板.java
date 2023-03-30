package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 跳水板 {
    @Test
    public void 面试题16_11_跳水板() {

        System.out.println("面试题16_11_跳水板:" + Arrays.toString(divingBoard(1, 2, 3)));
    }

    // 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
    //
    // 返回的长度需要从小到大排列。
    //
    // 示例：
    //
    // 输入：
    // shorter = 1
    // longer = 2
    // k = 3
    // 输出： {3,4,5,6}
    // 提示：
    //
    // 0 < shorter <= longer
    // 0 <= k <= 100000
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/diving-board-lcci
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[] divingBoardStupid(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }

        if (shorter == longer) {
            return new int[] {shorter * k};
        }

        // 愚蠢的解法：list存值，在转成int[]
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            int len = i * longer + (k - i) * shorter;
            list.add(len);
        }
        int[] result = new int[list.size()];
        for (int i = 0, l = list.size(); i < l; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }

        if (shorter == longer) {
            return new int[] {shorter * k};
        }

        // 短板和长板长度不一致，其中短板数量0~k（长板一样），所以共有k+1个可能组合长度
        int[] result = new int[k + 1];

        // 遍历，从长板数量从0开始组合，每次+1，则组合后的板子长度为等差递增
        int differ = longer - shorter;
        result[0] = shorter * k;
        for (int i = 1; i <= k; i++) {
            int len = result[i - 1] + differ;
            result[i] = len;
        }

        return result;
    }
}
