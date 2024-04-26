package com.cycloop.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cycloop.Exception.ProductException;
import com.cycloop.Repository.ProductRepository;
import com.cycloop.Repository.ReviewRepository;
import com.cycloop.model.Product;
import com.cycloop.model.Review;
import com.cycloop.model.User;
import com.cycloop.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {

	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;

	public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductService productService,
			ProductRepository productRepository) {
		this.reviewRepository = reviewRepository;
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());

		Review review = new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());

		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long ProductId) {
		// TODO Auto-generated method stub
		return reviewRepository.getAllProductsReviews(ProductId);
	}

}
