/*
Copyright (C) 2008 Grant Slender

This file is part of OFCGWT.

OFCGWT is free software: you can redistribute it and/or modify
it under the terms of the Lesser GNU General Public License as
published by the Free Software Foundation, either version 3 of
the License, or (at your option) any later version.

OFCGWT is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

See <http://www.gnu.org/licenses/lgpl-3.0.txt>.
 */
package com.rednels.ofcgwt.client.event;

import java.util.ArrayList;
import java.util.List;

public class ElementEvents {

	private List<ChartClickHandler> handlers;
	protected String onClick;
	protected String keyOnClick;

	/**
	 * Adds a ChartClickHandler handler.
	 * 
	 * @param handler
	 *            the ChartClickHandler
	 */
	public void addChartClickHandler(ChartClickHandler handler) {
		if (handlers == null) {
			handlers = new ArrayList<ChartClickHandler>();
		}
		handlers.add(handler);
	}

	public List<ChartClickHandler> getHandlers() {
		if (handlers == null) {
			return new ArrayList<ChartClickHandler>();
		}
		return handlers;
	}

	/**
	 * Gets the keyOnClick.
	 * 
	 * @return the keyOnClick
	 */
	public String getKeyOnClick() {
		return keyOnClick;
	}

	/**
	 * Gets the onClick.
	 * 
	 * @return the onClick
	 */
	public String getOnClick() {
		return onClick;
	}

	/**
	 * Sets the keyOnClick.
	 * 
	 * @param keyOnClick
	 *            the keyOnClick javascript method or url
	 */
	public void setKeyOnClick(String keyOnClick) {
		this.keyOnClick = keyOnClick;
	}

	/**
	 * If true, sets keyOnClick to toggle visibility
	 * 
	 * @param toggleKeyOnClick
	 *            boolean
	 */
	public void setKeyToggleOnClick(boolean toggleKeyOnClick) {
		if (toggleKeyOnClick)
			this.keyOnClick = "toggle-visibility";
		else
			this.keyOnClick = null;
	}

	/**
	 * Sets the onClick GWT event or url (if you set a GWT event, it will
	 * override the URL).
	 * 
	 * @param onClick
	 *            the onClick
	 */
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
}
