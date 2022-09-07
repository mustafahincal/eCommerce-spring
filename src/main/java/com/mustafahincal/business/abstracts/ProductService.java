package com.mustafahincal.business.abstracts;

import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.core.utilities.results.Result;
import com.mustafahincal.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    DataResult<List<Product>> getAll();

    Result add(Product product);

    Result delete(Product product);

    Result update(Product product);

}
