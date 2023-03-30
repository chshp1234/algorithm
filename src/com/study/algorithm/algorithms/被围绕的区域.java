package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class 被围绕的区域 {

    @Test
    public void 被围绕的区域() {
        char[][] ints =
                new char[][] {
                    {'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                    {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X'},
                    {'O', 'X', 'O', 'X', 'O', 'O', 'O', 'O', 'X'},
                    {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O'},
                    {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
                    {'X', 'X', 'O', 'O', 'X', 'O', 'X', 'O', 'X'},
                    {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O'},
                    {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O'},
                    {'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O'}
                };

        solve(ints);

        System.out.println("被围绕的区域:" + Arrays.deepToString(ints));
    }

    /*todo 有极大的优化空间。
    思路二：从边界进行遍历，找出与边界O节点相连通的O节点，并将其改为其他字符，代表此节点不做改变；
    将二维数组中其他O节点（不与边界上的O节点相连）改为X；
    最后将之前改为的其他字符（与边界O节点相连通的所有O节点）改回字符O，即可*/
    @Deprecated
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        // 思路：遍历所有O节点，若O在边界上，则与此O相连的所有O节点都在边界上（不设置为X），可用深度优先或广东优先

        // 需要改变O节点为X的所有节点
        List<List<int[]>> changes = new ArrayList<>();

        // 栈，利用深度优先查找
        Deque<int[]> stack = new LinkedList<>();

        int row = board.length, col = board[0].length;

        // 判断当前节点是否查找过，这里用+2的长度，用于防止越界
        boolean[][] visit = new boolean[row + 2][col + 2];

        // 这里对最外一层设置为“已经浏览过”，因为愿矩阵不会浏览至此，所以设最外一层为true，可以防止数组越界
        Arrays.fill(visit[row + 1], true);
        Arrays.fill(visit[0], true);
        for (int i = 0; i <= row; i++) {
            visit[i][0] = true;
            visit[i][col + 1] = true;
        }

        // 是否在边界（由于在边界的O节点不设置为X）
        boolean border = false;

        int[] temp;

        for (int i = 0, il = board.length - 1; i <= il; i++) {
            for (int j = 0, jl = board[0].length - 1; j <= jl; j++) {

                // 如果当前坐标没被浏览过，并且不为X节点
                if (!visit[i + 1][j + 1] && board[i][j] != 'X') {

                    // 设为已浏览
                    visit[i + 1][j + 1] = true;

                    // 与此相连的节点列表
                    List<int[]> list = new ArrayList<>();

                    temp = new int[] {i, j};

                    // 加入栈
                    stack.offerFirst(temp);

                    // 加入列表
                    list.add(temp);

                    while (!stack.isEmpty()) {

                        // 取出当前节点坐标
                        int[] pop = stack.pollFirst();

                        // 判断当前节点是否在边界
                        if (pop[0] == 0 || pop[0] == il || pop[1] == 0 || pop[1] == jl) {
                            border = true;
                        }

                        // 加入上一个节点
                        if (!visit[pop[0]][pop[1] + 1] && board[pop[0] - 1][pop[1]] == 'O') {
                            visit[pop[0]][pop[1] + 1] = true;
                            temp = new int[] {pop[0] - 1, pop[1]};
                            list.add(temp);
                            stack.offerFirst(temp);
                        }

                        // 加入右边节点
                        if (!visit[pop[0] + 1][pop[1] + 2] && board[pop[0]][pop[1] + 1] == 'O') {
                            visit[pop[0] + 1][pop[1] + 2] = true;
                            temp = new int[] {pop[0], pop[1] + 1};
                            list.add(temp);
                            stack.offerFirst(temp);
                        }

                        // 加入下面节点
                        if (!visit[pop[0] + 2][pop[1] + 1] && board[pop[0] + 1][pop[1]] == 'O') {
                            visit[pop[0] + 2][pop[1] + 1] = true;
                            temp = new int[] {pop[0] + 1, pop[1]};
                            list.add(temp);
                            stack.offerFirst(temp);
                        }

                        // 加入左边节点
                        if (!visit[pop[0] + 1][pop[1]] && board[pop[0]][pop[1] - 1] == 'O') {
                            visit[pop[0] + 1][pop[1]] = true;
                            temp = new int[] {pop[0], pop[1] - 1};
                            list.add(temp);
                            stack.offerFirst(temp);
                        }
                    }

                    // 如果当前所有节点不与边界相连，则加入待改变列表集合
                    if (!border) {
                        changes.add(list);
                    }

                    border = false;
                }
            }
        }

        // 将集合中数据O改变为X
        changes.forEach(
                new Consumer<List<int[]>>() {
                    @Override
                    public void accept(List<int[]> ints) {
                        for (int i = 0, l = ints.size(); i < l; i++) {
                            board[ints.get(i)[0]][ints.get(i)[1]] = 'X';
                        }
                    }
                });
    }
}
