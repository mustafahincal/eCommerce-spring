package com.mustafahincal.dataAccess;

import com.mustafahincal.dtos.ProductWithCategoryDto;
import com.mustafahincal.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    Product getByProductName(String productName);

    Product getById(int productId);

    Product getByProductNameAndCategory_Id(String productName, int categoryId);

    List<Product> getByProductNameOrCategory_Id(String productName, int categoryId);

    List<Product> getByCategory_IdIn(List<Integer> categories);

    List<Product> getByProductNameContains(String productName);

    List<Product> getByProductNameStartsWith(String productName);

    @Query("From Product where productName=:productName and category.id=:categoryId")
    List<Product> getByNameAndCategory(String productName, int categoryId);

    // select * from products where product_name = abc  and category_id = abc
    @Query("select new com.mustafahincal.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) From Category c Inner Join c.products p")
    List<ProductWithCategoryDto> getProductWithCategoryDetails();
    // select p.productId, p.productName, c.categoryName from Category c inner joim Product p on c.categoryId = p.categoryId
}
