package com.study.algorithm.sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 一句话总结：划分多个范围相同的区间，每个子区间自排序，最后合并。
//
// 桶排序是计数排序的扩展版本，计数排序可以看成每个桶只存储相同元素，而桶排序每个桶存储一定范围的元素，
// 通过映射函数（高效与否的关键就在于这个映射函数的确定），将待排序数组中的元素映射到各个对应的桶中，
// 对每个桶中的元素进行排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排），
// 最后将非空桶中的元素逐个放入原序列中。
//
// 桶排序需要尽量保证元素分散均匀，否则当所有数据集中在同一个桶中时，桶排序失效。
public class 桶排序 extends SortingUnitTest {

    // 桶排序最好情况下使用线性时间O(n)，桶排序的时间复杂度，取决与对各个桶之间数据进行排序的时间复杂度，因为其它部分的时间复杂度都为O(n)。
    // 很显然，桶划分的越小，各个桶之间的数据越少，排序所用的时间也会越少。但相应的空间消耗就会增大。
    @Test
    public void 桶排序() {
        // 桶排序过程中存在两个关键环节：
        // 1.元素值域的划分，也就是元素到桶的映射规则。映射规则需要根据待排序集合的元素分布特性进行选择，
        // 若规则设计的过于模糊、宽泛，则可能导致待排序集合中所有元素全部映射到一个桶上，则桶排序向比较性质排序算法演变。
        // 若映射规则设计的过于具体、严苛，则可能导致待排序集合中每一个元素值映射到每一个桶上，则桶排序向计数排序方式演化。
        // 2.排序算法的选择，从待排序集合中元素映射到各个桶上的过程，并不存在元素的比较和交换操作，
        // 但在对各个桶中元素进行排序时，可以自主选择合适的排序算法，桶排序算法的复杂度和稳定性，都根据选择的排序算法不同而不同。
        //
        // 算法过程：
        // 1.根据待排序集合中最大元素和最小元素的差值范围和映射规则，确定申请的桶个数；
        // 2.遍历待排序集合，将每一个元素移动到对应的桶中；
        // 3.对每一个桶中元素进行排序，并移动到已排序集合中。
        // 步骤 3 中提到的已排序集合，和步骤 1、2 中的待排序集合是同一个集合。与计数排序不同，桶排序的步骤 2
        // 完成之后，所有元素都处于桶中，并且对桶中元素排序后，移动元素过程中不再依赖原始集合，所以可以将桶中元素移动回原始集合即可。
        //
        // 作者：zhipingChen
        // 链接：https://www.jianshu.com/p/204ed43aec0c
        // 来源：简书
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        int[] ints = {50, 20, 90, 30, 80, 40, 7, 60, 110, 5};
        bucketSort(ints);
        /*todo 继续*/
        System.out.println("桶排序：" + Arrays.toString(ints));
    }

    // 设置一个定量的数组当作空桶；
    // 遍历输入数据，并且把数据一个一个放到对应的桶里去；
    // 对每个不是空的桶进行排序；
    // 从不是空的桶里把排好序的数据拼接起来。
    public void bucketSort(int[] arr) {

        // 计算最大值与最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        // 计算桶的数量
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<>());
        }

        // 将每个元素放入桶
        for (int i = 0; i < arr.length; i++) {
            int num = (arr[i] - min) / (arr.length);
            bucketArr.get(num).add(arr[i]);
        }

        // 对每个桶进行排序
        for (int i = 0; i < bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }

        // 将桶中的元素赋值到原序列
        int index = 0;
        for (int i = 0; i < bucketArr.size(); i++) {
            for (int j = 0; j < bucketArr.get(i).size(); j++) {
                arr[index++] = bucketArr.get(i).get(j);
            }
        }
    }
}
