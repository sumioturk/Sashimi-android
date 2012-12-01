package com.sumioturk.sashimi.android;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.net.Uri;

class SashimiApiService {
	private Uri url;
	private HashMap<String, String> params; 
	private String api;
	
	public SashimiApiService() {
	}
}
