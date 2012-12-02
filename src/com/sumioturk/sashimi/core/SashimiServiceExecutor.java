package com.sumioturk.sashimi.core;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

public class SashimiServiceExecutor {
	private Thread thread;
	private ArrayList<String> results;
	
	public SashimiServiceExecutor(final ArrayList<AbstractSashimiApiService> services){
		this.results = new ArrayList<String>(); 
		this.thread = new Thread(){
			public void start(){
				super.start();
			}
			public synchronized void run(){
				try {
					for(int i = 0; i < services.size(); i++){
						results.add(services.get(i).doRequest());
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	public ArrayList<String> execute() {
		this.thread.start();
		return this.results;
	}
}
