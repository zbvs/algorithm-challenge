#!/bin/python3

import math
import os
import random
import re
import sys
import bisect


# Complete the countTriplets function below.
def countTriplets(arr, r):
    num_map = {}

    def get_count(first_index, num, r):
        count = 0
        second = num * r
        if second not in num_map:
            return 0
        if r == 1:
            first_index += 1
        second_index_list = num_map[second]
        second_index = bisect.bisect_left(second_index_list, first_index)
        if r == 1:
            second_index += 1
        third = second * r
        if third not in num_map:
            return 0
        for i in range(second_index, len(second_index_list)):
            third_index = bisect.bisect_left(num_map[third], second_index_list[i])
            count += (len(num_map[third]) - third_index)

        return count

    for i in range(0, len(arr)):
        num = arr[i]
        if num not in num_map:
            num_map[num] = [i]
        else:
            num_map[num].append(i)

    result = 0
    for i in range(0, len(arr)):
        num = arr[i]
        result += get_count(i, num, r)
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
