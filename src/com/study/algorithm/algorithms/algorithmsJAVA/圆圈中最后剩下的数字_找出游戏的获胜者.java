package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
//
//例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
//
// 
//
//示例 1：
//
//输入: n = 5, m = 3
//输出: 3
//示例 2：
//
//输入: n = 10, m = 17
//输出: 2
// 
//
//限制：
//
//1 <= n <= 10^5
//1 <= m <= 10^6
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 圆圈中最后剩下的数字_找出游戏的获胜者 {

    @Test
    public void 圆圈中最后剩下的数字() {
        System.out.println("圆圈中最后剩下的数字:" + lastRemainingByRecursion(5, 3));
    }

    public int lastRemaining(int n, int m) {
        //常规，就按题意初始化一个列表，进行n-1次删除，最后列表剩下的数即为答案
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        m--;
        int index = 0;
        while (n > 1) {
            index = (index + m) % n;
            list.remove(index);
            n--;
        }
        return list.get(0);
    }

    public int lastRemainingByRecursion(int n, int m) {
        //递归(约瑟夫环)
        //这个问题是以弗拉维奥·约瑟夫命名的，他是1世纪的一名犹太历史学家。
        //他在自己的日记中写道，他和他的40个战友被罗马军队包围在洞中。
        //他们讨论是自杀还是被俘，最终决定自杀，并以抽签的方式决定谁杀掉谁。
        //约瑟夫斯和另外一个人是最后两个留下的人。约瑟夫斯说服了那个人，他们将向罗马军队投降，不再自杀。
        //约瑟夫斯把他的存活归因于运气或天意，他不知道是哪一个。 —— 【约瑟夫问题】维基百科
        //
        //我们将问题建模为函数 f(n, m)，该函数的返回值为最终留下的元素的序号。
        //很明显，最后当n=1时，这最后一个人所在的序号位置为下标0，此为递归终止条件。
        //反推上去后，当n=2时，需要在之前的（n=1）下标0前面再加入m个人后，即为这个幸存者的下标位置(0+m)%2，因为只有2个人（n=2）
        //继续反推后，当n=3时，需要在之前的（n=2）下标前面再加入m个人后，即为这个幸存者的下标位置((0+m)%2+m)%3
        //...
        //最后反推至n时，即可计算出f(n, m)的情况下，幸存者的下标位置。
        if (n == 1) {
            return 0;
        }
        return (lastRemainingByRecursion(n - 1, m) + m) % n;

        //递归改为迭代，避免栈空间开销
        /*int f = 0;
        for (int i = 2; i != n + 1; ++i) {
            f = (m + f) % i;
        }
        return f;

        作者：LeetCode-Solution
        链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-by-lee/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
