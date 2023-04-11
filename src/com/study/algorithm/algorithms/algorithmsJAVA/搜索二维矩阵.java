package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
//每行中的整数从左到右按升序排列。
//每行的第一个整数大于前一行的最后一个整数。
// 
//
//示例 1：
//
//
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
//示例 2：
//
//
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
//提示：
//
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 100
//-104 <= matrix[i][j], target <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/search-a-2d-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 搜索二维矩阵 {
    @Test
    public void 搜索二维矩阵() {
        int[][] ints = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println("搜索二维矩阵:" + searchMatrix(ints, 2));
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        //二维数组一维表示
        //将二维数组中，每一行街到上一行的结尾处
        //那么二维数组坐标(r,c)表示成一维数组时为r*c+c
        //一维数组坐标i对应的二维数组坐标为(i/col,i%col),col为二维数组列数
        //所以对于一个每一行升序，且当前行的第一个元素大于上一行最后一个元素的m*n的矩阵
        //我们可以简化为在一个升序的一维数组中查找目标值target
        //那么这就是个简单的一维升序数组查找目标值的题了

        //值得注意的是，若二维数组中的一维数组的元素个数不一，此方法将会失效。
        int col = matrix[0].length;

        int low  = 0;
        int high = matrix.length * col - 1;

        int mid;
        int r, c;
        while (low <= high) {
            mid = low + (high - low) / 2;
            r = mid / col;
            c = mid % col;
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }

    public boolean searchMatrixByLeetCode(int[][] matrix, int target) {
        //方法一：两次二分查找
        //思路
        //
        //由于每行的第一个元素大于前一行的最后一个元素，且每行元素是升序的，所以每行的第一个元素大于前一行的第一个元素，因此矩阵第一列的元素是升序的。
        //我们可以对矩阵的第一列的元素二分查找，找到最后一个不大于目标值的元素，然后在该元素所在行中二分查找目标值是否存在。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/search-a-2d-matrix/solution/sou-suo-er-wei-ju-zhen-by-leetcode-solut-vxui/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int rowIndex = binarySearchFirstColumn(matrix, target);
        if (rowIndex < 0) {
            return false;
        }
        return binarySearchRow(matrix[rowIndex], target);
    }

    public int binarySearchFirstColumn(int[][] matrix, int target) {
        int low = -1, high = matrix.length - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (matrix[mid][0] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean binarySearchRow(int[] row, int target) {
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
}
