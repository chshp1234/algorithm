"""
在 n*m 大小的棋盘中，有黑白两种棋子，黑棋记作字母 "X", 白棋记作字母 "O"，空余位置记作 "."。当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。

1.gif2.gif3.gif

「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 chessboard。若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。

注意：

若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 继续 翻转白棋
输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
示例 1：

输入：chessboard = ["....X.","....X.","XOOO..","......","......"]

输出：3

解释： 可以选择下在 [2,4] 处，能够翻转白方三枚棋子。

示例 2：

输入：chessboard = [".X.",".O.","XO."]

输出：2

解释： 可以选择下在 [2,2] 处，能够翻转白方两枚棋子。2126c1d21b1b9a9924c639d449cc6e65.gif

示例 3：

输入：chessboard = [".......",".......",".......","X......",".O.....","..O....","....OOX"]

输出：4

解释： 可以选择下在 [6,3] 处，能够翻转白方四枚棋子。803f2f04098b6174397d6c696f54d709.gif

提示：

1 <= chessboard.length, chessboard[i].length <= 8
chessboard[i] 仅包含 "."、"O" 和 "X"
通过次数
8.3K
提交次数
12.7K
通过率
65.2%
"""
from queue import Queue
from typing import List


class Solution:
    row: int
    col: int

    # 广度优先遍历
    # 遍历二维数组,只有当格子是'.'时才能落子
    # 落子后,向八个方向进行扩散,若遇到白字,则暂时将白子翻转,并继续朝一个方向延申直到遇到'X'或者被翻转后的白子(也就是'X')
    # 如果在朝一个方向延申的过程中,遇到超出边界,或者遇到'.'时,跳出循环,说明此路不通,无法将这一排白字翻转,那么再将之前暂时翻转的白子重新翻转回来
    # 否则将这些确认翻转的白子加入队列中,继续进行扩散遍历
    def flipChess(self, chessboard: List[str]) -> int:
        self.row = len(chessboard)
        self.col = len(chessboard[0])
        maxFlip = 0
        direction = [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [0, -1], [-1, -1]]

        for r in range(self.row):
            for c in range(self.col):
                if chessboard[r][c] == '.':
                    maxFlip = max(maxFlip, self.bfs(chessboard, direction, r, c))
        return maxFlip

    def bfs(self, chessboard, direction, startR, startC):
        visit = [[False for _ in range(self.col)] for _ in range(self.row)]
        queue = Queue()
        queue.put(startC + startR * self.col)
        visit[startR][startC] = True
        flipCount = 0
        while not queue.empty():
            index = queue.get()
            r = index // self.col
            c = index % self.col
            for dire in direction:
                nextR = r + dire[0]
                nextC = c + dire[1]
                flipList = []
                while self.checkBorder(nextR, nextC) and (visit[nextR][nextC] or chessboard[nextR][nextC] != '.'):
                    if visit[nextR][nextC] or chessboard[nextR][nextC] == 'X':
                        break
                    elif chessboard[nextR][nextC] == 'O':
                        visit[nextR][nextC] = True
                        flipList.append(nextC + nextR * self.col)
                        nextR += dire[0]
                        nextC += dire[1]
                else:
                    nextR -= dire[0]
                    nextC -= dire[1]
                    while nextR != r or nextC != c:
                        visit[nextR][nextC] = False
                        nextR -= dire[0]
                        nextC -= dire[1]
                    flipList.clear()
                flipCount += len(flipList)
                for i in flipList:
                    queue.put(i)
        return flipCount

    def checkBorder(self, r, c):
        return self.row > r >= 0 and self.col > c >= 0


print(Solution().flipChess(["..X.", ".OO.", "O.O.", "O.O.", "OOOX", "X..."]))
print(Solution().flipChess([".X.", ".O.", "XO."]))

# 两种创建二维数组区别:
# 第一种修改数据后, 每一项数据都会被修改
array1 = [[4] * 3] * 3
array1[1][1] = 1
array2 = [[4 for i in range(3)] for j in range(3)]
array2[1][1] = 1
print(array1)
print(array2)
