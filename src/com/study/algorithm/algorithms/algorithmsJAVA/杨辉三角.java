package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 杨辉三角 {
    @Test
    public void 杨辉三角() {
        System.out.println("杨辉三角:" + generate(33));
    }
    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     *
     * <p>在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * <p>示例:
     *
     * <p>输入: 5 输出: [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ]
     *
     * @param numRows the num rows
     * @return the list
     */
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> innerList;
        innerList = new ArrayList<>(1);
        innerList.add(1);
        lists.add(innerList);

        for (int i = 1; i < numRows; i++) {
            innerList = new ArrayList<>(i + 1);
            innerList.add(1);
            for (int j = 1; j < numRows; j++) {
                if (j == i) {
                    innerList.add(1);
                    break;
                }
                innerList.add(lists.get(i - 1).get(j - 1) + lists.get(i - 1).get(j));
            }
            lists.add(innerList);
        }
        return lists;
    }

}
