package com.sumioturk.sashimi.core;

import java.util.HashMap;

import org.apache.http.client.methods.HttpGet;

import android.net.Uri;

public class SashimiApiRequestBuilder {
	private SashimiApi api;
	private HashMap<String, String> params;
	private HttpGet request;
	
	public SashimiApiRequestBuilder(SashimiApi api, HashMap<String, String> params){
		this.api = api;
		this.params = params;
		Uri.Builder uriBuilder = new Uri.Builder();
		uriBuilder.scheme("http");
		uriBuilder.encodedAuthority("sashimiquality.com:9000");
		uriBuilder.path("/" + api);
		for(String e: params.keySet()){
			uriBuilder.appendQueryParameter(e, params.get(e));
		}
		String url = uriBuilder.build().toString();
		this.request = new HttpGet(uriBuilder.build().toString()); 
	}
	
	public HttpGet build(){
		return this.request;
	}
}
