#374. Guess Number Higher or Lower
class Solution:
    def guessNumber(self, n: int) -> int:
        left, right = 1, n
        while left <= right:
            mid = left + (right - left) // 2
            res = guessNumber(mid)
            if res == 0:
                return mid
            elif res < 0:
                right = mid - 1
            else:
                left = mid + 1
