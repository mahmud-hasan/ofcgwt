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
package com.rednels.ofcgwt.client;
/**
 * Base class that defines if cache fix is needed   
 */
public class CacheFixImplIE extends CacheFixImpl {

	/**
	 * Returns true is all cases (used by deferred bindings for IE agents)
	 * @return true
	 */
	public boolean isCacheFixNeeded() {
		return true;
	}		

}