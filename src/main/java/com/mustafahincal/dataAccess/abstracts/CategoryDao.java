package com.mustafahincal.dataAccess.abstracts;

import com.mustafahincal.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
