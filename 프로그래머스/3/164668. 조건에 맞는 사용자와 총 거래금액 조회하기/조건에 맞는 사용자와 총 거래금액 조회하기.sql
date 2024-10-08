SELECT UGU.USER_ID, UGU.NICKNAME, SUM(PRICE) AS TOTAL_SALES
FROM USED_GOODS_BOARD AS UGB
LEFT JOIN
USED_GOODS_USER AS UGU
ON UGB.WRITER_ID = UGU.USER_ID
GROUP BY UGB.WRITER_ID, UGB.STATUS
HAVING UGB.STATUS = 'DONE' AND TOTAL_SALES >= 700000
ORDER BY TOTAL_SALES;