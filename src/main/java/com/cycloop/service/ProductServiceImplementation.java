package com.cycloop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cycloop.Exception.ProductException;
import com.cycloop.Repository.CategoryRepository;
import com.cycloop.Repository.ProductRepository;
import com.cycloop.model.Category;
import com.cycloop.model.Product;
import com.cycloop.request.CreateProductRequest;

@Service
public class ProductServiceImplementation implements ProductService {

	private ProductRepository productRepository;
	private UserService userService;
	private CategoryRepository categoryRepository;

	public ProductServiceImplementation(ProductRepository productRepository, UserService userService,
			CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Product createProduct(CreateProductRequest req) {
		System.out.println(req.getImageUrl() + "\n" + req.getBrand() + "\n" + req.getTitle() + "\n" + req.getColor()
				+ "\n" + req.getQuantity() + "\n" + req.getPrice() + "\n" + req.getDiscountedPrice() + "\n"
				+ req.getDiscountPercent() + "\n" + req.getToplevelcategory() + "\n" + req.getSecondlevelcategory()
				+ "\n" + req.getThirdlevelcategory() + "\n" + req.getDescription() + "\n" + req.getSize());
		Category topLevel = categoryRepository.findByName(req.getToplevelcategory());
		System.out.println("TopLevel" + topLevel);
		if (topLevel == null) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(req.getToplevelcategory());
			topLevelCategory.setLevel(1);
			System.out.println("topLevel" + topLevelCategory.getName() + " " + topLevelCategory.getLevel());
			topLevel = categoryRepository.save(topLevelCategory);
		}

		Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondlevelcategory(), topLevel.getName());
		if (secondLevel == null) {
			Category secondLevelCategory = new Category();
			secondLevelCategory.setName(req.getSecondlevelcategory());
			secondLevelCategory.setParentCategory(topLevel);
			secondLevelCategory.setLevel(2);

			secondLevel = categoryRepository.save(secondLevelCategory);
		}

		Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdlevelcategory(),
				secondLevel.getName());
		if (thirdLevel == null) {
			Category thirdLevelCategory = new Category();
			thirdLevelCategory.setName(req.getThirdlevelcategory());
			thirdLevelCategory.setParentCategory(secondLevel);
			thirdLevelCategory.setLevel(3);

			thirdLevel = categoryRepository.save(thirdLevelCategory);
		}

		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDiscountPercent(req.getDiscountPercent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSize());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());

		Product savedProduct = productRepository.save(product);

		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product = findProductById(productId);
		product.getSizes().clear();
		productRepository.delete(product);

		return "Product deleted successfully";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {
		Product product = findProductById(productId);

		if (req.getQuantity() != 0) {
			product.setQuantity(req.getQuantity());
		}
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long productId) throws ProductException {
		Optional<Product> opt = productRepository.findById(productId);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new ProductException("Product not found with id - " + productId);
	}

	@Override
	public List<Product> findProductByCategory(String category) throws ProductException {
		List<Product> products = productRepository.findByCategory(category);
		if (!products.isEmpty()) {
			return products;
		}
		throw new ProductException("Products are not present for category - " + category);
	}

	@Override
	public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		if (!colors.isEmpty()) {
			products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
					.collect(Collectors.toList());
		}

		if (stock != null) {
			if (stock.equals("in_stock")) {
				products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());

			} else if (stock.equals("out_of_stock")) {
				products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
			}
		}

		int startIndex = (int) pageable.getOffset();
		int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

		List<Product> pageContent = products.subList(startIndex, endIndex);
		Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

		return filteredProducts;
	}

}
