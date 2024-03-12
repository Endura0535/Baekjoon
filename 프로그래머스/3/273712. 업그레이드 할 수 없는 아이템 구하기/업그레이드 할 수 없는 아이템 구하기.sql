SELECT ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO
WHERE ITEM_ID IN
(
    SELECT ITEM_ID
    FROM ITEM_TREE
    WHERE ITEM_ID NOT IN
    (
        SELECT IFNULL(PARENT_ITEM_ID, -1)
        FROM ITEM_TREE
    )
)
ORDER BY ITEM_ID DESC
;