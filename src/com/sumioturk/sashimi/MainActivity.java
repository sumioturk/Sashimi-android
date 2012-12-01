package com.sumioturk.sashimi;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private View v;
	private EditText name;
	private EditText pass;
	private SeekBar d;
	private SeekBar h;
	private SeekBar m;
	private TextView dn;
	private TextView hn;
	private TextView mn;
	private Button b;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		d = (SeekBar) findViewById(R.id.d);
		h = (SeekBar) findViewById(R.id.h);
		m = (SeekBar) findViewById(R.id.m);
		b = (Button) findViewById(R.id.b);
		name = (EditText) findViewById(R.id.name);
		pass = (EditText) findViewById(R.id.pass);

		d.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				TextView wn = (TextView) findViewById(R.id.dn);
				int week = (int) Math.floor(30 * progress / 100);
				wn.setText(week + "");
			}
		});

		h.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				TextView wn = (TextView) findViewById(R.id.hn);
				int week = (int) Math.floor(23 * progress / 100);
				wn.setText(week + "");
			}
		});

		m.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				TextView wn = (TextView) findViewById(R.id.mn);
				int week = (int) Math.floor(59 * progress / 100);
				wn.setText(week + "");
			}
		});

		b.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dn = (TextView) findViewById(R.id.dn);
				hn = (TextView) findViewById(R.id.hn);
				mn = (TextView) findViewById(R.id.mn);
				String nameInput = name.getText().toString();
				String passInput = pass.getText().toString();
				int dayInput = new Integer(dn.getText().toString()).intValue();
				int hourInput = new Integer(hn.getText().toString()).intValue();
				int minuteInput = new Integer(mn.getText().toString())
						.intValue();
				int sashimi = dayInput * (1440) + hourInput * (60)
						+ minuteInput;
				if (nameInput == "" || passInput == "" || sashimi == 0) {
					if (nameInput == "") {
						name.setTextColor(128);
					}
					if (passInput == "") {
						pass.setTextColor(128);
					}
					if (minuteInput == 0) {
						mn.setTextColor(128);
					}
				} else {
					Thread asyncApiCall = new Thread(){
						public void run(){}
					};
					Uri.Builder uriBuilder = new Uri.Builder();
					uriBuilder.scheme("http");
					uriBuilder.encodedAuthority("sashimiquality.com:9000");
					uriBuilder.path("/join/");
					uriBuilder.appendQueryParameter("name", nameInput);
					uriBuilder.appendQueryParameter("pass", passInput);
					uriBuilder.appendQueryParameter("sashimi", new String(
							sashimi + ""));
					String apiUrl = uriBuilder.build().toString();
					HttpGet request = new HttpGet(apiUrl);
					DefaultHttpClient httpClient = new DefaultHttpClient();
					try {
						String result = httpClient.execute(request,
								new ResponseHandler<String>() {

									public String handleResponse(
											HttpResponse response)
											throws ClientProtocolException,
											IOException {
										switch (response.getStatusLine()
												.getStatusCode()) {
										case HttpStatus.SC_OK:
											return EntityUtils.toString(
													response.getEntity(),
													"UTF-8");
										default:
											break;

										}
										return null;
									}
								});
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
