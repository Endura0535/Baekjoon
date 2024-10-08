WITH ADOPTION AS (
    SELECT AI.ANIMAL_ID, AI.NAME, TIMESTAMPDIFF(SECOND, AI.DATETIME, AO.DATETIME) AS PROTECTION_PERIOD
    FROM ANIMAL_INS AI
    INNER JOIN ANIMAL_OUTS AO
    ON AI.ANIMAL_ID = AO.ANIMAL_ID
)
SELECT ANIMAL_ID, NAME
FROM ADOPTION
ORDER BY PROTECTION_PERIOD DESC
LIMIT 2;