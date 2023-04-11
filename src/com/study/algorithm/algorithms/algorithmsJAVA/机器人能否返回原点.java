package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 机器人能否返回原点 {

    @Test
    public void 机器人能否返回原点() {
        System.out.println("机器人能否返回原点：" + judgeCircle("URR"));
    }

    public boolean judgeCircle(String moves) {
        int len = moves.length();

        if (len % 2 > 0) {
            return false;
        }

        // x轴y轴，最后向右x+1，向左x-1，向上y+1，向下y-1
        // 最后只要判断x和y是否都为0即可知道是否回到原点
        int x = 0, y = 0;

        for (int i = 0; i < len; i++) {
            switch (moves.charAt(i)) {
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
            }
        }

        return x == 0 && y == 0;
    }
}
