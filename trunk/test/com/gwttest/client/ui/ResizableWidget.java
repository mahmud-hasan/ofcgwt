/*
Copyright (C) 2009 Grant Slender

This file is part of OFCGWT.
http://code.google.com/p/ofcgwt/

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
package com.gwttest.client.ui;

import com.google.gwt.user.client.Element;

/**
 * An interface that defines the methods required to support automatic resizing
 * of the Widget element.
 */
public interface ResizableWidget {
	/**
	 * Get the widget's element.
	 */
	Element getElement();

	/**
	 * Check if this widget is attached to the page.
	 * 
	 * @return true if the widget is attached to the page
	 */
	boolean isAttached();

	/**
	 * This method is called when the dimensions of the parent element change.
	 * Subclasses should override this method as needed.
	 * 
	 * @param width
	 *            the new client width of the element
	 * @param height
	 *            the new client height of the element
	 */
	void onResize(int width, int height);
}
