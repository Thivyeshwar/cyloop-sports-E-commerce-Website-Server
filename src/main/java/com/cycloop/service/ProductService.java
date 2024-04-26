package com.cycloop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cycloop.Exception.ProductException;
import com.cycloop.model.Product;
import com.cycloop.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req);

	public String deleteProduct(Long productId) throws ProductException;

	public Product updateProduct(Long productId, Product req) throws ProductException;

	public Product findProductById(Long productId) throws ProductException;

	public List<Product> findProductByCategory(String category)throws ProductException;

	public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

}
