package com.audition;

import java.math.BigDecimal;

public enum Coins {
	penny(1), nickel(5), dime(10), quarter(25);

	public final BigDecimal value;
	public final int diameter;

	private Coins(int diameter) {
		this.diameter = diameter;
		switch (diameter) {
		case 1:
			this.value = new BigDecimal(".01");
			break;
		case 5:
			this.value = new BigDecimal(".05");
			break;
		case 10:
			this.value = new BigDecimal(".10");
			break;
		case 25:
			this.value = new BigDecimal(".25");
			break;
		default:
			this.value = new BigDecimal("0");
			break;
		}

	}

}
