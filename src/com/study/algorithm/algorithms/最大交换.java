package com.study.algorithm.algorithms;

//给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
//
//示例 1 :
//
//输入: 2736
//输出: 7236
//解释: 交换数字2和数字7。
//示例 2 :
//
//输入: 9973
//输出: 9973
//解释: 不需要交换。
//注意:
//
//给定数字的范围是 [0, 108]
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-swap
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大交换 {
    public int maximumSwap(int num) {

        //贪心
        //对数字从右到左进行遍历每一个数,并选取最大数max.
        //为方便计算我们记录每次选取尾数时的位数step.
        //
        //若当前尾数temp>max,说明这个数比右边最大的数还要大,那么更新最大值max,并且保存当前数所在的位数maxIndex=step
        //若当前尾数temp<max,说明其可以和max进行交换,使得num更大,交换其实就是高位加上差值,低位减去差值.那么result=(num + diff * Math.pow(10, step) - diff * Math.pow(10, maxIndex))(其中diff=max-temp)
        //重复进行上述遍历与判断,直到结束,就可以把数字右侧最大的数尽可能的和最左侧小于其的数进行交换,得到结果"最大交换"的数
        //
        //作者：chshp
        //链接：https://leetcode.cn/problems/maximum-swap/solution/tan-xin-by-chshp-uhtp/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        //小于10不要考虑
        if (num < 10) {
            return num;
        }

        //最大值
        int max = num % 10;
        //与最大值相对应的,最大值所在的位数
        int maxIndex = 0;
        //方便计算num的每个尾数
        int n = num / 10;

        //位数
        int step = 1;

        //结果
        int result = num;

        while (n > 0) {
            //当前遍历的尾数
            int temp = n % 10;
            if (temp > max) {
                max = temp;
                maxIndex = step;
            } else if (temp < max) {
                //更新结果
                int diff = max - temp;
                result = (int) (num + diff * Math.pow(10, step) - diff * Math.pow(10, maxIndex));
            }

            step++;
            n /= 10;
        }

        return result;
    }
}
