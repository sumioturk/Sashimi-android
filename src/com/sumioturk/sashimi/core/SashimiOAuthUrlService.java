package com.sumioturk.sashimi.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class SashimiOAuthUrlService extends AbstractSashimiApiService {

	private HashMap<String, String> params;
	private SashimiApi api = SashimiApi.oauth_url;
	private HttpGet request;
	private DefaultHttpClient httpClient;

	public SashimiOAuthUrlService(String key) {
		this.params = new HashMap<String, String>();
		this.httpClient = new DefaultHttpClient();
		params.put("key", key);
		this.request = new SashimiApiRequestBuilder(api, params).build();
	}

	public String doRequest() throws ClientProtocolException, IOException {
		String result = httpClient.execute(request,
				new ResponseHandler<String>() {

					public String handleResponse(HttpResponse response)
							throws ClientProtocolException, IOException {
						switch (response.getStatusLine().getStatusCode()) {
						default:
							return EntityUtils.toString(response.getEntity(),
									"UTF-8");
						}
					}
				});
		return result;
	}

}
