package com.audition;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.audition.product.Product;

public class MyVendingMachineTest {

	private Product myProduct = new Product(4, "MyProduct", new BigDecimal("1"));

	@Test
	public void testConstructorInventory() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		assertEquals(1, vendingMachine.getInventory().get(1).intValue());
		assertEquals(2, vendingMachine.getInventory().get(2).intValue());
		assertEquals(3, vendingMachine.getInventory().get(3).intValue());

	}

	@Test
	public void testConstructorAmount() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		assertEquals(0, vendingMachine.getCurrentAmount().intValue());

	}

	@Test
	public void testConstructorProduct() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		assertEquals(1, vendingMachine.getProductList().get(0).getId());
		assertEquals(2, vendingMachine.getProductList().get(1).getId());
		assertEquals(3, vendingMachine.getProductList().get(2).getId());

		assertEquals("Cola", vendingMachine.getProductList().get(0).getName());
		assertEquals("Chips", vendingMachine.getProductList().get(1).getName());
		assertEquals("Candy", vendingMachine.getProductList().get(2).getName());

		assertEquals(1, vendingMachine.getProductList().get(0).getPrice().doubleValue(), 1e-15);
		assertEquals(.50, vendingMachine.getProductList().get(1).getPrice().doubleValue(), 1e-15);
		assertEquals(.65, vendingMachine.getProductList().get(2).getPrice().doubleValue(), 1e-15);
	}

	@Test
	public void testAddProductInventory() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		vendingMachine.addProductInventory(1);
		vendingMachine.addProductInventory(2);
		vendingMachine.addProductInventory(3);

		assertEquals(2, vendingMachine.getInventory().get(1).intValue());
		assertEquals(3, vendingMachine.getInventory().get(2).intValue());
		assertEquals(4, vendingMachine.getInventory().get(3).intValue());
	}

	@Test
	public void testRemoveProductInventory() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		vendingMachine.removeProductInventory(2);
		vendingMachine.removeProductInventory(3);

		assertEquals(1, vendingMachine.getInventory().get(1).intValue());
		assertEquals(1, vendingMachine.getInventory().get(2).intValue());
		assertEquals(2, vendingMachine.getInventory().get(3).intValue());
	}

	@Test
	public void testAddProduct() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		vendingMachine.addProduct(myProduct);

		assertEquals(4, vendingMachine.getProductList().size());
		assertEquals("MyProduct", vendingMachine.getProductList().get(3).getName());

	}

	@Test
	public void testRemoveProduct() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		vendingMachine.addProduct(myProduct);
		assertEquals(4, vendingMachine.getProductList().size());
		assertEquals("MyProduct", vendingMachine.getProductList().get(3).getName());

		vendingMachine.removeProduct(myProduct);
		assertEquals(3, vendingMachine.getProductList().size());

	}

	@Test
	public void testInsertCoin() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		vendingMachine.insertCoin(1);
		assertEquals(0, vendingMachine.getCurrentAmount().doubleValue(), 1e-15);

		vendingMachine.insertCoin(5);
		assertEquals(.05, vendingMachine.getCurrentAmount().doubleValue(), 1e-15);

		vendingMachine.insertCoin(5);
		assertEquals(.10, vendingMachine.getCurrentAmount().doubleValue(), 1e-15);

		vendingMachine.insertCoin(10);
		assertEquals(.20, vendingMachine.getCurrentAmount().doubleValue(), 1e-15);

		vendingMachine.insertCoin(10);
		assertEquals(.30, vendingMachine.getCurrentAmount().doubleValue(), 1e-15);

		vendingMachine.insertCoin(25);
		assertEquals(.55, vendingMachine.getCurrentAmount().doubleValue(), 1e-15);
	}

	@Test
	public void testCalculateChange() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		vendingMachine.insertCoin(25);
		vendingMachine.insertCoin(25);
		vendingMachine.insertCoin(25);

		vendingMachine.setSelectedProduct(new Product(3, "Candy", new BigDecimal(".65")));

		assertEquals(.10, vendingMachine.calculateChange().doubleValue(), 1e-15);

	}

	@Test
	public void testSelectProduct() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		assertEquals("Cola", vendingMachine.selectProduct(1).getName());
		assertEquals("Cola", vendingMachine.getSelectedProduct().getName());
	}

	@Test
	public void testReturnCoin() {
		MyVendingMachine vendingMachine = new MyVendingMachine();

		vendingMachine.selectProduct(1);

		vendingMachine.insertCoin(25);
		vendingMachine.insertCoin(25);
		vendingMachine.insertCoin(25);
		vendingMachine.insertCoin(25);
		vendingMachine.insertCoin(25);

		List<Coins> coinList = vendingMachine.returnCoin();

		assertEquals(1, coinList.size());
		assertEquals(Coins.quarter, coinList.get(0));
	}

}
