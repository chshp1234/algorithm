package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 是否包含子串 {
    @Test
    public void 是否包含子串() {
        /*todo 继续*/
        System.out.println(
                "是否包含子串(子串出现的位置)：" + indexOfKMP("aaassdaaa".toCharArray(), "ssda".toCharArray()));
    }
    /**
     * 从一个字符串中，寻找到目标子串出现的位置
     *
     * <p>原字符串是否包含目标字符串（KMP算法）
     *
     * <p>字符串的模式匹配是对字符串的基本操作之一，广泛应用于生物信息学、信息检索、拼写检查、语言翻译、数据压缩、网络入侵检测等领域，
     * 如何简化其复杂性一直是算法研究中的经典问题。字符串的模式匹配实质上就是寻找模式串P是否在主串T 中， 且其出现的位置。我们对字符串匹配的效率的要求越来越高，
     * 应不断地改良模式匹配算法，减少其时间复杂度。
     *
     * <p>KMP算法是由D.E. Knuth、J.H.Morris和V.R. Pratt提出的，可在一个主文本字符串S内查找一个词W的出现位置。
     * 此算法通过运用对这个词在不匹配时本身就包含足够的信息来确定下一个匹配将在哪里开始的发现， 从而避免重新检查先前匹配的字符。这个算法是由高德纳和沃恩·普拉特在1974年构思，
     * 同年詹姆斯·H·莫里斯也独立地设计出该算法，最终由三人于1977年联合发表。 该算法减少了BF算法中i回溯所进行的无谓操作，极大地提高了字符串匹配算法的效率。
     *
     * @param origin the src
     * @param target the dst
     * @return the boolean
     */
    private int indexOfKMP(char[] origin, char[] target) {
        int originLen = origin.length;
        int targetLen = target.length;

        if (originLen == 0 || targetLen == 0 || originLen < targetLen) {
            return -1;
        }

        if (origin == target) return 0;

        int i = 0, j = 0;
        int[] next = getNext(target);

        while (i < originLen && j < targetLen) {
            if (j == -1 || origin[i] == target[j]) {
                // 如果相同就一一比较,j=-1表示不需要比较
                i++;
                j++;
            } else {
                // j返回到合适的位置，i不再需要回溯
                j = next[j];
            }
        }

        if (j > targetLen - 1) return i - targetLen;
        else return -1;
    }

    private int[] getNext(char[] target) {
        int len = target.length;
        if (len == 0) {
            return new int[] {-1};
        }

        int[] next = new int[target.length];

        // j表示当前位置，k表示子串需比较的第一位。
        int j = 0, k = -1;
        next[0] = -1;
        while (j < len - 1) {
            if (k == -1 || target[j] == target[k]) {
                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }

        return next;
    }
}
