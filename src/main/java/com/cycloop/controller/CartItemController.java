package com.cycloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cycloop.Exception.CartItemException;
import com.cycloop.Exception.ProductException;
import com.cycloop.Exception.UserException;
import com.cycloop.model.CartItem;
import com.cycloop.model.User;
import com.cycloop.request.AddItemRequest;
import com.cycloop.response.ApiResponse;
import com.cycloop.service.CartItemService;
import com.cycloop.service.CartService;
import com.cycloop.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

//	@io.swagger.v3.oas.annotations.responses.ApiResponse(description="Delete Item")
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
			@RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
		User user = userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);

		ApiResponse res = new ApiResponse();
		res.setMessage("Deleted item from cart");
		res.setStatus(true);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PutMapping("/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException {
		User user = userService.findUserProfileByJwt(jwt);
		cartService.addCartItem(user.getId(), req);
		ApiResponse res = new ApiResponse();
		res.setMessage("Item added to cart");
		res.setStatus(true);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId,
			@RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
		User user = userService.findUserProfileByJwt(jwt);
		CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
		System.out.println("updated product");
		return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
	}
}
