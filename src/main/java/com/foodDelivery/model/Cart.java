package com.foodDelivery.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	//the cart items, stored as a map of item IDs to cartItem objects
	private Map<Integer, CartItem> items;

	public Cart() {
		this.items = new HashMap();
	}
	
	
	//Add an item to the cart
	public void addItem(CartItem newItem) {
		int itemId = newItem.getItemId();
		if(items.containsKey(itemId)) {
			//If item already exists, increase the quantity
			CartItem existingItem = items.get(itemId);
			existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
			
		}
		else {
			//If item is new add to cart
			items.put(itemId, newItem);
		}
	}
	
	
	//Update the quantity of an Item in the cart
	public void updateItem(int itemId, int quantity) {
		if(items.containsKey(itemId)) {
			if(quantity <= 0) {
				items.remove(itemId);
			}
			else {
				items.get(itemId).setQuantity(quantity);
			}
		}
	}
	
	
	//Remove an item from the cart
	public void removeItem(int itemId) {
		items.remove(itemId);
	}
	
	//get all items in the cart
	public Map<Integer, CartItem> getItems() {
		return items;
	}
	
	
	//Clear the cart
	public void clear() {
		items.clear();
	}
}
