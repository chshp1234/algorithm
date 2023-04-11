package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 二叉树_数组_是否为镜像 {
    @Test
    public void 是否为镜像() {
        System.out.println(
                "二叉树(数组)是否为镜像：" + isMirror(new Integer[] {1, 2, 2, null, null, null, 3}));
    }
    /**
     * 二叉树(数组)是否为镜像
     *
     * @param sou the sou
     * @return the boolean
     */
    public boolean isMirror(Integer[] sou) {
        if (sou == null || (sou.length & (sou.length + 1)) != 0) {
            return false;
        }

        return mirror(sou, 2);
    }

    private boolean mirror(Integer[] sou, int depth) {
        if ((sou.length + 1) >>> depth == 0 || depth <= 1) {
            return true;
        }

        int start = (1 << (depth - 1)) - 1, end = (1 << depth) - 2;
        for (int i = 0, l = (end - start + 1) >> 1; i < l; i++) {

            if ((sou[start] != null && !sou[start].equals(sou[end]))
                    || (sou[end] != null && !sou[end].equals(sou[start]))) {
                return false;
            }
            if ((sou[start] == null && sou[end] != null)
                    || (sou[end] == null && sou[start] != null)) {
                return false;
            }
            start++;
            end--;
        }
        return mirror(sou, ++depth);
    }
}
