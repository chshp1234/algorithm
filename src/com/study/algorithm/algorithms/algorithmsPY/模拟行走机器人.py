"""
机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 commands ：

-2 ：向左转 90 度
-1 ：向右转 90 度
1 <= x <= 9 ：向前移动 x 个单位长度
在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点  obstacles[i] = (xi, yi) 。

机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。

返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ）


注意：

北表示 +Y 方向。
东表示 +X 方向。
南表示 -Y 方向。
西表示 -X 方向。


示例 1：

输入：commands = [4,-1,3], obstacles = []
输出：25
解释：
机器人开始位于 (0, 0)：
1. 向北移动 4 个单位，到达 (0, 4)
2. 右转
3. 向东移动 3 个单位，到达 (3, 4)
距离原点最远的是 (3, 4) ，距离为 32 + 42 = 25
示例 2：

输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
输出：65
解释：机器人开始位于 (0, 0)：
1. 向北移动 4 个单位，到达 (0, 4)
2. 右转
3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
4. 左转
5. 向北走 4 个单位，到达 (1, 8)
距离原点最远的是 (1, 8) ，距离为 12 + 82 = 65


提示：

1 <= commands.length <= 104
commands[i] is one of the values in the list [-2,-1,1,2,3,4,5,6,7,8,9].
0 <= obstacles.length <= 104
-3 * 104 <= xi, yi <= 3 * 104
答案保证小于 231
"""
from typing import List


class Solution:
    """
    模拟
    先用哈希表将每个障碍物的位置记录下来
    再对每条命令进行模拟
    如果命令>0,模拟机器人行走,如果行走的下一步有障碍物,则跳出模拟,继续下一条命令
    为方便计算,可以用一个数组表示机器人的每一个朝向,一个下标表示机器人当前朝向的下标
    """

    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        direction = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        direct = 0
        x = 0
        y = 0
        mp = {}
        res = 0
        for obs in obstacles:
            if obs[0] not in mp:
                mp[obs[0]] = set()
            mp[obs[0]].add(obs[1])
        for command in commands:
            if command == -2:
                direct = (direct - 1) % 4
            elif command == -1:
                direct = (direct + 1) % 4
            else:
                for step in range(command):
                    if x + direction[direct][0] in mp and y + direction[direct][1] in mp[x + direction[direct][0]]:
                        break
                    else:
                        x += direction[direct][0]
                        y += direction[direct][1]
                res = max(res, x ** 2 + y ** 2)
        return res


print(Solution().robotSim([6, -1, -1, 6], [[1, 2], [5, 6]]))
