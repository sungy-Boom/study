class Solution:
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        s = 1
        if x == 0:
            s = 1
        elif x < 0:
            s = -1
        else:
            s = 1
        reverse = int(repr(s*x)[::-1])
        if reverse <= 2 ** 31:
            return s*reverse
        elif reverse > 2 ** 31:
            return 0
        pass
    pass


num = int(input("输入： "))
res = Solution().reverse(num)
print(res)
