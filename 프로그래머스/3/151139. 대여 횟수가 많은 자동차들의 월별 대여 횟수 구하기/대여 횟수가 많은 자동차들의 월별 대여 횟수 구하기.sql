WITH FILTERED_CAR AS (SELECT DISTINCT CAR_ID, COUNT(*) AS RECORD_COUNT
     FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
     WHERE YEAR(START_DATE) = 2022 AND MONTH(START_DATE) >= 8
            AND YEAR(START_DATE) = 2022 AND MONTH(START_DATE) <= 10
     GROUP BY CAR_ID
     HAVING RECORD_COUNT >= 5)
SELECT 
    MONTH(CRCRH.START_DATE) AS MONTH, 
    CRCRH.CAR_ID, 
    COUNT(*) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY CRCRH
JOIN FILTERED_CAR FC
ON CRCRH.CAR_ID = FC.CAR_ID
WHERE YEAR(CRCRH.START_DATE) = 2022 AND MONTH(CRCRH.START_DATE) >= 8
        AND YEAR(CRCRH.START_DATE) = 2022 AND MONTH(CRCRH.START_DATE) <= 10
GROUP BY 
    MONTH(CRCRH.START_DATE), CRCRH.CAR_ID
ORDER BY 
    MONTH(CRCRH.START_DATE), CRCRH.CAR_ID DESC;