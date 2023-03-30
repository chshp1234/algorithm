package com.study.algorithm.algorithms;

import java.util.Random;

//给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。
//
//实现 Solution 类:
//
//Solution(double radius, double x_center, double y_center) 用圆的半径 radius 和圆心的位置 (x_center, y_center) 初始化对象
//randPoint() 返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
// 
//
//示例 1：
//
//输入:
//["Solution","randPoint","randPoint","randPoint"]
//[[1.0, 0.0, 0.0], [], [], []]
//输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
//解释:
//Solution solution = new Solution(1.0, 0.0, 0.0);
//solution.randPoint ();//返回[-0.02493，-0.38077]
//solution.randPoint ();//返回[0.82314,0.38945]
//solution.randPoint ();//返回[0.36572,0.17248]
// 
//
//提示：
//
//0 < radius <= 108
//-107 <= x_center, y_center <= 107
//randPoint 最多被调用 3 * 104 次
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/generate-random-point-in-a-circle
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 在圆内随机生成点 {
    class Solution {
        //方法一：拒绝采样
        //拒绝采样的意思是说：我们在一个更大的范围内生成随机数，并拒绝掉那些不在题目给定范围内的随机数，此时保留下来的随机数都是在范围内的。
        //为了在一个半径为 R 的圆中生成均匀随机点，我们可以使用一个边长为 2R 的正方形覆盖住圆，并在正方形内生成均匀随机点，
        //此时就只需要对于横坐标和纵坐标分别生成一个随机数即可。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode.cn/problems/generate-random-point-in-a-circle/solution/zai-yuan-nei-sui-ji-sheng-cheng-dian-by-qp342/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        //方法二：等概率随机采样
        //在一维上，[0,r]上取随机范围是成立的，因为满足「任意连续段中点被抽到的次数与总次数的比例」与「该连续段长度与总长度的比例」。
        //但在二维上，就不成立了，因为边长为r的正方形，(1/2)r的边长，此面积为原正方形面积的1/4，因此，随机到1/2边长的正方形范围理应为1/4
        //为了消除这种一维转圆导致的「等概率」失效，我们可以从 [0, r^2]内随机再开平方，从而确保距离与面积比例一致。
        double x, y, radius;
        Random random;

        public Solution(double radius, double x_center, double y_center) {
            this.radius = radius;
            random = new Random();
            x = x_center;
            y = y_center;
        }

        public double[] randPoint() {
            double lenRandom = Math.sqrt(random.nextDouble());
            double angel = random.nextDouble() * 2 * Math.PI;
            return new double[]{Math.cos(angel) * lenRandom * radius + x, Math.sin(angel) * lenRandom * radius + y};
        }
    }
}
