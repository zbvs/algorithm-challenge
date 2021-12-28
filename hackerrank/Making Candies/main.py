# !/bin/python3

"""
[Hackerrank] Search : Making Candies

Karl loves playing games on social networking sites. His current favorite is CandyMaker, where the goal is to make candies.

Karl just started a level in which he must accumulate n candies starting with m machines and w workers. In a single pass, he can make m x w candies. After each pass, he can decide whether to spend some of his candies to buy more machines or hire more workers. Buying a machine or hiring a worker costs p units, and there is no limit to the number of machines he can own or workers he can employ.
"""
import math
import os
import sys


#
# Complete the 'minimumPasses' function below.
#
# The function is expected to return a LONG_INTEGER.
# The function accepts following parameters:
#  1. LONG_INTEGER m
#  2. LONG_INTEGER w
#  3. LONG_INTEGER p
#  4. LONG_INTEGER n
#
def minimumPasses(m, w, p, n):
    invest = 0
    spend = math.ceil(n / m * w)
    candy = 0
    while candy < n:
        skip = (p - candy) // (m * w)
        if skip <= 1:
            mw = (candy // p) + m + w
            half = math.ceil(mw / 2)
            if m > w:
                m = max(m, half)
                w = mw - m
            else:
                w = max(w, half)
                m = mw - w
            candy %= p
            skip = 1
        candy += skip * m * w
        invest += skip
        spend = min(spend, invest + math.ceil((n - candy) / (m * w)))
        print(f"(invest:{invest}, spend:{spend})  skiped:{skip} candy:{candy} m:{m}, w:{w} ")
    return min(invest, spend)



if __name__ == '__main__':
    minimumPasses(1, 1, 6, 45)
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    first_multiple_input = input().rstrip().split()

    m = int(first_multiple_input[0])

    w = int(first_multiple_input[1])

    p = int(first_multiple_input[2])

    n = int(first_multiple_input[3])

    result = minimumPasses(m, w, p, n)

    fptr.write(str(result) + '\n')

    fptr.close()
