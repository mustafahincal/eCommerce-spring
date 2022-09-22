package com.mustafahincal.business.concretes;

import com.mustafahincal.business.abstracts.CategoryService;
import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.core.utilities.results.SuccessDataResult;
import com.mustafahincal.dataAccess.abstracts.CategoryDao;
import com.mustafahincal.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryManager(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public DataResult<List<Category>> getAll() {
        return new SuccessDataResult<List<Category>>(this.categoryDao.findAll(), "Kategoriler Getirildi");
    }
}
