
"""
2021 KAKAO BLIND RECRUITMENT : 광고 삽입

카카오TV에서 유명한 크리에이터로 활동 중인 죠르디는 환경 단체로부터 자신의 가장 인기있는 동영상에 지구온난화의 심각성을 알리기 위한 공익광고를 넣어 달라는 요청을 받았습니다. 평소에 환경 문제에 관심을 가지고 있던 "죠르디"는 요청을 받아들였고 광고효과를 높이기 위해 시청자들이 가장 많이 보는 구간에 공익광고를 넣으려고 합니다. "죠르디"는 시청자들이 해당 동영상의 어떤 구간을 재생했는 지 알 수 있는 재생구간 기록을 구했고, 해당 기록을 바탕으로 공익광고가 삽입될 최적의 위치를 고를 수 있었습니다.
참고로 광고는 재생 중인 동영상의 오른쪽 아래에서 원래 영상과 동시에 재생되는 PIP(Picture in Picture) 형태로 제공됩니다.
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


def parse_time(play_time):
    times = play_time.split(":")
    return int(times[0]) * 60 * 60 + int(times[1]) * 60 + int(times[2])


def solution(play_time, adv_time, logs):
    play_time = parse_time(play_time)
    adv_time = parse_time(adv_time)

    start_map = {}
    end_map = {}
    for log in logs:
        pair = log.split("-")
        start = parse_time(pair[0])
        end = parse_time(pair[1])
        if start not in start_map:
            start_map[start] = 1
        else:
            start_map[start] += 1

        if end not in end_map:
            end_map[end] = 1
        else:
            end_map[end] += 1

    max_time = 0
    inc = 0
    next_total_time = 0
    result_next = 0

    for head in range(0, play_time):
        if head in start_map:  # enter window
            inc += start_map[head]
        if head in end_map:  # shrink inc
            inc -= end_map[head]

        if head >= adv_time:

            tail = head - adv_time
            if tail in start_map:  # leave window
                inc -= start_map[tail]
            if tail in end_map:  # revert shrinked inc
                inc += end_map[tail]

        next_total_time += inc
        if next_total_time > max_time:
            max_time = next_total_time
            result_next = head + 1

    if result_next < adv_time:
        return "00:00:00"
    stamp = result_next - adv_time
    return f"{(stamp // 3600):02d}:{(stamp % 3600 // 60):02d}:{(stamp % 60):02d}"


_print(test("50:00:00", "50:00:00", ["15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"]))
