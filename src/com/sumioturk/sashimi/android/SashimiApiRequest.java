package com.sumioturk.sashimi.android;

import java.util.HashMap;

import org.apache.http.client.methods.HttpGet;

import android.net.Uri;

public class SashimiApiRequest {
	private SashimiApi api;
	private HashMap<String, String> params;
	private HttpGet request;
	
	public SashimiApiRequest(SashimiApi api, HashMap<String, String> params){
		this.api = api;
		this.params = params;
		Uri.Builder uriBuilder = new Uri.Builder();
		uriBuilder.scheme("http");
		uriBuilder.encodedAuthority("sashimiquality.com:9000");
		uriBuilder.path("/" + api + "/");
		for(String e: params.keySet()){
			uriBuilder.appendQueryParameter(e, params.get(e));
		}
		this.request = new HttpGet(uriBuilder.build().toString()); 
	}
}
