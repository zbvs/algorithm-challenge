
/*
GROUP BY : 입양 시각 구하기(2)

ANIMAL_OUTS 테이블은 동물 보호소에서 입양 보낸 동물의 정보를 담은 테이블입니다. ANIMAL_OUTS 테이블 구조는 다음과 같으며, ANIMAL_ID, ANIMAL_TYPE, DATETIME, NAME, SEX_UPON_OUTCOME는 각각 동물의 아이디, 생물 종, 입양일, 이름, 성별 및 중성화 여부를 나타냅니다.
*/

-- 코드를 입력하세요
WITH RECURSIVE CTE(H) AS (
    SELECT  0
    UNION
    SELECT H +1 FROM CTE WHERE H < 23
)

SELECT CTE.H, count(ANIMAL.animal_id)
FROM CTE
 LEFT OUTER JOIN 
(SELECT datetime, animal_id FROM ANIMAL_OUTS ) AS ANIMAL
 ON HOUR(ANIMAL.datetime) = CTE.H
 GROUP BY CTE.H
 