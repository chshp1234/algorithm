package com.study.algorithm.algorithms.algorithmsJAVA;

public class 第一个只出现一次的字符 {

    public char firstUniqChar(String s) {
        int len = s.length();
        if (len==0){
            return ' ';
        }

        int[] counter = new int[26];
        char[] chars = s.toCharArray();
        for (int i=0;i<len;i++){
            int temp = chars[i] - 'a';
            counter[temp]++;
        }
        for (int i=0;i<len;i++){
            int temp = chars[i] - 'a';
            if (counter[temp]==1){
                return chars[i];
            }
        }
        return ' ';
    }
}
