"""
2020 KAKAO BLIND RECRUITMENT : 자물쇠와 열쇠


고고학자인 "튜브"는 고대 유적지에서 보물과 유적이 가득할 것으로 추정되는 비밀의 문을 발견하였습니다. 그런데 문을 열려고 살펴보니 특이한 형태의 자물쇠로 잠겨 있었고 문 앞에는 특이한 형태의 열쇠와 함께 자물쇠를 푸는 방법에 대해 다음과 같이 설명해 주는 종이가 발견되었습니다.

잠겨있는 자물쇠는 격자 한 칸의 크기가 1 x 1인 N x N 크기의 정사각 격자 형태이고 특이한 모양의 열쇠는 M x M 크기인 정사각 격자 형태로 되어 있습니다.

자물쇠에는 홈이 파여 있고 열쇠 또한 홈과 돌기 부분이 있습니다. 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조입니다. 자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 자물쇠를 여는 데 영향을 주지 않지만, 자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하며 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됩니다. 또한 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.

열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때, 열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false를 return 하도록 solution 함수를 완성해주세요.
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


######################################################################################
######################################################################################

def check_one(key, lock, key_x, key_y, lock_x, lock_y, ovsize_x, ovsize_y, hole):
    for i in range(0, ovsize_y):
        for j in range(0, ovsize_x):
            if key[key_y + i][key_x + j] == lock[lock_y + i][lock_x +j]:
                return False
            if lock[lock_y + i][lock_x + j] == 0:
                hole -= 1
    return hole == 0


def check(key, lock, hole):
    size = len(key) + len(lock) - 1
    lock_y = len(key) - 1
    lock_x = len(key) - 1
    for key_y in range(0, size):
        for key_x in range(0, size):
            ovlap_y = max(key_y, lock_y)
            ovsize_y = min(key_y + len(key) - ovlap_y, lock_y + len(lock) - ovlap_y)

            ovlap_x = max(key_x, lock_x)
            ovsize_x = min(key_x + len(key) - ovlap_x, lock_x + len(lock) - ovlap_x)

            if check_one(key, lock, ovlap_x - key_x, ovlap_y - key_y,
                         ovlap_x - lock_x, ovlap_y - lock_y, ovsize_x, ovsize_y, hole):
                return True
    return False

def lotate(key):
    new_key = [[0 for _ in range(0, len(key))] for _ in range(0, len(key))]
    for y in range(0, len(key)):
        for x in range(0, len(key)):
            new_key[len(key) - y - 1][x] = key[x][y]
    return new_key

def solution(key, lock):
    if 'STOP' in globals() and STOP: return
    hole = 0
    for i in range(0, len(lock)):
        for j in range(0, len(lock)):
            if lock[i][j] == 0:
                hole += 1

    for _ in range(0, 4):
        if check(key, lock, hole):
            return True
        key = lotate(key)
    return False

test([[0, 0, 0], [1, 0, 0], [0, 1, 1]], [[1, 1, 1], [1, 1, 0], [1, 0, 1]])
