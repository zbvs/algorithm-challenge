"""
[Hackerrank] Dictionaries and HashmapsCount : Triplets
You are given an array and you need to find number of tripets of indices  such that the elements at those indices are in geometric progression for a given common ratio  and .
"""
#!/bin/python3

import math
import os
import random
import re
import sys
import bisect
from collections import Counter

def countTriplets(arr, r):
    f_map = Counter()
    s_map = Counter()
    t_map = Counter()
    for value in arr:
        if value % r == 0:
            divided = value//r
            if divided in s_map:
                t_map[value] += s_map[divided]
            if divided in f_map:
                s_map[value] += f_map[divided]
        f_map[value] += 1
    result = 0
    
    for v in t_map.values():
        result += v
    return result

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nr = input().rstrip().split()

    n = int(nr[0])

    r = int(nr[1])

    arr = list(map(int, input().rstrip().split()))

    ans = countTriplets(arr, r)

    fptr.write(str(ans) + '\n')

    fptr.close()
