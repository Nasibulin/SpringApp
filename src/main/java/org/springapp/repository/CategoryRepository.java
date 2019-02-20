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

    List<Category> findByParentIdEqualsAndIdLessThan(Integer i, Integer j);

    List<Category> findByIdEquals(Integer id);

    List<Category> findByParentIdEquals(Integer id);

    Page<Category> findByParentIdEquals(Integer i, Pageable pageable);

    @Query(value = "SELECT categories.id, categories.parent_id, catname FROM (SELECT CONNECT_BY_ROOT id AS parent_id, id FROM categories WHERE CONNECT_BY_ISLEAF = 1 AND id = :catid CONNECT BY   NOCYCLE PRIOR id = parent_id) t1 JOIN categories ON t1.parent_id = categories.id WHERE categories.id > -50 ORDER BY parent_id",
           nativeQuery = true)
    List<Category> findCatPathById(@Param("catid") Integer catId);

    @Query(value = "SELECT id, parent_id, catname FROM categories WHERE LEVEL = :level CONNECT BY PRIOR id = parent_id START WITH   id = -1",
           nativeQuery = true)
    List<Category> findCatnameByLevel(@Param("level") Integer level);

}
