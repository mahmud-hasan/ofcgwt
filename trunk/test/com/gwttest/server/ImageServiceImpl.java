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
package com.gwttest.server;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.gwttest.client.ImageService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ImageServiceImpl extends RemoteServiceServlet implements ImageService {

	public String getImageToken(String base64image) {
		byte[] imageBytes = Base64.decode(base64image);
		String token = getMd5(base64image);
		if (token == null) token = Math.round(Math.random()) + "";
		getThreadLocalRequest().getSession().setAttribute("img_" + token, imageBytes);
		return token;
	}

	private String getMd5(String base64image) {
		String token = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(base64image.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			token = hash.toString(16);
		}
		catch (NoSuchAlgorithmException nsae) {}
		return token;
	}

}