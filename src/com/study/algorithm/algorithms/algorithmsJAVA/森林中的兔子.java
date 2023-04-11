package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

//森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
//
//返回森林中兔子的最少数量。
//
//示例:
//输入: answers = [1, 1, 2]
//输出: 5
//解释:
//两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
//之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
//设回答了 "2" 的兔子为蓝色。
//此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
//因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
//
//输入: answers = [10, 10, 10]
//输出: 11
//
//输入: answers = []
//输出: 0
//说明:
//
//answers 的长度最大为1000。
//answers[i] 是在 [0, 999] 范围内的整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/rabbits-in-forest
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 森林中的兔子 {
    @Test
    public void 森林中的兔子() {

        System.out.println("森林中的兔子：" + numRabbits(new int[]{1, 1, 1, 1}));
        //        System.out.println("有效括号的嵌套深度：" + calculateDepth(seq, seq.length()));
    }

    public int numRabbits(int[] answers) {
        //排序
        //将数组中所有相同的数字分为一组
        //若两数字x,y代表相同的颜色，那么x=y-1(除y之外还有一个相同颜色)+1（再加上y自己），所以x=y
        //要求兔子最小的数量，那么我们可以把所有相同的数字x分为一组
        //1.求x的数量count，若x=count，说明所有相同颜色的兔子都已在数组中记录
        //2.若遇到不同的数字y，则说明遇到另一种颜色的兔子
        //数量result=result+x+1（代表除了自己+还需x个兔子）

        //我们可以先对数组进行排序
        //遍历过程中，把不同数字当作不同颜色，记录当前兔子的颜色current，当前统计的额外兔子数量count，以及结果result
        //1.若统计的count=current（已找到所有额外的兔子）
        //2.answers[i] != current，找到另一种颜色的兔子
        //那么，result=result+current+1，重新统计current=answers[i]，count=0；
        //否则令count+1
        if (answers.length == 0) {
            return 0;
        }

        int len = answers.length;
        Arrays.sort(answers);

        int current = answers[0];
        int count   = 0;
        int result  = 0;

        for (int i = 1; i < len; i++) {
            if (count == current || answers[i] != current) {
                //1.若统计的count=current（已找到所有额外的兔子）
                //2.answers[i] != current，找到另一种颜色的兔子
                result += current + 1;
                count = 0;
                current = answers[i];
            } else {
                count++;
            }
        }

        return result + current + 1;
    }

}
