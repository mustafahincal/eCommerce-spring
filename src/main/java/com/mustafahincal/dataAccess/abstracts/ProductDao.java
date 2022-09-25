package com.mustafahincal.dataAccess.abstracts;

import com.mustafahincal.entities.concretes.Product;
import com.mustafahincal.entities.dtos.ProductWithCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    Product getByProductName(String productName);

    Product getByProductId(int productId);

    Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);

    List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);

    List<Product> getByCategory_CategoryIdIn(List<Integer> categories);

    List<Product> getByProductNameContains(String productName);

    List<Product> getByProductNameStartsWith(String productName);

    @Query("From Product where productName=:productName and category.categoryId=:categoryId")
    List<Product> getByNameAndCategory(String productName, int categoryId);

    // select * from products where product_name = abc  and category_id = abc
    @Query("select new com.mustafahincal.entities.dtos.ProductWithCategoryDto(p.productId, p.productName, c.categoryName) From Category c Inner Join c.products p")
    List<ProductWithCategoryDto> getProductWithCategoryDetails();
    // select p.productId, p.productName, c.categoryName from Category c inner joim Product p on c.categoryId = p.categoryId
}
