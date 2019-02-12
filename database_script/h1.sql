SELECT   category.id, category.parent_id, catname
    FROM      category
           JOIN
              (    SELECT   CONNECT_BY_ROOT id AS parent_id, id
                     FROM   category
                    WHERE   CONNECT_BY_ISLEAF = 1 AND id = 1121
               CONNECT BY   PRIOR id = parent_id) t1
           ON t1.parent_id = category.id
ORDER BY   parent_id