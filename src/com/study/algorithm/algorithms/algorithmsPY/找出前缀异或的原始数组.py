from typing import List


class Solution:
    # 位运算
    # 对于pref[i]=res[i]^res[1]..^res[i]
    # 而:
    # res[i]^res[1]=pref[1]
    # res[i]^res[1]^res[2]=pref[1]^res[2]=pref[2] (这就类似于前缀和的思路了)
    # 那么可得 pref[i]=pref[i-1]^res[i]
    # 所以res[i]=pref[i-1]^pref[i]
    def findArray(self, pref: List[int]) -> List[int]:
        # res=[0 for i in range(len(pref))]
        res = list(range(len(pref)))
        res[0] = pref[0]
        for i in range(1, len(pref)):
            res[i] = pref[i - 1] ^ pref[i]
        return res


print(Solution().findArray([5, 2, 0, 3, 1]))
