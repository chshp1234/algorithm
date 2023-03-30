package com.study.algorithm.algorithms;

import org.junit.Test;

@Deprecated
public class 最低票价 {

    @Test
    public void 存在重复元素() {

        System.out.println(
                "存在重复元素："
                        + minCostTickets(
                                new int[] {
                                    1, 4, 6, 9, 10, 11, 12, 13, 14, 16, 16, 17, 18, 20, 21, 22, 23,
                                    27, 28
                                },
                                new int[] {3, 13, 45}));
    }

    /*todo 未完成*/
    public int minCostTickets(int[] days, int[] costs) {
        int cost = 0;

        int d2w = costs[1] / costs[0];

        int wIndex = 0, mIndex = 0;

        int temp = 0;

        int dayCount;
        int interval;

        for (int i = 0; i < days.length; ) {

            dayCount = days[i] - days[wIndex];

            if ((dayCount >= 7)) {
                interval = i - wIndex;
                if (interval > d2w) {
                    temp += costs[1];
                    wIndex += 1;

                } else {
                    temp += costs[0];
                    wIndex += 1;
                }
                //                    k--;
            } else {

                i++;
            }

            /*temp = 0;
                        wIndex = i;
                        mIndex = i;
                        for (int k = i; ; k++) {
                            if ((days[k] - days[wIndex] >= 7)) {
                                int interval = k - wIndex;
                                if (interval > d2w) {
                                    temp += costs[1];
                                    wIndex = k;
                                } else {
                                    temp += costs[0];
                                    wIndex += 1;
                                }
            //                    k--;
                            }
                            if ((days[k] - days[mIndex] >= 30)) {
                                mIndex = k;

                                int interval = k - wIndex;
                                if (interval > d2w) {
                                    temp += costs[1];
                                } else {
                                    temp += costs[0] * interval;
                                }
                                break;
                            }
                            if (k == days.length - 1) {
                                int interval = k - wIndex + 1;
                                if (interval > d2w) {
                                    temp += costs[1];
                                } else {
                                    temp += costs[0] * interval;
                                }
                                cost += Math.min(costs[2], temp);
                                return cost;
                            }
                        }
                        cost += Math.min(costs[2], temp);
                        i = mIndex;*/
        }

        return cost;
    }
}
