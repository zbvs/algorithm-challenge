"""
[2021 KAKAO BLIND RECRUITMENT] 순위 검색


[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

카카오는 하반기 경력 개발자 공개채용을 진행 중에 있으며 현재 지원서 접수와 코딩테스트가 종료되었습니다. 이번 채용에서 지원자는 지원서 작성 시 아래와 같이 4가지 항목을 반드시 선택하도록 하였습니다.

코딩테스트 참여 개발언어 항목에 cpp, java, python 중 하나를 선택해야 합니다.
지원 직군 항목에 backend와 frontend 중 하나를 선택해야 합니다.
지원 경력구분 항목에 junior와 senior 중 하나를 선택해야 합니다.
선호하는 소울푸드로 chicken과 pizza 중 하나를 선택해야 합니다.
"""

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


import re
import bisect


def solution(infos, querys):
    if 'STOP' in globals() and STOP: return
    total_id_set = set()

    maps = [{} for _ in range(0, 4)]
    id_map = {}

    for id in range(0, len(infos)):
        info = infos[id]
        total_id_set.add(id)
        group = re.search("([^ ]+) ([^ ]+) ([^ ]+) ([^ ]+) ([^ ]+)", info).groups()
        for i in range(0, 4):
            key = group[i]
            data_map = maps[i]
            if key not in data_map:
                data_map[key] = set()
            data_map[key].add(id)

        score = int(group[4])
        id_map[id] = score

    cache = {}
    result_arr = []
    for query in querys:
        print("\n\nquery:", query)
        groups = re.search("([^ ]+) and ([^ ]+) and ([^ ]+) and ([^ ]+) ([^ ]+)", query).groups()

        compound_key = " ".join(groups[:4])
        if compound_key in cache:
            sorted_list = cache[compound_key]
        else:
            id_set = set()
            initialized = False

            for i in range(0, 4):
                key = groups[i]
                if key == "-": continue
                data_map = maps[i]
                if not initialized:
                    id_set.update(data_map[key])
                    initialized = True
                else:
                    id_set = id_set.intersection(data_map[key])
            if initialized:
                sorted_list = sorted(map(lambda id: id_map[id], id_set))
            else:
                sorted_list = sorted(id_map.values())
            cache[compound_key] = sorted_list
        score = int(groups[4])

        index = bisect.bisect_left(sorted_list, score)
        result_arr.append(len(sorted_list) - index)
        print(result_arr)

    return result_arr


test(["java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150",
      "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"],
     ["java and backend and junior and pizza 100", "python and frontend and senior and chicken 200",
      "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100",
      "- and - and - and - 150"]
     )