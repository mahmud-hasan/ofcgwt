package com.rednels.ofcgwt.client.model.axis;

/**
 * OFC Rotation.
 */
public enum Rotation {

	VERTICAL("vertical"), DIAGONAL("diagonal"),	HORIZONTAL("horizontal");

	private final String text;

	Rotation(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}