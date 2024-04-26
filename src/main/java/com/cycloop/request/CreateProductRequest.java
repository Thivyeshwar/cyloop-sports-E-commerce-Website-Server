package com.cycloop.request;

import java.util.HashSet;
import java.util.Set;

import com.cycloop.model.Size;

public class CreateProductRequest {

	private String title;
	private String description;
	private int price;

	private int discountedPrice;

	private int discountPercent;

	private int quantity;

	private String brand;

	private String color;
	private Set<Size> size = new HashSet<>();

	private String imageUrl;

	private String toplevelcategory;

	private String secondlevelcategory;

	private String thirdlevelcategory;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set<Size> getSize() {
		return size;
	}

	public void setSize(Set<Size> size) {
		this.size = size;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getToplevelcategory() {
		return toplevelcategory;
	}

	public void setToplevelcategory(String toplevelcategory) {
		this.toplevelcategory = toplevelcategory;
	}

	public String getSecondlevelcategory() {
		return secondlevelcategory;
	}

	public void setSecondlevelcategory(String secondlevelcategory) {
		this.secondlevelcategory = secondlevelcategory;
	}

	public String getThirdlevelcategory() {
		return thirdlevelcategory;
	}

	public void setThirdlevelcategory(String thirdlevelcategory) {
		this.thirdlevelcategory = thirdlevelcategory;
	}

}
