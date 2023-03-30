package com.study.algorithm.algorithms;

import org.junit.Test;

//给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
//
//点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
//
//一个 有效的正方形 有四条等边和四个等角(90度角)。
//
// 
//
//示例 1:
//
//输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
//输出: True
//示例 2:
//
//输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
//输出：false
//示例 3:
//
//输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
//输出：true
// 
//
//提示:
//
//p1.length == p2.length == p3.length == p4.length == 2
//-104 <= xi, yi <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/valid-square
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 有效的正方形 {
    @Test
    public void 有效的正方形() {
        System.out.println(
                "有效的正方形:" + validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}, new int[]{0, 1}));
    }

    @Test
    public void 构成最大正方形数量() {
        System.out.println(
                "构成最大正方形数量:" + numSquare(new int[][]{{0, 0}, {1, 2}, {3, 1}, {2, -1}}));
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        //內积为0的两个向量（非零向量）互相垂直。
        //
        //p1->p2->p4->p3->p1
        //p1->p3->p2->p4->p1
        //p1->p2->p3->p4->p1
        //3种情况下，判断4个向量，模相等，內积为0即可。
        //
        //又由于4个点不是按顺（逆）时针给出的，所以，我们需要判断6条边
        //如果4个点按顺（逆）时针给出，那么我们可以按点给定的顺序p1->p2->p3->p4构造4个向量，并且判断4个向量的模是否相等（长度），判断內积是否为0（垂直）
        //否则，我们还需额外判断两个向量，p1->p3,p2->p4
        //这里我们再根据以上的总结判断p1->p2->p4->p3和p1->p3->p2->p4这两种情况的4个向量是否符合条件即可

        //4个向量
        int[] vector1 = {p1[0] - p2[0], p1[1] - p2[1]};
        int[] vector2 = {p3[0] - p2[0], p3[1] - p2[1]};
        int[] vector3 = {p3[0] - p4[0], p3[1] - p4[1]};
        int[] vector4 = {p1[0] - p4[0], p1[1] - p4[1]};

        //4条边
        double border1 = Math.pow(vector1[0], 2) + Math.pow(vector1[1], 2);
        double border2 = Math.pow(vector2[0], 2) + Math.pow(vector2[1], 2);
        double border3 = Math.pow(vector3[0], 2) + Math.pow(vector3[1], 2);
        double border4 = Math.pow(vector4[0], 2) + Math.pow(vector4[1], 2);

        //4条边长都相等
        if (border1 == border2 && border1 == border3 && border1 == border4 && border1 != 0) {
            //向量內积为0说明一个角为90度
            if (vector1[0] * vector2[0] + vector1[1] * vector2[1] == 0) {
                //又因为4条边都相等，所以此可构成正方形
                return true;
            }
        }

        int[] vector5 = {p1[0] - p3[0], p1[1] - p3[1]};
        int[] vector6 = {p2[0] - p4[0], p2[1] - p4[1]};
        double border5 = Math.pow(vector5[0], 2) + Math.pow(vector5[1], 2);
        double border6 = Math.pow(vector6[0], 2) + Math.pow(vector6[1], 2);
        //4条边长都相等
        if (border1 == border5 && border1 == border6 && border1 == border3 && border1 != 0) {
            //向量內积为0说明一个角为90度
            if (vector1[0] * vector5[0] + vector1[1] * vector5[1] == 0) {
                //又因为4条边都相等，所以此可构成正方形
                return true;
            }
        }

        //4条边长都相等
        if (border5 == border2 && border5 == border6 && border5 == border4 && border5 != 0) {
            //向量內积为0说明一个角为90度
            if (vector5[0] * vector4[0] + vector5[1] * vector4[1] == 0) {
                //又因为4条边都相等，所以此可构成正方形
                return true;
            }
        }


        return false;
    }

    //给定坐标点，求可构成的正方形数量
    public int numSquare(int[][] points) {
        //向量內积A(x1,y1)*B(x2,y2)=x1*x2+y1*y2
        //不为零的两个向量，內积为零，说明向量互相垂直

        int len = points.length;
        if (len < 4) {
            return 0;
        }
        int result = 0;

        for (int p1 = 0; p1 < len; p1++) {
            for (int p2 = p1 + 1; p2 < len; p2++) {
                for (int p3 = p2 + 1; p3 < len; p3++) {
                    for (int p4 = p3 + 1; p4 < len; p4++) {
                        //4个向量
                        int[] vector1 = {points[p1][0] - points[p2][0], points[p1][1] - points[p2][1]};
                        int[] vector2 = {points[p1][0] - points[p4][0], points[p1][1] - points[p4][1]};
                        int[] vector3 = {points[p3][0] - points[p2][0], points[p3][1] - points[p2][1]};
                        int[] vector4 = {points[p3][0] - points[p4][0], points[p3][1] - points[p4][1]};

                        //4条边
                        double border1 = Math.sqrt(Math.pow(vector1[0], 2) + Math.pow(vector1[1], 2));
                        double border2 = Math.sqrt(Math.pow(vector2[0], 2) + Math.pow(vector2[1], 2));
                        double border3 = Math.sqrt(Math.pow(vector3[0], 2) + Math.pow(vector3[1], 2));
                        double border4 = Math.sqrt(Math.pow(vector4[0], 2) + Math.pow(vector4[1], 2));

                        //4条边长都相等
                        if (border1 == border2 && border1 == border3 && border1 == border4) {
                            //向量內积为0说明一个角为90度
                            if (vector1[0] * vector2[0] + vector1[1] * vector2[1] == 0) {
                                //又因为4条边都相等，所以此可构成正方形
                                result++;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }
}
