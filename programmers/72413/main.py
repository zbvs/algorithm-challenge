import traceback

"""
[카카오 블라인드 2021] 그래프 : 합승 택시 요금
[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]
밤늦게 귀가할 때 안전을 위해 항상 택시를 이용하던 무지는 최근 야근이 잦아져 택시를 더 많이 이용하게 되어 택시비를 아낄 수 있는 방법을 고민하고 있습니다. "무지"는 자신이 택시를 이용할 때 동료인 어피치 역시 자신과 비슷한 방향으로 가는 택시를 종종 이용하는 것을 알게 되었습니다. "무지"는 "어피치"와 귀가 방향이 비슷하여 택시 합승을 적절히 이용하면 택시요금을 얼마나 아낄 수 있을 지 계산해 보고 "어피치"에게 합승을 제안해 보려고 합니다.
"""
def solution1(n, s, a, b, fares):
    inf = float("inf")
    min = inf
    arr = [[inf] * n for i in range(0, n)]
    for i in range(0, n):
        arr[i][i] = 0

    for fare in fares:
        arr[fare[0] - 1][fare[1] - 1] = fare[2]
        arr[fare[1] - 1][fare[0] - 1] = fare[2]

    for k in range(0, n):
        for i in range(0, n):
            for j in range(0, n):
                if arr[i][j] > arr[i][k] + arr[k][j]:
                    arr[i][j] = arr[i][k] + arr[k][j]

    for k in range(0, n):
        distance = arr[s - 1][k] + arr[k][a - 1] + arr[k][b - 1]
        if min > distance:
            min = distance

    return min


def solution(n, s, a, b, fares):
    pass


def test():
    try:
        solution1(6, 4, 6, 2,
                  [[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22],
                   [1, 6, 25]])
    except Exception as e:
        print(traceback.format_exc())
        print("start exception:", e)


test()
