package com.study.algorithm.algorithms.algorithmsJAVA;

//图像平滑器 是大小为 3 x 3 的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。
//
//每个单元格的  平均灰度 定义为：该单元格自身及其周围的 8 个单元格的平均值，结果需向下取整。（即，需要计算蓝色平滑器中 9 个单元格的平均值）。
//
//如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中 4 个单元格的平均值）。
//
//
//
//给你一个表示图像灰度的 m x n 整数矩阵 img ，返回对图像的每个单元格平滑处理后的图像 。
//
// 
//
//示例 1:
//
//
//
//输入:img = [[1,1,1],[1,0,1],[1,1,1]]
//输出:[[0, 0, 0],[0, 0, 0], [0, 0, 0]]
//解释:
//对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
//对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
//对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
//示例 2:
//
//
//输入: img = [[100,200,100],[200,50,200],[100,200,100]]
//输出: [[137,141,137],[141,138,141],[137,141,137]]
//解释:
//对于点 (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
//对于点 (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
//对于点 (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
// 
//
//提示:
//
//m == img.length
//n == img[i].length
//1 <= m, n <= 200
//0 <= img[i][j] <= 255
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/image-smoother
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 图片平滑器 {
    public int[][] imageSmoother(int[][] img) {
        //模拟
        //遍历二维数组，注意边界
        int row = img.length;
        int col = img[0].length;
        int[][] result = new int[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int count = 0;
                int sum = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        int pr = r + i;
                        int pc = c + j;
                        if (pr >= 0 && pr < row && pc >= 0 && pc < col) {
                            sum += img[pr][pc];
                            count++;
                        }
                    }
                }
                result[r][c] = sum / count;
            }
        }
        return result;
    }

    public int[][] imageSmootherByAC_OIer(int[][] img) {
        //二维数组前缀和
        //对于某个 ans[i][j] 而言，我们可以直接计算出其所在 item 的左上角 (a,b)=(i−1,j−1) 以及其右下角 (c,d)=(i+1,j+1)，
        //同时为了防止超出原矩阵，我们需要将 (a,b) 与 (c,d) 对边界分别取 max 和 min。
        //
        //当有了合法的 (a,b) 和 (c,d) 后，我们可以直接计算出 item 的单元格数量（所包含的行列乘积）及 item 的单元格之和（前缀和查询），
        //从而算得 ans[i][j]。
        //
        //作者：AC_OIer
        //链接：https://leetcode-cn.com/problems/image-smoother/solution/by-ac_oier-nn3v/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int m = img.length, n = img[0].length;
        int[][] sum = new int[m + 10][n + 10];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + img[i - 1][j - 1];
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int a = Math.max(0, i - 1), b = Math.max(0, j - 1);
                int c = Math.min(m - 1, i + 1), d = Math.min(n - 1, j + 1);
                int cnt = (c - a + 1) * (d - b + 1);
                int tot = sum[c + 1][d + 1] - sum[a][d + 1] - sum[c + 1][b] + sum[a][b];
                ans[i][j] = tot / cnt;
            }
        }
        return ans;
    }
}
