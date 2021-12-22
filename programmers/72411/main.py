

/*
[2021 KAKAO BLIND RECRUITMENT] 메뉴 리뉴얼
레스토랑을 운영하던 스카피는 코로나19로 인한 불경기를 극복하고자 메뉴를 새로 구성하려고 고민하고 있습니다.
기존에는 단품으로만 제공하던 메뉴를 조합해서 코스요리 형태로 재구성해서 새로운 메뉴를 제공하기로 결정했습니다. 어떤 단품메뉴들을 조합해서 코스요리 메뉴로 구성하면 좋을 지 고민하던 "스카피"는 이전에 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성하기로 했습니다.
단, 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성하려고 합니다. 또한, 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함하기로 했습니다.

예를 들어, 손님 6명이 주문한 단품메뉴들의 조합이 다음과 같다면,
(각 손님은 단품메뉴를 2개 이상 주문해야 하며, 각 단품메뉴는 A ~ Z의 알파벳 대문자로 표기합니다.)
*/
PRINT = True
STOP = False
_print = print

import traceback

PRINT = True
STOP = False
_print = print


def print(*args):
    if PRINT: _print(*args)


def test(*args):
    global STOP, PRINT

    try:
        result = solution(*args)
    except Exception as e:
        print("======================")
        print("[*]Exception in solution:\n")
        print(traceback.format_exc())
        STOP = True
        result = e
    PRINT = False
    return result

def combination(opts, selected, num):
    if num == 0: return [selected]
    result = []
    for i in range(0, len(opts)):
        selected += opts[i]
        result.extend(combination(opts[i + 1:], selected, num - 1))
        selected = selected[:-1]
    return result


def solution(orders, course):
    if 'STOP' in globals() and STOP: return

    result = []
    for num in course:
        max = 2
        most_set = set()
        comb_map = {}
        for order in orders:
            order = "".join(sorted(order))
            for comb in combination(order, "", num):
                if comb in comb_map:
                    comb_map[comb] += 1
                else:
                    comb_map[comb] = 1
                if max < comb_map[comb]:
                    most_set = set()
                    most_set.add(comb)
                    max = comb_map[comb]
                elif max == comb_map[comb]:
                    most_set.add(comb)
        result.extend(most_set)
    return sorted(result)



import collections
import itertools
def solution__2(orders, course):
    result = []

    for course_size in course:
        order_combinations = []
        for order in orders:
            order_combinations += itertools.combinations(sorted(order), course_size)

        most_ordered = collections.Counter(order_combinations).most_common()
        result += [ k for k, v in most_ordered if v > 1 and v == most_ordered[0][1] ]

    return [ ''.join(v) for v in sorted(result) ]


test(["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"], [2,3,5])
