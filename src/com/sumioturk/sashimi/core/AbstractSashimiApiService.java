package com.sumioturk.sashimi.core;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class AbstractSashimiApiService {
	private HashMap<String, String> params;
	private SashimiApi api;
	private HttpGet request;
	private DefaultHttpClient httpClient;
	abstract public String doRequest() throws ClientProtocolException, IOException;
}
