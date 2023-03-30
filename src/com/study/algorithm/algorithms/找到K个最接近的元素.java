package com.study.algorithm.algorithms;

import java.util.*;

public class 找到K个最接近的元素 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //双指针,二分查找
        //首先用二分查找,找到x值应在数组中的位置index
        //定义双指针left,right,left=index-1,right=index
        //每次判断(arr[right] - x) - (x - arr[left]),也就是left和right与x的差值diff的大小,来决定left和right哪一个离"更近"
        //如果diff>=0,说明left离x近,那么将arr[left]加入结果列表,并将left--
        //否则,right离x近,将arr[right]加入结果列表,并将right++
        //每次加入后,k--,当k=0时,所有离x近的k个元素就查找完成了
        //注意,遍历时,left可能小于0,right有可能等于arr.length.那么这时候,越界的一端就不需要再进行判断了,将剩下的数从另一端加入即可
        //这里方便插入操作,我们使用LinkedList,可以方便快速的进行头插和尾插
        int index = Arrays.binarySearch(arr, x);
        int left, right;
        LinkedList<Integer> result = new LinkedList<>();

        if (index < 0) {
            index = -index - 1;
        }

        left = index - 1;
        right = index;

        while (left >= 0 && right < arr.length && k > 0) {
            int diff = (arr[right] - x) - (x - arr[left]);
            if (diff >= 0) {
                result.addFirst(arr[left--]);
            } else {
                result.addLast(arr[right++]);
            }
            k--;
        }

        while (k > 0) {
            if (left < 0) {
                result.addLast(arr[right++]);
            } else if (right == arr.length) {
                result.addFirst(arr[left--]);
            }
            k--;
        }

        return result;
    }

    public List<Integer> findClosestElementsByPriorityQueue(int[] arr, int k, int x) {
        //排序,优先队列
        //直接对数组进行排序,离x近的数排在前面,远的数排在后面,最后输出排序后的数组中的前k个数即可
        //由于我们只需要找到前k个数,其实不用完全排序,那么可以用小顶堆来构造排序数组,从小顶堆中找出前k个数即可
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) {
                    //如果两个数相同,返回0
                    return 0;
                }
                //两个数与x的差值的大小
                int diff = (Math.abs(o1 - x) - Math.abs(o2 - x));
                if (diff == 0) {
                    //如果大小相同,那么小的数排在前面
                    return o1 - o2;
                }
                return diff;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            queue.offer(arr[i]);
        }

        List<Integer> result = new ArrayList<>(k);
        while (k > 0) {
            result.add(queue.poll());
            k--;
        }

        Collections.sort(result);

        return result;
    }
}
