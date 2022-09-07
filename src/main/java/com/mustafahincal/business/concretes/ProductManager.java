package com.mustafahincal.business.concretes;

import com.mustafahincal.business.abstracts.ProductService;
import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.core.utilities.results.Result;
import com.mustafahincal.core.utilities.results.SuccessDataResult;
import com.mustafahincal.core.utilities.results.SuccessResult;
import com.mustafahincal.dataAccess.abstracts.ProductDao;
import com.mustafahincal.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {
    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public DataResult<List<Product>> getAll() {
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(), "Data Listelendi");
    }

    @Override
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessResult("Ürün eklendi");
    }

    @Override
    public Result delete(Product product) {
        this.productDao.delete(product);
        return new SuccessResult("Ürün silindi");
    }

    @Override
    public Result update(Product product) {
        return null;
    }
}
