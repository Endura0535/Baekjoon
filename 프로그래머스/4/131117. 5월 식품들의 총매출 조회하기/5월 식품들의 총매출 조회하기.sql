SELECT FP.PRODUCT_ID, FP.PRODUCT_NAME, SUM(FP.PRICE * FO.AMOUNT) AS TOTAL_SALES
FROM FOOD_ORDER AS FO
LEFT JOIN FOOD_PRODUCT AS FP ON FO.PRODUCT_ID = FP.PRODUCT_ID
WHERE YEAR(FO.PRODUCE_DATE) = 2022 AND MONTH(FO.PRODUCE_DATE) = 5
GROUP BY FP.PRODUCT_ID, FP.PRODUCT_NAME
ORDER BY TOTAL_SALES DESC, FP.PRODUCT_ID
;