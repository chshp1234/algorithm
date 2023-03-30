package com.study.algorithm.algorithms;

import java.util.*;

public class 在线选举 {

    class TopVotedCandidate {
        //预处理+二分查找
        //预处理每张票投出去时获胜的人
        //获取时二分查找是第几张票，再从预处理的结果中取出到这张票为止的获胜的人
        List<Integer> winner = new ArrayList<>();
        int[] times;

        public TopVotedCandidate(int[] persons, int[] times) {

            //哈希表记录每个人获得的票数
            //比较当前人的票数和最大票数，从而判断投出当前票的情况下，获胜的人是谁，并加入预处理结果中
            this.times = times;
            int max = 0;
            int pMax = -1;
            Map<Integer, Integer> pMap = new HashMap<>();
            for (int person : persons) {
                int pCount = pMap.getOrDefault(person, 0) + 1;
                pMap.put(person, pCount);
                if (pCount >= max) {
                    winner.add(person);
                    pMax = person;
                    max = pCount;
                } else {
                    winner.add(pMax);
                }
            }
        }

        public int q(int t) {
            int time = Arrays.binarySearch(times, t);
            if (time < 0) {
                time = -time - 2;
            }
            return winner.get(time);
        }
    }
}
