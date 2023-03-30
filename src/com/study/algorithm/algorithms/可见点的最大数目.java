package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//给你一个点数组 points 和一个表示角度的整数 angle ，你的位置是 location ，其中 location = [posx, posy] 且 points[i] = [xi, yi] 都表示 X-Y 平面上的整数坐标。
//
//最开始，你面向东方进行观测。你 不能 进行移动改变位置，但可以通过 自转 调整观测角度。换句话说，posx 和 posy 不能改变。你的视野范围的角度用 angle 表示， 这决定了你观测任意方向时可以多宽。设 d 为你逆时针自转旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
//
//对于每个点，如果由该点、你的位置以及从你的位置直接向东的方向形成的角度 位于你的视野中 ，那么你就可以看到它。
//
//同一个坐标上可以有多个点。你所在的位置也可能存在一些点，但不管你的怎么旋转，总是可以看到这些点。同时，点不会阻碍你看到其他点。
//
//返回你能看到的点的最大数目。
//
// 
//
//示例 1：
//
//
//
//输入：points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
//输出：3
//解释：阴影区域代表你的视野。在你的视野中，所有的点都清晰可见，尽管 [2,2] 和 [3,3]在同一条直线上，你仍然可以看到 [3,3] 。
//示例 2：
//
//输入：points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
//输出：4
//解释：在你的视野中，所有的点都清晰可见，包括你所在位置的那个点。
//示例 3：
//
//
//
//输入：points = [[1,0],[2,1]], angle = 13, location = [1,1]
//输出：1
//解释：如图所示，你只能看到两点之一。
// 
//
//提示：
//
//1 <= points.length <= 105
//points[i].length == 2
//location.length == 2
//0 <= angle < 360
//0 <= posx, posy, xi, yi <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-number-of-visible-points
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 可见点的最大数目 {
    @Test
    public void 可见点的最大数目() {
        List<List<Integer>> points = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        p.add(41);
        p.add(33);
        points.add(p);
        p = new ArrayList<>();
        p.add(96);
        p.add(46);
        points.add(p);
        p = new ArrayList<>();
        p.add(96);
        p.add(15);
        points.add(p);
        p = new ArrayList<>();
        p.add(96);
        p.add(46);
        points.add(p);
        p = new ArrayList<>();
        p.add(45);
        p.add(1);
        points.add(p);
        p = new ArrayList<>();
        p.add(21);
        p.add(3);
        points.add(p);
        p = new ArrayList<>();
        p.add(1);
        p.add(24);
        points.add(p);
        p = new ArrayList<>();
        p.add(79);
        p.add(42);
        points.add(p);
        p = new ArrayList<>();
        p.add(36);
        p.add(38);
        points.add(p);
        p = new ArrayList<>();
        p.add(6);
        p.add(27);
        points.add(p);
        p = new ArrayList<>();
        p.add(38);
        p.add(12);
        points.add(p);
        p = new ArrayList<>();
        p.add(63);
        p.add(23);
        points.add(p);
        p = new ArrayList<>();
        p.add(3);
        p.add(23);
        points.add(p);
        p = new ArrayList<>();
        p.add(18);
        p.add(11);
        points.add(p);
        p = new ArrayList<>();
        p.add(76);
        p.add(22);
        points.add(p);
        p = new ArrayList<>();
        p.add(17);
        p.add(82);
        points.add(p);
        p = new ArrayList<>();
        p.add(57);
        p.add(39);
        points.add(p);
        p = new ArrayList<>();
        p.add(85);
        p.add(6);
        points.add(p);
        p = new ArrayList<>();
        p.add(93);
        p.add(91);
        points.add(p);
        p = new ArrayList<>();
        p.add(73);
        p.add(58);
        points.add(p);
        p = new ArrayList<>();
        p.add(68);
        p.add(87);
        points.add(p);
        p = new ArrayList<>();
        p.add(83);
        p.add(6);
        points.add(p);
        p = new ArrayList<>();
        p.add(68);
        p.add(51);
        points.add(p);
        p = new ArrayList<>();
        p.add(62);
        p.add(83);
        points.add(p);
        p = new ArrayList<>();
        p.add(72);
        p.add(90);
        points.add(p);
        p = new ArrayList<>();
        p.add(51);
        p.add(79);
        points.add(p);
        p = new ArrayList<>();
        p.add(69);
        p.add(57);
        points.add(p);
        p = new ArrayList<>();
        p.add(65);
        p.add(20);
        points.add(p);
        p = new ArrayList<>();
        p.add(69);
        p.add(43);
        points.add(p);
        p = new ArrayList<>();
        p.add(28);
        p.add(89);
        points.add(p);
        p = new ArrayList<>();
        p.add(40);
        p.add(61);
        points.add(p);
        p = new ArrayList<>();
        p.add(21);
        p.add(37);
        points.add(p);
        p = new ArrayList<>();
        p.add(64);
        p.add(10);
        points.add(p);

        int angle = 36;

        List<Integer> location = new ArrayList<>();
        location.add(57);
        location.add(39);
        System.out.println("可见点的最大数目:" + visiblePoints(points, angle, location));
    }

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        //排序+滑动窗口
        //以人物为坐标原点，按每个点与原点形成的角度排序
        //使用双指针，代表当前可观察的最大点数
        //若以left指针为可视区域左端，当前right节点在left节点+可视区域范围内，right+1，可观察最大点数+1
        //若不在范围内，right+1的同时left+1，另窗口大小保持不变的同时，窗口右移至下一个节点，继续判断
        //left移至len处时，right与left的距离即为最大可视的点数
        int x = location.get(0);
        int y = location.get(1);

        for (List<Integer> p : points) {
            p.set(0, p.get(0) - x);
            p.set(1, p.get(1) - y);
        }

        points.sort((o1, o2) -> {
            if (o1.get(0) == 0 && o1.get(1) == 0) {
                return -1;
            }
            if (o2.get(0) == 0 && o2.get(1) == 0) {
                return 1;
            }

            return Double.compare(Math.toDegrees(Math.atan2((double) (o1.get(1)), (o1.get(0)))),
                    Math.toDegrees(Math.atan2((double) (o2.get(1)), (o2.get(0)))));
        });

        int len = points.size();
        int left = 0;
        int ori = 0;
        while (left < len) {
            if (points.get(left).get(0) == 0 && points.get(left).get(1) == 0) {
                ori++;
            } else {
                break;
            }
            left++;
        }

        if (left == len) {
            return len;
        }

        int right = left + 1;
        double leftDegrees = Math.toDegrees(Math.atan2((double) (points.get(left).get(1)), (double) (points.get(left).get(0))));
        if (leftDegrees < 0) {
            leftDegrees += 360;
        }

        double diffDegrees;

        while (true) {
            double rightDegrees = Math.toDegrees(Math.atan2((double) (points.get(right).get(1)), (points.get(right).get(0))));
            if (rightDegrees < 0) {
                rightDegrees += 360;
            }
            if (leftDegrees > rightDegrees) {
                diffDegrees = rightDegrees + 360 - leftDegrees;
            } else {
                diffDegrees = rightDegrees - leftDegrees;
            }
            if (diffDegrees > angle || (right < left && diffDegrees == 0)) {
                left++;
                if (left == len) {
                    break;
                }
                leftDegrees = Math.toDegrees(Math.atan2((double) (points.get(left).get(1)), (double) (points.get(left).get(0))));
                if (leftDegrees < 0) {
                    leftDegrees += 360;
                }
            }
            right++;
            if (right == len) {
                right = ori;
            }
            if (right == left) {
                return len;
            }
        }

        return right + 1;
    }
}
