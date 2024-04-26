package com.cycloop.service;

import java.util.List;

import com.cycloop.Exception.ProductException;
import com.cycloop.model.Review;
import com.cycloop.model.User;
import com.cycloop.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user) throws ProductException;

	public List<Review> getAllReview(Long ProductId);
}
