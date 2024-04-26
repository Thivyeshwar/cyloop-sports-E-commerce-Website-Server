package com.cycloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cycloop.Exception.CartItemException;
import com.cycloop.Exception.ProductException;
import com.cycloop.Exception.UserException;
import com.cycloop.model.Cart;
import com.cycloop.model.CartItem;
import com.cycloop.model.User;
import com.cycloop.request.AddItemRequest;
import com.cycloop.response.ApiResponse;
import com.cycloop.service.CartItemService;
import com.cycloop.service.CartService;
import com.cycloop.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException {
		User user = userService.findUserProfileByJwt(jwt);
		Cart cart = cartService.findUserCart(user.getId());
		System.out.println(cart);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@PutMapping("/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException {
		User user = userService.findUserProfileByJwt(jwt);
		cartService.addCartItem(user.getId(), req);
		System.out.println("Request Parameters are " + req.getQuantity() + "" + req.getSize() + "" + req.getPrice() + ""
				+ req.getProductId());
		ApiResponse res = new ApiResponse();
		res.setMessage("item added to cart");
		res.setStatus(true);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}


}
