
"""
2021 KAKAO BLIND RECRUITMENT : 괄호 변환

카카오에 신입 개발자로 입사한 "콘"은 선배 개발자로부터 개발역량 강화를 위해 다른 개발자가 작성한 소스 코드를 분석하여 문제점을 발견하고 수정하라는 업무 과제를 받았습니다. 소스를 컴파일하여 로그를 보니 대부분 소스 코드 내 작성된 괄호가 개수는 맞지만 짝이 맞지 않은 형태로 작성되어 오류가 나는 것을 알게 되었습니다.
수정해야 할 소스 파일이 너무 많아서 고민하던 "콘"은 소스 코드에 작성된 모든 괄호를 뽑아서 올바른 순서대로 배치된 괄호 문자열을 알려주는 프로그램을 다음과 같이 개발하려고 합니다.
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


def solution(p):
    if 'STOP' in globals() and STOP: return

    def split(m):
        stack = 0
        for i in range(0, len(m)):
            c = m[i]
            if c == "(":
                stack += 1
            else:
                stack -= 1
            if stack == 0:
                return m[:i + 1], m[i + 1:]
        return m, ""

    def check_right(m):
        stack = 0
        for c in m:
            if c == "(":
                stack += 1
            else:
                if stack == 0: return False
                stack -= 1
        return stack == 0

    def flip(m):
        return m.replace("(", "#").replace(")", "(").replace("#", ")")

    def recur(m):
        if m == "": return ""
        u, v = split(m)
        if check_right(u):
            return u + recur(v)
        else:
            return "(" + recur(v) + ")" + flip(u[1:-1])

    return recur(p)

test("()))((()")

