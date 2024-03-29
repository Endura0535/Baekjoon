SELECT FLAVOR
FROM 
(
    (
    SELECT FH.FLAVOR, FH.TOTAL_ORDER
    FROM FIRST_HALF FH
    LEFT JOIN
    JULY AS J
    ON FH.FLAVOR = J.FLAVOR
)

UNION

(
    SELECT J.FLAVOR, J.TOTAL_ORDER
    FROM FIRST_HALF FH
    RIGHT JOIN
    JULY AS J
    ON FH.FLAVOR = J.FLAVOR
)

) AS COMBINED_RESULT

GROUP BY FLAVOR
ORDER BY SUM(TOTAL_ORDER) DESC
LIMIT 3;