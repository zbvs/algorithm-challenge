# -*- coding: utf-8 -*-
"""
[2020 KAKAO BLIND RECRUITMENT] Trie(트라이) : 가사 검색

[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

친구들로부터 천재 프로그래머로 불리는 "프로도"는 음악을 하는 친구로부터 자신이 좋아하는 노래 가사에 사용된 단어들 중에 특정 키워드가 몇 개 포함되어 있는지 궁금하니 프로그램으로 개발해 달라는 제안을 받았습니다.
그 제안 사항 중, 키워드는 와일드카드 문자중 하나인 '?'가 포함된 패턴 형태의 문자열을 뜻합니다. 와일드카드 문자인 '?'는 글자 하나를 의미하며, 어떤 문자에도 매치된다고 가정합니다. 예를 들어 "fro??"는 "frodo", "front", "frost" 등에 매치되지만 "frame", "frozen"에는 매치되지 않습니다.

가사에 사용된 모든 단어들이 담긴 배열 words와 찾고자 하는 키워드가 담긴 배열 queries가 주어질 때, 각 키워드 별로 매치된 단어가 몇 개인지 순서대로 배열에 담아 반환하도록 solution 함수를 완성해 주세요.
"""

import traceback

PRINT = True
STOP = False
_print = print

error_list = [StopIteration, StopAsyncIteration, ArithmeticError, AssertionError, AttributeError, BufferError, EOFError,
              ImportError, LookupError, MemoryError, NameError, OSError,
              ReferenceError, RuntimeError, SyntaxError, SystemError, TypeError, ValueError]


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

class Node:
    def __init__(self, ch=None):
        self.child = {}
        self.cnt = 0
        self.ch = "" if ch is None else ch

    def insert(self, word):
        parent = self
        stk = [parent]
        for i in range(0, len(word)):
            ch = word[i]
            if ch not in parent.child:
                chld = Node(ch)
                parent.child[ch] = chld
                parent = chld
                stk.append(parent)
                if i == len(word) - 1:
                    for node in stk:
                        node.cnt += 1
                    break
            else:
                if i == len(word) - 1:
                    # duplicated insert. ignore
                    break
                parent = parent.child[ch]
                stk.append(parent)

    def count(self, word):
        if word == "": return self.cnt
        node = self
        for i in range(0, len(word)):
            ch = word[i]
            if ch not in node.child:
                return 0
            else:
                node = node.child[ch]
                if i == len(word) - 1:
                    return node.cnt

    def __repr__(self):
        return f"{self.ch}:{self.cnt}"


def solution(words, queries):
    if 'STOP' in globals() and STOP: return
    size_map = {}
    for word in words:
        size = len(word)
        if size not in size_map:
            size_map[size] = (Node(), Node())
        size_map[size][0].insert(word)
        size_map[size][1].insert(word[::-1])

    result = []
    for query in queries:
        size = len(query)
        if size in size_map:
            roots = size_map[size]
            q = query.replace("?", "")
            if query.startswith("?"):
                result.append(roots[1].count(q[::-1]))
            else:
                result.append(roots[0].count(q))
        else:
            result.append(0)
    return result

