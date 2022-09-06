package com.mustafahincal.dataAccess.abstracts;

import com.mustafahincal.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
