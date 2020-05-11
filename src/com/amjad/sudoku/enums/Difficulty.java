package com.amjad.sudoku.enums;

/**
 * Represents difficulty or percentage of cells to be removed
 * @author Ja3far
 *
 */
public enum Difficulty {
	Easy(.4),
	Medium(.5),
	Hard(.6);
	
	private final double value;
	
	
	private Difficulty(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
}
