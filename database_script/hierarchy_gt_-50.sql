SELECT   category.id, category.parent_id, catname
    FROM      (    SELECT   CONNECT_BY_ROOT id AS parent_id, id
                     FROM   category
                    WHERE   CONNECT_BY_ISLEAF = 1 AND id = 1093
               CONNECT BY   NOCYCLE PRIOR id = parent_id) t1
           JOIN
              category
           ON t1.parent_id = category.id
   WHERE   category.id > -50
ORDER BY   parent_id