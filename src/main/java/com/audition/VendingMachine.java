package com.audition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audition.product.Product;

public abstract class VendingMachine {

	private BigDecimal currentAmount;

	private Map<Integer, Integer> inventory = new HashMap<Integer, Integer>();

	private Product selectedProduct;
	private String displayText = "INSERT COINS";

	private List<Product> productList = new ArrayList<Product>();

	public abstract BigDecimal calculateChange();

	public abstract BigDecimal insertCoin(int diamter);

	public abstract List<Coins> returnCoin();

	public abstract Product selectProduct(int productID);

	public void addProductInventory(int productID) {
		if (inventory.containsKey(productID)) {
			int count = inventory.get(productID);
			count++;

			inventory.put(productID, count);
		} else {
			inventory.put(productID, 1);
		}

	}

	public void removeProductInventory(int productID) {
		if (inventory.containsKey(productID)) {
			int count = inventory.get(productID);
			if (count > 0) {
				count--;
			}

			inventory.put(productID, count);
		} else {
			inventory.put(productID, 0);
		}

	}

	public void addProduct(Product product) {
		if (!productList.contains(product)) {
			productList.add(product);
		}

	}

	public void removeProduct(Product product) {
		if (productList.contains(product)) {
			productList.remove(product);
		}
	}

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Map<Integer, Integer> getInventory() {
		return Collections.unmodifiableMap(inventory);
	}

	public void setInventory(Map<Integer, Integer> inventory) {
		this.inventory = inventory;
	}

	public List<Product> getProductList() {
		return Collections.unmodifiableList(productList);
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

}
