package com.audition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.audition.product.Product;

public class MyVendingMachine extends VendingMachine {

	public MyVendingMachine() {
		super.setCurrentAmount(BigDecimal.ZERO);

		super.addProduct(new Product(1, "Cola", new BigDecimal("1")));
		super.addProduct(new Product(2, "Chips", new BigDecimal(".50")));
		super.addProduct(new Product(3, "Candy", new BigDecimal(".65")));

		super.addProductInventory(1);

		super.addProductInventory(2);
		super.addProductInventory(2);

		super.addProductInventory(3);
		super.addProductInventory(3);
		super.addProductInventory(3);
	}

	@Override
	public BigDecimal calculateChange() {
		BigDecimal newAmount = super.getCurrentAmount().subtract(super.getSelectedProduct().getPrice());
		return newAmount;
	}

	@Override
	public BigDecimal insertCoin(int diamter) {
		BigDecimal newAmount = null;
		BigDecimal amount = super.getCurrentAmount();
		if (diamter == Coins.penny.diameter) {
			newAmount = amount;
		} else if (diamter == Coins.nickel.diameter) {
			newAmount = amount.add(Coins.nickel.value);
		} else if (diamter == Coins.dime.diameter) {
			newAmount = amount.add(Coins.dime.value);
		} else if (diamter == Coins.quarter.diameter) {
			newAmount = amount.add(Coins.quarter.value);
		}
		super.setCurrentAmount(newAmount);
		return newAmount;

	}

	@Override
	public List<Coins> returnCoin() {
		List<Coins> coinList = new ArrayList<Coins>();
		BigDecimal returnAmount = calculateChange();
		BigDecimal returnAmountAfterEverything = null;

		BigDecimal numberOfQuarters = returnAmount.divide(Coins.quarter.value);
		coinList.addAll(addCoins(Coins.quarter, numberOfQuarters.intValue()));

		BigDecimal returnAmountAfterQuarter = returnAmount.subtract(new BigDecimal(numberOfQuarters.intValue() * .25));

		if (returnAmountAfterQuarter.doubleValue() > 0) {
			BigDecimal numberOfDimes = returnAmountAfterQuarter.divide(Coins.dime.value);
			coinList.addAll(addCoins(Coins.dime, numberOfDimes.intValue()));
			BigDecimal returnAmountAfterDime = returnAmount.subtract(new BigDecimal(numberOfDimes.intValue() * .10));

			if (returnAmountAfterDime.doubleValue() > 0) {
				BigDecimal numberOfNickels = returnAmountAfterDime.divide(Coins.nickel.value);
				coinList.addAll(addCoins(Coins.nickel, numberOfNickels.intValue()));
				returnAmountAfterEverything = returnAmount.subtract(new BigDecimal(numberOfNickels.intValue() * .25));
			} else {
				returnAmountAfterEverything = returnAmountAfterDime;
			}
		} else {
			returnAmountAfterEverything = returnAmountAfterQuarter;
		}

		if (returnAmountAfterEverything.intValue() == 0) {
			super.setDisplayText("THANK YOU");
		} else {
			coinList.clear();
			super.setDisplayText("EXACT CHANGE REQUIRED");
		}

		return coinList;
	}

	private List<Coins> addCoins(Coins coin, int numberOf) {
		List<Coins> coinList = new ArrayList<Coins>();

		for (int i = 0; i < numberOf; i++) {
			coinList.add(coin);
		}

		return coinList;
	}

	@Override
	public Product selectProduct(int productID) {
		for (Product product : this.getProductList()) {
			if (product.getId() == productID) {
				this.setSelectedProduct(product);
				return product;
			}
		}
		return null;
	}

}
