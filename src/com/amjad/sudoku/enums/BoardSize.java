package com.amjad.sudoku.enums;

/**
 * 
 * @author Ja3far
 *
 */
public enum BoardSize {
	
	FourByFour(4),
	//SixBySix(6),
	NineByNine(9);
	
	private final int value;

	BoardSize(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
}
