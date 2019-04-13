package org.springapp.repository;

import org.springapp.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAll();

    Category findByIdEquals(Integer id);

    List<Category> findByParentIdEquals(Integer id);

    Page<Category> findByParentIdEquals(Integer i, Pageable pageable);

    @Query(value = "select categories.id, categories.parent_id, catname from (select connect_by_root id as parent_id, id from categories where id = :catid connect by nocycle prior id = parent_id) t1 join categories on t1.parent_id = categories.id where categories.is_root=0 order by parent_id",
            nativeQuery = true)
    List<Category> findCatPathById(@Param("catid") Integer catId);

    @Query(value = "select id, parent_id, catname from categories where level = :level connect by prior id = parent_id start with id = -1",
            nativeQuery = true)
    List<Category> findCatnameByLevel(@Param("level") Integer level);

}
