"""
Hackerrank : Interview Preparation KitArrays

It is New Year's Day and people are in line for the Wonderland rollercoaster ride. Each person wears a sticker indicating their initial position in the queue from  to . Any person can bribe the person directly in front of them to swap positions, but they still wear their original sticker. One person can bribe at most two others.

Determine the minimum number of bribes that took place to get to a given queue order. Print the number of bribes, or, if anyone has bribed more than two people, print Too chaotic.

Example


If person  bribes person , the queue will look like this: . Only  bribe is required. Print 1.


Person  had to bribe  people to get to the current position. Print Too chaotic.

Function Description

Complete the function minimumBribes in the editor below.

minimumBribes has the following parameter(s):

int q[n]: the positions of the people after all bribes
Returns

No value is returned. Print the minimum number of bribes necessary or Too chaotic if someone has bribed more than  people.
"""
#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'minimumBribes' function below.
#
# The function accepts INTEGER_ARRAY q as parameter.
#

def minimumBribes(q):
    # Write your code here
    cur = [i for i in range(0, len(q))]
    q = [i -1 for i in q]
    cnt = 0
    for i in range(0, len(q)):
        fr = q[i]
        cur_v = cur[i]
        for j in range(i, min(i+3,len(q))):
            if cur_v == fr:
                break
            if j == i+2:
                return "Too chaotic"
            next_v = cur[j+1]
            cur[j+1] = cur_v
            cur_v = next_v
            cnt += 1
        cur[i] = fr
    return cnt
            
        
    
if __name__ == '__main__':
    t = int(input().strip())

    for t_itr in range(t):
        n = int(input().strip())

        q = list(map(int, input().rstrip().split()))

        print(minimumBribes(q))
