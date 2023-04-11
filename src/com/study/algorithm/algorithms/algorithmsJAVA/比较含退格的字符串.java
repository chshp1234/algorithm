package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 比较含退格的字符串 {

    @Test
    public void 比较含退格的字符串() {
        String S = "";
        String T = "c#";
        System.out.println("比较含退格的字符串:S=" + S + " T=" + T + " " + backspaceCompare(S, T));
    }

    public boolean backspaceCompare(String S, String T) {
        int SLen = S.length();
        int TLen = T.length();

        StringBuilder SBuilder = new StringBuilder(SLen);
        StringBuilder TBuilder = new StringBuilder(TLen);

        int SBLen = -1;
        char temp;
        for (int i = 0; i < SLen; i++) {
            temp = S.charAt(i);
            if (temp == '#') {
                if (SBLen >= 0) {
                    SBuilder.deleteCharAt(SBLen);
                    SBLen--;
                }
            } else {
                SBuilder.append(temp);
                SBLen++;
            }
        }

        int TBLen = -1;
        for (int i = 0; i < TLen; i++) {
            temp = T.charAt(i);
            if (temp == '#') {
                if (TBLen >= 0) {
                    TBuilder.deleteCharAt(TBLen);
                    TBLen--;
                }
            } else {
                TBuilder.append(temp);
                TBLen++;
            }
        }
        System.out.println("比较含退格的字符串:S=" + SBuilder.toString() + " T=" + TBuilder.toString());

        if (SBLen == TBLen) {
            while (SBLen >= 0) {
                if (SBuilder.charAt(SBLen) == TBuilder.charAt(SBLen)) {
                    SBLen--;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
