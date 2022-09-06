package com.mustafahincal.business.abstracts;

import com.mustafahincal.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
}
