package com.sumioturk.sashimi.core;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class SashimiLoginService extends AbstractSashimiApiService {
	private HashMap<String, String> params;
	private SashimiApi api = SashimiApi.login;
	private HttpGet request;
	private DefaultHttpClient httpClient;

	public SashimiLoginService(String name, String pass) {
		this.params = new HashMap<String, String>();
		this.httpClient = new DefaultHttpClient();
		params.put("name", name);
		params.put("pass", pass);
		this.request = new SashimiApiRequestBuilder(api, params).build();
	}

	public String doRequest() throws ClientProtocolException, IOException {
		String result = httpClient.execute(request,
				new ResponseHandler<String>() {

					public String handleResponse(HttpResponse response)
							throws ClientProtocolException, IOException {
						switch (response.getStatusLine().getStatusCode()) {
						case HttpStatus.SC_OK:
							return EntityUtils.toString(response.getEntity(),
									"UTF-8");
						default:
							break;

						}
						return null;
					}
				});
		return result;
	}
}
