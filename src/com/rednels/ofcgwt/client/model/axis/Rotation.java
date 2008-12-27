package com.rednels.ofcgwt.client.model.axis;

/**
 * The Enum Rotation.
 */
public enum Rotation {

	/** The VERTICAL. */
	VERTICAL("vertical"),

	/** The DIAGONAL. */
	DIAGONAL("diagonal"),

	/** The HORIZONTAL. */
	HORIZONTAL("horizontal");

	/** The text. */
	private final String text;

	/**
	 * Creates a new rotation.
	 * 
	 * @param text
	 *            the text
	 */
	Rotation(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return text;
	}
}