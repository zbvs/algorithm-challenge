
#!/bin/python3

"""
[hackerrank] Greedy Algorithms : Reverse Shuffle Merge
Given a string, A, we define some operations on the string as follows:

a.reverse(A) denotes the string obtained by reversing string A. Example: reverse("abc") = "cba"

b.shuffle(A) denotes any string that’s a permutation of string A. Example: shuffle("god") \in ['god', 'gdo', 'ogd', 'odg', 'dgo', 'dog']

c.merge(A1, A2) denotes any string that’s obtained by interspersing the two strings A1 & A2, maintaining the order of characters in both. For example, A1 = 'abc' & A2 = 'def', one possible result of merge(A1, A2) could be 'abcdef', another could be 'abdecf', another could be 'adbecf' and so on.

Given a string s such that s \in merge(reverse(A), shuffle(A)) for some string A, find the lexicographically smallest A.
"""

import math
import os
import random
import re
import sys

#
# Complete the 'reverseShuffleMerge' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#
from collections import Counter, defaultdict


def reverseShuffleMerge(s):
    remain = defaultdict(int)
    for ch in s:
        remain[ch] += 1

    stk = []
    need = {k: remain[k] // 2 for k in remain}
    used = defaultdict(int)

    for i in range(len(s) - 1, -1, -1):
        ch = s[i]
        if used[ch] < need[ch]:
            while len(stk) > 0 and stk[-1] > ch \
                    and need[stk[-1]] < used[stk[-1]] + remain[stk[-1]]:
                used[stk[-1]] -= 1
                stk.pop()
            used[ch] += 1
            stk.append(ch)
        remain[ch] -= 1
    return "".join(stk)


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = reverseShuffleMerge(s)

    fptr.write(result + '\n')

    fptr.close()
