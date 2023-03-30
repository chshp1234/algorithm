package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给你一个由一些多米诺骨牌组成的列表 dominoes。
//
//如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
//
//形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
//
//在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
//
// 
//
//示例：
//
//输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
//输出：1
// 
//
//提示：
//
//1 <= dominoes.length <= 40000
//1 <= dominoes[i][j] <= 9
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 等价多米诺骨牌对的数量 {

    @Test
    public void 等价多米诺骨牌对的数量() {
        int[][] ints = {{1, 2}, {2, 1}, {1, 1}, {1, 2}, {2, 2}};

        System.out.println("等价多米诺骨牌对的数量:" + numEquivDominoPairs(ints));
    }

    public int numEquivDominoPairs(int[][] dominoes) {
        //哈希表
        //由于在一张牌中，每个数组x,y都是个位数，所以一张牌可以表示两个数字组成的一个“两位数”，并且唯一
        //使用哈希表，记录每个唯一的数字在数组中共有多少个
        //由于每一对数｛i，j｝中，i<j，所以我们从尾部向前遍历，每次只需要找到10*x+y和10*y+x所对应的数量，即为可凑成的等价牌的对数
        //注意在x=y的情况下，我们只需要查找10*x+y一中情况，因为反过来时这个数字还是一样的
        //这里可以在构造num（10*x+y）时，使得x<=y，也就是把两个数中小的放前面，就可只需记录一个数，来进行匹配查找

        //注意到二元对中的元素均不大于 9，因此我们可以将每一个二元对拼接成一个两位的正整数，即 (x,y)→10x+y。
        //这样就无需使用哈希表统计元素数量，而直接使用长度为 100 的数组即可。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/solution/deng-jie-duo-mi-nuo-gu-pai-dui-de-shu-li-yjlz/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int len = dominoes.length;
        Map<Integer, Integer> map = new HashMap<>();

        int result = 0;
        for (int i = len - 1; i >= 0; i--) {
            int num = dominoes[i][0] <= dominoes[i][1] ? dominoes[i][0] * 10 + dominoes[i][1] : dominoes[i][1] * 10 + dominoes[i][0];
            Integer count = map.getOrDefault(num, 0);
            result += count;
            map.put(num, ++count);
        }

        return result;
    }
}
