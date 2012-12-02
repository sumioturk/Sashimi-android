package com.sumioturk.sashimi;

import org.apache.http.cookie.Cookie;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OauthActivity extends Activity {

	private WebView wv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oauth);


		wv = (WebView) findViewById(R.id.webView_1);
		wv.setWebViewClient(new WebViewClient());
		wv.setWebChromeClient(new WebChromeClient());
		wv.getSettings().setJavaScriptEnabled(true);
		
		
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		String sessionKey = intent.getStringExtra("sessionKey");
		String cookieString = "SashimiSessionKey" + "=" + sessionKey + ";";
		
		CookieSyncManager.createInstance(this);
		CookieSyncManager.getInstance().startSync();
		CookieManager.getInstance().setAcceptCookie(true);
		CookieManager.getInstance().removeAllCookie();
		CookieManager.getInstance().setCookie("sashimiquality.com",
				cookieString);
		CookieSyncManager.getInstance().sync();
		
		boolean f = CookieManager.getInstance().hasCookies();

		wv.loadUrl(url);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_oauth, menu);
		return true;
	}
}
