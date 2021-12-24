#!/bin/python3

"""
[Hackerrank] : String ManipulationSpecial String Again

A string is said to be a special string if either of two conditions is met:

All of the characters are the same, e.g. aaa.
All characters except the middle one are the same, e.g. aadaa.
A special substring is any substring of a string which meets one of those criteria. Given a string, determine how many special substrings can be formed from it.
"""

import math
import os
import random
import re
import sys


# Complete the substrCount function below.
def substrCount(n, s):
    cnt = 1
    for i in range(1, n):
        cnt += 1

        for j in range(1, i + 1):
            if s[i] == s[i - j]:
                cnt += 1
            else:
                break

        if s[i] != s[i - 1]:
            l = min(i, n - 1 - i)
            ch = s[i - 1]
            for j in range(1, l + 1):
                if s[i - j] == ch and s[i + j] == ch:
                    cnt += 1
                else:
                    break

    return cnt


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    s = input()

    result = substrCount(n, s)

    fptr.write(str(result) + '\n')

    fptr.close()
