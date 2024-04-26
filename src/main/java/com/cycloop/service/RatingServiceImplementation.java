package com.cycloop.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cycloop.Exception.ProductException;
import com.cycloop.Repository.RatingRepository;
import com.cycloop.model.Product;
import com.cycloop.model.Rating;
import com.cycloop.model.User;
import com.cycloop.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService {

	private RatingRepository ratingRepository;
	private ProductService productService;

	public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
		this.ratingRepository = ratingRepository;
		this.productService = productService;
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		Rating rating = new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long ProductId) {

		return ratingRepository.getAllProductsRating(ProductId);
	}

}
