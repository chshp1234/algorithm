package com.study.algorithm.algorithms;

import org.junit.Test;

//老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
//
//你需要按照以下要求，帮助老师给这些孩子分发糖果：
//
//每个孩子至少分配到 1 个糖果。
//相邻的孩子中，评分高的孩子必须获得更多的糖果。
//那么这样下来，老师至少需要准备多少颗糖果呢？
//
//示例 1:
//
//输入: [1,0,2]
//输出: 5
//解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
//示例 2:
//
//输入: [1,2,2]
//输出: 4
//解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
//     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/candy
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 分发糖果 {
    @Test
    public void 分发糖果() {
        int[] graph = {1, 2, 7, 6};
        System.out.println("分发糖果:" + candy(graph));
    }
    public int candy(int[] ratings) {
        //贪心，两趟遍历

        //第一次遍历从左往右，若当前值比上一个值大，则设当前需要的糖果数为上一哥孩子糖果数+1
        //若当前得分比上个孩子小或等于，则重新设当前孩子糖果为1
        //第二次遍历从右往左，同第一次，若得分比上一个孩子大，则糖果+1，否则糖果为1。
        //除此，更新结果，根据当前孩子获得的糖果（从右往左计算），和第一次遍历后计算的当前孩子糖果（从左往右计算）进行比较，
        //取最大值计算到结果里。

        int len = ratings.length;
        int[] count = new int[len];
        count[0] = 1;
        for (int i=1;i<len;i++){
            if (ratings[i]>ratings[i-1]){
                count[i] = count[i - 1] + 1;
            }else {
                count[i] = 1;
            }
        }
        int result = count[len - 1];
        for (int i=len-2;i>=0;i--){
            int temp = count[i];
            if (ratings[i]>ratings[i+1]){
                count[i] = count[i + 1] + 1;
            }else {
                count[i] = 1;
            }
            result += Math.max(temp, count[i]);
        }

        return result;
    }
}
