package com.cycloop.service;

import java.util.List;

import com.cycloop.Exception.ProductException;
import com.cycloop.model.Rating;
import com.cycloop.model.User;
import com.cycloop.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req, User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long ProductId);

}
