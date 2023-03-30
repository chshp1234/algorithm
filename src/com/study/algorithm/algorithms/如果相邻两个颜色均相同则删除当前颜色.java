package com.study.algorithm.algorithms;

public class 如果相邻两个颜色均相同则删除当前颜色 {
    public boolean winnerOfGame(String colors) {

        //贪心
        //统计所有左右相邻'A'的'A'的数量和‘B’的数量
        //如果统计的A的数量大于B，则Alice获胜，否则Bob获胜
        int[] counts = {0, 0};

        for (int i = 1, l = colors.length() - 1; i < l; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1) && colors.charAt(i) == colors.charAt(i + 1)) {
                counts[colors.charAt(i) - 'A']++;
            }
        }

        return counts[0] > counts[1];
    }
}
