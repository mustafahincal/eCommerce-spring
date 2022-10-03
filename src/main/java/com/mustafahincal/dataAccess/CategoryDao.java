package com.mustafahincal.dataAccess;

import com.mustafahincal.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
