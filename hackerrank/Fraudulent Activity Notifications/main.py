"""
[Hackerrank] Sorting : Fraudulent Activity Notifications

HackerLand National Bank has a simple policy for warning clients about possible fraudulent account activity. If the amount spent by a client on a particular day is greater than or equal to 2 ×the client's median spending for the last d days, they send the client a notification about potential fraud. The bank doesn't send the client any notifications until they have at least d prior days of transaction data.

Given the value of d and a client's total daily expenditures for a period of n days 
"""
#!/bin/python3

import math
import os
import random
import re
import sys
import bisect
#
# Complete the 'activityNotifications' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER_ARRAY expenditure
#  2. INTEGER d
#
from collections import deque
import heapq
import bisect


def activityNotifications(expenditure, d):
    queue = deque([])
    sorted_list = []
    cnt = 0
    for exp in expenditure:
        
        if len(queue) == d:
            
            if d % 2 == 1:
                median = sorted_list[d // 2]
            else:
                median = (sorted_list[d//2 - 1] + sorted_list[d//2])/2
            if exp >= median*2:
                cnt += 1
            value = queue.popleft()
            sorted_list.pop(bisect.bisect_left(sorted_list, value))
            queue.append(exp)
            bisect.insort_left(sorted_list, exp)
        else:
            queue.append(exp)
            bisect.insort_left(sorted_list, exp)
    return cnt

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    first_multiple_input = input().rstrip().split()

    n = int(first_multiple_input[0])

    d = int(first_multiple_input[1])

    expenditure = list(map(int, input().rstrip().split()))

    result = activityNotifications(expenditure, d)

    fptr.write(str(result) + '\n')

    fptr.close()
