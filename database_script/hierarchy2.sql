SELECT id, parent_id, catname, level, 
 CONNECT_BY_ISLEAF as IsLeaf, 
 PRIOR catname as Parent, 
 CONNECT_BY_ROOT catname as Root
FROM category
START WITH parent_id = 0
CONNECT BY PRIOR id = parent_id
ORDER SIBLINGS BY catname;