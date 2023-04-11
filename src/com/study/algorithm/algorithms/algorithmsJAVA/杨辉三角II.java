package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
//
//
//
//在杨辉三角中，每个数是它左上方和右上方的数的和。
//
//示例:
//
//输入: 3
//输出: [1,3,3,1]
//进阶：
//
//你可以优化你的算法到 O(k) 空间复杂度吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/pascals-triangle-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 杨辉三角II {
    @Test
    public void 杨辉三角II() {
        System.out.println("杨辉三角II:" + getRow(1));
    }

    public List<Integer> getRow(int rowIndex) {
        //滚动数组
        //由于下一行的数只和上一行数有关，所以我们可以用滚动数组的思想，用O(n)的空间复杂度。
        //其中f(i)=f(i-1)+f(i)。又因为一行数中，其实只有前一半是需要计算的，后一半的数和前一半是镜像关系，可以直接从前一半复制过来。

        int len = rowIndex + 1;
        List<Integer> result = new ArrayList<>(len);
        result.add(1);
        if (rowIndex == 0) {
            return result;
        }
        result.add(1);
        for (int i = 3; i <= len; i++) {
            int last = result.get(0);
            for (int j = 1, l = i / 2 + i % 2; j < l; j++) {
                int temp = result.get(j);
                result.set(j, last + temp);
                last = temp;
            }
            //当层数为偶数时，需要多加一个中间数，供下一行计算
            if (i % 2 == 0) {
                result.add(result.get(result.size() - 1));
            }
        }

        //从前一半的数复制到后一半中
        for (int i = ((len - 1) / 2) - 1; i >= 0; i--) {
            result.add(result.get(i));
        }
        return result;
    }

    //线性递推
    //由组合数公式 C(m,n) =n!m!(n−m)!，可以得到同一行的相邻组合数的关系:
    //C(m.n)=C(m−1,n) × (n−m+1)/n.
    //​C(0,n)=1，利用上述公式我们可以在线性时间计算出第 n 行的所有组合数。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/pascals-triangle-ii/solution/yang-hui-san-jiao-ii-by-leetcode-solutio-shuk/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public List<Integer> getRowByLeetCode(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }
}
