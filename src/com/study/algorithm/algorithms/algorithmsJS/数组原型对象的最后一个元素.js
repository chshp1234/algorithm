/**
 * 请你编写一段代码实现一个数组方法，使任何数组都可以调用 array.last() 方法，这个方法将返回数组最后一个元素。如果数组中没有元素，则返回 -1 。

你可以假设数组是 JSON.parse 的输出结果。

 

示例 1 ：

输入：nums = [null, {}, 3]
输出：3
解释：调用 nums.last() 后返回最后一个元素： 3。
示例 2 ：

输入：nums = []
输出：-1
解释：因为此数组没有元素，所以应该返回 -1。
 

提示：

arr 是一个有效的 JSON 数组
0 <= arr.length <= 1000
 * @return {null|boolean|number|string|Array|Object}
 */
Array.prototype.last = function() {
    //判断当前数组长度：
    //如果长度为0，返回-1
    //如果长度不为0，返回最后一个元素
    return this.length == 0 ? -1 : this[this.length - 1];
};

console.log([null, {}, 3].last());