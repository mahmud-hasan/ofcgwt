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

import java.io.BufferedOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImageByteReader extends HttpServlet {
	private static final String SESSION_VAR_NAME = "var";

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			String variable = request.getParameter(SESSION_VAR_NAME);
			HttpSession session = request.getSession();
			byte[] imageBytes = (byte[]) session.getAttribute(variable);
			response.reset();
			int contentLength = imageBytes.length;
			response.setContentLength(contentLength);
			response.setContentType("application");
			response.setHeader("Content-Disposition", "attachment; filename=chart.png");
			BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
			output.write(imageBytes);
			output.flush();
			output.close();
		}
		catch (Exception e) {}
	}
}
