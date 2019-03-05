WITH    got_path    AS
(
    SELECT  SYS_CONNECT_BY_PATH (id, '/')  AS path
    ,       id                             AS ancestor, catname
    FROM   category
   WHERE   LEVEL  > 1
    CONNECT BY   id  =  PRIOR parent_id
)
SELECT    SUBSTR ( path
                 , 2
                 , INSTR (path, '/', 2) - 2
                 )    AS descendant, catname
,         ancestor
FROM      got_path
ORDER BY  descendant  DESC,
ancestor    DESC