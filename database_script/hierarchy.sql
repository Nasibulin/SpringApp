select t1.id, catname from category join (
select connect_by_root id as id, id as root_parent_id
from       category
where      connect_by_isleaf = 1 and id=1121
connect by parent_id = prior id
order by   id) t1 on t1.id=category.id