package com.mustafahincal.business.abstracts;

import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.entities.Category;

import java.util.List;

public interface CategoryService {
    DataResult<List<Category>> getAll();
}
