package com.cycloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cycloop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("SELECT c FROM Category c Where c.name=:name")
	public Category findByName(@Param("name") String name);

	@Query("SELECT c FROM Category c Where c.name=:name AND c.parentCategory.name=:parentCategoryName")
	public Category findByNameAndParent(@Param("name") String name,
			@Param("parentCategoryName") String parentCategoryName);

}
