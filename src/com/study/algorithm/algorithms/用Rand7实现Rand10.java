package com.study.algorithm.algorithms;

import java.util.Random;

@Deprecated
public class 用Rand7实现Rand10 {
    public int rand10() {
        int left = rand7();
        int right = (rand7() + rand7() + rand7() + rand7() - 1) % 4;
        return left + right;

    }

    public int rand7() {
        return new Random().nextInt(7) + 1;
    }
}
