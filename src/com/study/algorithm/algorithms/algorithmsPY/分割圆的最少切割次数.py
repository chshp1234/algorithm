class Solution:
    def numberOfCuts(self, n: int) -> int:
        if n == 1:
            return 0
        elif n & 1 == 0:
            return n // 2
        else:
            return n


print(Solution().numberOfCuts(5))
