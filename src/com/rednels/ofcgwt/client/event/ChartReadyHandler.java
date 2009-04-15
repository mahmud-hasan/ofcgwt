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

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link ChartReadyEvent} events.
 */
public interface ChartReadyHandler extends EventHandler {

	/**
	 * Called when a chart ready event is fired.
	 * 
	 * @param event
	 *            the {@link ChartReadyEvent} that was fired
	 */
	void onReady(ChartReadyEvent event);
}