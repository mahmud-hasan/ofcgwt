package com.gwttest.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ImageService extends RemoteService {
	public String getImageToken(String base64image);
}