package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
//
//Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
//
//禁止一名参议员的权利：
//
//参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
//
//宣布胜利：
//
//          如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
//
// 
//
//给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
//
//以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
//
//假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
//
// 
//
//示例 1：
//
//输入："RD"
//输出："Radiant"
//解释：第一个参议员来自 Radiant 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人
//示例 2：
//
//输入："RDD"
//输出："Dire"
//解释：
//第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利
//第二个来自 Dire 阵营的参议员会被跳过因为他的权利被禁止
//第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利
//因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
// 
//
//提示：
//
//给定字符串的长度在 [1, 10,000] 之间.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/dota2-senate
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Dota2参议院 {
    @Test
    public void Dota2参议院() {
        System.out.println("Dota2参议院:" + predictPartyVictory("DRDRR"));
    }

    public String predictPartyVictory(String senate) {

        // 贪心
        // 每个成员都禁止其下一个最近的对手
        // 寻找下一个最近的对手过程中，若遇到同一阵营成员，则需要禁止的对手数量将+1。
        // 直到需要禁止的对手数量为0时，继续下一轮禁止。
        // 若需要禁止的对手数量和剩余的成员数量相等，则说明对手已全被禁止完，则当前成员即为获胜成员。

        int len = senate.length();

        // 保存已被禁止的成员
        boolean[] isForbidden = new boolean[len];

        char[] chars = senate.toCharArray();

        // 当前成员
        char current = chars[0];

        // 对手位置
        int rival = 1;

        // 需要禁止的对手数量
        int nextRivalCount = 1;

        // 剩余成员数量
        int left = len;

        while (true) {

            // 直到需要禁止的对手数量为0时
            while (nextRivalCount > 0) {
                // 若需要禁止的对手数量和剩余的成员数量相等，则说明对手已全被禁止完，则当前成员即为获胜成员。
                if (nextRivalCount >= left) {
                    return current == 'D' ? "Dire" : "Radiant";
                }

                // 如果当前对手未被禁止
                if (!isForbidden[rival]) {
                    //如果当前对手是自己人（也就不能称为“对手”）
                    if (chars[rival] == current) {
                        // 则需要禁止的对手数量+1
                        nextRivalCount++;
                    } else {
                        // 若当前对手是敌方（真正的对手）
                        // 禁止对手
                        isForbidden[rival] = true;
                        //禁止的对手数量-1
                        nextRivalCount--;
                        //剩余成员数量-1
                        left--;
                    }
                }
                // 继续判断下一个对手
                rival = (rival + 1) % len;
            }

            // 找到没被禁止的对手
            while (isForbidden[rival]) {
                rival = (rival + 1) % len;
            }

            // 上一轮禁止结束，接下来开始对手环节（如果接下去是的话）
            current = chars[rival];
            rival = (rival + 1) % len;
            // 禁止数量为1
            nextRivalCount = 1;

        }

    }
}
