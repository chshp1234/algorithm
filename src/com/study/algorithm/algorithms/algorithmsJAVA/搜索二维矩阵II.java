package com.study.algorithm.algorithms.algorithmsJAVA;

//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
//
//每行的元素从左到右升序排列。
//每列的元素从上到下升序排列。
// 
//
//示例 1：
//
//
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
//输出：true
//示例 2：
//
//
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
//输出：false
// 
//
//提示：
//
//m == matrix.length
//n == matrix[i].length
//1 <= n, m <= 300
//-109 <= matrix[i][j] <= 109
//每行的所有元素从左到右升序排列
//每列的所有元素从上到下升序排列
//-109 <= target <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 搜索二维矩阵II {
    public boolean searchMatrix(int[][] matrix, int target) {
        //二分
        //由于每行每列都是递增的，那么可以确定的是，二维矩阵左上角元素最小，右下角元素最大
        //那么我们定位左上角和右下角坐标，排除所有小于目标的值和所有大于目标的值
        //当定位右下角坐标后，判断右下角坐标是否等于目标值，如果是，则返回true；如果右下角坐标值小于目标值，那么说明矩阵范围内所有元素值均小于目标值，返回false；
        //当定位左上角左边后，判断左上角坐标是否等于目标值，如果是，则返回true；如果左上角坐标值大于目标值，那么说明矩阵范围内所有元素值均大于目标值，返回false；
        //一步步细分后即可确定目标值是否存在

        int row = matrix.length;
        int col = matrix[0].length;
        int[] left = new int[]{0, 0}, right = new int[]{row - 1, col - 1};

        if (matrix[left[0]][left[1]] > target || matrix[right[0]][right[1]] < target) {
            return false;
        }
        while (true) {

            //定位右下角坐标
            findRight(matrix, left, right, target);
            if (matrix[right[0]][right[1]] < target) {
                return false;
            }
            if (matrix[right[0]][right[1]] == target) {
                return true;
            }

            //定位左上角坐标
            findLeft(matrix, left, right, target);
            if (matrix[left[0]][left[1]] > target) {
                return false;
            }
            if (matrix[left[0]][left[1]] == target) {
                return true;
            }
        }
    }

    public void findLeft(int[][] matrix, int[] left, int[] right, int target) {
        int l = left[1], h = right[1];
        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            //等于目标值，直接返回
            if (matrix[right[0]][mid] == target) {
                left[0] = right[0];
                left[1] = mid;
                return;
            }
            if (matrix[right[0]][mid] > target) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        left[1] = l;

        l = left[0];
        h = right[0];

        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            //等于目标值，直接返回
            if (matrix[mid][right[1]] == target) {
                right[0] = mid;
                left[1] = right[1];
                return;
            }
            if (matrix[mid][right[1]] > target) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        left[0] = l;
    }

    public void findRight(int[][] matrix, int[] left, int[] right, int target) {
        int l = left[1], h = right[1];
        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            //等于目标值，直接返回
            if (matrix[left[0]][mid] == target) {
                right[0] = left[0];
                right[1] = mid;
                return;
            }
            if (matrix[left[0]][mid] > target) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        right[1] = l - 1;

        l = left[0];
        h = right[0];

        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            //等于目标值，直接返回
            if (matrix[mid][left[1]] == target) {
                right[0] = mid;
                right[1] = left[1];
                return;
            }
            if (matrix[mid][left[1]] > target) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        right[0] = l - 1;
    }

    public boolean searchMatrixByLeetCode(int[][] matrix, int target) {
        //思路与算法
        //
        //我们可以从矩阵 matrix 的右上角 (0,n−1) 进行搜索。
        //在每一步的搜索过程中，如果我们位于位置 (x,y)，那么我们希望在以 matrix 的左下角为左下角、以 (x,y) 为右上角的矩阵中进行搜索，
        //即行的范围为 [x,m−1]，列的范围为 [0,y]：
        //
        //如果 matrix[x,y]=target，说明搜索完成；
        //
        //如果matrix[x,y]>target，由于每一列的元素都是升序排列的，那么在当前的搜索矩阵中，
        //所有位于第 y 列的元素都是严格大于 target 的，因此我们可以将它们全部忽略，即将 y 减少 1；
        //
        //如果 matrix[x,y]<target，由于每一行的元素都是升序排列的，那么在当前的搜索矩阵中,
        //所有位于第 x 行的元素都是严格小于 target 的，因此我们可以将它们全部忽略，即将 x 增加 1。
        //
        //在搜索的过程中，如果我们超出了矩阵的边界，那么说明矩阵中不存在 target。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/sou-suo-er-wei-ju-zhen-ii-by-leetcode-so-9hcx/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }

}
